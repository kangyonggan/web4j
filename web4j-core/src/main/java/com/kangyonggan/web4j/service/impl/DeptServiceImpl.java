package com.kangyonggan.web4j.service.impl;

import com.kangyonggan.web4j.aop.annotation.CacheDelete;
import com.kangyonggan.web4j.aop.annotation.CacheDeleteAll;
import com.kangyonggan.web4j.aop.annotation.CacheGetOrSave;
import com.kangyonggan.web4j.aop.annotation.LogTime;
import com.kangyonggan.web4j.constants.AppConstants;
import com.kangyonggan.web4j.mapper.DeptMapper;
import com.kangyonggan.web4j.model.Dept;
import com.kangyonggan.web4j.model.User;
import com.kangyonggan.web4j.service.DeptService;
import com.kangyonggan.web4j.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/12/22
 */
@Service
public class DeptServiceImpl extends BaseService<Dept> implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private UserService userService;

    @Override
    @LogTime
    @CacheGetOrSave("dept:all")
    public List<Dept> findAllDepts() {
        return super.select(new Dept());
    }

    @Override
    @LogTime
    @CacheGetOrSave("dept:code:{0}")
    public Dept findDeptByCode(String code) {
        Dept dept = new Dept();
        dept.setCode(code);

        return super.selectOne(dept);
    }

    @Override
    @LogTime
    @CacheDelete("dept:all")
    public void saveDept(Dept dept) {
        super.insertSelective(dept);
    }

    @Override
    @LogTime
    @CacheGetOrSave("dept:id:{0}")
    public Dept findDeptById(Long id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    @LogTime
    @CacheDeleteAll("dept")
    public void updateDept(Dept dept) {
        if (StringUtils.isNotEmpty(dept.getDeptHeaderUsername())) {
            User user = userService.findUserByUsername(dept.getDeptHeaderUsername());
            dept.setDeptHeaderFullname(user.getFullname());
        }

        super.updateByPrimaryKeySelective(dept);
    }

    @Override
    @LogTime
    public boolean existsDeptCode(String code) {
        Dept dept = new Dept();
        dept.setCode(code);

        return deptMapper.selectCount(dept) == 1;
    }

    @Override
    @LogTime
    @CacheGetOrSave("dept:all:active")
    public List<Dept> findAllActiveDepts() {
        Dept dept = new Dept();
        dept.setIsDeleted(AppConstants.IS_DELETED_NO);

        return super.select(dept);
    }
}
