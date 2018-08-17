package com.test.getNews;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.test.util.DBHelper;

public class GetNews {
	
	private static Logger logger = Logger.getLogger(GetNews.class);
	
	private static HttpClient httpClient = new DefaultHttpClient();

	static HttpResponse response;
	
	static HttpGet httpGet = new HttpGet("http://about.sinosig.com/common/news/html/2277.html");
	
	static String sql = null;
	
	static DBHelper db = null;
	
	public static void main(String[] args) {
		try {
			response = httpClient.execute(httpGet);
			String countent = EntityUtils.toString(response.getEntity(), "utf-8");
			//System.out.println(countent);
			Document document = Jsoup.parse(countent);
			Elements links = document.select(".bomtextlist ul");
			Elements elements = links.select("a[href]");
			sql = "insert into news (newsTitle,newsContext,newsUpdateTime,newsName) values (?,?,?,?)";
			db = new DBHelper(sql);// 创建DBHelper对象
			int newsName = 10000;
			for (Element element : elements) {
				logger.info("爬取到的连接为:" + element.attr("href") + " " + element.html());
				httpGet = new HttpGet("http://about.sinosig.com/" + element.attr("href"));
				int retCode =sendRequest(httpGet, httpClient); 
				logger.info("返回状态码:" + retCode);
				response = httpClient.execute(httpGet);
				countent = EntityUtils.toString(response.getEntity(), "utf-8");
				document = Jsoup.parse(countent);
				String title = document.select("h3").html().toString();
				logger.info("title = " + title);
				String context = document.select("p").toString();
				//System.out.println(context);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				db.pst.setString(1, title);
				db.pst.setString(2, context);
				db.pst.setString(3, df.format(new Date()));
				db.pst.setString(4, "news" + newsName);
				int returnFlag = db.pst.executeUpdate();
				logger.info("受影响的行数为:" + returnFlag);
				newsName++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
			db.close();
		}
	}
	
	private static int sendRequest(HttpGet get, HttpClient client) throws InterruptedException {  
        HttpResponse resp = null;  
        try {  
            resp = client.execute(get);  
            StatusLine statusLine = resp.getStatusLine();
			int retCode = statusLine.getStatusCode();
			return retCode;
            // get response content here  
        } catch (Exception e) {  
            e.printStackTrace();
            return 0;
        } finally {  
            try {  
                resp.getEntity().getContent().close(); //!!!IMPORTANT  
            } catch (Exception e) {  
                // go to hell  2B8F070FCA9C89A924CA43ABC1E8C86A
            }  
        }  
    }

}
