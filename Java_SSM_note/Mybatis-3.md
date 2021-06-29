# MyBatis映射文件深入

## 动态sql语句

### 动态sql语句概述

* Mybatis的映射文件中，前面我们SQL是比较简单的，有时候业务逻辑复杂时，我们的sql是动态变化的。

``` xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="makoto.mapper.UserMapper">

    <select id="findByCondition" parameterType="user" resultType="user">
        select * from user
        <where>
            <if test="id!=0">
                and id = #{id}
            </if>
            <if test="username != null">
                and username = #{username}
            </if>
            <if test="password != null">
                and password = #{password}
            </if>
        </where>

    </select>

</mapper>
```

``` xml
<select id="findByIds" parameterType="list" resultType="user">
    select * from user
    <where>
        <foreach collection="list" open="id in (" close=")" item="id" separator="," >
            #{id}
        </foreach>
    </where>
</select>

```

## SQL片段抽取

``` xml
    <!--sql语句抽取-->
    <sql id="selectUser">select * from user</sql>
```

