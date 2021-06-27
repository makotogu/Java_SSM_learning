package makoto;

import makoto.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    @Test
    public void test1() throws IOException {
        //获得核心文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session回话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作 参数：namespace+id
        List<User> userList = sqlSession.selectList("userMapper.findAll");
        //打印数据
        System.out.println(userList);
        //释放资源
        sqlSession.close();
    }

    @Test
    public void test2() throws IOException {
        User user = new User();
        user.setUsername("lisi");
        user.setPassword("123");

        //获得核心文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session回话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作 参数：namespace+id
        sqlSession.insert("userMapper.insert",user);
        //mybatis执行更新操作 提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Test
    public void test3() throws IOException {
        User user = new User();
        user.setId(2);
        user.setUsername("lisi");
        user.setPassword("1234");

        //获得核心文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session回话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作 参数：namespace+id
        sqlSession.update("userMapper.update",user);
        //mybatis执行更新操作 提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Test
    public void test4() throws IOException {
       //User user = new User();
       //user.setId(2);
       //user.setUsername("lisi");
       //user.setPassword("1234");

        //获得核心文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session回话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作 参数：namespace+id
        sqlSession.delete("userMapper.delete",2);
        //mybatis执行更新操作 提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }
}
