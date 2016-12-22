package com.kangyonggan.web4j.controller.admin;

import com.kangyonggan.web4j.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author kangyonggan
 * @since 2016/12/22
 */
@Controller
@RequestMapping("admin")
public class AdminIndexController extends BaseController {

    /**
     * 控制台
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return getPathRoot();
    }

}
