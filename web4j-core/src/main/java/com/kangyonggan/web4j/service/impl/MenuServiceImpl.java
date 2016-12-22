package com.kangyonggan.web4j.service.impl;

import com.kangyonggan.web4j.aop.annotation.CacheDeleteAll;
import com.kangyonggan.web4j.aop.annotation.CacheGetOrSave;
import com.kangyonggan.web4j.aop.annotation.LogTime;
import com.kangyonggan.web4j.constants.AppConstants;
import com.kangyonggan.web4j.mapper.MenuMapper;
import com.kangyonggan.web4j.model.Menu;
import com.kangyonggan.web4j.service.MenuService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/12/22
 */
@Service
public class MenuServiceImpl extends BaseService<Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    @LogTime
    @CacheGetOrSave("menu:username:{0}:all")
    public List<Menu> findMenusByUsername(String username) {
        return menuMapper.selectMenusByUsernameAndType(username, AppConstants.MENU_TYPE_ALL);
    }

    @Override
    @LogTime
    @CacheGetOrSave("menu:username:{0}:dashboard")
    public List<Menu> findDashboardMenus(String username) {
        List<Menu> menus = menuMapper.selectMenusByUsernameAndType(username, AppConstants.MENU_TYPE_DASHBOARD);
        List<Menu> wrapList = new ArrayList();

        return recursionList(menus, wrapList, AppConstants.MENU_CODE_DASHBOARD, 0L);
    }

    @Override
    @LogTime
    @CacheGetOrSave("menu:username:{0}:admin")
    public List<Menu> findAdminMenus(String username) {
        List<Menu> menus = menuMapper.selectMenusByUsernameAndType(username, AppConstants.MENU_TYPE_ADMIN);
        List<Menu> wrapList = new ArrayList();

        return recursionList(menus, wrapList, AppConstants.MENU_CODE_ADMIN, 0L);
    }

    @Override
    @LogTime
    public boolean existsMenuCode(String code) {
        Menu menu = new Menu();
        menu.setCode(code);

        return menuMapper.selectCount(menu) == 1;
    }

    @Override
    @LogTime
    @CacheGetOrSave("menu:roleCode:{0}:admin")
    public List<Menu> findAdminMenus4Role(String code) {
        return menuMapper.selectMenus4Role(code, AppConstants.MENU_TYPE_ADMIN);
    }

    @Override
    @LogTime
    @CacheGetOrSave("menu:all:admin")
    public List<Menu> findAllAdminMenus() {
        Menu menu = new Menu();
        menu.setType(AppConstants.MENU_TYPE_ADMIN);
        List<Menu> menus = super.select(menu);

        List<Menu> wrapList = new ArrayList();
        return recursionTreeList(menus, wrapList, "", 0L);
    }

    @Override
    @LogTime
    @CacheGetOrSave("menu:roleCode:{0}:dashboard")
    public List<Menu> findDashboardMenus4Role(String code) {
        return menuMapper.selectMenus4Role(code, AppConstants.MENU_TYPE_DASHBOARD);
    }

    @Override
    @LogTime
    @CacheGetOrSave("menu:all:dashboard")
    public List<Menu> findAllDashboardMenus() {
        Menu menu = new Menu();
        menu.setType(AppConstants.MENU_TYPE_DASHBOARD);
        List<Menu> menus = super.select(menu);

        List<Menu> wrapList = new ArrayList();
        return recursionTreeList(menus, wrapList, "", 0L);
    }

    @Override
    @LogTime
    @CacheGetOrSave("menu:code:{0}")
    public Menu findMenuByCode(String code) {
        Menu menu = new Menu();
        menu.setCode(code);

        return super.selectOne(menu);
    }

    @Override
    @LogTime
    @CacheDeleteAll("menu:all")
    public void saveMenu(Menu menu) {
        super.insertSelective(menu);
    }

    @Override
    @LogTime
    @CacheGetOrSave("menu:id:{0}")
    public Menu findMenuById(Long id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    @LogTime
    @CacheDeleteAll("menu")
    public void updateMenu(Menu menu) {
        super.updateByPrimaryKeySelective(menu);
    }

    @Override
    @LogTime
    @CacheDeleteAll("menu")
    public void deleteMenu(Menu menu) {
        super.deleteByPrimaryKey(menu);
    }

    /**
     * 递归找出 parentCode 下边的所有子节点
     *
     * @param from
     * @param toList
     * @param pcode
     * @param pid
     * @return
     */
    private List<Menu> recursionList(List<Menu> from, List<Menu> toList, String pcode, Long pid) {

        if (CollectionUtils.isEmpty(from)) {
            return null;
        }

        for (int i = 0; i < from.size(); i++) {
            Menu menu = from.get(i);
            if (pcode.equals(menu.getPcode())) {
                List<Menu> leaf = new ArrayList();
                menu.setLeaf(leaf);
                menu.setPid(pid);
                toList.add(menu);
                recursionList(from, leaf, menu.getCode(), menu.getId());
            }
        }
        return toList;
    }

    /**
     * 递归找出 parentCode 下边的所有子节点
     *
     * @param from
     * @param toList
     * @param pcode
     * @param pid
     * @return
     */
    private List<Menu> recursionTreeList(List<Menu> from, List<Menu> toList, String pcode, Long pid) {

        if (CollectionUtils.isEmpty(from)) {
            return null;
        }

        for (int i = 0; i < from.size(); i++) {
            Menu menu = from.get(i);
            if (pcode.equals(menu.getPcode())) {
                menu.setPid(pid);
                toList.add(menu);
                recursionTreeList(from, toList, menu.getCode(), menu.getId());
            }
        }
        return toList;
    }
}
