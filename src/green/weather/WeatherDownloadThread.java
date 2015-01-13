package green.weather;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class WeatherDownloadThread extends Thread {

	private WeatherFrame frame;

	public WeatherDownloadThread(WeatherFrame frame) {
		this.frame = frame;

	}

	@Override
	public void run() {

		
		try {
			URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Brooklyn&units=imperial");

			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();

			String json = IOUtils.toString(in);
			Gson gson = new Gson();
			WeatherNow now = gson.fromJson(json, WeatherNow.class);
			frame.displayWeather(now);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
