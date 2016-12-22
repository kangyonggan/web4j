package com.kangyonggan.web4j.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public class Dept implements Serializable {
    /**
     * 主键, 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 部门代码
     */
    private String code;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门负责人用户名
     */
    @Column(name = "dept_header_username")
    private String deptHeaderUsername;

    /**
     * 部门负责人
     */
    @Column(name = "dept_header_fullname")
    private String deptHeaderFullname;

    /**
     * 逻辑删除:{0:未删除, 1:已删除}
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    private static final long serialVersionUID = 1L;
}