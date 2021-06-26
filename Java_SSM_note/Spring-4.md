# SpringMVC的数据响应

## SpringMVC的数据响应方式

1. 页面跳转
   * 直接返回字符串
   * 通过ModelAndView对象返回
2. 回写数据
   * 直接返回字符串
   * 返回对象或集合

## 页面跳转

### 返回字符串形式

直接返回字符串：此种方式会将返回的字符串与视图解析器的前后缀拼接后跳转

* 转发资源地址：/WEB-INF/VIEWS/index.jsp
* 转发：forward：/WEB-INF/VIEWS/index.jsp
* 重定向：redirect：/index.jsp

### 返回ModelAndView对象

## 回写数据

### 直接返回字符串

* 将需要回写的字符串直接返回，但此时需要通过@ResponseBody注解告知SpringMVC框架，方法返回的字符串不是跳转，是直接在http响应体中返回。

### 返回对象或集合

* 在方法上添加@ResponseBody就可以返回json格式的字符串，但是这样配置比较麻烦，配置的代码比较多，因此，我们可以使用mvc的注解驱动代替上述配置

  ``` xml
  <mvc:annotation-driven/>
  ```

# SpringMVC获得请求数据

## 获得请求参数

* 客户端请求参数的格式是：name=value&name=value... ...

* 服务器端要获得请求的参数，优势还需要进行数据的封装，SpringMVC可以接受如下类型的参数：
  * 基本类型参数
  * POJO类型参数
  * 数组类型参数
  * 集合类型参数

## 获得基本类型参数

* Controller中的业务方法的参数名称要与请求的name一致，参数值会自动映射匹配

## 获得集合类型参数

* 获得几何参数时，要将集合参数包装到一个POJO中才可以
* 当时用AJAX提交时，可以指定contentType为json形式，那么在方法参数位置使用@RequestBody可以直接接收结合数据而无需使用POJO包装

## 请求数据乱码问题

```xml
<!--配置全局过滤的filter-->
    <filter>
        <filter-name>CharacterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>CharacterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```

## 参数绑定注解@RequestParam

* 当请求的参数名称与Controller的业务方法参数名称不一致时，就需要通过@RequestParam注解显示的绑定
  * value：与请求参数名称
  * required：指定的请求参数是否必须包含，默认是true，提交时没有此参数则报错
  * defaultValue：当没有指定请求参数时，则使用指定的默认值赋值

## 获得RestFul风格的参数

* RestFul是一种软件*架构风格*、*设计风格*，而不是标准，只是提供了一组设计原则和约束条件。主要用于客户端和服务器交互类的软件，基于这个风格设计的软件可以更简洁、更有层次、更易于实现缓存机制等。
* RestFul风格的请求是使用“url+请求方式”表示一次请求目的的，HTTP协议里面四个表示操作方式的动词如下：
  * GET: 用于获取资源
  * POST: 用于新建资源
  * PUT: 用于更新资源
  * DELETE: 用于删除资源

* 如url地址/user/1的1就是要获得的请求参数，在SpringMVC中可以使用占位符进行参数绑定。地址可以写为/user/{id}，占位符{id}对应的就是1的值。在业务方法中，可以使用@PathVariable注解进行占位符的匹配操作

## 自定义类型转换器

* SpringMVC默认已经提供了一些常用的类型转换器例如客户端提交的字符串转换成int类型进行参数设置
* 但是不是所有的数据类型都提供了转换器，没有提供的就需要自定义转换器，例如：日期类型的数据就需要自定义转换器

自定义类型转换器的开发步骤

* 定义转换器类实现Converter接口
* 在配置文件中声明转换器
* 在<annotation-driven>中引用转换器

## 获得Servlet相关API

* SpringMVC支持使用原始的ServletAPI对象作为控制器方法的参数进行注入，常用的对象如下:
  * HttpServletRequest
  * HttpServletResponse
  * HttpSession

## 获得请求头

1. @RequestHeader
   * 使用@RequestHeader可以获得请求头信息，相当于web阶段学习的request.getHeader(name)
   * @RequestHeader注解的属性如下
     * value：请求头的名称
     * required：是否必须携带此请求头
2. @CookieValue
   * 使用@CookieValue可以获得指定Cookie的值
   * @CookieValue的注解属性如下：
     * value：指定cookie的名称
     * required：是否必须携带此cookie

## 文件上传

1. 文件上传客户端三要素
   * 表单项 type="file"
   * 表单的提交方式是post
   * 表单的enctype属性是多部分表单形式，即enctype="multipart/form-data"
2. 文件上传原理
   * 当form表单修改为多部分表单时，request.getParameter()将失效
   * enctype="application/x-www-form-ur;encoded"时，form表单的正文内容是：key=value & key=value & key=value
   * 当form表单的enctype取值为Mutilpart/form-data时，请求正文内容就变成多部分形式
3. 单文件上传步骤
   1. 导入fileupload和io坐标
   2. 配置文件上传解析器
   3. 编写文件上传代码

4. 多文件上传步骤（与单文件基本相同）
