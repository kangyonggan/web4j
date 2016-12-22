package com.kangyonggan.web4j.controller.admin;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.web4j.constants.AppConstants;
import com.kangyonggan.web4j.controller.BaseController;
import com.kangyonggan.web4j.model.Menu;
import com.kangyonggan.web4j.model.Role;
import com.kangyonggan.web4j.service.MenuService;
import com.kangyonggan.web4j.service.RoleService;
import com.kangyonggan.web4j.util.Collections3;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 2016/12/2
 */
@Controller
@RequestMapping("admin/sys/role")
public class AdminSysRoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 角色管理
     *
     * @param pageNum
     * @param code
     * @param name
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_ROLE")
    public String index(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                        @RequestParam(value = "code", required = false, defaultValue = "") String code,
                        @RequestParam(value = "name", required = false, defaultValue = "") String name,
                        Model model) {
        List<Role> roles = roleService.searchRoles(pageNum, code, name);
        PageInfo<Role> page = new PageInfo(roles);

        model.addAttribute("page", page);
        return getPathList();
    }

    /**
     * 添加角色
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_ROLE")
    public String create(Model model) {
        model.addAttribute("role", new Role());
        return getPathFormModal();
    }

    /**
     * 保存角色
     *
     * @param role
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("ADMIN_SYS_ROLE")
    public Map<String, Object> save(@ModelAttribute("role") @Valid Role role, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();
        if (!result.hasErrors()) {
            roleService.saveRole(role);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
    }

    /**
     * 编辑角色
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_ROLE")
    public String create(@PathVariable("id") Long id, Model model) {
        model.addAttribute("role", roleService.findRoleById(id));
        return getPathFormModal();
    }

    /**
     * 更新角色
     *
     * @param role
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("ADMIN_SYS_ROLE")
    public Map<String, Object> update(@ModelAttribute("role") @Valid Role role, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();
        if (!result.hasErrors()) {
            roleService.updateRole(role);
        } else {
        }

        return resultMap;
    }

    /**
     * 删除/恢复
     *
     * @param id
     * @param isDeleted
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/{isDeleted:\\bundelete\\b|\\bdelete\\b}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    @RequiresPermissions("ADMIN_SYS_ROLE")
    public String delete(@PathVariable("id") Long id, @PathVariable("isDeleted") String isDeleted, Model model) {
        Role role = roleService.findRoleById(id);
        role.setIsDeleted((byte) (isDeleted.equals("delete") ? 1 : 0));
        roleService.updateRole(role);

        model.addAttribute("role", role);
        return getPathTableTr();
    }

    /**
     * 角色详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_ROLE")
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("role", roleService.findRoleById(id));
        return getPathDetailModal();
    }

    /**
     * 修改角色的控制台菜单
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/am", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_ROLE")
    public String adminMenus(@PathVariable("id") Long id, Model model) {
        Role role = roleService.findRoleById(id);
        List<Menu> role_menus = menuService.findAdminMenus4Role(role.getCode());
        if (role_menus != null) {
            role_menus = Collections3.extractToList(role_menus, "code");
        }

        List<Menu> all_menus = menuService.findAllAdminMenus();

        model.addAttribute("role_menus", role_menus);
        model.addAttribute("all_menus", all_menus);
        model.addAttribute("type", AppConstants.MENU_TYPE_ADMIN);
        model.addAttribute("roleId", id);
        return getPathRoot() + "/menus-modal";
    }

    /**
     * 修改角色的工作台菜单
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/dm", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_ROLE")
    public String dashboardMenus(@PathVariable("id") Long id, Model model) {
        Role role = roleService.findRoleById(id);
        List<Menu> role_menus = menuService.findDashboardMenus4Role(role.getCode());
        if (role_menus != null) {
            role_menus = Collections3.extractToList(role_menus, "code");
        }

        List<Menu> all_menus = menuService.findAllDashboardMenus();

        model.addAttribute("role_menus", role_menus);
        model.addAttribute("all_menus", all_menus);
        model.addAttribute("type", AppConstants.MENU_TYPE_DASHBOARD);
        model.addAttribute("roleId", id);
        return getPathRoot() + "/menus-modal";
    }

    /**
     * 更新角色菜单
     *
     * @param id
     * @param menus
     * @param type
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/menus", method = RequestMethod.POST)
    @RequiresPermissions("ADMIN_SYS_ROLE")
    @ResponseBody
    public Map<String, Object> menus(@PathVariable Long id, @RequestParam(value = "menus") String menus, @RequestParam(value = "type") String type) {
        Map<String, Object> resultMap = getResultMap();
        Role role = roleService.findRoleById(id);

        List<Menu> role_menus;
        if (AppConstants.MENU_TYPE_ADMIN.equals(type)) {
            role_menus = menuService.findAdminMenus4Role(role.getCode());
        } else {
            role_menus = menuService.findDashboardMenus4Role(role.getCode());
        }

        roleService.updateRoleMenus(role.getCode(), role_menus, menus);
        return resultMap;
    }
}
