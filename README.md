# spark-platform
spark 平台

- 基于 Spring Cloud Hoxton 、Spring Boot 2.2、 OAuth2 的RBAC权限管理系统  
- 基于vue-element-template 实现的前端交互  
- 前后端分离架构，客户端和服务端纯Token交互，接口全部使用restful风格
- 认证服务器与资源服务器分离，方便接入自己的微服务系统；
- 基于flowable的工作流系统，提供完善基于业务的流程系统(工作流结合业务例子已经在开发中)

### 已实现功能
|   用户管理  |  角色管理   |  菜单管理   |  部门管理   |  字典管理   |  客户端管理   |  待办事项   |  已办事项   |
| --- | --- | --- | --- | --- | --- | --- | --- |
|   流程管理   |  系统日志   |  系统监控   | 代码生成器    | 网关限流    | 文件管理    |   登录日志  |     |




### 项目地址
 平台  | spark-platform（后端）|spark-admin（前端）
---|---|---
GitHub | [https://github.com/wangdingfeng/spark-platform](https://github.com/wangdingfeng/spark-platform)|[https://github.com/wangdingfeng/spark-admin](https://github.com/wangdingfeng/spark-admin)
Gitee  | [https://gitee.com/dreamfeng/spark-platform](https://gitee.com/dreamfeng/spark-platform)|[https://gitee.com/dreamfeng/spark-admin](https://gitee.com/dreamfeng/spark-admin)

### 演示地址

演示地址：[http://www.sparkplatform.cn/](http://www.sparkplatform.cn/)

演示环境账号密码：
| 账号  | 密码   | 权限               |
| ----- | ------ | ------------------ |
| admin | 123456 | 除删除外所有的权限 |
| zuzhang | 123456 | 工作流权限 |
| zhubian1 | 123456 | 工作流权限 |
| zhubian2 | 123456 | 工作流权限 |

依赖 | 版本
---|---
Spring Boot |  2.2.6.RELEASE 
Spring Cloud | Hoxton.SR3   
Flowable | 6.4.0
Mybatis Plus | 3.3.1
Spring Boot Admin | 2.2.0
Security Jwt | 1.0.10.RELEASE

#### 模块说明
```lua
spark-platform 
├── spark-auth -- 授权服务 oauth2
└── spark-common -- 系统公共模块 
     ├── spark-common-base -- 基本公共类
     ├── spark-common-config -- 公共配置类
     ├── spark-common-feigh -- 公共feigh类
     ├── spark-common-security -- 安全工具类
     └── spark-common-utils -- 工具类
     └── spark-common-log -- 日志记录
├── spark-gateway -- Spring Cloud Gateway网关
├── spark-eureka -- Spring Cloud eureka注册中心
├── spark-control -- Spring Boot Admin监控
└── spark-admin -- 通用用户权限管理模块
     └── spark-admin-api -- 通用用户权限管理系统公共api模块
     └── spark-admin-biz -- 通用用户权限管理系统业务处理模块
└── spark-flowable -- 通用工作流模块
     └── spark-flowable-api -- 通用工作流模块公共api模块
     └── spark-flowable-biz -- 通用工作流模块业务处理模块
```
 **平台截图**
 
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/203519_d3bb2ecf_1890906.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/203457_15593a6b_1890906.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/203428_709e61c6_1890906.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/203543_ed7c5f02_1890906.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/205632_66bdcc0b_1890906.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/205726_e7fc1fd3_1890906.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/205905_b67406dd_1890906.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/205836_f50e2362_1890906.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/205954_e8763418_1890906.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/210304_7fb0e942_1890906.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/210343_6346c833_1890906.png "屏幕截图.png")