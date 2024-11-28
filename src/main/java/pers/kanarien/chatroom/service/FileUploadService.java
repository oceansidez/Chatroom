package pers.kanarien.chatroom.service;

import org.springframework.web.multipart.MultipartFile;
import pers.kanarien.chatroom.model.vo.ResponseJson;

import javax.servlet.http.HttpServletRequest;

public interface FileUploadService {

    ResponseJson upload(MultipartFile file, HttpServletRequest request);
}
