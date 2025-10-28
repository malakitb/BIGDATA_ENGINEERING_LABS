// src/main/java/edu/ismagi/hadoop/ReadHDFS.java
package edu.ismagi.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import java.io.*;

public class ReadHDFS {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        FileSystem fs;
        try {
            fs = FileSystem.get(conf);
            Path filepath = new Path("/user/root/input/achats.txt");

            if (!fs.exists(filepath)) {
                System.out.println("File does not exist: " + filepath);
                System.exit(1);
            }

            System.out.println("=== Contenu du fichier achats.txt ===");
            FSDataInputStream in = fs.open(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
            in.close();
            fs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}