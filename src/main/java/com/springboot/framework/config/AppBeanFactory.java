package com.springboot.framework.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import com.springboot.framework.interceptor.CrossDomainFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 生成特殊bean的工厂bean, 有些第三方的Bean没有标注@component, 需要手工初始化.
 */
@Configuration
@EnableConfigurationProperties
public class AppBeanFactory {
    @Resource
    private AppConfig appConfig;
    @Resource
    private COSConfig cosConfig;

//    private static COSClient cosClient = null;
//    public static COSClient getCOSClient() {
//        if (cosClient == null) {
//        }
//    }

    private COSClient getCOSClient(String endpoint) {
        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = cosConfig.getSecretId();
        String secretKey = cosConfig.getSecretKey();
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(endpoint);
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        return new COSClient(cred, clientConfig);
    }

    /**
     * 存储-下载
     *
     * @return COSClient
     */
    @Bean(name = "downloadClient")
    public COSClient downloadClient() {
        return getCOSClient(cosConfig.getDownloadEndpoint());
    }

    /**
     * 存储-上传
     *
     * @return COSClient
     */
    @Bean(name = "uploadClient")
    public COSClient uploadClient() {
        return getCOSClient("ap-shanghai");
    }


    /**
     * 注册跨域支持过滤器
     */
    @Bean
    public FilterRegistrationBean registerCrossDomainFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CrossDomainFilter crossDomainFilter = new CrossDomainFilter();
        // 设置是否允许跨域访问
        crossDomainFilter.setAllowCrossDomain(appConfig.getAllowCrossDomainAccess());
        registrationBean.setFilter(crossDomainFilter);
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
