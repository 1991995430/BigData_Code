package com.ss.Test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Test1 {

    public static void main(String[] args) {

        Configuration conf = new Configuration();

        try {
            System.setProperty("hadoop.home.dir", "X:\\hadoop-2.7.5");

            System.out.println("hadoop.home.dir："+"X:\\hadoop-2.7.5");

            FileSystem fs = FileSystem.get(new URI("hdfs://192.168.137.128:8020"),conf,"root");

            System.out.println(conf.toString());

            fs.copyFromLocalFile(new Path("X:/s1.txt"),new Path("hdfs://192.168.137.128:8020/tmp/ss"));

            System.out.println("文件上传至hadoop成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}