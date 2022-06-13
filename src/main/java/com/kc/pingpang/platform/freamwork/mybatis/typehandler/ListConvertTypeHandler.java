package com.kc.pingpang.platform.freamwork.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * 字符串转换集合
 */
public class ListConvertTypeHandler extends BaseTypeHandler<List<String>> {
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<String> strings, JdbcType jdbcType) throws SQLException {
        //1.List集合转字符串
        StringBuilder sb = new StringBuilder();
        for (String str : strings) {
            sb.append(str).append(",");
        }
        //2.设置给ps
        preparedStatement.setString(i, sb.toString().substring(0, sb.toString().length() - 1));
    }

    public List<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {

        String str = resultSet.getString(s);
        if (str == null) {
            return null;
        } else {
            return Arrays.asList(str.split(","));
        }
    }

    public List<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {

        String str = resultSet.getString(i);
        if (str == null) {
            return null;
        } else {
            return Arrays.asList(str.split(","));
        }
    }

    public List<String> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {

        String str = callableStatement.getString(i);
        if (str == null) {
            return null;
        } else {
            return Arrays.asList(str.split(","));
        }
    }
}
