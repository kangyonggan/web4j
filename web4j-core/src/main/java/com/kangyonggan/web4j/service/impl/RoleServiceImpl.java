package com.kangyonggan.web4j.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.web4j.aop.annotation.CacheDelete;
import com.kangyonggan.web4j.aop.annotation.CacheDeleteAll;
import com.kangyonggan.web4j.aop.annotation.CacheGetOrSave;
import com.kangyonggan.web4j.aop.annotation.LogTime;
import com.kangyonggan.web4j.constants.AppConstants;
import com.kangyonggan.web4j.mapper.RoleMapper;
import com.kangyonggan.web4j.model.Menu;
import com.kangyonggan.web4j.model.Role;
import com.kangyonggan.web4j.service.RoleService;
import com.kangyonggan.web4j.util.Collections3;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/12/22
 */
@Service
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @LogTime
    @CacheGetOrSave("role:username:{0}")
    public List<Role> findRolesByUsername(String username) {
        return roleMapper.selectRolesByUsername(username);
    }

    @Override
    @LogTime
    @CacheGetOrSave("role:all")
    public List<Role> findAllRoles() {
        Role role = new Role();
        role.setIsDeleted(AppConstants.IS_DELETED_NO);

        return super.select(role);
    }

    @Override
    @LogTime
    public boolean existsRoleCode(String code) {
        Role role = new Role();
        role.setCode(code);

        return roleMapper.selectCount(role) == 1;
    }

    @Override
    @LogTime
    public List<Role> searchRoles(int pageNum, String code, String name) {
        Example example = new Example(Role.class);

        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(code)) {
            criteria.andLike("code", String.format("%%%s%%", code));
        }
        if (StringUtils.isNotEmpty(name)) {
            criteria.andLike("name", String.format("%%%s%%", name));
        }

        example.setOrderByClause("created_time desc");

        PageHelper.startPage(pageNum, AppConstants.PAGE_SIZE);
        return super.selectByExample(example);
    }

    @Override
    @LogTime
    @CacheDelete("role:all")
    public void saveRole(Role role) {
        super.insertSelective(role);
    }

    @Override
    @LogTime
    @CacheGetOrSave("role:id:{0}")
    public Role findRoleById(Long id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    @LogTime
    @CacheDeleteAll("role")
    public void updateRole(Role role) {
        super.updateByPrimaryKeySelective(role);
    }

    @Override
    @LogTime
    @CacheDeleteAll("menu:username||menu:roleCode:{0}")
    public void updateRoleMenus(String code, List<Menu> role_menus, String menuCodes) {
        if (Collections3.isNotEmpty(role_menus)) {
            deleteRoleMenus(code, Collections3.extractToList(role_menus, "code"));
        }

        if (StringUtils.isNotEmpty(menuCodes)) {
            roleMapper.insertRoleMenus(code, Arrays.asList(menuCodes.split(",")));
        }
    }

    /**
     * 删除角色菜单
     *
     * @param code
     * @param menuCodes
     */
    @LogTime
    private void deleteRoleMenus(String code, List<String> menuCodes) {
        roleMapper.deleteRoleMenus(code, menuCodes);
    }
}
