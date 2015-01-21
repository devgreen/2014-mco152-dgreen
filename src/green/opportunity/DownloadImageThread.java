package green.opportunity;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DownloadImageThread extends Thread {

	private JLabel display;
	private String url;

	public DownloadImageThread(JLabel display, String url) {
		this.display = display;
		this.url = url;
	}

	@Override
	public void run() {

		try {
			URL getUrl = new URL(url);
			Image getImage = ImageIO.read(getUrl);
			ImageIcon getIcon = new ImageIcon(getImage);
			display.setIcon(getIcon);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
