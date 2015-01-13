package green.weather;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WeatherFrame extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JLabel minTemp;
	private JLabel maxTemp;
	private JLabel description;
	private JLabel image;
	private JPanel panel;

	public WeatherFrame() throws IOException {
		setSize(800, 600);
		setTitle("Current Weather");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		

		/*
		 * URL url = new URL(
		 * "http://api.openweathermap.org/data/2.5/weather?q=Brooklyn&units=imperial"
		 * ); URLConnection connection = url.openConnection(); InputStream in =
		 * connection.getInputStream();
		 * 
		 * byte b[] = new byte[4096]; int n = -1; StringBuilder info = new
		 * StringBuilder(); while ((n = in.read(b)) != -1) { info.append(new
		 * String(b, 0, n));// new string from b till n-1 }
		 * 
		 * String json = info.toString(); Gson gson = new Gson(); WeatherNow now
		 * = gson.fromJson(json, WeatherNow.class);
		 */

		label = new JLabel("Downloading weather");
		minTemp = new JLabel ("min temp");
		maxTemp = new JLabel ("Max temp");
		description = new JLabel("description");
		panel = new JPanel();
		panel.add(label);
		panel.add(minTemp);
		panel.add(maxTemp);
		container.add(panel, BorderLayout.WEST);
		container.add(description, BorderLayout.SOUTH);
		image = new JLabel();
		container.add(image, BorderLayout.CENTER);
		WeatherDownloadThread thread = new WeatherDownloadThread(this);
		thread.start();
		/*
		 * double temp = now.getMain().getTemp(); double minTemp =
		 * now.getMain().getMinTemp(); double maxTemp =
		 * now.getMain().getMaxTemp();
		 * 
		 * JPanel panel = new JPanel(); Weather[] description =
		 * now.getWeather(); for (int i = 0; i < description.length; i++) {
		 * String main = description[i].getMain(); String des =
		 * description[i].getDescription(); panel.add(new JLabel(main + "; " +
		 * des)); } container.add(panel, BorderLayout.WEST);
		 * 
		 * container.add(new JLabel("Current temp: " + String.valueOf(temp) +
		 * "; Max temp: " + String.valueOf(maxTemp) + "; Min temp: " +
		 * String.valueOf(minTemp)), BorderLayout.NORTH); String icon =
		 * description[0].getIcon(); Image image = null; StringBuilder getUrl =
		 * new StringBuilder();
		 * getUrl.append("http://openweathermap.org/img/w/");
		 * getUrl.append(icon); getUrl.append(".png"); URL url2 = new
		 * URL(getUrl.toString()); image = ImageIO.read(url2); container.add(new
		 * JLabel(new ImageIcon(image)), BorderLayout.CENTER);
		 */

	}

	public void displayWeather(WeatherNow now) {
		Main main = now.getMain();
		label.setText("Temp: " + String.valueOf(main.getTemp()));
		minTemp.setText("Min Temp: " + String.valueOf(main.getMinTemp()));
		maxTemp.setText("Max Temp: "+ String.valueOf(main.getMaxTemp()));
		Weather [] desc = now.getWeather();
		description.setText(desc[0].getMain() + "; " + desc[0].getDescription());
//		StringBuilder getUrl = new StringBuilder();
//		String icon = desc[0].getIcon();
//		getUrl.append("http://openweathermap.org/img/w/");
//		getUrl.append(icon); getUrl.append(".png");
//		String url = getUrl.toString();
//		ImageDownloadThread thread = new ImageDownloadThread(url, image);
//		thread.run();
	}

	public static void main(String[] args) throws IOException {

		WeatherFrame frame = new WeatherFrame();
		frame.setVisible(true);

	}

}
