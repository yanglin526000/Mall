package com.mall.search.dao;

import com.mall.search.pojo.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * <p>
 * SkuInfoElasticsearchRepository
 * </p>
 *
 * @author yanglin
 * @date 2020-11-01 17:30:16
 */
public interface SkuInfoElasticsearchRepository extends ElasticsearchRepository<SkuInfo, String> {
}
