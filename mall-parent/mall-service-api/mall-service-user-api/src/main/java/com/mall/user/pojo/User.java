package com.mall.user.pojo;

import com.mall.common.base.pojo.CommonPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * <p>
 * User
 * </p>
 *
 * @author yanglin
 * @date 2020-11-17 23:42:11
 */
@Data
@Entity
@Table(name = "user")
public class User extends CommonPo {

    @Column(name = "username")
    private String username;//用户名

    @Column(name = "password")
    private String password;//密码，加密存储

    @Column(name = "phone")
    private String phone;//注册手机号

    @Column(name = "email")
    private String email;//注册邮箱

    @Column(name = "source_type")
    private String sourceType;//会员来源：1:PC，2：H5，3：Android，4：IOS

    @Column(name = "nick_name")
    private String nickName;//昵称

    @Column(name = "name")
    private String name;//真实姓名

    @Column(name = "status")
    private Byte status;//使用状态（1正常 0非正常）

    @Column(name = "head_pic")
    private String headPic;//头像地址

    @Column(name = "qq")
    private String qq;//QQ号码

    @Column(name = "is_mobile_check")
    private String isMobileCheck;//手机是否验证 （0否  1是）

    @Column(name = "is_email_check")
    private String isEmailCheck;//邮箱是否检测（0否  1是）

    @Column(name = "sex")
    private Byte sex;//性别，1男，0女

    @Column(name = "user_level")
    private Integer userLevel;//会员等级

    @Column(name = "points")
    private Integer points;//积分

    @Column(name = "experience_value")
    private Integer experienceValue;//经验值

    @Column(name = "birthday")
    private Date birthday;//出生年月日

    @Column(name = "last_login_time")
    private Date lastLoginTime;//最后登录时间

}
