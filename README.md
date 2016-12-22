# web4j
## 1. 一句话简介
web4j是一个单系统web项目原型，用于快速生成一个经典的java web项目。

## 2. 项目结构
项目分为两个模块：web层和core层，web层依赖core层。  

### 2.1. core层  
#### 2.1.1 Mybatis Generator(MBG)
MBG集成MapperPlugin和LombokPlugin, 用于生成更好用的持久层代码:
- 生成BeanMapper.java
- 生成BeanMapper.xml
- 生成Bean.java

#### 2.1.2 Log4j2
Log4j 2 包含了基于LMAX 分离库的下一代的异步日志系统，在多线程环境下，异步日志系统比 Log4j 1.x 和Logback 提高了10倍性能提升（吞吐量和延迟率 ）。

- 错误日志和其他日志分开打印
- 基于文件大小分割日志文件
- 同时基于时间分割日志文件

#### 2.1.3 Redis
redis结合aop和annotation，实现缓存的管理

#### 2.1.4 Code Generator(CG)
用于代码生成，提供多套方案，满足绝大多数需求。  
- 生成基于某个Bean，带有Bean的CRUD等的BeanService、BeanServiceImpl、XxxBeanController
- 生成基于某个Bean，对Bean进行CURD的UI界面，list、form、form-modal、detail、detail-modal等
- 生成UI界面对应的js、菜单、权限等
- 以上套餐均可自由选择

#### 2.1.5 Autoconfig
在一个应用中，我们总是会遇到一些参数，例如：  
- 数据库服务器IP地址、端口、用户名；
- 用来保存上传资料的目录。
- 一些参数，诸如是否打开cache、加密所用的密钥名称等等。

这些参数有一个共性，那就是：`它们和应用的逻辑无关，只和当前环境、当前系统用户相关`。以下场景很常见：  
- 在开发、测试、发布阶段，使用不同的数据库服务器；
- 在开发阶段，使用Windows的A开发者将用户上传的文件存放在d:\my_upload目录中，而使用Linux的B开发者将同样的文件存放在/home/myname/my_upload目录中。
- 在开发阶段设置cache=off，在生产环境中设置cache=on。

很明显，`这些参数不适合被“硬编码”在配置文件或代码中`。因为每一个从源码库中取得它们的人，都有可能需要修改它们，使之与自己的环境相匹配, autoconfig就是很好的选择。

### 2.2. web层
#### 2.2.1 Shiro
Shiro登录，URL权限管理，会话管理，强制结束会话等，并结合redis缓存，重启服务会话不丢失。

#### 2.2.2 Spring Message
实现UI的国际化提示信息

#### 2.2.3 Freemarker
基于模板的前端框架，可以像java那样继承、覆写等

#### 2.2.4 Ace Admin
是一套UI界面，中规中矩，比较适合做后台管理系统


