# SpringMVC拦截器

## 拦截器的作用

* SpringMVC拦截器类似于Servlet开发中的过滤器Filter，用于对处理器进行预处理和后处理。
* 将拦截器按照一定的顺序连接成为一条链，这条链被成为拦截器链(Interceptoe Chain)。在访问被拦截的方法或字段时，拦截器链中的拦截器就会按照其之前定义的顺序被调用。拦截器也是AOP思想的具体实现。

## 拦截器和过滤器的区别

* 拦截器
  * 适用范围
    * 是SpringMVC框架自己的，只有使用了SpringMVC框架的工程才能使用
  * 拦截范围
    * 只会拦截访问的控制器方法，如果访问的是jsp、html、css、image、js是不会进行拦截的
* 过滤器
  * 适用范围
    * 是servlet规范中的一部分，任何JavaWeb工程都可以用
  * 拦截访问
    * 在url-pattern中配置了/*后可以对所有要访问的资源拦截。

## 拦截器的快速入门

* 自定义拦截器
  1. 创建拦截器类实现HandlerInterceptor接口
  2. 配置拦截器
  3. 测试拦截器的拦截效果

## 拦截器方法说明

1. preHandle() ，在请求前处理
2. postHandle()，在请求后，视图前处理
3. afterCompletion()，在全部完成后

