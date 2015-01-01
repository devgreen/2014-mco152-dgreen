package green.earthquakes;

import green.weather.WeatherFrame;
import green.weather.WeatherNow;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.google.gson.Gson;

public class Earthquakes extends JFrame {

	public Earthquakes() throws IOException {

		setSize(800, 600);
		setTitle("Earthquakes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		URL url = new URL("http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/significant_month.geojson");
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
		GetEarthquakes now = gson.fromJson(json, GetEarthquakes.class);

		Features[] features = now.getFeatures();

		String[] information = new String[features.length];
		for (int i = 0; i < features.length; i++) {
			StringBuilder builder = new StringBuilder();
			Properties properties = features[i].getProperties();
			double mag = properties.getMag();
			builder.append("Magnitude: ");
			builder.append(mag);
			builder.append(" Location: ");

			String place = properties.getPlace();
			builder.append(place);
			information[i] = builder.toString();

		}
		JList list = new JList(information);
		container.add(list);
	}

	public static void main(String[] args) throws IOException {

		Earthquakes frame = new Earthquakes();
		frame.setVisible(true);

	}

}
