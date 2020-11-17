package com.mall.user.controller;

import com.mall.common.base.controller.BaseHibernateController;
import com.mall.user.pojo.Address;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * AddressController
 * </p>
 *
 * @author yanglin
 * @date 2020-11-18 00:07:36
 */
@RestController
@RequestMapping("/address")
public class AddressController extends BaseHibernateController<Address> {
}
