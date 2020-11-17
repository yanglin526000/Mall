package com.mall.user.pojo;

import com.mall.common.base.pojo.CommonPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>
 * OauthClientDetails
 * </p>
 *
 * @author yanglin
 * @date 2020-11-17 23:24:04
 */
@Data
@Entity
@Table(name = "oauth_client_details")
public class OauthClientDetails extends CommonPo {

    @Column(name = "resource_ids")
    private String resourceIds;//

    @Column(name = "client_secret")
    private String clientSecret;//客户端秘钥，BCryptPasswordEncoder加密算法加密

    @Column(name = "scope")
    private String scope;//对应的范围

    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;//认证模式

    @Column(name = "web_server_redirect_uri")
    private String webServerRedirectUri;//认证后重定向地址

    @Column(name = "authorities")
    private String authorities;//

    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;//令牌有效期

    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;//令牌刷新周期

    @Column(name = "additional_information")
    private String additionalInformation;//

    @Column(name = "auto_approve")
    private String autoApprove;

}
