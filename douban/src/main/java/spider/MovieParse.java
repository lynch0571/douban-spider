package spider;

import org.jsoup.nodes.Document;

/**
 * @author Lynch
 * Created at:2016年7月23日 下午1:48:48
 * 参考：http://www.cnblogs.com/chenying99/archive/2013/01/04/2844615.html
 */
public class MovieParse {
	
	/**获取电影名称
	 * @param doc
	 * @return
	 */
	public static String getMovieName(Document doc){
		return doc.select("#content h1 span").first().text();
	}
	
	/**获取上映年份
	 * @param doc
	 * @return
	 */
	public static String getMovieYear(Document doc){
		return doc.select("#content h1 span").last().text().substring(1, 5);
	}
}
