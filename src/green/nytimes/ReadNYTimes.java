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

	private static final long serialVersionUID = 1L;
	private int count;
	private JList<String> list;
	private Docs[] articles;

	public ReadNYTimes() throws IOException {

		setSize(800, 600);
		setTitle("NY Times");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		list = new JList<String>();

		container.add(new JScrollPane(list));
		JButton next = new JButton("next");
		next.addActionListener(listener);
		container.add(next, BorderLayout.SOUTH);
		DownloadThread thread = new DownloadThread(this, list, openArticle, articles);
		thread.start();

	}

	ListSelectionListener openArticle = new ListSelectionListener() {

		@Override
		public void valueChanged(ListSelectionEvent event) {
			try {
				if (!event.getValueIsAdjusting()) {
					open();
				}
			} catch (IOException a) {
				a.printStackTrace();
			} catch (URISyntaxException a) {
				a.printStackTrace();
			}
		}
	};
	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent event) {
			getNextArticles();
		}
	};

	public void getNextArticles() {
		LaterArticlesDownloadThread thread = new LaterArticlesDownloadThread(this, list, articles, ++count);
		thread.start();

	}

	public void open() throws IOException, URISyntaxException {
		int i = list.getSelectedIndex();
		if (i != -1) {
			URL url = articles[i].getWeb_url();
			Desktop.getDesktop().browse(url.toURI());
		}
	}

	public static void main(String[] args) throws IOException {
		ReadNYTimes frame = new ReadNYTimes();
		frame.setVisible(true);
	}

	public void setArticles(Docs[] articles) {
		this.articles = articles;
	}
}
