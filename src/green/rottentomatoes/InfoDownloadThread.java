package green.rottentomatoes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class InfoDownloadThread extends Thread {

	private RTFrame frame;

	public InfoDownloadThread(RTFrame frame) {
		this.frame = frame;
	}

	@Override
	public void run() {

		URL url;
		try {
			url = new URL(
					"http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=665ga2dxrbquyzkxqust3j4e&q=Toy+STory+3&page_limit=2");

			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();
			String json = IOUtils.toString(in);
			Gson gson = new Gson();
			GetResponse response = gson.fromJson(json, GetResponse.class);
			frame.displayInfo(response);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
