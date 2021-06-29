# Mybatis简介

## 原始jdbc操作（插入数据）

1. 模拟实体对象 （domain对象set）
2. 注册驱动
3. 获得链接
4. 获得statement
5. 设置占位符参数
6. 执行更新操作
7. 释放资源

## 原始jdbc操作分析

* 存在的问题如下：
  1. 数据库连接创造、释放频繁造成系统资源浪费从而影响系统性能。
  2. sql语句在代码中硬编码，造成代码不易维护，实际应用sql变化的可能较大，sql变动需要改动java代码
  3. 查询操作时，需要手动将结果集中的数据手动封装到实体中。插入操作时，需要手动将实体的数据设置到sql语句的占位符位置
* 应对上述问题给出的解决方案
  1. 使用数据库连接池初始化链接资源
  2. 将sql语句抽取到xml配置文件中
  3. 使用反射、内省等底层技术，自动将实体与表进行属性与字段的自动映射。

## 什么是Mybatis

* MyBatis 是一款优秀的持久层框架，它支持自定义 SQL、存储过程以及高级映射。MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作。MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。

# Mybatis快速入门

## Mybatis开发步骤

1. 添加Mybatis的坐标
2. 创建user数据表
3. 编写User实体类
4. 编写映射文件UserMapper.xml
5. 编写核心文件SqlMapConfig.xml
6. 编写测试类

# Mybatis的映射文件概述

```xml
<!--映射文件DTD约束头-->
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="userMapper">

    <select id="findAll" resultType="makoto.domain.User">
        select * from user
    </select>

</mapper>
```

# Mybatis的增删查改操作

## 插入操作注意问题

* 插入语句使用insert标签
* 在映射文件中使用parameterType属性指定要插入的数据类型
* Sql语句中使用#{实体属性名}方式引用实体中的属性值
* 插入操作使用的API是sqlSession.insert(“命名空间.id”，实体对象)；
* 插入操作涉及数据库数据变化，所以要使用sqlSession对象显示的提交事务即sqlSession.commit()

## 修改操作注意问题

* 修改语句使用update标签
* 修改操作使用的API是sqlSession.update("命名空间.id"，实体对象);

## 删除操作注意问题

* 删除语句使用delete标签
* Sql语句中使用#{任意字符串}方式引用传递的单个参数
* 删除操作使用的是API是sqlSession.delete("命名空间.id"，Object)；

# Mybatis核心配置文件概述

``` xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <!--数据源环境-->
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"></transactionManager>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/test"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>

    <!--加载映射文件-->
    <mappers>
        <mapper resource="makoto/mapper/UserMapper.xml"></mapper>
    </mappers>
</configuration>
```

## MyBatis常用配置解析

### environments标签

* 其中事务管理器(transactionManager)类型有两种
  * JDBC: 这个配置就是直接使用了JDBC的提交和回滚设置，它依赖于从数据源的到的连接来管理事务作用域
  * MANAGED: 这个配置几乎没做什么。它从来不提交或回滚一个连接，而是让容器来管理事务的整个生命周期（比如JEE应用服务器的上下文。）默认情况下它会关闭连接，然而一些容器并不希望这样，因此需要将closeConnection属性设置为false来阻止它默认的关闭行为
* 其中，数据源(dataSource)类型有三种
  * UNPOOLED: 这个数据源的实现知识每次被请求时打开和关闭连接
  * POOLED: 这种数据源利用“池”的概念将JDBC连接对象组织起来
  * JNDI: 这个数据源的实现是为了能在如EJB或应用服务器这类容器中，容器可以集中或在外部配置数据源，然后放置一个JNDI上下文的引用

### mappers标签

* 该标签的作用是加载映射的，加载方式有如下几种
  * 使用相对类路径的资源引用，例如：\<mapper resource="org/mybatis/builder/AuthorMapper.xml"/>
  * 使用完全限定资源定位符（URL），例如：\<mapper url="file:///var/mappers/AuthorMapper.xml"/>
  * 使用映射器接口实现类的完全限定类名，例如：\<mapper class="org.mybatis.builder.AuthorMapper"/>
  * 将包内的映射器接口实现全部注册为映射器，例如：\<package name="org.mybatis.builder"/>

### properties标签

* 实际开发中，习惯将数据源的配置信息单独抽取为一个properties文件，改标签可以加载额外的properties文件

### typeAliases标签

* 类别别名是为Java类型设置一个短的名字，配置typeAliases，为makoto.domain.User定义别名为user

  ``` xml
  <typeAliases>
      <typeAlias type="makoto.domain.User" alias="user"></typeAlias>
  </typeAliases>
  ```


## Mybatis 相应API

### sqlSession工厂构建器SqlSessionFactoryBuilder

* 常用API：SqlSessionFactory build(InputStream inputStream)

* 通过加载mybatis的核心文件的输入流的形式构建一个SqlSessionFactory对象

  ```java
  String resources = "org/mybatis/builder/mybatis-config.xml";
  InputStream inputStream = Resources.getResourcesAsStream(resource);
  SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
  SqlSessionFactory factory = builder.build(inputStream);
  ```

### SqlSession工厂对象SqlSwssionFactory

* 常用的方法有：
  * openSession():会默认开启一个事务，但事务不会自动提交，也就是意味着需要手动进行事务提交，更新操作数据才会持久化到数据库中。
  * openSession(boolean autoCommit):参数为是否自动提交，如果设置为true，那么不需要手动提交事务。

### SqlSession会话对象

* SqlSession实例在Mybatis中是一个非常强大的类，在这里会看到所有的执行语句。提交或回滚事务和获取映射器实例方法。