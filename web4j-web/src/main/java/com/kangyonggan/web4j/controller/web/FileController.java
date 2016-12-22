package com.kangyonggan.web4j.controller.web;

import com.kangyonggan.web4j.util.FileUpload;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 2016/12/7
 */
@Controller
@RequestMapping("file")
@Log4j2
public class FileController {

    /**
     * 编辑器文件、图片上传
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "editor", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(@RequestParam(value = "imgFile") MultipartFile file,
                                      HttpServletRequest request) {

        Map<String, Object> result = new HashMap();
        ServletContext context = request.getServletContext();
        String ctx = context.getContextPath();

        String fileName = null;
        int error = 0;
        try {
            fileName = FileUpload.upload(file);
        } catch (Exception e) {
            log.error("编辑器上传失败", e);
            error = -1;
        }

        result.put("error", error);
        result.put("url", ctx + "/" + fileName);
        return result;
    }
}
