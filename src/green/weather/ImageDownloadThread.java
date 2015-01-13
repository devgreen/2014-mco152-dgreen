package green.weather;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class ImageDownloadThread extends Thread {

	private String url;
	private JLabel image;
	
	public  ImageDownloadThread(String url, JLabel image){
		this.url = url;
		this.image = image;
		
	}
	
	
	@Override
	public void run() {

		URL geturl;
		try {
			geturl = new URL(url.toString());
			Image image = ImageIO.read(geturl);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*Image image = null;
		StringBuilder getUrl = new StringBuilder();
		getUrl.append("http://openweathermap.org/img/w/");
		getUrl.append(icon);
		getUrl.append(".png");
		URL url2 = new URL(getUrl.toString());
		image = ImageIO.read(url2);*/

	}

}
