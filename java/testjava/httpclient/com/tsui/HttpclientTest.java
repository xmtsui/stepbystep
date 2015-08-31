package com.tsui;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * [build]
 * javac -d . com/tsui/HttpclientTest.java -cp "lib/commons-httpclient-3.0.jar:lib/commons-codec-1.9.jar:lib/commons-logging-1.1.1.jar" 
 * 
 * [run]
 * java -cp ".:lib/commons-httpclient-3.0.jar:lib/commons-codec-1.9.jar:lib/commons-logging-1.1.1.jar" com.tsui.HttpclientTest "http://xxx/rest/"
 *
 * [jar]
 * jar cvfm http.jar MANIFEST.MF ./*
 * jar xf http.jar
 * 
 * @author xiaomin.cxm
 */
public class HttpclientTest {
	public static void main(String[] args) {
		try {
			HttpMethod method = null;
			String restUrl = args[0];
			HttpClient client = new HttpClient();
			client.getParams().setConnectionManagerTimeout(3000);
			method = new GetMethod(restUrl);
			method.getParams().setSoTimeout(3000);
			DefaultHttpMethodRetryHandler retryhandler = new DefaultHttpMethodRetryHandler(0, false);
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, retryhandler);
			int statusCode = client.executeMethod(method);
			if (statusCode == 200) {
				String strs = method.getResponseBodyAsString();
				if (strs.contains("<!DOCTYPE") || strs.contains("302 Found")
					|| strs.contains("<html>")) {
					strs = "";
				System.out
				.println("load from rest interface ("
					+ restUrl
					+ ") success ,but receive null ,will try to load from local file !");
			} else {
				System.out.println("load from rest interface (" + restUrl + ") success !");
				System.out.println(strs);
			}
		}
	} catch (Exception e) {
		System.err.println("Exception" + e);
	}
}
}