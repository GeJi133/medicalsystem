package utils;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * 描述：模仿Form表单进行Post请求提交 依赖包： 
 * 1.commons-httpclient-3.1-rc1.zip
 * 2.commons-codec-1.3.jar 
 * 3.commons-logging-api.jar
 * 
 * @author Jeffrey
 * @DateTime 2017-12-04
 *
 */
public class HttpModelFormRequest {

        /**
	 * @param url 接口地址
	 * @param para 提交参数
	 * @return
	 */
	@SuppressWarnings("finally")
	public static String sendPostLikeForm(String url, Map<String, String> para) {

		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		// 填入各个表单域的值
		int paraSize = para.size();
		NameValuePair[] data = new NameValuePair[paraSize];
		int i = 0;
		for (String key : para.keySet()) {
			String value = para.get(key);// 得到每个key多对用value的值
            System.out.println ("para.get(key)"+para.get(key));
			data[i] = new NameValuePair(key, value);
			i++;
		}
		System.out.println ("data"+data);
		// 将表单的值放入postMethod中
		postMethod.setRequestBody(data);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		System.out.println ("发送请求前");

		// 执行postMethod
		String result = "";
		try {
			httpClient.executeMethod(postMethod);// int statusCode=httpClient.executeMethod(postMethod)
			result = postMethod.getResponseBodyAsString();
			System.out.println ("result"+postMethod.getResponseBodyAsString ());
			System.out.println ("发送强求吼" +
                    "");
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//		    System.out.println ("result"+result);
			return result;
		}
	}
}