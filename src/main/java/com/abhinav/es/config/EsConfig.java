package com.abhinav.es.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;



@Configuration
@EnableElasticsearchRepositories(basePackages = "com.abhinav.es.repository")
public class EsConfig {

    @Value("${spring.data.elasticsearch.client.username}")
    private String username;

    @Value("${spring.data.elasticsearch.client.password}")
    private String password;

    @Bean(destroyMethod = "close")
    public RestHighLevelClient restHighLevelClient() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

        RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "http"))
                .setHttpClientConfigCallback(httpClientBuilder ->
                        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));

        return new RestHighLevelClient(builder);
    }


//        @Value("${elasticsearch.host}")
//        private String host;
//        @Value("${elasticsearch.username}")
//        private String username;
//        @Value("${elasticsearch.password}")
//        private String password;
//
//        @Bean
//        public RestHighLevelClient client() {
//            ClientConfiguration clientConfiguration
//                    = ClientConfiguration.builder()
//                    .connectedTo(host)
//                    .withBasicAuth(username, password) // put your credentials
//                    .build();
//            return RestClients.create(clientConfiguration).rest();
//        }
//
//        @Bean
//        public ElasticsearchOperations elasticsearchTemplate() {
//            return new ElasticsearchRestTemplate(client());
//        }
//



}
