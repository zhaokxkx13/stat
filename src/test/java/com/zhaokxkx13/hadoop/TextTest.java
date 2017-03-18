package com.zhaokxkx13.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.net.URI;

/**
 * Created by zhaokxkx13 on 2017/3/7.
 */
public class TextTest {
    public static void main(String args[]) throws Exception {
        String url = "hdfs://43.241.222.147:9000/";
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(url), conf);
        FileStatus[] fileStatus = fs.listStatus(new Path("/"));
        for (FileStatus item : fileStatus) {
            System.out.println("name : " + item.getPath().toString());
        }
    }
}
