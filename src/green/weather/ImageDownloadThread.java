package green.weather;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageDownloadThread extends Thread {

	private String url;
	private JLabel image;

	public ImageDownloadThread(String url, JLabel image) {
		this.url = url;
		this.image = image;

	}

	@Override
	public void run() {

		URL geturl;
		try {
			geturl = new URL(url.toString());
			Image getimage = ImageIO.read(geturl);
			ImageIcon icon = new ImageIcon(getimage);
			image.setIcon(icon);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
