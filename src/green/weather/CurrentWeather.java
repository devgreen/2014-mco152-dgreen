package green.weather;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

public class CurrentWeather {

	/*
	 * public static void main(String[] args) throws IOException {
	 * 
	 * // malform exception extends ioexception URL url = new URL(
	 * "http://api.openweathermap.org/data/2.5/weather?q=Brooklyn&units=imperial"
	 * ); URLConnection connection = url.openConnection(); InputStream in =
	 * connection.getInputStream();
	 * 
	 * byte b[] = new byte[4096]; int n = -1; StringBuilder info = new
	 * StringBuilder(); while ((n = in.read(b)) != -1) { info.append(new
	 * String(b, 0, n));// new string from b till n-1 }
	 * 
	 * String json = info.toString(); Gson gson = new Gson(); WeatherNow now =
	 * gson.fromJson(json, WeatherNow.class);
	 * //System.out.println(now.getMain().getTemp()); WeatherFrame frame = new
	 * WeatherFrame(); frame.setVisible(true); }
	 */

}
