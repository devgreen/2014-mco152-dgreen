package green.opportunity;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class DownloadMIIamages extends Thread {

	private NasaFrame frame;
	private JLabel image;

	public DownloadMIIamages(NasaFrame frame) {
		this.frame = frame;
	}

	@Override
	public void run() {

		URL url;
		try {
			url = new URL("http://merpublic.s3.amazonaws.com/oss/mera/images/images_sol13.json");

			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();
			String json = IOUtils.toString(in);
			Gson gson = new Gson();
			GetResponse response = gson.fromJson(json, GetResponse.class);
			frame.displayImage(response);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
