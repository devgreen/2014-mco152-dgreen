package green.opportunity;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NasaFrame extends JFrame {

	private JLabel display;
	private JLabel picNum;
	private JButton previous;
	private JButton next;
	private ArrayList<String> urls = new ArrayList<String>();
	private int location = 0;
	private JTextField field;
	private JButton get;

	public NasaFrame() {

		setSize(800, 600);
		setTitle("NASA Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		display = new JLabel();
		container.add(display, BorderLayout.CENTER);

		picNum = new JLabel();
		container.add(picNum, BorderLayout.SOUTH);

		previous = new JButton();
		next = new JButton();
		Container south = new Container();
		south.setLayout(new FlowLayout());
		previous.setText("<");
		next.setText(">");
		south.add(previous);
		south.add(picNum);

		south.add(next);
		container.add(south, BorderLayout.SOUTH);
		Container north = new Container();
		north.setLayout(new BorderLayout());
		field = new JTextField();
		north.add(field, BorderLayout.CENTER);
		get = new JButton("  get  ");

		ActionListener getSpecific = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String getNum = field.getText();

				String url = urls.get(Integer.parseInt(getNum));
				picNum.setText(getNum + " of " + (urls.size() - 1));
				DownloadImageThread thread = new DownloadImageThread(display, url);
				thread.start();

			}
		};
		get.addActionListener(getSpecific);
		north.add(get, BorderLayout.EAST);
		container.add(north, BorderLayout.NORTH);

		DownloadMIIamages thread = new DownloadMIIamages(this);
		thread.start();

	}

	public void displayImage(GetResponse response) {
		final MIImages[] mi_images = response.getMi_images();
		Images[] images = mi_images[0].getImages();
		for (int i = 0; i < mi_images.length; i++) {
			images = mi_images[i].getImages();
			for (int j = 0; j < images.length; j++) {
				String url = images[j].getUrl();
				urls.add(url);
			}
		}
		PCamImages[] pcam_images = response.getPcam_images();

		for (int i = 0; i < pcam_images.length; i++) {
			images = pcam_images[i].getImages();
			for (int j = 0; j < images.length; j++) {
				String url = images[j].getUrl();
				urls.add(url);
			}
		}

		NCamImages[] ncam_images = response.getNcam_images();

		for (int i = 0; i < ncam_images.length; i++) {
			images = ncam_images[i].getImages();
			for (int j = 0; j < images.length; j++) {
				String url = images[j].getUrl();
				urls.add(url);
			}
		}

		FCamImages[] fcam_images = response.getFcam_images();

		for (int i = 0; i < fcam_images.length; i++) {
			images = fcam_images[i].getImages();
			for (int j = 0; j < images.length; j++) {
				String url = images[j].getUrl();
				urls.add(url);
			}
		}

		picNum.setText("0 of " + (urls.size() - 1));
		String getUrl = images[0].getUrl();

		ActionListener getNext = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (location == (urls.size() - 1)) {
					location = 0;
					String url = urls.get(0);
					picNum.setText(location + " of " + (urls.size() - 1));
					DownloadImageThread thread = new DownloadImageThread(display, url);
					thread.start();

				} else {
					String url = urls.get(++location);
					picNum.setText(location + " of " + (urls.size() - 1));
					DownloadImageThread thread = new DownloadImageThread(display, url);
					thread.start();
				}
			}
		};
		next.addActionListener(getNext);

		ActionListener getPrevious = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (location == 0) {
					location = (urls.size() - 1);
					String url = urls.get(location);
					picNum.setText(location + " of " + (urls.size() - 1));
					DownloadImageThread thread = new DownloadImageThread(display, url);
					thread.start();

				} else {
					String url = urls.get(--location);
					picNum.setText(location + " of " + urls.size());
					DownloadImageThread thread = new DownloadImageThread(display, url);
					thread.start();

				}

			}
		};
		previous.addActionListener(getPrevious);
		DownloadImageThread thread = new DownloadImageThread(display, getUrl);
		thread.start();

	}

	public static void main(String[] args) {

		NasaFrame frame = new NasaFrame();
		frame.setVisible(true);

	}

}
