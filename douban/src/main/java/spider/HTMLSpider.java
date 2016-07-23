package spider;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 获取HTML文档类
 * 
 * @author Lynch Created at:2016年7月23日 下午2:57:54
 */
public class HTMLSpider {
	
	private static Logger log = Logger.getLogger(HTMLSpider.class);  
	/**
	 * 根据URL获取网络HTML文档
	 * 
	 * @param urlStr
	 * @return
	 */
	public static Document getHTMLDocFromWeb(String urlStr) {
		try {
			return Jsoup.connect(urlStr).get();
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 根据URL获取网络HTML文档
	 * 
	 * @param urlStr
	 * @param userAgent	用户代理信息
	 * @return
	 */
	public static Document getHTMLDocFromWeb(String urlStr, String userAgent) {
		try {
			return Jsoup.connect(urlStr).userAgent(userAgent).get();
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 根据URL获取网络HTML文档
	 * 
	 * @param urlStr
	 * @param timeout	超时时间(单位ms)
	 *            
	 * @return
	 */
	public static Document getHTMLDocFromWeb(String urlStr, int timeout) {
		try {
			return Jsoup.connect(urlStr).timeout(timeout).get();
		} catch (IOException e) {
			return null;
		}
	}

	/**根据URL获取网络HTML文档
	 * @param urlStr
	 * @param userAgent		用户代理信息
	 * @param timeout		超时时间(单位ms)
	 * @return
	 */
	public static Document getHTMLDocFromWeb(String urlStr, String userAgent,
			int timeout) {
		try {
			return Jsoup.connect(urlStr).userAgent(userAgent).timeout(timeout)
					.get();
		} catch (IOException e) {
			return null;
		}
	}


	/**根据URL获取网络HTML文档
	 * @param urlStr
	 * @param proxyIp		代理服务器ip地址
	 * @param proxyPort		代理服务器ip地址对应端口
	 * @param timeout		超时时间(单位ms)
	 * @return
	 */
	public static Document getHTMLDocFromWeb(String urlStr, String proxyIp, int proxyPort,int timeout) {
		URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			log.error("[URL错误]urlStr:"+urlStr+",proxyIp:"+proxyIp+",proxyPort:"+proxyPort);
			return null;
		}
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIp,
				proxyPort));
		HttpURLConnection uc = null;
		
		try {
			uc = (HttpURLConnection) url.openConnection(proxy);
		} catch (IOException e) {
			log.error("[openConnection错误]urlStr:"+urlStr+",proxyIp:"+proxyIp+",proxyPort:"+proxyPort);
			return null;
		}
		try {
			uc.setConnectTimeout(timeout);
			uc.connect();
		} catch (IOException e) {
			log.error("[连接错误或超时]urlStr:"+urlStr+",proxyIp:"+proxyIp+",proxyPort:"+proxyPort);
			return null;
		}
		String line = null;
		StringBuffer stringBuffer = new StringBuffer();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		} catch (IOException e) {
			log.error("[BufferedReader读取错误]urlStr:"+urlStr+",proxyIp:"+proxyIp+",proxyPort:"+proxyPort);
			return null;
		}
		try {
			while ((line = in.readLine()) != null) {
				stringBuffer.append(line);
			}
		} catch (IOException e) {
			log.error("[StringBuffer读取错误]urlStr:"+urlStr+",proxyIp:"+proxyIp+",proxyPort:"+proxyPort);
			return null;
		}
		return Jsoup.parse(String.valueOf(stringBuffer));
	}

	/**
	 * 获取本地HTML文档，字符编码为UTF-8
	 * 
	 * @param filename
	 *            文件绝对路径
	 * @return
	 */
	public static Document getHTMLDocFromLocal(String filename) {
		try {
			File in = new File(filename);
			return Jsoup.parse(in, "UTF-8", "");
		} catch (IOException e) {
			return null;
		}
	}
}
