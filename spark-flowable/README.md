 ** _由于现在还没有完整的例子测试工作流，需要使用接口进行测试：** 
### 流程图

![输入图片说明](https://images.gitee.com/uploads/images/2020/0424/102707_2837dc87_1890906.png "屏幕截图.png")
### 参与用户
| 用户                | 承担任务               |
| ------------------- | ---------------------- |
| admin或者其他的用户 | 发起流程，退户修改节点 |
| zuzhang             | 组长审核节点           |
| zhubian1            | 主编1审核              |
| zhubian2            | 主编2审核              |

### 接口测试请访问 http://localhost:9001/swagger-ui.html
1. 流程发起

http://localhost:9001/flow/runtime/process-instances?businessKey=20200423001&businessName=%E6%B5%8B%E8%AF%95&businessType=PROCESS_ARTICLE&key=audit-article

![输入图片说明](https://images.gitee.com/uploads/images/2020/0424/102908_120935e2_1890906.png "屏幕截图.png")

2. 获取用户的代办任务

http://localhost:9001/flow/runtime/tasks
查询参数：

![输入图片说明](https://images.gitee.com/uploads/images/2020/0424/104422_d96f2562_1890906.png "屏幕截图.png")
请务必传入用户组

3. 组长审核节点：

由于此节点是组审核节点，所以需要先签收在完成任务：

签收：

http://localhost:9001/flow/runtime/tasks/你的任务id?action=complete&localScope=false
![输入图片说明](https://images.gitee.com/uploads/images/2020/0424/103250_1b50c02c_1890906.png "屏幕截图.png")

完成任务：

http://localhost:9001/flow/runtime/tasks/你的任务id?action=claim&localScope=false

流程中参数：

{"GROUP_LEADER_APPROVE_SUBMIT_VALUE","GROUP_EDITOR","group_editor1":"spark","group_editor2":"test"}

![输入图片说明](https://images.gitee.com/uploads/images/2020/0424/103319_7f897375_1890906.png "屏幕截图.png")

4. 主编审核（只有当两个主编的审核结果都是true的时候流程才会结束，否则流转到退回修改节点）

主编1审核:

http://localhost:9001/flow/runtime/tasks/你的任务id?action=complete&localScope=false

传入的主编1审核结论参数：

{"multiInstance_result_1":true}

主编2审核:

http://localhost:9001/flow/runtime/tasks/你的任务id?action=complete&localScope=false

传入的主编2审核结论参数：

{"multiInstance_result_1":false}

5. 系统判断节点

自动审核节点，不需要任何操作，具体实现的逻辑请参考我的博客：
https://blog.csdn.net/wangdingfeng5141/article/details/105724437

6. 退回修改节点

http://localhost:9001/flow/runtime/tasks/你的任务id?action=complete&localScope=false

流程参数：

{"SUBMIT_APPROVAL_SUBMIT_VALUE":"GROUP_LEADER_APPROVAL"}

如果是关闭流程参数设置为
{"SUBMIT_APPROVAL_SUBMIT_VALUE":"over"}

![输入图片说明](https://images.gitee.com/uploads/images/2020/0424/103950_24c29215_1890906.png "屏幕截图.png")