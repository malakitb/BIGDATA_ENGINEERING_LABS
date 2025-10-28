// src/main/java/edu/ismagi/hadoop/WriteHDFS.java
package edu.ismagi.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import java.io.*;

public class WriteHDFS {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        FileSystem fs;
        try {
            fs = FileSystem.get(conf);
            Path hdfsPath = new Path("/user/root/input/resultat.txt");
            Path localPath = new Path("/shared_volume/purchases.txt");

            if (!new File(localPath.toString()).exists()) {
                System.out.println("Local file does not exist: " + localPath);
                System.exit(1);
            }

            FSDataOutputStream out = fs.create(hdfsPath);
            BufferedReader br = new BufferedReader(new FileReader(localPath.toString()));
            String line;
            while ((line = br.readLine()) != null) {
                out.writeBytes(line + "\n");
            }
            br.close();
            out.close();
            fs.close();

            System.out.println("File copied to HDFS: " + hdfsPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}