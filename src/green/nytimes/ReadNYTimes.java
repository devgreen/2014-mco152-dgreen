package green.nytimes;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.google.gson.Gson;

public class ReadNYTimes extends JFrame {

	int count;
	JList list;
	Docs[] articles;

	public ReadNYTimes() throws IOException {

		setSize(800, 600);
		setTitle("NY Times");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		URL url = new URL(
				"http://api.nytimes.com/svc/search/v2/articlesearch.json?page=0&api-key=5b124297be5c2a5a0b3cb65343beeef1%3A18%3A70540252");
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();

		byte b[] = new byte[4096];
		int n = -1;
		StringBuilder info = new StringBuilder();
		while ((n = in.read(b)) != -1) {
			info.append(new String(b, 0, n));
		}

		String json = info.toString();
		Gson gson = new Gson();

		GetResponse response = gson.fromJson(json, GetResponse.class);
		Response a = response.getResponse();
		articles = a.getDocs();
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

		ListSelectionListener openArticle = new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent event) {
				try {
					open();
				} catch (IOException a) { // TODO Auto-generated catch block
					a.printStackTrace();
				} catch (URISyntaxException a) {
					// TODOAuto-generated catch block
					a.printStackTrace();
				}
			}
		};

		list = new JList(getArticles);
		 list.addListSelectionListener(openArticle);
		container.add(new JScrollPane(list));

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					list.setListData(getNext());

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		JButton next = new JButton("next");
		next.addActionListener(listener);
		container.add(next, BorderLayout.SOUTH);

	}

	public String[] getNext() throws IOException {
		StringBuilder builder = new StringBuilder();
		builder.append("http://api.nytimes.com/svc/search/v2/articlesearch.json?page=");
		builder.append(String.valueOf(++count));
		builder.append("&api-key=5b124297be5c2a5a0b3cb65343beeef1%3A18%3A70540252");
		URL url = new URL(builder.toString());
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();

		byte b[] = new byte[4096];
		int n = -1;
		StringBuilder info = new StringBuilder();
		while ((n = in.read(b)) != -1) {
			info.append(new String(b, 0, n));
		}

		String json = info.toString();
		Gson gson = new Gson();
		GetResponse response = gson.fromJson(json, GetResponse.class);
		Response a = response.getResponse();
		articles = a.getDocs();
		String[] getArticles = new String[articles.length];
		for (int i = 0; i < articles.length; i++) {
			StringBuilder articleBuilder = new StringBuilder();
			articleBuilder.append("<html>");
			articleBuilder.append(String.valueOf(i + 1));
			articleBuilder.append(". ");
			articleBuilder.append(articles[i].getHeadline().getMain());
			articleBuilder.append(":<br>");
			articleBuilder.append(articles[i].getLead_paragraph());
			articleBuilder.append("</html>");
			getArticles[i] = articleBuilder.toString();
		}
		return getArticles;
	}

	public void open() throws IOException, URISyntaxException {
		int i = list.getSelectedIndex();
		URL url = articles[i].getWeb_url();
		Desktop.getDesktop().browse(url.toURI());
	}

	public static void main(String[] args) throws IOException {
		ReadNYTimes frame = new ReadNYTimes();
		frame.setVisible(true);
	}
}