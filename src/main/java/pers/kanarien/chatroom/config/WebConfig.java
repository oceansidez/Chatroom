package pers.kanarien.chatroom.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pers.kanarien.chatroom.common.Constants;
import pers.kanarien.chatroom.web.interceptor.UserAuthInteceptor;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author zzh
 * @since 2024/11/27
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AppConfig appConfig;

    public WebConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    /**
     * 设置编码过滤器，解决中文乱码问题
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<CharacterEncodingFilter> encodingFilter() {
        FilterRegistrationBean<CharacterEncodingFilter> registrationBean = new FilterRegistrationBean<>();
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        registrationBean.setFilter(encodingFilter);
        registrationBean.addUrlPatterns("/*");  // 应用到所有请求
        return registrationBean;
    }

    /**
     * 添加拦截器，拦截所有请求，判断用户是否登录
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserAuthInteceptor())
                .addPathPatterns("/chatroom/**");
    }

    /**
     * 视图解析器 此解析器只适合没配置thymeleaf临时展示页面用，使用Thymeleaf模板引擎时需要注释
     *
     * @return
     */
//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/webapp/views/");
//        resolver.setSuffix(".html");
//        resolver.setOrder(0);
//        return resolver;
//    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置静态文件映射
        registry.addResourceHandler("/webapp/views/**")
                .addResourceLocations("classpath:/webapp/views/");

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/webapp/static/");
        // websocket 文件上传映射
        registry.addResourceHandler("/websocket/**")
                .addResourceLocations("file:" + appConfig.getBaseUploadPath() + File.separator + Constants.WEBSOCKET_FILEPATH + File.separator);
    }


    /**
     * 自定义消息转换器，解决中文乱码问题
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 创建 StringHttpMessageConverter，设置为 UTF-8 编码
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        // 创建并自定义 MappingJackson2HttpMessageConverter
        MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        // 在此处可以自定义 ObjectMapper
        jacksonConverter.setObjectMapper(objectMapper);
        // 添加到消息转换器列表
        converters.add(jacksonConverter);
        // 添加到 converters 列表中
        converters.add(stringConverter);
    }


    /**
     * 文件上传解析器
     *
     * @return
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setMaxUploadSize(10485760); // 10MB
        resolver.setMaxInMemorySize(40960); // 40KB
        return resolver;
    }
}
