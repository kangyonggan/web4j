package com.kangyonggan.web4j.controller.web;

import com.kangyonggan.web4j.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author kangyonggan
 * @since 2016/12/22
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    /**
     * 首页 - 重定向到工作台
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "redirect:/dashboard";
    }

}
