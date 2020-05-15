package com.ss.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import org.apache.hadoop.fs.CreateFlag;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

public class hdfs {

    FileSystem fs = null;

    /**
     * 初始化FileSystem
     */
    @Before
    public void init() throws Exception {
        // 构造一个配置参数对象，设置一个参数：我们要访问的hdfs的URI
        // 从而FileSystem.get()方法就知道应该是去构造一个访问hdfs文件系统的客户端，以及hdfs的访问地址
        // new Configuration();的时候，它就会去加载jar包中的hdfs-default.xml
        // 然后再加载classpath下的hdfs-site.xml
        Configuration conf = new Configuration();
        //conf.set("fs.defaultFS", "hdfs://hdp-node01:9000");
        /**
         * 参数优先级： 1、客户端代码中设置的值 2、classpath下的用户自定义配置文件 3、然后是服务器的默认配置
         */
        //conf.set("dfs.replication", "3");

        // 获取一个hdfs的访问客户端，根据参数，这个实例应该是DistributedFileSystem的实例
        // fs = FileSystem.get(conf);

        System.setProperty("hadoop.home.dir", "X:\\hadoop-2.7.5");

        // 如果这样去获取，那conf里面就可以不要配"fs.defaultFS"参数，而且，这个客户端的身份标识已经是hadoop用户
        fs = FileSystem.get(new URI("hdfs://192.168.137.128:8020"), conf, "root");
    }

    /**
     * 往hdfs上传文件
     */
    @Test
    public void testAddFileToHdfs() throws Exception {
        // 要上传的文件所在的本地路径
        Path src = new Path("X:/s2.txt");
        // 要上传到hdfs的目标路径
        Path dst = new Path("/tmp/ss.java");
        fs.copyFromLocalFile(src, dst);

        fs.close();
    }

    /**
     * 从hdfs中复制文件到本地文件系统
     */
    @Test
    public void testDownloadFileToLocal() throws IllegalArgumentException, IOException {
        fs.copyToLocalFile(new Path("/tmp/ss.java/sms.txt"), new Path("X:/"));
        fs.close();
    }

    /**
     * 在hfds中创建目录、删除目录、重命名
     */
    @Test
    public void testMkdirAndDeleteAndRename() throws IllegalArgumentException, IOException {
        // 创建目录
        fs.mkdirs(new Path("/a1/b1/c1"));

        // 删除文件夹 ，如果是非空文件夹，参数2必须给值true
        fs.delete(new Path("/aaa"), true);

        // 重命名文件或文件夹
        fs.rename(new Path("/a1"), new Path("/a2"));
    }

}