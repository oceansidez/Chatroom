package pers.kanarien.chatroom.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.InetAddress;
import java.net.UnknownHostException;

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

    @Value("${file.baseUploadPath}")
    private String baseUploadPath;

    @Value("${server.port}")
    private String serverPort;

    public String getServerHost() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
