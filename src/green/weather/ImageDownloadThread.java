package green.weather;

import java.awt.Image;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class ImageDownloadThread extends Thread {

	private String url;
	private JLabel image;
	
	public  ImageDownloadThread(String url, JLabel image){
		
	}
	@Override
	public void run() {

		
		Image image = null;
		StringBuilder getUrl = new StringBuilder();
		getUrl.append("http://openweathermap.org/img/w/");
		getUrl.append(icon);
		getUrl.append(".png");
		URL url2 = new URL(getUrl.toString());
		image = ImageIO.read(url2);

	}

}
