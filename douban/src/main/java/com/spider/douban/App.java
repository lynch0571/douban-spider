package com.spider.douban;

import org.jsoup.nodes.Document;

import spider.HTMLSpider;
import spider.MovieParse;

public class App 
 {

    /**
     * @param args
     */
    public static void main( String[] args )
    {
		//String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
		String url = "https://movie.douban.com/subject/1292052/";
		String proxyIp = "149.202.248.203"; // 德国高匿名代理
		int proxyPort = 3128;
		int timeout=100;
		Document doc=HTMLSpider.getHTMLDocFromWeb(url,proxyIp,proxyPort,timeout);
		//Document doc = HTMLSpider.getHTMLDocFromWeb(url);
		if(doc!=null){
			System.out.println(MovieParse.getMovieName(doc));
			System.out.println(MovieParse.getMovieYear(doc));
		}
    }
}
