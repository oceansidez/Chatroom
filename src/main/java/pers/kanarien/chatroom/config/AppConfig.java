package pers.kanarien.chatroom.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zzh
 * @since 2024/11/27
 */
@Configuration
// ignoreResourceNotFound = true 一定要加上，否则找不到会报错。加上之后会忽略找不到的配置文件，配置文件demo.properties放到和jar包同级下就可以了
@PropertySource(value = {"classpath:system.properties", "file:./system.properties"}, ignoreResourceNotFound = true)
@Getter
public class AppConfig {

    private final HttpServletRequest request;


    public AppConfig(HttpServletRequest request) {
        this.request = request;
    }

    @Value("${websocket.server.port}")
    private Integer websocketPort;

    @Value("${file.baseUploadPath}")
    private String baseUploadPath;

    @Value("${server.port}")
    private String serverPort;

    public String getServerHost() {
        return request.getServerName();
    }
}
