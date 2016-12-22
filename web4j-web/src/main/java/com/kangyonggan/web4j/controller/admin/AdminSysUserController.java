package com.kangyonggan.web4j.controller.admin;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.web4j.controller.BaseController;
import com.kangyonggan.web4j.model.Dept;
import com.kangyonggan.web4j.model.Role;
import com.kangyonggan.web4j.model.User;
import com.kangyonggan.web4j.service.DeptService;
import com.kangyonggan.web4j.service.RoleService;
import com.kangyonggan.web4j.service.UserService;
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
@RequestMapping("admin/sys/user")
public class AdminSysUserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private DeptService deptService;

    /**
     * 用户管理
     *
     * @param pageNum
     * @param deptCode
     * @param fullname
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_USER")
    public String index(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                        @RequestParam(value = "deptCode", required = false, defaultValue = "") String deptCode,
                        @RequestParam(value = "fullname", required = false, defaultValue = "") String fullname,
                        Model model) {
        List<User> users = userService.searchUsers(pageNum, deptCode, fullname);
        PageInfo<User> page = new PageInfo(users);
        List<Dept> depts = deptService.findAllDepts();

        model.addAttribute("page", page);
        model.addAttribute("depts", depts);
        return getPathList();
    }

    /**
     * 添加用户
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_USER")
    public String create(Model model) {
        List<Dept> depts = deptService.findAllActiveDepts();

        model.addAttribute("depts", depts);
        model.addAttribute("user", new User());
        return getPathFormModal();
    }

    /**
     * 保存用户
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("ADMIN_SYS_USER")
    public Map<String, Object> save(@ModelAttribute("user") @Valid User user, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();
        if (!result.hasErrors()) {
            userService.saveUserWithDefaultRole(user);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
    }

    /**
     * 编辑用户
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_USER")
    public String create(@PathVariable("id") Long id, Model model) {
        List<Dept> depts = deptService.findAllActiveDepts();

        model.addAttribute("depts", depts);
        model.addAttribute("user", userService.findUserById(id));
        return getPathFormModal();
    }

    /**
     * 更新用户
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("ADMIN_SYS_USER")
    public Map<String, Object> update(@ModelAttribute("user") @Valid User user, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();
        if (!result.hasErrors()) {
            userService.updateUser(user);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
    }

    /**
     * 锁定/解锁
     *
     * @param id
     * @param isLocked
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/{isLocked:\\bunlock\\b|\\block\\b}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    @RequiresPermissions("ADMIN_SYS_USER")
    public String lock(@PathVariable("id") Long id, @PathVariable("isLocked") String isLocked, Model model) {
        User user = userService.findUserById(id);
        user.setIsLocked((byte) (isLocked.equals("unlock") ? 0 : 1));
        userService.updateUser(user);

        model.addAttribute("user", user);
        return getPathTableTr();
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
    @RequiresPermissions("ADMIN_SYS_USER")
    public String delete(@PathVariable("id") Long id, @PathVariable("isDeleted") String isDeleted, Model model) {
        User user = userService.findUserById(id);
        user.setIsDeleted((byte) (isDeleted.equals("delete") ? 1 : 0));
        userService.updateUser(user);

        model.addAttribute("user", user);
        return getPathTableTr();
    }

    /**
     * 用户详情
     *
     * @param username
     * @param model
     * @return
     */
    @RequestMapping(value = "{username:[\\w]+}", method = RequestMethod.GET)
    public String detail(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userService.findUserByUsername(username));
        return getPathDetailModal();
    }

    /**
     * 修改密码
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/password", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_USER")
    public String password(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return getPathRoot() + "/password-modal";
    }

    /**
     * 修改密码
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "password", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("ADMIN_SYS_USER")
    public Map<String, Object> password(@ModelAttribute("user") @Valid User user, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();
        if (!result.hasErrors()) {
            userService.updateUserPassword(user);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
    }

    /**
     * 设置角色
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/roles", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_USER")
    public String roles(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        List<Role> user_roles = roleService.findRolesByUsername(user.getUsername());
        user_roles = Collections3.extractToList(user_roles, "code");
        List<Role> all_roles = roleService.findAllRoles();

        model.addAttribute("userId", id);
        model.addAttribute("user_roles", user_roles);
        model.addAttribute("all_roles", all_roles);
        return getPathRoot() + "/roles-modal";
    }

    /**
     * 保存角色
     *
     * @param id
     * @param roles
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/roles", method = RequestMethod.POST)
    @RequiresPermissions("ADMIN_SYS_USER")
    @ResponseBody
    public Map<String, Object> updateUserRoles(@PathVariable(value = "id") Long id,
                                               @RequestParam(value = "roles", defaultValue = "") String roles) {
        User user = userService.findUserById(id);
        userService.updateUserRoles(user.getUsername(), roles);

        return getResultMap();
    }

}
