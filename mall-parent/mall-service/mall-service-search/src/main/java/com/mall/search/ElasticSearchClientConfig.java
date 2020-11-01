package com.mall.search;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * ElasticSearchClient Config
 * Java类的配置更利于调试和扩展
 * </p>
 *
 * @author yanglin
 * @date 2020-11-01 01:16:05
 */
@Configuration
public class ElasticSearchClientConfig {

    @Value("${spring.elasticsearch.rest.uris}")
    public String uris;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        HttpHost.create(uris)
                )
        );
        return client;
    }

}
