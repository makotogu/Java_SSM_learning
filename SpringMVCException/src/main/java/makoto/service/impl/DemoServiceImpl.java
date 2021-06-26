package makoto.service.impl;

import makoto.exception.MyException;
import makoto.service.DemoService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public void show1() {
        System.out.println("输出类型转换异常");
        Object str = "zhangsan";
        Integer num = (Integer) str;
    }

    @Override
    public void show2() {
        System.out.println("抛出除零异常");
        int i = 1/0;
    }

    @Override
    public void show3() throws FileNotFoundException {
        System.out.println("文件找不到异常");
        File file;
        InputStream in = new FileInputStream("C:/XXX/X/X/X.TXT");
    }

    @Override
    public void show4() {
        System.out.println("空指针异常");
        String str = null;
        str.length();
    }

    @Override
    public void show5() throws MyException {
        System.out.println("自定义异常");
        throw new MyException();
    }
}
