package com.cheerful.oj.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheerful.oj.common.exception.EmailExistException;
import com.cheerful.oj.common.exception.UsernameExistException;
import com.cheerful.oj.platform.dao.UserDao;
import com.cheerful.oj.platform.entity.User;
import com.cheerful.oj.platform.pojo.dto.GiteeUserInfo;
import com.cheerful.oj.platform.pojo.vo.LoginAccountVo;
import com.cheerful.oj.platform.pojo.vo.RegisterVO;
import com.cheerful.oj.platform.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-03-10 17:04:24
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Override
    public User register(RegisterVO registerVO) {
        //检查用户名和邮箱是否唯一
        checkEmailUnique(registerVO.getEmail());
        checkUsernameUnique(registerVO.getUsername());
        //注册用户
        User user=new User();
        user.setNickname(registerVO.getUsername());
        user.setEmail(registerVO.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(registerVO.getPassword());
        user.setPassword(password);
        return this.save(user)?user:null;
    }

    @Override
    public User login(LoginAccountVo vo) {
        String loginAccount = vo.getLoginAccount();
        String password = vo.getPassword();
        User user = this.baseMapper.selectOne(
                new QueryWrapper<User>()
                        .eq("nickname", loginAccount)
                        .or()
                        .eq("email",loginAccount)
        );
        if (user != null) {
            //加密的密码
            String dbPassword = user.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(password, dbPassword)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User login(GiteeUserInfo userInfo) {
        //判断当前gitee账号是否已经注册过了?
        Long id = userInfo.getId();
        User user = this.getOne(new QueryWrapper<User>().eq("social_id", id));
        if (user!=null){
            //已经注册过
            return user;
        }else {
            //把传来的gitee账号的一些信息注册进我们自己的系统
            User user1 = new User();
            user1.setNickname(userInfo.getLogin());
            user1.setAvatarUrl(userInfo.getAvatar_url());
            user1.setSocialId(id);
            this.save(user1);
            return user1;
        }
    }

    public void checkUsernameUnique(String username) throws UsernameExistException{
        int count = this.count(new QueryWrapper<User>().eq("nickname", username));
        if (count>0){
            throw new UsernameExistException("用户名已存在");
        }
    }

    public void checkEmailUnique(String email) throws EmailExistException {
        int count = this.count(new QueryWrapper<User>().eq("email", email));
        if (count>0){
            throw new EmailExistException("邮箱号已存在");
        }
    }
}

