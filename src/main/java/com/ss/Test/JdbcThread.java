package com.ss.Test;

import java.sql.*;

public class JdbcThread {
    private static final String URL="jdbc:mysql://192.168.0.108:3306/shangsong";
    private static final String USER="root";
    private static final String PASSWORD="199551";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //2.获得数据连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

        String sql = "insert into t_hadoop_user values(?,?,?)";

        //3.使用数据库的连接创建声明
        //Statement stmt = conn.createStatement();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1,"23");
        statement.setString(2,"as");
        statement.setString(3,"gg");
        //4.使用声明执行SQL语句
        //ResultSet rs = stmt.executeQuery("select * from t_hadoop_user");

        int isSucccess = statement.executeUpdate();

        System.out.println(isSucccess);
        if (isSucccess == 1){
            System.out.println("插入数据成功！");

        conn.close();


        //5.读取数据库的信息
/**
 *boolean next() 方法 如果新的当前行有效，则返回 true；如果不存在下一行，则返回 false
 * 将光标从当前位置向前移一行。ResultSet 光标最初位于第一行之前；第一次调用 next 方法使第一行成为当前行；第二次调用使第二行成为当前行，依此类推。
 */
        /*while(rs.next()){
            String name = rs.getString("name");
            String address = rs.getString("address");
            System.out.println(" 用户名："+name+"密码："+address);
        }*/

    }
}}

