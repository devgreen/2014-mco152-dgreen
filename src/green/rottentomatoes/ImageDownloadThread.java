package green.rottentomatoes;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageDownloadThread extends Thread {

	private JLabel image;
	private String url;

	public ImageDownloadThread(JLabel image, String url) {
		this.image = image;
		this.url = url;
	}

	@Override
	public void run() {

		try {
			URL getUrl = new URL(url);
			Image getImage = ImageIO.read(getUrl);
			//getImage.getScaledInstance(50000000, 500000000, Image.SCALE_DEFAULT);
		    ImageIcon getIcon = new ImageIcon(getImage.getScaledInstance(100, 100,Image.SCALE_SMOOTH));
			image.setIcon(getIcon);
		    

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
