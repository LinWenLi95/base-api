# base-api 一个基础的api服务项目
### base-dependencies:统一Maven依赖版本管理
### base-common:公共工具类/实体类
### base-project:api服务项目
* 添加代码生成插件依赖及配置
* logback配置
* 添加mysql/mybatis相关依赖及配置
* 添加Spring Security + JWT相关依赖及配置
* 添加Spring Security异常统一响应数据格式
* Controller异常处理统一响应数据格式
* api输入输出日志打印
* 添加redis相关依赖及配置
* redis key统一管理
* 添加redis工具类
* 统一api参数校验
* 添加knife4j

待处理: 
* 使用mybatisplus代码生成器
* 写一个对应的代码模板
https://blog.csdn.net/magician_Code/article/details/102950173
* 从jwt中获取username供请求方法中使用
* 整合mybatisplus并调整模板
* 整理前端页面项目，按钮级权限|登录|常用列表查询|表单提交|权限管理等
* 线程池创建
* 邮件发送服务

* 动态导入权限表数据
```java
@Api(value = "接口类名称",tags = "接口类名称")
@ApiOperation(value = "接口名称",notes = "接口描述")
```
* 日常所需工具类整理
    JSONObject取出多级下的数据
    字符串驼峰下划线互转

controller功能：
* 匹配请求（设置请求方式/uri）
* 接收入参
* 校验入参（校验非空及是否符合指定格式）
* 调用相关service方法，传入参数
* 接收并返回响应结果实体
* 不编写任何业务逻辑代码

service功能：
service方法分为返回结果数据的和返回响应结果实体的
* 返回结果数据的方法
    * 由service调用
    * 处理业务逻辑
        * 编写业务逻辑实现代码
        * 调用其他service方法
        * 调用dao方法
* 返回响应结果实体的方法
    * 由controller调用
    * 处理业务逻辑
        * 编写业务逻辑实现代码
        * 调用其他service方法
        * 调用dao方法
    * 组装响应结果

在git bash命令行设置显示的用户名及邮箱
git config --global user.name "usernamestr"
git config --global user.email "**@qq.com"
显示配置详情
git config -l

```java
@PostMapping("/")
public Result<Object> add(@Valid @RequestBody SysUser obj) {

}
```
postman发送请求要让@RequestBody后的对象接收到参数，在发送请求时的`Content-Type`使用`application/json;charset=utf-8`
参数必须放在body的raw中，不能放在form-data或x-www-form-urlencoded中，否则会取不到数据

GET方式的接口接收参数使用@RequestParam，因为无法将参数放在请求体中
@RequestParam接收参数可按数据类型接收单个
POST/PUT/DELETE接收参数使用@RequestBody
@RequestBody传入的是json数据，可以自动解析成对象数据

