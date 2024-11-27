package pers.kanarien.chatroom.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zzh
 * @since 2024/11/27
 */
@Configuration
@PropertySource("classpath:system.properties")
@Getter
public class AppConfig {

    @Value("${websocket.server.port}")
    private Integer websocketPort;

    @Value("${file.uploadPath}")
    private String uploadPath;
}
