package com.kangyonggan.web4j.service;

import com.kangyonggan.web4j.model.Dept;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/12/22
 */
public interface DeptService {

    /**
     * 查找所有部门
     *
     * @return
     */
    List<Dept> findAllDepts();

    /**
     * 根据部门代码查找部门
     *
     * @param code
     * @return
     */
    Dept findDeptByCode(String code);

    /**
     * 保存部门
     *
     * @param dept
     */
    void saveDept(Dept dept);

    /**
     * 查找部门
     *
     * @param id
     * @return
     */
    Dept findDeptById(Long id);

    /**
     * 更新部门
     *
     * @param dept
     */
    void updateDept(Dept dept);

    /**
     * 校验部门代码是否存在
     *
     * @param code
     * @return
     */
    boolean existsDeptCode(String code);

    /**
     * 查找所有可用的部门
     *
     * @return
     */
    List<Dept> findAllActiveDepts();

}
