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

### 插入操作注意问题

* 插入语句使用insert标签
* 在映射文件中使用parameterType属性指定要插入的数据类型
* Sql语句中使用#{实体属性名}方式引用实体中的属性值
* 插入操作使用的API是sqlSession.insert(“命名空间.id”，实体对象)；
* 插入操作涉及数据库数据变化，所以要使用sqlSession对象显示的提交事务即sqlSession.commit()

### 修改操作注意问题

* 修改语句使用update标签
* 修改操作使用的API是sqlSession.update("命名空间.id"，实体对象);

### 删除操作注意问题

* 删除语句使用delete标签
* Sql语句中使用#{任意字符串}方式引用传递的单个参数
* 删除操作使用的是API是sqlSession.delete("命名空间.id"，Object)；

