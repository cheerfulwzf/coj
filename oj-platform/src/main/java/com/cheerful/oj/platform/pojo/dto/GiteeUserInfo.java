package com.cheerful.oj.platform.pojo.dto;

import java.util.Date;
import lombok.Data;

/**
 * @author: Wang Zhifu
 * @create: 2021/11/16 11:58
 * @Description: 获取gitee用户信息详情实体类
 */
@Data
public class GiteeUserInfo {

	private long id;
	private String login;
	private String name;
	private String avatar_url;
	private String url;
	private String html_url;
	private String remark;
	private String followers_url;
	private String following_url;
	private String gists_url;
	private String starred_url;
	private String subscriptions_url;
	private String organizations_url;
	private String repos_url;
	private String events_url;
	private String received_events_url;
	private String type;
	private String blog;
	private String weibo;
	private String bio;
	private int public_repos;
	private int public_gists;
	private int followers;
	private int following;
	private int stared;
	private int watched;
	private Date created_at;
	private Date updated_at;
	private String email;
}
