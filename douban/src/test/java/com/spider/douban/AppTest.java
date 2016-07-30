package com.spider.douban;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppTest {
	private static String urlBase = "https://api.douban.com/";
	private static String urlVersion = "v2/";

	@Test
	public void printMovieSubject() throws IOException {
		String urlBody = "movie/subject/";
		String urlNum = "1764796";
		String jsonStr = getHTMLDoc(urlBody, urlNum).body().html();
		System.out.println(jsonStr);
		printJson(jsonStr);
	}

	public Document getHTMLDoc(String urlBody, String urlNum)
			throws IOException {
		return Jsoup.connect(urlBase + urlVersion + urlBody + urlNum)
				.ignoreContentType(true).get();
	}

	public void printJson(String jsonStr) throws JsonParseException,
			JsonMappingException, IOException {
		Map<String, String> map = new ObjectMapper().readValue(jsonStr,
				Map.class);
		System.out.println("---------------------------");
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		System.out.println("---------------------------");
	}
}
