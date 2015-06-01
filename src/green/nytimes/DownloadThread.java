package green.nytimes;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JList;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class DownloadThread extends Thread {

	private ReadNYTimes frame;
	private JList<String> list;
	private ListSelectionListener listener;

	public DownloadThread(JList<String> list, ListSelectionListener listener) {
		this.list = list;
		this.listener = listener;
	}

	@Override
	public void run() {

		URL url;
		try {
			url = new URL(
					"http://api.nytimes.com/svc/search/v2/articlesearch.json?page=0&api-key=5b124297be5c2a5a0b3cb65343beeef1%3A18%3A70540252");

			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();
			String json = IOUtils.toString(in);
			Gson gson = new Gson();
			GetResponse response = gson.fromJson(json, GetResponse.class);
			Response a = response.getResponse();
			Docs[] articles = a.getDocs();
			String[] getArticles = new String[articles.length];
			for (int i = 0; i < articles.length; i++) {
				StringBuilder builder = new StringBuilder();
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
			list.addListSelectionListener(listener);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
