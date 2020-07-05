package com.mall.goods.controller;

import com.mall.common.base.controller.BaseHibernateController;
import com.mall.goods.pojo.Album;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Album
 * </p>
 *
 * @author yanglin
 * @date 2020-07-05 23:41:00
 */
@RestController
@RequestMapping("/album")
public class AlbumController extends BaseHibernateController<Album> {
}
