package com.open.coinnews.web.controller.web;

import com.open.coinnews.basic.tools.ConfigTools;
import com.open.coinnews.basic.tools.NormalTools;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 公共的Controller
 */
@RestController
@RequestMapping(value = "public")
public class PublicController {

    @Autowired
    private ConfigTools configTools;


    private static final String PATH_PRE = "public/";

    /**
     * 上传demo
     * success : 0 | 1,           // 0 表示上传失败，1 表示上传成功
     * message : "提示的信息，上传成功或上传失败及错误信息等。",
     * url     : "图片地址"        // 上传成功时才返回
     */
    @RequestMapping("upload")
    public @ResponseBody
    Map<String, Object> upload(@RequestParam(value = "editormd-image-file", required = false) MultipartFile files, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (files != null) {
            BufferedOutputStream bw = null;
            try {
                String fileName = files.getOriginalFilename();
                if (fileName != null && !"".equalsIgnoreCase(fileName.trim()) && NormalTools.isImageFile(fileName)) {
                    File outFile = new File(configTools.getUploadPath(PATH_PRE) + "/" + UUID.randomUUID().toString() + NormalTools.getFileType(fileName));

                    String filePath = outFile.getAbsolutePath().replace(configTools.getUploadPath(), "");
                    resultMap.put("success", 1);
                    resultMap.put("message", "上传成功！");
                    resultMap.put("url", configTools.getFileBaseUrl() + filePath);

                    FileUtils.copyInputStreamToFile(files.getInputStream(), outFile);
                }
            } catch (IOException e) {
                resultMap.put("success", 0);
                resultMap.put("message", "上传失败！");
                e.printStackTrace();
            } finally {
                try {
                    if (bw != null) {
                        bw.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultMap;
    }


}
