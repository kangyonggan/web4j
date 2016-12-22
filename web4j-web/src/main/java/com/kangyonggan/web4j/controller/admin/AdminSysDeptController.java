package com.kangyonggan.web4j.controller.admin;

import com.kangyonggan.web4j.controller.BaseController;
import com.kangyonggan.web4j.model.Dept;
import com.kangyonggan.web4j.model.User;
import com.kangyonggan.web4j.service.DeptService;
import com.kangyonggan.web4j.service.UserService;
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
 * @since 2016/12/11
 */
@Controller
@RequestMapping("admin/sys/dept")
public class AdminSysDeptController extends BaseController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private UserService userService;

    /**
     * 部门管理
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_DEPT")
    public String list(Model model) {
        List<Dept> depts = deptService.findAllDepts();

        model.addAttribute("depts", depts);
        return getPathList();
    }

    /**
     * 添加部门
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_DEPT")
    public String create(Model model) {
        model.addAttribute("dept", new Dept());
        return getPathFormModal();
    }

    /**
     * 保存部门
     *
     * @param dept
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("ADMIN_SYS_DEPT")
    public Map<String, Object> save(@ModelAttribute("dept") @Valid Dept dept, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();

        if (!result.hasErrors()) {
            deptService.saveDept(dept);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
    }

    /**
     * 编辑部门
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_SYS_DEPT")
    public String create(@PathVariable("id") Long id, Model model) {
        List<User> users = userService.findUsers4DeptHeader(id);

        model.addAttribute("users", users);
        model.addAttribute("dept", deptService.findDeptById(id));
        return getPathFormModal();
    }

    /**
     * 更新部门
     *
     * @param dept
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("ADMIN_SYS_DEPT")
    public Map<String, Object> update(@ModelAttribute("dept") @Valid Dept dept, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();

        if (!result.hasErrors()) {
            deptService.updateDept(dept);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
    }

    /**
     * 部门详情
     *
     * @param code
     * @param model
     * @return
     */
    @RequestMapping(value = "{code:[\\w]+}", method = RequestMethod.GET)
    public String detail(@PathVariable("code") String code, Model model) {
        model.addAttribute("dept", deptService.findDeptByCode(code));
        return getPathDetailModal();
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
    @RequiresPermissions("ADMIN_SYS_DEPT")
    public String delete(@PathVariable("id") Long id, @PathVariable("isDeleted") String isDeleted, Model model) {
        Dept dept = deptService.findDeptById(id);
        dept.setIsDeleted((byte) (isDeleted.equals("delete") ? 1 : 0));
        deptService.updateDept(dept);

        model.addAttribute("dept", dept);
        return getPathTableTr();
    }

}
