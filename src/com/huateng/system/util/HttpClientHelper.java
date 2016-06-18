package com.huateng.system.util;

import java.io.Closeable;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class HttpClientHelper {
	public static CloseableHttpClient getSSLClient() throws NoSuchAlgorithmException, KeyManagementException {
		// TODO Auto-generated method stub
		// 连接超时时间，默认10秒
		int socketTimeout = 30000;

		// 传输超时时间，默认30秒
		int connectTimeout = 100000;
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
				.setConnectTimeout(connectTimeout).build();
		// TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
		SSLContext ctx = SSLContext.getInstance("TLS");
		X509TrustManager xtm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		ctx.init(null, new TrustManager[] { xtm }, null);
		SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(ctx,
				SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig)
				.setSSLSocketFactory(sslSocketFactory).build();

		return httpClient;
	}

	public static String post(HttpClient httpClient,String url, String data, String encoding) {

		if(httpClient==null){
			httpClient = HttpClients.createDefault();
		}
		String returnStr = null;
		try {

			HttpPost httppost = new HttpPost(url);
			System.out.println(URLEncoder.encode(data, encoding));
			HttpEntity reqEntity = new StringEntity(URLEncoder.encode(data, encoding),
					ContentType.create("text/plain", encoding));

			httppost.setEntity(reqEntity);
			CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httppost);
			try {
				// 打印响应状态
				// System.out.println(response.getStatusLine());
				// 获取响应对象
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					returnStr = EntityUtils.toString(resEntity, Charset.forName(encoding));
					// 打印响应内容
					// System.out.println(returnStr);
				}
				// 销毁
				EntityUtils.consume(resEntity);
			} finally {
				response.close();
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				((Closeable) httpClient).close();
			} catch (Exception ignore) {

			}
		}
		return returnStr;
	}
}
