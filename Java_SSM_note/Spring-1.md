# Spring 简介

## Spring是什么

Spring是分层的Java SE/EE应用full-stack轻量级开源框架。以IoC（inverse of control：反向控制）和AOP（Aspect Oriented Programing ：面向切面编程）为内核

提供了展现层SpringMVC和持久层SpringJDBCTemplate以及业务层事务管理等众多的企业级应用技术。

## Spring的优势

1. 方便解耦、简化开发
2. AOP编程的支持
3. 声明式事物的支持
4. 方便程序的测试
5. 方便集成各种框架
6. 降低JavaEE API的使用难度

## Spring的体系结构



# Spring快速入门

## Spring开发步骤

1. 导入spring的maven坐标
2. 编写Dao接口和实现类
3. 创建Spring核心配置文件
4. 将User的Impl配置到配置文件
5. 通过Spring的getBean方法获得对象



# Spring配置文件

## Bean标签的基本配置

默认情况下它调用的是类中的无参构造函数

* **基本属性**：
*  ***id***： bean实例在Spring中的唯一标识
* ***class***：bean的全限定名称

## Bean标签范围配置

### ***scope***：指对象的的作用范围，取值如下：

*singleton*：默认值，单例的

*prototype*：多例的

request： web项目中，Spring创建一个Bean对象，将对象存入到request域中

session：web项目中，Spring创建一个Bean对象，将对象存入到session域中

global session：web项目中，应用在Portlet环境，如果没有Portlet环境那么globalSession相当于session

### 当scope的取值为singleton时

* Bean的实例化个数：1个
* Bean的实例化时机：当Spring核心文件加载时，实例化配置的Bean实例
* Bean的生命周期：
  * 对象创建：当应用加载，创建容器时，对象就被创建了
  * 对象运行：只要容器在，对象一直活着
  * 对象销毁：当应用卸载，销毁容器时，对象就销毁了

### 当scope的取值为prototype时

* Bean的实例化个数：多个
* Bean的实例化时机：当调用getBean()方法时实例化Bean
* Bean的生命周期：
  * 对象创建：当使用对象时，创建新的对象实例
  * 对象运行：只要对象在使用中，就一直活着
  * 对象销毁：当对象长时间不使用，被java的垃圾回收器回收了

## Bean生命周期配置

* init-method:初始化方法
* destroy-method:销毁方法

## Bean实例化三种方式

* 无参构造方法实例化
* 工厂静态方法实例化
* 工厂实例方法实例化

## Bean的依赖注入分析

* 因为UserService和UserDao都在Spring容器中，而最终程序直接使用的是UserService，所以可以在Spring容器中，将UserDao设置到UserService内部。

### 依赖注入(Dependency Injection)

* 是Spring框架核心IOC的具体实现

### 依赖注入方式

* 构造方法
* set方法

#### p命名空间 set方式注入

1. 首先引入p命名空间

```xml
<beans xmlns:p="http://www.springframework.org/schema/p"></beans>
```

2. 其次，需要修改注入方式

``` xml
<bean id="UserService" class="com.makoto.service.impl.UserServiceImpl" p:userDao-ref="UserDao"></bean>
```



## Bean的依赖注入的数据类型

* 普通数据类型
* 引用数据类型
* 集合数据类型

## 引入其他配置文件

实际开发中可以将配置文件拆解到其他的配置文件中，在Spring主配置文件中通过import标签进行加载

``` xml
<import resource="applicationContext-xxx.xml"></import>
```

### Spring的重点配置

*<bean>* 标签

* id属性：在容器中Bean实例的唯一标识，不允许重复

* class属性：要实例化的Bean的全限定名

* scope属性：Bean的作用范围，常用的是singleton和propotype

  *<property>*标签：属性注入

  * name属性：属性名称

  * value属性：注入的普通属性值

  * ref属性：注入的对象引用值

    *<ref>*标签

    *<map>*标签

    *<properties>*标签

  *<constructor-arg>*标签

*<import>*标签：导入其他的Spring的分文件

# Spring相关API

## ApplicationContext的继承关系

* applicationContext: 接口类型，代表应用上下文，可以通过其实例获得Spring容器的Bean对象

## ApplicationContext的实现类

* ClassPathXmlApplicationContext
  * 他是从类的根路径下加载配置文件 推荐使用
* FileSystemXmlApplicationContext
  * 它是从磁盘路径上加载配置文件，配置文件可以在磁盘的任意位置
* AnnotationConfigApplicationContext
  * 使用注解配置容器对象，需要使用此类来创建Spring容器，它用来读取注解

## getBean()方法使用

``` java
ApplicationContext app = new ClasspathXmlApplicationContext("xxx.xml");
app.getBean("id");
app.getBean(Class);
```

