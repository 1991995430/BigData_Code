package com.ss.Test;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import java.sql.*;

public class JdbcThread {
    private static final String URL="jdbc:mysql://192.168.0.108:3306/shangsong";
    private static final String USER="root";
    private static final String PASSWORD="199551";

    private static BasicDataSource basicDataSource = new BasicDataSource();

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");

        basicDataSource.setUrl(URL);

        basicDataSource.setUsername(USER);

        basicDataSource.setPassword(PASSWORD);

        basicDataSource.setInitialSize(1);

        basicDataSource.setMaxActive(1);

        Connection connection = basicDataSource.getConnection();

        //1.加载驱动程序
        //Class.forName("com.mysql.jdbc.Driver");
        //2.获得数据连接
        //Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

        String sql = "insert into t_hadoop_user values(?,?,?)";

        //3.使用数据库的连接创建声明
        //Statement stmt = conn.createStatement();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1,999);
        statement.setString(2,"dbcp");
        statement.setString(3,"connectionPool");
        //4.使用声明执行SQL语句
        //ResultSet rs = stmt.executeQuery("select * from t_hadoop_user");

        int isSucccess = statement.executeUpdate();

        ResultSet rs = statement.executeQuery("select * from t_hadoop_user");

        System.out.println(isSucccess);

        while(rs.next()){
            String name = rs.getString("name");
            String address = rs.getString("address");
            System.out.println(" 用户名："+name+"密码："+address);
        }

        if (isSucccess == 1){
            System.out.println("插入数据成功！");
            System.out.println(connection.isClosed());
            connection.close();
            System.out.println(connection.isClosed());

        //5.读取数据库的信息
/**
 *boolean next() 方法 如果新的当前行有效，则返回 true；如果不存在下一行，则返回 false
 * 将光标从当前位置向前移一行。ResultSet 光标最初位于第一行之前；第一次调用 next 方法使第一行成为当前行；第二次调用使第二行成为当前行，依此类推。
 */

    }
        System.out.println(basicDataSource.isClosed());

        basicDataSource.close();

        System.out.println(basicDataSource.isClosed());


}}

