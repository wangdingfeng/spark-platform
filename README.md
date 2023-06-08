# spark-platform V2.1
[![star](https://gitee.com/dreamfeng/spark-platform/badge/star.svg?theme=white)](https://gitee.com/dreamfeng/spark-platform/stargazers)
[![github](https://img.shields.io/github/stars/wangdingfeng/spark-platform)](https://github.com/wangdingfeng/spark-platform)

## 作者这两年沉迷旅游、摄影，所以一直在停更......


SPARK-PLATFORM 开发平台 作者：polaris.wang

 _如果您喜欢，请伸出您的小手，给作者star一下，感谢您的支持_  :smile: 


![Apache](https://img.shields.io/badge/Apache-2.0-brightgreen)
![cloud版本](https://img.shields.io/badge/Spring%20Cloud-2020.0.4-brightgreen)
![boot版本](https://img.shields.io/badge/Spring%20Boot-2.5.6-brightgreen)
![https://img.shields.io/badge/Nacos-1.3-brightgreen](https://img.shields.io/badge/Nacos-2021.1-brightgreen)
![https://img.shields.io/badge/Flowable-6.4.2-brightgreen](https://img.shields.io/badge/Flowable-6.4.2-brightgreen)
![https://img.shields.io/badge/Mybatis%20Plus-3.3.1-brightgreen](https://img.shields.io/badge/Mybatis%20Plus-3.3.1-brightgreen)


- 基于 Spring Cloud、Spring Boot、 OAuth2 的RBAC权限管理系统  
- 基于vue-element-template，更简洁的页面，实现的前后端分离交互
- 微服务和前后端分离架构，客户端和服务端纯Token交互，接口全部使用restful风格
- 认证服务器与资源服务器分离，方便接入自己的微服务系统
- 功能模块采用组件插拔方式，使用更简单整洁规范
- 基于flowable的工作流系统，提供完善基于业务的流程系统
- 提供配置自定义组件包注解。  
- 提供代码生成器、封装Mybatis plus 查询，提高开发效率
- ~~提供微信商城小程序功能，此功能不在master分支展示~~

 ---
#### 如果大家有什么建议或者问题，请在Issues中提交，希望大家一起让这个开源项目变的更好，我的本意是大家一起学习，所以此项目完全开源，完全免费。您的star和建议是作者维护这个项目的最大动力，感谢！
#### 后续更新计划：由于作者精力有限，不会在提供业务代码的更新，会把所有的精力转移到里边的bug修改、底层技术和组件的研究。小程序将会在master分支移除，转移到shop分支，后续也不在提供维护。

---
### 2021-11-09 更新日志
- 升级spring boot和 cloud版本，解决冲突。 
  
- 添加Sentinel 监控。

- 对冗杂的jar包进行删除。 
- 优化配置
---

### Spring Cloud版本升级的遇到的问题汇总:

1. Spring Cloud Netflix 彻底删除掉了除Eureka外的所有组件。
   - 基于 Eureka Client 的 RestTemplate 已支持 TLS 属性  
   - netflix组件替代方案 
     
   |Netflix|推荐替代品|
   | --- | --- |
   |Hystrix|Sentinel、Resilience4j|
   |Hystrix Dashboard / Turbine|Micrometer + Monitoring System|
   |Ribbon|Spring Cloud Loadbalancer|
   |Zuul|Spring Cloud Gateway|
   |Archaius|Spring Boot外部化配置 + Spring Cloud配置|
   
   
2. Bootstrap上下文默认不再启动:  
  Spring Cloud容器是靠Bootstrap Context引导上下文来启动的，对应的类是BootstrapApplicationListener。  
  解决方案:
   - 设置值spring.cloud.bootstrap.enabled=true或者 spring.config.use-legacy-processing=true即可。注意：这些个属性值必须确保其能放进环境里才能生效。比如靠谱的方式是：系统属性、环境变量、命令行等
   - 引入下边的jar包
     
     ```
       <!--bootstrap 启动器-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bootstrap</artifactId>
        </dependency>
     ```
3. 移除了Spring Cloud Security:  
   其主要代码已经移到了 Spring Cloud Commons下
   引入JAR包实例
   ```
      <!--security-->
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-config</artifactId>
        </dependency>
        <!-- oauth-->
        <dependency>
            <groupId>org.springframework.security.oauth.boot</groupId>
            <artifactId>spring-security-oauth2-autoconfigure</artifactId>
        </dependency>
   ```
5. Spring Cloud Openfeign
   - 支持在 Spring Cloud CircuitBreakers 中包装 feign 客户端；
   - Spring Cloud OpenFeign 安全部分的代码，从 Spring Cloud Security 移到了 Spring Cloud OpenFeign。
     
     引入JAR包修改：
      ```
      feign.hystrix.FallbackFactory 替换成 org.springframework.cloud.openfeign.FallbackFactory
      ```
6. 增加了 LoadBalancer 统计功能；
   添加JAR包：
   ```
     <!-- LB 统计 -->
     <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-starter-loadbalancer</artifactId>
     </dependency>
   ```
---

### 文档地址 (补充中)
 [https://www.kancloud.cn/polaris_wang/spark/1762689](https://www.kancloud.cn/polaris_wang/spark/1762689)

### 已实现功能

<table>
    <tr>
        <th colspan="7">系统管理</th>
    </tr>
    <tr>
        <td>用户管理</td>
        <td>角色管理</td>
        <td>角色管理</td>
        <td>菜单管理</td>
        <td>部门管理</td>
        <td>字典管理</td>
        <td>客户端管理</td>
    </tr>
    <tr>
        <th colspan="7">协同管理</th>
    </tr>
    <tr>
        <td>代办事项</td>
        <td>已办事项</td>
        <td>流程设计</td>
        <td>流程管理</td>
        <td colspan="3">实例管理</td>
    </tr>
    <tr>
        <th colspan="7">系统监控</th>
    </tr>
    <tr>
        <td>系统日志</td>
        <td>登陆日志</td>
        <td>接口文档</td>
        <td>Nacos</td>
        <td>Admin监控</td>
        <td>数据库监控</td>
        <td>Minio</td>
    </tr>
    <tr>
        <th colspan="7">扩展功能</th>
    </tr>
    <tr>
        <td>微信凭证</td>
        <td>行政区域</td>
        <td>文件管理</td>
        <td>定时任务</td>
        <td>代码生成</td>
        <td colspan="2">表单例子</td>
    </tr>
</table>

### 项目地址
 平台  | spark-platform（后端）|spark-admin（前端）
---|---|---
GitHub | [https://github.com/wangdingfeng/spark-platform](https://github.com/wangdingfeng/spark-platform)|[https://github.com/wangdingfeng/spark-admin](https://github.com/wangdingfeng/spark-admin)
Gitee  | [https://gitee.com/dreamfeng/spark-platform](https://gitee.com/dreamfeng/spark-platform)|[https://gitee.com/dreamfeng/spark-admin](https://gitee.com/dreamfeng/spark-admin)

小程序地址：https://gitee.com/dreamfeng/wx-spark-shop

### 演示地址

~~演示地址：[http://www.sparkplatform.cn/](http://www.sparkplatform.cn/) 暂停~~

~~备用演示地址: [http://admin.xiapeiyi.com/](http://admin.xiapeiyi.com/) 不允许对里边的数据进行操作，感谢~~

### 演示环境工作流账号密码：

| 账号  | 密码   | 权限               |
| ----- | :----: | --- |
| admin | 123456 | 除删除外所有的权限 |
| zuzhang | 123456 | 工作流权限 |
| zhubian1 | 123456 | 工作流权限 |
| zhubian2 | 123456 | 工作流权限 |

### 平台账号密码

| 平台  | 账号   | 密码               |
| --- | :---: | :---: |
| Admin监控 | spark | spark |
| 数据库监控 | spark | spark |
| Nacos |  |  |
| Minio|  |  |

### 版本说明

|依赖 | 版本|
|---|---
|Spring Boot |  2.5.6 |
|Spring Cloud | 2020.0.4 | 
|Nacos | 2.0.0 |   
|Flowable | 6.4.2 |
|Mybatis Plus | 3.3.1|
|Spring Boot Admin | 2.2.3|

### 模块说明
```
spark-platform 
├── spark-auth -- 授权服务 oauth2
└── spark-common -- 系统公共模块 
     ├── spark-common-base -- 基本公共类
     ├── spark-common-config -- 公共配置类
     ├── spark-common-feigh -- 公共feigh类
     ├── spark-common-datasource -- 数据权限
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
├── spark-cms -- 内容管理平台模块
├── spark-tx-manager -- 分布式事务模块
├── spark-quartz -- 定时任务模块
└── spark-file -- 文件模块
     └── spark-file-api -- 文件模块公共api模块
     └── spark-file-biz -- 文件模块业务处理模块
```

### 注意：
1. 流程测试：
- 这里是列表文本使用admin 账号添加文章，发布，之后，角色是组长的都会接到待办消息。
- 流程流转到主编审核，使用主编1和主编2审核，入口从待办任务中进入。
- 系统判断节点为自动判断，逻辑为 主编审核节点只有当两个主编都审核通过，则为审核流程通过，如果其中有人拒绝，退回到发起人修改，也就是admin
- 发起人admin重新修改数据 提交给组长角色审核，或者关闭当前的流程。
2. 流程测试图
   ![输入图片说明](https://images.gitee.com/uploads/images/2020/0424/102707_2837dc87_1890906.png "屏幕截图.png")


 **平台截图**
![首页](https://images.gitee.com/uploads/images/2020/0808/115033_41517db6_1890906.png "屏幕截图.png")
![用户管理](https://images.gitee.com/uploads/images/2020/0808/115125_1e0193a6_1890906.png "屏幕截图.png")
![角色管理](https://images.gitee.com/uploads/images/2020/0808/115235_a19ed4eb_1890906.png "屏幕截图.png")
![菜单管理](https://images.gitee.com/uploads/images/2020/0502/205952_3dec6a78_1890906.png "屏幕截图.png")
![部门管理](https://images.gitee.com/uploads/images/2020/0808/115426_9a85fb78_1890906.png "屏幕截图.png")
![字典管理](https://images.gitee.com/uploads/images/2020/0808/115515_65e33c4b_1890906.png "屏幕截图.png")
![客户端管理](https://images.gitee.com/uploads/images/2020/0808/115608_15b69477_1890906.png "屏幕截图.png")
![文件管理](https://images.gitee.com/uploads/images/2020/0808/115657_018bfdc2_1890906.png "屏幕截图.png")
![定时任务调度](https://images.gitee.com/uploads/images/2020/0808/121224_09bc9429_1890906.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0502/210204_2518650a_1890906.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/203302_50a1fecd_1890906.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/203457_96a2cf2f_1890906.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/203554_e2af54e1_1890906.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0502/210359_b671c3c6_1890906.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0502/210425_955364bf_1890906.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0502/210633_70f91502_1890906.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0502/210505_dd8d86d8_1890906.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0502/210541_e9f22e3c_1890906.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0809/101850_d13ffd50_1890906.png "屏幕截图.png")
![nacos](https://images.gitee.com/uploads/images/2020/0930/174546_bb42ea24_1890906.png "屏幕截图.png")

 ** :heart: 贡献**
 
 后续开始招收志同道合的仙友一起致力于维护SPARK项目功能的开发和完善，有意愿的小伙伴请私信我。感谢大家的支持！
