package com.kangyonggan.web4j.service;

import com.kangyonggan.web4j.model.ShiroUser;
import com.kangyonggan.web4j.model.User;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/12/22
 */
public interface UserService {

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 获取当前登录的用户
     *
     * @return
     */
    ShiroUser getShiroUser();

    /**
     * 搜索用户
     *
     * @param pageNum
     * @param deptCode
     * @param fullname
     * @return
     */
    List<User> searchUsers(int pageNum, String deptCode, String fullname);

    /**
     * 保存用户
     *
     * @param user
     */
    void saveUserWithDefaultRole(User user);

    /**
     * 根据主键查找用户
     *
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * 更新用户
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 更新用户密码
     *
     * @param user
     */
    void updateUserPassword(User user);

    /**
     * 修改用户角色
     *
     * @param username
     * @param roleCodes
     */
    void updateUserRoles(String username, String roleCodes);

    /**
     * 校验用户名是否存在
     *
     * @param username
     * @return
     */
    boolean existsUsername(String username);

    /**
     * 查找可用的部门负责人
     *
     * @param id
     * @return
     */
    List<User> findUsers4DeptHeader(Long id);
}
