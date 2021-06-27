# Spring的事务控制

## 编程式事务控制相关对象

### PlatformTransactionManager平台事务管理器

* TransactionStatus getTransaction(TransactionDefination defination)  获取事务的状态信息
* void commit(TransactionStatus status)  提交事务
* void rollback(TransactionStatus status)  回滚事务

### TranscationDefinition事务定义

* int getIsolationLevel() 获得事务的隔离级别
* int getPropogationBehavior() 获得事务的传播行为
* int getTimeout() 获得超时时间
* boolean isReadOnly() 是否只读

1. 事务隔离级别
   * ISOLATION_DEFAULT
   * ISOLATION_READ_UNCOMMITTED
   * ISOLATION_READ_COMMITTED
   * ISOLATION_REPEATABLE_READ
   * ISOLATION_SERIALIZABLE
2. 事务传播行为
   * REQUIRED：如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。（默认值）
   * SUPPORTS：支持当前事务，如果当前没有事务，就以非事务方式执行（没有事务）。
   * MANDATORY：使用当前的事务，如果当前没有事务就抛出异常。
   * REQUERS_NEW：新建事务，如果当前在事务中，把当前事务挂起。
   * NOT_SUPPORTED：以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
   * NEVER：以非事务方式运行，如果当前存在事务，抛出异常。
   * NESTED：如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行REQUIRED类似的操作。
   * 超时时间：默认值是-1，没有超时限制。如果有，以秒为单位。
   * 是否只读：建议查询时设置为只读

### TransactionStatus 事务状态

* boolean hasSavepoint()：是否储存回滚点
* boolean isCompleted()： 事务是否完成
* boolean isNewTransaction()：是否是新事务
* boolean isRollbackOnly()：事务是否回滚

## 基于XML的声明式事务控制

### 什么是声明式事务控制

* 采用声明的方式去控制事务，这里的声明，指的就是在配置文件中声明。用在Spring配置文件中声明式的处理事务来代替代码式的处理事务。

### 声明式事务处理的作用

* 事务管理不侵入开发的组件。
* 不需要事务管理的时候，只要在设定文件上修改一下，即可移去事务管理服务，无需改变代码重新编译，这样维护方便。
* Spring声明式事务控制底层就是AOP

### 声明式事务控制的实现

* 明确事项
  * 谁是切点？
  * 谁是通知？
  * 配置切面？

``` xml
 <!--通知  事务的增强-->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <!--设置事务的属性信息的-->
        <tx:attributes>
            <tx:method name="transfer" isolation="REPEATABLE_READ" propagation="REQUIRED" read-only="false"/>
            <tx:method name="save" isolation="REPEATABLE_READ" propagation="REQUIRED" read-only="false"/>
            <tx:method name="findAll" isolation="REPEATABLE_READ" propagation="REQUIRED" read-only="true"/>
            <tx:method name="update*" isolation="REPEATABLE_READ" propagation="REQUIRED" read-only="true"/>
            <tx:method name="*"/>
        </tx:attributes>
    </tx:advice>
```

