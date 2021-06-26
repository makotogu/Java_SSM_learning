package makoto.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyAspect {

    public void before() {
        System.out.println("前置增强");
    }

    public void afterReturning() {
        System.out.println("后置增强");
    }

    //Proceeding JoinPoint:正在执行的连接点
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕前增强");
        Object proceed = proceedingJoinPoint.proceed();//切点方法
        System.out.println("环绕后增强");
        return proceed;
    }

    public void afterThrowing() {
        System.out.println("异常增强");
    }

    public void after() {
        System.out.println("最终增强");
    }
}
