package utils;
  
import java.io.BufferedReader;  
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;  
import java.net.HttpURLConnection;  
import java.net.URL;  
import java.util.Map;  
import java.util.Map.Entry;  
  
public class HttpUtil {  
  
    public static String http(String url, Map<String, String> params) {  
        URL u = null;  
        HttpURLConnection con = null;  
        // 构建请求参数  
        StringBuffer sb = new StringBuffer();  
        if (params != null) {  
            for (Entry<String, String> e : params.entrySet()) {  
                sb.append(e.getKey());  
                sb.append("=");  
                sb.append(e.getValue());  
                sb.append("&");  
            }  
            sb.substring(0, sb.length() - 1);  
        }  
        System.out.println("send_url:" + url);  
        System.out.println("send_data:" + sb.toString());  
        // 尝试发送请求  
        try {  
            u = new URL(url);  
            con = (HttpURLConnection) u.openConnection();  
            //// POST 只能为大写，严格限制，post会不识别  
            con.setRequestMethod("POST");  
            con.setDoOutput(true);  
            con.setDoInput(true);  
            con.setUseCaches(false);  
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");  
            osw.write(sb.toString());  
            osw.flush();  
            osw.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (con != null) {  
                con.disconnect();  
            }  
        }  
  
        // 读取返回内容  
        StringBuffer buffer = new StringBuffer();  
        try {  
            //一定要有返回值，否则无法把请求发送给server端。  
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));  
            String temp;
            System.out.println ("br"+br);

            while ((temp = br.readLine()) != null) {  
                buffer.append(temp);  
                buffer.append("\n");
                System.out.println ("temo"+temp);
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return buffer.toString();  
    }  
  
} 