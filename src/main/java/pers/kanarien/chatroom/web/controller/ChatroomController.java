package pers.kanarien.chatroom.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.kanarien.chatroom.config.AppConfig;
import pers.kanarien.chatroom.model.vo.ResponseJson;
import pers.kanarien.chatroom.service.UserInfoService;
import pers.kanarien.chatroom.util.Constant;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/chatroom")
public class ChatroomController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private AppConfig appConfig;

    /**
     * 描述：登录成功后，调用此接口进行页面跳转
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String toChatroom(Model model) {
        model.addAttribute("websocketAddress", appConfig.getServerHost() + ":" + appConfig.getWebsocketPort());
        return "chatroom";
    }

    /**
     * 描述：登录成功跳转页面后，调用此接口获取用户信息
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/get_userinfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson getUserInfo(HttpSession session) {
        Object userId = session.getAttribute(Constant.USER_TOKEN);
        return userInfoService.getByUserId((String) userId);
    }
}
