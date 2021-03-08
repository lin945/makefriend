package com.lin945.makefriend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lin945
 * @date 2021/3/7 8:12
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    /**
     * 数据库uid
     */
    private Integer uid;
    /**
     * 用户名（登陆）
     */
    private String username;
    /**
     * 信息
     */
    private String info;
    /**
     * 头像
     */
    private String icon;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 昵称
     */
    private String nickname;
}
