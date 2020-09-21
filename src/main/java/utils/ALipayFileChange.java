package utils;

import java.io.*;

public class ALipayFileChange {

    public synchronized  static void changeFile(String html) {
        try {
            BufferedWriter out = new BufferedWriter (new FileWriter ("src/main/resources/ui/alipay.html"));
            out.write (html);
            out.close ();
            System.out.println ("文件创建成功！");
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }


    public static void read() {
        File file = new File("src/main/resources/ui/alipay.html");
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

}
