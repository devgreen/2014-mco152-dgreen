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

		label = new JLabel("Downloading weather");
		minTemp = new JLabel("min temp");
		maxTemp = new JLabel("Max temp");
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

	}

	public void displayWeather(WeatherNow now) {
		Main main = now.getMain();
		label.setText("Temp: " + String.valueOf(main.getTemp()));
		minTemp.setText("Min Temp: " + String.valueOf(main.getMinTemp()));
		maxTemp.setText("Max Temp: " + String.valueOf(main.getMaxTemp()));
		Weather[] desc = now.getWeather();
		description.setText(desc[0].getMain() + "; " + desc[0].getDescription());
		StringBuilder getUrl = new StringBuilder();
		String icon = desc[0].getIcon();
		getUrl.append("http://openweathermap.org/img/w/");
		getUrl.append(icon);
		getUrl.append(".png");
		String url = getUrl.toString();
		ImageDownloadThread thread = new ImageDownloadThread(url, image);
		thread.start();
	}

	public static void main(String[] args) throws IOException {

		WeatherFrame frame = new WeatherFrame();
		frame.setVisible(true);

	}

}
