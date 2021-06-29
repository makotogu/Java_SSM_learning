# Mybatis核心配置文件深入

## typeHandlers标签

* 无论是MyBatis在预处理语句中设置一个参数时，还是从结果集中取出一个值时，都会用类型处理器将获取的值以合适的方式转换成Java类型。下表描述了一些默认的类处理器（截取部分）
  * BooleanTypeHandler
  * ByteTypeHandler
  * ShortTypeHandler
  * IntergerTypeHandler
  * LongTypeHandler

* 可以重写类型处理器或创建自己的类型处理器来处理不支持的或者不标准的类型。具体做法为：实现org.apache.ibatis.type.TypeHandler接口，或继承一个很便利的类org.apache.ibatis.type.BaseTypeHandler,然后可以选择性的将她映射到一个JDBC类型。
* 开发步骤
  1. 定义转换继承类BaseTypeHandler<T>
  2. 覆盖四个为实现的方法，其中setNonNullParameter为java程序设置数据到数据库的回调方法，getNullableResult为查询时mysql的字符串类型转换成java的Type类型的方法
  3. 在Mybatis核心配置文件中进行注册
  4. 测试转换是否正确

## plugins标签

* Mybatis可以使用第三方的插件来对功能进行扩展，分页助手PageHelper是将分页的复杂操作进行封装，使用简单的方式即可获得分页的相关数据。
* 开发步骤
  1. 导入通用的PageHelper插件
  2. 在Mybatis核心配置文件中配置PageHelper插件
  3. 测试分页数据获取

