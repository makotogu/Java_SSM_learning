package makoto.aop.jdk;

import makoto.aop.Target;
import makoto.aop.TargetInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void main(String[] args) {

        //创建目标对象
        final Target target = new Target();

        //获得增强对象
        Advice advice = new Advice();

        //返回值就是动态生成的代理对象
        TargetInterface proxy = (TargetInterface) Proxy.newProxyInstance(
                //目标对象类加载器
                target.getClass().getClassLoader(),

                //目标对象相同的接口字节码对象数组
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    //调用代理对象的任何方法 实质是执行的都是invoke方法
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        advice.before();//前置增强
                        Object invoke = method.invoke(target, args);//执行目标方法
                        advice.afterReturning();//后置增强
                        return invoke;
                    }
                }
        );

        //调用代理对象的方法
        proxy.save();

    }
}
