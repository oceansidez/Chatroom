package pers.kanarien.chatroom.config;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zzh
 * @since 2024/11/28
 */
public class CustomTest extends BaseTest {


    @Test
    public void addressTest() {
        InetAddress localHost = null;
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        System.out.println(localHost.getHostAddress());
    }

    @Test
    public void addressTest2() {
        InetAddress localHost = null;
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        System.out.println(localHost.getHostAddress());
    }
}
