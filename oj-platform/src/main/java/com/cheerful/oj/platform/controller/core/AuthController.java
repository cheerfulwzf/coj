package com.cheerful.oj.platform.controller.core;

import com.cheerful.oj.common.constant.AuthConstant;
import com.cheerful.oj.common.vo.Result;
import com.cheerful.oj.platform.entity.User;
import com.cheerful.oj.platform.pojo.dto.GiteeUserInfo;
import com.cheerful.oj.platform.pojo.vo.LoginAccountVo;
import com.cheerful.oj.platform.pojo.vo.RegisterVO;
import com.cheerful.oj.platform.pojo.vo.SocialUser;
import com.cheerful.oj.platform.service.UserService;
import com.cheerful.oj.platform.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/30 21:39
 * @DESCRIPTION:
 */
@RestController
public class AuthController {
    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    MailUtil mailUtil;

    @Autowired
    UserService userService;

    @Autowired
    RestTemplate restTemplate;

    /**
     * 邮箱验证码 防抖
     * @param sendTo 接收者邮箱
     */
    @GetMapping("/ems/sendCode")
    public Result<String> sendCode(@RequestParam String sendTo){
        String key= AuthConstant.EMS_CODE_CACHE_PREFIX+sendTo;
        String oldCode = redisTemplate.opsForValue().get(key);
        if (!ObjectUtils.isEmpty(oldCode)){
            long oldTime = Long.parseLong(oldCode.split("_")[1]);
            if (System.currentTimeMillis()-oldTime< 60*1000){
                return Result.error("60s内无法再次发送验证码");
            }
        }
        int code= (int) ((Math.random() * 9 + 1) * 100000);
        String value=code+"_"+System.currentTimeMillis();
        redisTemplate.opsForValue().set(key,value,10, TimeUnit.MINUTES);
        mailUtil.sendCode(code,sendTo);
        return Result.success("发送验证码成功");
    }

    @PostMapping("/register")
    public Result<Object> register(@RequestBody RegisterVO registerVO, HttpSession session){
        String code=registerVO.getCode();
        String key = AuthConstant.EMS_CODE_CACHE_PREFIX + registerVO.getEmail();
        String s = redisTemplate.opsForValue().get(key);
        //验证码是否正确
        if (!ObjectUtils.isEmpty(s) && code.equals(s.split("_")[0])){
            redisTemplate.delete(key);
            User user = userService.register(registerVO);
            if (user!=null) {
                //保存进spring session
                session.setAttribute(AuthConstant.LOGIN_USER,user);
            }else{
                return Result.error("账号或者密码错误");
            }
        }else{
            return Result.error("验证码出错");
        }
        return Result.success("注册成功，已自动登录");
    }

    /**
     * 登录接口
     * @param loginAccountVo 用户传来的账户
     * @param session 保存入session
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginAccountVo loginAccountVo,HttpSession session){
        User user = userService.login(loginAccountVo);
        if (user!=null){
            session.setAttribute(AuthConstant.LOGIN_USER,user);
            return Result.success("登陆成功");
        }
        return Result.error("账号或者密码错误");
    }

    @GetMapping(value = "/logout")
    public Result<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute(AuthConstant.LOGIN_USER);
        request.getSession().invalidate();
        return Result.success();
    }

    //https://gitee.com/oauth/authorize?client_id=43906c86280b1affa184290c9f27c6a86a1fb2f39ca100f91c715b31d25b6a2d&redirect_uri=http://192.168.44.1:88/api/platform/oauth2.0/gitee/success&response_type=code
    @GetMapping("/oauth2.0/gitee/success")
    public Result<String> giteeLogin(@RequestParam String code, HttpSession session){
        //body信息
        HashMap<String, String> map = new HashMap<>();
        map.put("client_id", "43906c86280b1affa184290c9f27c6a86a1fb2f39ca100f91c715b31d25b6a2d");
        map.put("grant_type","authorization_code");
        map.put("code",code);
        map.put("client_secret","77b4770d53dcc9adfd6b80ca6603706be3b3c3be48615a46f7403e98b239ec78");
        map.put("redirect_uri","http://192.168.44.1:88/api/platform/oauth2.0/gitee/success");
        //gitee授权
        SocialUser giteeUser = restTemplate.postForObject("https://gitee.com/oauth/token", map, SocialUser.class);
        if (ObjectUtils.isEmpty(giteeUser) || ObjectUtils.isEmpty(giteeUser.getAccess_token())){
            return Result.error();
        }
        //拿到access_token,才能拿到用户信息
        String accessToken = giteeUser.getAccess_token();
        GiteeUserInfo giteeUserInfo = restTemplate.getForObject("https://gitee.com/api/v5/user?access_token=" + accessToken, GiteeUserInfo.class);
        User user = userService.login(giteeUserInfo);
        if (user == null){
            return  Result.error();
        }
        session.setAttribute(AuthConstant.LOGIN_USER,user);
        return Result.success();
    }
}
