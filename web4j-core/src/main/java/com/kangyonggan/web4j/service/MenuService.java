package com.kangyonggan.web4j.service;

import com.kangyonggan.web4j.model.Menu;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/12/22
 */
public interface MenuService {

    /**
     * 根据用户名查找菜单
     *
     * @param username
     * @return
     */
    List<Menu> findMenusByUsername(String username);

    /**
     * 根据用户名查找工作台菜单
     *
     * @param username
     * @return
     */
    List<Menu> findDashboardMenus(String username);

    /**
     * 根据用户名查找控制台菜单
     *
     * @param username
     * @return
     */
    List<Menu> findAdminMenus(String username);

    /**
     * 校验菜单代码是否存在
     *
     * @param code
     * @return
     */
    boolean existsMenuCode(String code);

    /**
     * 查找角色的控制台菜单
     *
     * @param code
     * @return
     */
    List<Menu> findAdminMenus4Role(String code);

    /**
     * 查找所有控制台菜单
     *
     * @return
     */
    List<Menu> findAllAdminMenus();

    /**
     * 查找角色的工作台菜单
     *
     * @param code
     * @return
     */
    List<Menu> findDashboardMenus4Role(String code);

    /**
     * 查找所有工作台菜单
     *
     * @return
     */
    List<Menu> findAllDashboardMenus();

    /**
     * 根据菜单代码查找菜单
     *
     * @param code
     * @return
     */
    Menu findMenuByCode(String code);

    /**
     * 保存菜单
     *
     * @param menu
     */
    void saveMenu(Menu menu);

    /**
     * 查找菜单根据ID
     *
     * @param id
     * @return
     */
    Menu findMenuById(Long id);

    /**
     * 更新菜单
     *
     * @param menu
     */
    void updateMenu(Menu menu);

    /**
     * 删除菜单，物理删除
     *
     * @param menu
     */
    void deleteMenu(Menu menu);
}
