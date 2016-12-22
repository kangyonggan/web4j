package com.kangyonggan.web4j.controller.dashboard;

import com.kangyonggan.web4j.controller.BaseController;
import com.kangyonggan.web4j.model.ShiroUser;
import com.kangyonggan.web4j.model.User;
import com.kangyonggan.web4j.service.UserService;
import com.kangyonggan.web4j.util.FileUpload;
import com.kangyonggan.web4j.util.Images;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 2016/12/10
 */
@Controller
@RequestMapping("dashboard/user")
public class DashboardUserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping(value = "password", method = RequestMethod.GET)
    @RequiresPermissions("DASHBOARD_USER_PASSWORD")
    public String password() {
        return getPathRoot() + "/password";
    }

    /**
     * 修改密码
     *
     * @param password
     * @return
     */
    @RequestMapping(value = "password", method = RequestMethod.POST)
    @RequiresPermissions("DASHBOARD_USER_PASSWORD")
    @ResponseBody
    public Map<String, Object> password(@RequestParam("password") String password) {
        User user = userService.findUserByUsername(userService.getShiroUser().getUsername());
        user.setPassword(password);
        userService.updateUserPassword(user);

        return getResultMap();
    }

    /**
     * 个人资料
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "profile", method = RequestMethod.GET)
    @RequiresPermissions("DASHBOARD_USER_PROFILE")
    public String profile(Model model) {
        ShiroUser user = userService.getShiroUser();

        model.addAttribute("user", userService.findUserById(user.getId()));
        return getPathRoot() + "/profile";
    }

    /**
     * 个人资料
     *
     * @param user
     * @param result
     * @param avatar
     * @return
     * @throws FileUploadException
     */
    @RequestMapping(value = "profile", method = RequestMethod.POST)
    @RequiresPermissions("DASHBOARD_USER_PROFILE")
    public String profile(@ModelAttribute("user") @Valid User user, BindingResult result,
                          @RequestParam(value = "avatar", required = false) MultipartFile avatar) throws FileUploadException {
        if (!result.hasErrors()) {
            if (!avatar.isEmpty()) {
                String fileName = FileUpload.upload(avatar);

                String large = Images.large(fileName);
                user.setLargeAvatar(large);
                String middle = Images.middle(fileName);
                user.setMediumAvatar(middle);
                String small = Images.small(fileName);
                user.setSmallAvatar(small);
            }

            userService.updateUser(user);
        }

        return "redirect:/dashboard/user/profile";
    }

}
