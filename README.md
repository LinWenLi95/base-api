# base-api 一个基础的api服务项目
### base-dependencies:统一Maven依赖版本管理
### base-common:公共工具类/实体类
### base-project:api服务项目
* 添加代码生成插件依赖及配置
* logback配置
* 添加mysql/mybatis相关依赖及配置
* 添加Spring Security + JWT相关依赖及配置
* 全局异常处理
* api输入输出日志打印
* 添加redis相关依赖及配置
* redis key统一管理
* 添加redis工具类

待处理: 
* 统一api参数校验
* 添加knife4j
* 动态导入权限表数据
* 添加redis相关依赖及配置
* redis key统一管理
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
    * 组装响应结果（是否要放到service中组装？）

在git bash命令行设置显示的用户名及邮箱
git config --global user.name "usernamestr"
git config --global user.email "**@qq.com"
显示配置详情
git config -l
