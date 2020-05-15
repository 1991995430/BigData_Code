package com.ss.Test;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JdbcThread1 {
    private static final String URL="jdbc:mysql://localhost:3306/shangsong";
    private static final String USER="root";
    private static final String PASSWORD="199551";
    private static String path = "X://shangsong.txt";

    private static BasicDataSource basicDataSource = new BasicDataSource();

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        setBasicDataSource("", URL, USER, PASSWORD);

        Connection connection = basicDataSource.getConnection();

        int jobid = new Random().nextInt(10000);

        PreparedStatement stat = connection.prepareStatement("insert into taskInfo values(?, ?, ?)");
        stat.setString(1,"job_id_" + jobid);
        stat.setInt(2, 0);
        stat.setString(3, "");
        stat.execute();

        //1.加载驱动程序
        //Class.forName("com.mysql.jdbc.Driver");
        //2.获得数据连接
        //Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

        String sql = "insert into t_hadoop_user values(?,?,?)";
        String jobsql = "insert into jobstate values(?,?)";
        String jobsql1 = "update table jobstate set jobstate = ? where jobid = ?";

        //3.使用数据库的连接创建声明
        //Statement stmt = conn.createStatement();
        PreparedStatement statement = connection.prepareStatement(sql);

        List<String> list = FileReader.getDataList(path);
        int isSucccess = 0;
     /*   for (int i = 0 ;i < list.size();i++) {
            //System.out.println(list.get(i));
            for (int j = 0;j < list.get(i).length;j++) {
                statement.setObject(j+1,list.get(i)[j]);
            }
            isSucccess = statement.executeUpdate();
        }*/

        /*statement.setInt(1,999);
        statement.setString(2,"dbcp");
        statement.setString(3,"connectionPool");*/
        //4.使用声明执行SQL语句
        //ResultSet rs = stmt.executeQuery("select * from t_hadoop_user");

        String sql1 = ("select * from shangsong.test1");
        System.out.println(sql1);
        ResultSet rs = statement.executeQuery(sql1);

        int index = rs.getMetaData().getColumnCount();

        List<String> lines = getData();

        System.out.println(lines);

        basicDataSource.close();
        
 }

     public static List<String> getData(){

        List<String> data = new ArrayList<>();

         InputStream inputStream = null;
         BufferedReader bufferedReader = null;
         File file = new File("X:/s2.txt");
         try {
              inputStream = new FileInputStream(file);
             bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
             String line;
             while ((line = bufferedReader.readLine()) != null){
                 data.add(line);
             }
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }

         return data;

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

