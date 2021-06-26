# SpringMVC异常处理

## 异常处理的两种方式

* 使用SpringMVC提供的简单异常处理器SimpleMappingExceptionResolver
* 实现Spring的异常处理接口HandlerExceptionResolver自己定义的异常处理器

## 自定义异常处理步骤

1. 创建异常处理器类实现HandlerExceptionResolver
2. 配置异常处理器
3. 编写异常界面
4. 测试异常跳转