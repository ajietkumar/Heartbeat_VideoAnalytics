package com.mop.qa.Utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class SendGetPost {
	private final String USER_AGENT = "Mozilla/5.0";
	
	// HTTP GET request
	public static String sendGet(String url) throws Exception {
	//	url="http://feed.theplatform.com/f/HNK2IC/bravo_cpc_iOS_v4_prd?byGUID=3534572";
		HttpClient client = new DefaultHttpClient();
//		HttpClient client = (HttpClient) new SendGetPost();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println(result.toString());
		return result.toString();
	}

	// HTTP POST request
	public static String sendPost(String url) throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println(result.toString());
		return result.toString();
	}
	public static void main(String arg[]) throws Exception{
		String url="https://feed.theplatform.com/f/HNK2IC/nbcd_app_adstitch_v3_prod?byGUID=3504897";
				//"http://feed.theplatform.com/f/HNK2IC/bravo_cpc_iOS_v4_prd?byGUID=3534572";
		//https://feed.theplatform.com/f/HNK2IC/nbcd_app_adstitch_v3_prod?byGUID=3504897;
		System.out.println(SendGetPost.sendGet(url));
		sendGet(url);
	}
}