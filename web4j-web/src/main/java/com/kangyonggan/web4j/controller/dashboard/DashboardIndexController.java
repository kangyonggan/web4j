package com.kangyonggan.web4j.controller.dashboard;

import com.kangyonggan.web4j.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author kangyonggan
 * @since 2016/12/22
 */
@Controller
@RequestMapping("dashboard")
public class DashboardIndexController extends BaseController {

    /**
     * 工作台
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("DASHBOARD")
    public String index() {
        return getPathRoot();
    }

}
