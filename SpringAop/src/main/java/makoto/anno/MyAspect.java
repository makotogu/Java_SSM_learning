package makoto.anno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component("myAspect")
@Aspect //标注当前MyAspect是一个切面类
public class MyAspect {

    @Pointcut("execution(* makoto.anno.*.*(..))")
    public void myPointcut() {

    }

    //配置前置增强
    @Before("execution(* makoto.anno.*.*(..))")
    public void before() {
        System.out.println("前置增强");
    }

    @AfterReturning("execution(* makoto.anno.*.*(..))")
    public void afterReturning() {
        System.out.println("后置增强");
    }


    @Around("execution(* makoto.anno.*.*(..))")
    //Proceeding JoinPoint:正在执行的连接点
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕前增强");
        Object proceed = proceedingJoinPoint.proceed();//切点方法
        System.out.println("环绕后增强");
        return proceed;
    }

    @AfterThrowing("execution(* makoto.anno.*.*(..))")
    public void afterThrowing() {
        System.out.println("异常增强");
    }

    @After("myPointcut()")
    public void after() {
        System.out.println("最终增强");
    }
}
