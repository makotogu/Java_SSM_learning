# MyBatis的Dao层实现

## 传统开发方式

### 编写UserDao接口

## 代理开发方式

### 代理开发方式介绍

* 采用Mybatis的代理开发方式实现Dao层开发，是企业的主流。Mapper接口开发方法只需要程序员编写Mapper接口（相当于Dao接口），由Mybatis框架根据接口定义创建接口的动态代理对象，代理对象的方法同上边Dao接口实现类方法。
* Mapper接口开发需要遵循一下规范：
  1. Mapper.xml文件中的namespace与mapper接口的全限定名相同。
  2. Mapper接口方法名和Mapper.xml中定义的每个statement的id相同。
  3. Mapper接口方法的输入参数类型和mapper.xml中定义的每个sql的parameterType的类型相同。
  4. Mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的requestType的类型相同。

