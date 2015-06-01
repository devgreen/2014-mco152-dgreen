package green.nytimes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JList;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class LaterArticlesDownloadThread extends Thread{
	
	

	private ReadNYTimes frame;
	private JList<String> list;
	private ListSelectionListener listener;
	private Docs[] articles;
	private int count;

	public LaterArticlesDownloadThread(ReadNYTimes frame, JList<String> list, Docs[] articles, int count) {
		this.list = list;
		this.articles = articles;
		this.frame = frame;
		this.count= count;
	}

	@Override
	public void run() {

	
		try {
			StringBuilder builder = new StringBuilder();
			builder.append("http://api.nytimes.com/svc/search/v2/articlesearch.json?page=");
			builder.append(String.valueOf(count));
			builder.append("&api-key=5b124297be5c2a5a0b3cb65343beeef1%3A18%3A70540252");
			URL url = new URL(builder.toString());

			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();
			String json = IOUtils.toString(in);
			Gson gson = new Gson();
			GetResponse response = gson.fromJson(json, GetResponse.class);
			Response a = response.getResponse();
			articles = a.getDocs();
			frame.setArticles(articles);
			String[] getArticles = new String[articles.length];
			for (int i = 0; i < articles.length; i++) {
				builder = new StringBuilder();
				builder.append("<html>");
				builder.append(String.valueOf(i + 1));
				builder.append(". ");
				builder.append(articles[i].getHeadline().getMain());
				builder.append(":<br>");
				builder.append(articles[i].getLead_paragraph());
				builder.append("</html>");
				getArticles[i] = builder.toString();
			}
			list.setListData(getArticles);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
