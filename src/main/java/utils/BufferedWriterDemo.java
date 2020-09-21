package utils;

import java.io.*;

public class BufferedWriterDemo {
    public static void main(String[] args) {
        try {
            BufferedWriter out = new BufferedWriter (new FileWriter ("src/main/resources/ui/alipay.html"));
            out.write ("<html>You are my sunshine!</html>");
            out.close ();
            System.out.println ("文件创建成功！");
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }


}
