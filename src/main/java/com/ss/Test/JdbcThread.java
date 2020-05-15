package com.ss.Test;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class JdbcThread {

    private static final String DBDRIVER = "com.mysql.jdbc.Driver";

    private static final String URL = "jdbc:mysql://192.168.137.132:3306/shangsong";

    private static final String USER = "root";

    private static final String PASSWORD = "123456";

    private static String path = "X:/s2.txt";

    private static String scheam = "shangsong";

    private static String table = "test1";

    private static String columns = "a,b,c";

    private static BasicDataSource basicDataSource = new BasicDataSource();

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        setBasicDataSource(DBDRIVER, URL, USER, PASSWORD);

        Connection connection = basicDataSource.getConnection();

        List<String> lines = FileReader.getDataList(path);

        System.out.println(lines);

        String sql = ("select * from shangsong.test1");

        PreparedStatement ps = connection.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        int columnCount = rs.getMetaData().getColumnCount();

        System.out.println(rs.getMetaData().getColumnCount());

        String insertSql = getSql(columns, scheam, table, rs);

        System.out.println(insertSql);
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);



        basicDataSource.close();

    }

    private static String getSql(String columns, String scheam, String table, ResultSet rs) throws SQLException {

        int columnSize;
        String[] columnSplit = columns.split(",");
        if (columnSplit != null) {
            columnSize = columnSplit.length;
        } else {
            columnSize = rs.getMetaData().getColumnCount();
        }

        StringBuilder sb = new StringBuilder("insert into ");

        sb.append(scheam)
                .append(".")
                .append(table)
                .append("(");
        if (columnSplit != null) {
            for (int i = 0; i < columnSize; i++) {
                sb.append(columnSplit[i] + ",");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")")
        .append(" values(");
        for (int i = 0; i < columnSize; i++) {
            sb.append("?,");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }

    private static void setParameter() {

    }

    private static void setBasicDataSource(String driver, String URL, String USER, String PASSWORD) {
        basicDataSource.setDriverClassName(driver);

        basicDataSource.setUrl(URL);

        basicDataSource.setUsername(USER);

        basicDataSource.setPassword(PASSWORD);

        basicDataSource.setInitialSize(1);

        basicDataSource.setMaxActive(1);
    }

}

