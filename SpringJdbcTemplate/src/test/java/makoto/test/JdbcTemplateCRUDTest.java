package makoto.test;

import makoto.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcTemplateCRUDTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testUpdate() {
        jdbcTemplate.update("update account set money = ? where name = ? ",1000, "tom");
    }

    @Test
    public void testDelete() {
        jdbcTemplate.update("delete from account where name = ?", "tom");
    }

    @Test
    public void testCreate() {
        jdbcTemplate.update("insert account values (? , ?)", "tom", "100");
    }

    @Test
    public void testQueryAll() {
        List<Account> accounts = jdbcTemplate.query("select * from account", new BeanPropertyRowMapper<Account>(Account.class));
        System.out.println(accounts);
    }

    @Test
    public void testQueryOne() {
        Account tom = jdbcTemplate.queryForObject("select * from account where name = ?", new BeanPropertyRowMapper<Account>(Account.class), "tom");
        System.out.println(tom);
    }

    @Test
    public void testCount() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from account where money < ?", Integer.class, 1000);
        System.out.println(count);
    }
}
