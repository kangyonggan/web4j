DROP DATABASE IF EXISTS web4j;

CREATE DATABASE web4j
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;

USE web4j;

-- ----------------------------
--  Table structure for user
-- ----------------------------
DROP TABLE
IF EXISTS user;

CREATE TABLE user
(
  id            BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  username      VARCHAR(20)                           NOT NULL
  COMMENT '用户名',
  password      VARCHAR(64)                           NOT NULL
  COMMENT '密码',
  salt          VARCHAR(64)                           NOT NULL
  COMMENT '密码盐',
  fullname      VARCHAR(32)                           NOT NULL
  COMMENT '姓名',
  mobile        VARCHAR(16)                           NOT NULL                    DEFAULT ''
  COMMENT '手机号',
  email         VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '电子邮箱',
  small_avatar  VARCHAR(255)                          NOT NULL                    DEFAULT ''
  COMMENT '小头像',
  medium_avatar VARCHAR(255)                          NOT NULL                    DEFAULT ''
  COMMENT '中头像',
  large_avatar  VARCHAR(255)                          NOT NULL                    DEFAULT ''
  COMMENT '大头像',
  dept_code     VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '部门代码',
  dept_name     VARCHAR(64)                           NOT NULL                    DEFAULT ''
  COMMENT '部门名称',
  is_locked     TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '是否锁定:{0:未锁定, 1:已锁定}',
  is_deleted    TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time  TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time  TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '用户表';
CREATE UNIQUE INDEX id_UNIQUE
  ON user (id);
CREATE INDEX create_ix
  ON user (created_time);
CREATE UNIQUE INDEX username_UNIQUE
  ON user (username);

-- ----------------------------
--  Table structure for dept
-- ----------------------------
DROP TABLE
IF EXISTS dept;

CREATE TABLE dept
(
  id                   BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  code                 VARCHAR(32)                           NOT NULL
  COMMENT '部门代码',
  name                 VARCHAR(64)                           NOT NULL
  COMMENT '部门名称',
  dept_header_username VARCHAR(20)                           NOT NULL                    DEFAULT ''
  COMMENT '部门负责人用户名',
  dept_header_fullname VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '部门负责人',
  is_deleted           TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time         TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time         TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '部门表';
CREATE UNIQUE INDEX id_UNIQUE
  ON dept (id);
CREATE INDEX create_ix
  ON dept (created_time);
CREATE UNIQUE INDEX code_UNIQUE
  ON dept (code);

-- ----------------------------
--  Table structure for role
-- ----------------------------
DROP TABLE
IF EXISTS role;

CREATE TABLE role
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  code         VARCHAR(32)                           NOT NULL
  COMMENT '角色代码',
  name         VARCHAR(32)                           NOT NULL
  COMMENT '角色名称',
  is_deleted   TINYINT                               NOT NULL                DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '角色表';
CREATE UNIQUE INDEX id_UNIQUE
  ON role (id);
CREATE INDEX create_ix
  ON role (created_time);
CREATE UNIQUE INDEX code_UNIQUE
  ON role (code);

-- ----------------------------
--  Table structure for menu
-- ----------------------------
DROP TABLE
IF EXISTS menu;

CREATE TABLE menu
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  code         VARCHAR(32)                           NOT NULL
  COMMENT '菜单代码',
  name         VARCHAR(32)                           NOT NULL
  COMMENT '菜单名称',
  type         VARCHAR(16)                           NOT NULL
  COMMENT '菜单类型:{dashboard:工作台, admin:控制台}',
  pcode        VARCHAR(32)                           NOT NULL                DEFAULT ''
  COMMENT '父菜单代码',
  url          VARCHAR(128)                          NOT NULL                DEFAULT ''
  COMMENT '菜单地址',
  sort         INT(11)                               NOT NULL                DEFAULT 0
  COMMENT '菜单排序(从0开始)',
  icon         VARCHAR(128)                          NOT NULL                DEFAULT ''
  COMMENT '菜单图标的样式',
  is_deleted   TINYINT                               NOT NULL                DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '菜单表';
CREATE UNIQUE INDEX id_UNIQUE
  ON menu (id);
CREATE INDEX create_ix
  ON menu (created_time);
CREATE INDEX sort_ix
  ON menu (sort);
CREATE UNIQUE INDEX code_UNIQUE
  ON menu (code);

-- ----------------------------
--  Table structure for user_role
-- ----------------------------
DROP TABLE
IF EXISTS user_role;

CREATE TABLE user_role
(
  username  VARCHAR(20) NOT NULL
  COMMENT '用户名',
  role_code VARCHAR(32) NOT NULL
  COMMENT '角色代码',
  PRIMARY KEY (username, role_code)
)
  COMMENT '用户角色表';

-- ----------------------------
--  Table structure for role_menu
-- ----------------------------
DROP TABLE
IF EXISTS role_menu;

CREATE TABLE role_menu
(
  role_code VARCHAR(32) NOT NULL
  COMMENT '角色代码',
  menu_code VARCHAR(32) NOT NULL
  COMMENT '菜单代码',
  PRIMARY KEY (role_code, menu_code)
)
  COMMENT '角色菜单表';

#====================初始数据====================#

-- ----------------------------
--  data for user
-- ----------------------------
INSERT INTO user
(username, password, salt, fullname, mobile, email, dept_code, dept_name)
VALUES
  ('admin', '9606b0029ba4a8c9369f288cced0dc465eb5eabd', '3685072edcf8aad8', '管理员', '15151679072',
   'kangyonggan@gmail.com', '', '');

-- ----------------------------
--  data for role
-- ----------------------------
INSERT INTO role
(code, name)
VALUES
  ('ROLE_ADMIN', '管理员'),
  ('ROLE_DEPT_HEADER', '部门负责人'),
  ('ROLE_USER', '普通用户');

-- ----------------------------
--  data for menu
-- ----------------------------
INSERT INTO menu
(code, name, type, pcode, url, sort, icon)
VALUES
  ('ADMIN', '控制台', 'admin', '', 'admin', 0, 'menu-icon fa fa-desktop'),
  ('DASHBOARD', '工作台', 'dashboard', '', 'dashboard', 0, 'menu-icon fa fa-dashboard'),

  ('ADMIN_SYS', '系统', 'admin', 'ADMIN', 'admin/sys', 0, 'menu-icon fa fa-cogs'),
  ('ADMIN_SYS_USER', '用户管理', 'admin', 'ADMIN_SYS', 'admin/sys/user', 0, ''),
  ('ADMIN_SYS_ROLE', '角色管理', 'admin', 'ADMIN_SYS', 'admin/sys/role', 1, ''),
  ('ADMIN_SYS_MENU', '菜单管理', 'admin', 'ADMIN_SYS', 'admin/sys/menu', 2, ''),
  ('ADMIN_SYS_DEPT', '部门管理', 'admin', 'ADMIN_SYS', 'admin/sys/dept', 3, ''),

  ('ADMIN_DATA', '数据', 'admin', 'ADMIN', 'admin/data', 1, 'menu-icon fa fa-gavel'),
  ('ADMIN_DATA_CACHE', '缓存管理', 'admin', 'ADMIN_DATA', 'admin/data/cache', 0, ''),

  ('DASHBOARD_USER', '我的', 'dashboard', 'DASHBOARD', 'dashboard/user', 0, 'menu-icon fa fa-user'),
  ('DASHBOARD_USER_PROFILE', '个人信息', 'dashboard', 'DASHBOARD_USER', 'dashboard/user/profile', 0, ''),
  ('DASHBOARD_USER_PASSWORD', '修改密码', 'dashboard', 'DASHBOARD_USER', 'dashboard/user/password', 1, '');

-- ----------------------------
--  data for user_role
-- ----------------------------
INSERT INTO user_role
VALUES
  ('admin', 'ROLE_ADMIN');

-- ----------------------------
--  data for role_menu
-- ----------------------------
INSERT INTO role_menu SELECT
                        'ROLE_ADMIN',
                        code
                      FROM menu;

INSERT INTO role_menu SELECT
                        'ROLE_USER',
                        code
                      FROM menu
                      WHERE code LIKE 'DASHBOARD%';