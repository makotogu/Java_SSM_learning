# 	Spring配置数据源

## 数据源（连接池）的作用

* 提高程序性能
* 实例化数据源，初始化连接资源
* 使用连接资源时从数据源中获取
* 使用完毕后将链接资源归还给数据源
* 常见数据源DBCP, C3P0, BoneCP, Druid等

## 数据源的开发步骤

1. 导入数据源坐标和数据库驱动坐标
2. 创建数据源对象
3. 设置数据源的基本连接数据

## 数据源的手动创建

## Spring配置数据源

## 抽取jdbc配置文件

* 命名空间

  *xmlns:context="http://www.springframework.org/schema/context"*

* 约束路径

  *http://springframework.org/schema/context*

  *http://www.springframework.org/schema.context/spring-context.xsd*

# Spring 注解开发

## Spring原始注解

* Spring原始注解主要是替代<Bean>的配置
* @Component：使用在类上用于实例化Bean
* @Controller：使用在web层类上用于实例化Bean
* @Service：使用在service层类上用于实例化Bean
* @Repository：使用在dao层类上用于实例化Bean
* @Autowired：使用在字段上用于根据类型依赖注入
* @Qualifier：结合@Autowired一起使用用于根据名称进行依赖注入
* @Resource：相当于@Autowired+@Qualifier，按照名称进行注入
* @Value：注入普通属性
* @Scope：标注Bean的作用范围
* @PostConstruct：使用在方法上标注该方法是Bean的初始化方法
* @PreDestroy：使用在方法上标注该方法是Bean的销毁方法

### 使用注解进行开发的时候，需要在applicationContext.xml中配置组件扫描，作用是指定哪个包以及其子包下的Bean需要进行扫描以便识别使用注解配置的类、字段、方法。

##  新注解

* @Configuration：用于指定当前类是一个Spring配置类，当创建容器时会从该类上加载注解
* @ComponentScan：用于指定Spring在初始化容器时所要扫描的包。作用和在Spring的xml配置文件中的*<context:component-scan base-package="com.xxxx"/>*一样
* @Bean：是用在方法上，标注将该方法的返回值存储到Spring中
* @PropertySource：用于加载.properties文件中的配置
* @Import：用于导入其他配置类

# Spring集成Junit

* 让SpringJunit负责创建Spring容器，但是需要将配置文件的名称告诉它
* 将需要进行测试Bean直接在测试类中进行注入

## 步骤

1. 导入spring集成Junit的坐标
2. 使用@Runwith注解替换原来的运行期
3. 使用@ContextConfiguration指定配置文件或配置类
4. 使用@Autowired注入需要测试的对象
5. 创建测试方法进行测试

