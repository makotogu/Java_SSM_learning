package makoto.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DateTypeHandler extends BaseTypeHandler<Date> {

    /**
     * 将java类型 转换成 数据库需要的类型
     * @param preparedStatement
     * @param i
     * @param date
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        long time = date.getTime();
        preparedStatement.setLong(i,time);
    }

    /**
     * 将数据库中的类型 转换成 java类型
     * @param resultSet 查询结果集
     * @param s 数据库中要转换的字段的名称
     * @return
     * @throws SQLException
     */
    @Override
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
        //获得结果集中需要的数据
        long aLong = resultSet.getLong(s);
        Date date = new Date(aLong);
        return date;
    }

    /**
     * 将数据库中的类型 转换成 java类型
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        //获得结果集中需要的数据
        long aLong = resultSet.getLong(i);
        Date date = new Date(aLong);
        return date;
    }

    /**
     * 将数据库中的类型 转换成 java类型
     * @param callableStatement
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        long aLong = callableStatement.getLong(i);
        Date date = new Date(aLong);
        return date;
    }
}
