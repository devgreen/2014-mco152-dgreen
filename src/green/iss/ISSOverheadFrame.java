package green.iss;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class ISSOverheadFrame extends JFrame {

	JTextField field;
	JList info;

	public ISSOverheadFrame() throws IOException {

		setSize(800, 600);
		setTitle("ISS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		final Container container = getContentPane();
		container.setLayout(new BorderLayout());

		field = new JTextField();
		container.add(field, BorderLayout.NORTH);

		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					info = get();
					container.add(info, BorderLayout.CENTER);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};

		JButton next = new JButton("get times");
		next.addActionListener(listener);
		container.add(next, BorderLayout.EAST);

	}

	public static void main(String[] args) throws IOException {
		ISSOverheadFrame frame = new ISSOverheadFrame();
		frame.setVisible(true);
	}

	public JList get() throws IOException {

		String address = field.getText();
		String[] split = address.split(" ");
		StringBuilder getaddress = new StringBuilder();
		getaddress.append("https://maps.googleapis.com/maps/api/geocode/json?address=");
		for (int i = 0; i < split.length; i++) {
			getaddress.append(split[i]);
			if (i < split.length - 1) {
				getaddress.append("+");

			}
		}
		// getaddress.append(split[0] + "+");
		// getaddress.append(split[1] + "+" + split[2] + "+" + split[3] + ",+" +
		// split[4] + "+" + split[5]);
		getaddress.append("&key=AIzaSyCNsW3hsoGLhkTftDggoCxdJtzKN8X_pOk");

		URL url = new URL(getaddress.toString());
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();

		byte b[] = new byte[4096];
		int n = -1;
		StringBuilder info = new StringBuilder();
		while ((n = in.read(b)) != -1) {
			info.append(new String(b, 0, n));
		}

		String json = info.toString();
		Gson gson = new Gson();

		GetResults iss = gson.fromJson(json, GetResults.class);
		Results[] results = iss.getResults();
		Geometry geo = results[0].getGeometry();
		Location location = geo.getLocation();
		double lat = location.getLat();
		double lng = location.getLng();

		StringBuilder builder = new StringBuilder();
		builder.append("http://api.open-notify.org/iss-pass.json?lat=");
		builder.append(String.valueOf(lat));
		builder.append("&lon=");
		builder.append(lng);

		URL url2 = new URL(builder.toString());
		URLConnection connection2 = url2.openConnection();
		InputStream in2 = connection2.getInputStream();
		String json2 = IOUtils.toString(in2);
		Gson gson2 = new Gson();

		GetLocation loc = gson2.fromJson(json2, GetLocation.class);

		Res[] res = loc.getResponse();
		Date[] times = new Date[res.length];
		for (int i = 0; i < res.length; i++) {
			int time = res[i].getRisetime();
			Date date = new Date();
			// SimpleDateFormat formater = new
			// SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			date.setTime((long) time * 1000);
			// String datesformatted = formater.format(date);
			times[i] = date;

		}
		JList list = new JList(times);
		return list;

	}
}