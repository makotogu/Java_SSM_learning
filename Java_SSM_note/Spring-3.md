# Spring与web环境集成

## ApplicationContext应用上下文获取方式

在web项目中，可以使用ServletContextListener监听Web应用的启动，我们可以在web启动时，就加载Spring配合文件。

## Spring提供获取应用上下文的工具ContextLoaderListener



# SpringMVC 简介

## SpringMVC概述

* 是一种基于Java的实现MVC设计模型的请求驱动类型的轻量级Web框架，属于SpringFrameWork的后续产品，已经融合在Spring Web Flow中。

## SpringMVC开发步骤

* 客户端发起请求，服务器接受请求，执行逻辑并进行视图跳转

1. 导入SpringMVC相关坐标
2. 配置SpringMVC核心控制器DispathcerServlet
3. 创建Controller类和视图页面
4. 使用注解配置Controller类中业务方法的映射地址
5. 配置SpringMVC核心文件spring-mvc.xml
6. 客户端发起请求测试

## SpringMVC的执行流程

1. 用户发送请求到前端控制器DispatcherServlet
2. DispatcherServlet收到请求调用HandlerMapping处理器映射器
3. 处理器映射器找到具体的处理器（可以根据xml配置，注解进行查找），生成处理器对象及处理器拦截器（如果有则生成）一并返回给DispatcherServlet
4. DispatcherServlet调用HandlerAdpater处理器适配器
5. HandlerAdapter经过适配调用具体的处理器（Controller，也叫后端控制器）
6. Controller执行完成返回ModelAndView
7. HandlerAdapter将controller执行结果ModelAndView返回给DispatcherServlet
8. DispatcherServlet将ModelAndView传给ViewReslover视图解析器
9. ViewReslover解析后返回具体View
10. DispatcherServlet根据View进行渲染视图(即将模型数据填充至视图中)。DispatcherServlet响应用户

## SpringMVC注解解析

@RequestMapping

* 用于建立请求URL和处理请求之间的对应关系
  * 位置：
    * 类上，请求URL的第一级访问目录。此处不写的话，就相当于应用的根目录
    * 方法上，请求URL的第二集访问目录，与类上使用@RequestMapping标注的一级目录一起组成访问虚拟路径
    * value：用于请求指定的URL，和path属性的作用是一样的
    * method：用于指定请求的方式
    * params：用于指定限制请求参数的条件，他支持简单的表达式，要求请求参数的key和value必须和配置一模一样
    * params = {"accountName"} 表示请求必须有accountName
    * params = {"money!100"} 表示请求中money不能是100

## SpringMVC的相关组件

* 前端控制器：DispatcherServlet
* 处理器映射器：HandlerMapping
* 处理器适配器：HandlerAdapter
* 处理器：Handler
* 视图解析器：ViewResolver
* 视图：View

## SpringMVC的注解和配置

* 请求映射注解：@RequestMapping

* 视图解析器配置

  *REDIRECT_URL_PREFIX = "redirect"*

  *FORWARD_URL_PREFIX* = "forward"

  *prefix = "";*

  *suffix = "";*
