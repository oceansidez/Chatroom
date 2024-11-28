package pers.kanarien.chatroom.config;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 基础测试类
 *
 * @author zzh
 * @since 2024/11/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
//@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, NettyConfig.class})
public class BaseTest {

}

