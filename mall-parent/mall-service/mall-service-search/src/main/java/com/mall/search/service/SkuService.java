package com.mall.search.service;

import java.util.Map;

/**
 * 描述
 *
 * @author www.itheima.com
 * @version 1.0
 * @package com.changgou.search.service *
 * @since 1.0
 */
public interface SkuService {

    /**
     * <p>
     * 1.调用 goods微服务的fegin 查询 符合条件的sku的数据
     * 2.调用spring data elasticsearch的API 导入到ES中
     * </p>
     *
     * @author yanglin
     * @date 2020-11-03 22:45:54
     */
    void importEs();


    /**
     * <p>
     * 通用搜索
     * </p>
     *
     * @author yanglin
     * @date 2020-11-03 22:46:38
     */
    Map<String, Object> search(Map<String, Object> searchMap);
}
