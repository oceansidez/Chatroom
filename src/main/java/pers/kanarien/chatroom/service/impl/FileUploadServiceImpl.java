package pers.kanarien.chatroom.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pers.kanarien.chatroom.config.AppConfig;
import pers.kanarien.chatroom.model.vo.ResponseJson;
import pers.kanarien.chatroom.service.FileUploadService;
import pers.kanarien.chatroom.util.FileUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private final static String SERVER_URL_PREFIX = "http://localhost:8282";
    private final static String FILE_STORE_PATH = "WebSocketUploadFile";
    private final AppConfig appConfig;

    public FileUploadServiceImpl(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    public ResponseJson upload(MultipartFile file, HttpServletRequest request) {
        // 重命名文件，防止重名
        String filename = getRandomUUID();
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        String fileSize = FileUtils.getFormatSize(file.getSize());
        // 截取文件的后缀名
        if (originalFilename.contains(".")) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        filename = filename + suffix;
        String prefix = appConfig.getUploadPath() + File.separator + FILE_STORE_PATH;
        // 文件夹不存在创建文件夹
        File fileDir = new File(prefix);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        System.out.println("存储路径为:" + prefix + File.separator + filename);
        Path filePath = Paths.get(prefix, filename);
        try {
            Files.copy(file.getInputStream(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseJson().error("文件上传发生错误！");
        }
        return new ResponseJson().success()
                .setData("originalFilename", originalFilename)
                .setData("fileSize", fileSize)
                .setData("fileUrl", SERVER_URL_PREFIX + File.separator + FILE_STORE_PATH + File.separator + filename);
    }

    private String getRandomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
