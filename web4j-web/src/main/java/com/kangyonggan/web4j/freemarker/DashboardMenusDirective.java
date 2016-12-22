package com.kangyonggan.web4j.freemarker;

import com.kangyonggan.web4j.model.Menu;
import com.kangyonggan.web4j.service.MenuService;
import com.kangyonggan.web4j.service.UserService;
import com.kangyonggan.web4j.shiro.SuperTag;
import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 2016/12/2
 */
@Component
public class DashboardMenusDirective extends SuperTag {

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        String username = userService.getShiroUser().getUsername();
        List<Menu> menus = menuService.findDashboardMenus(username);
        env.setVariable("menus", ObjectWrapper.DEFAULT_WRAPPER.wrap(menus));
        renderBody(env, body);
    }
}
