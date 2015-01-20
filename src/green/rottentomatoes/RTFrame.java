package green.rottentomatoes;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class RTFrame extends JFrame {

	JPanel image;
	JPanel info;
	JLabel id;
	JLabel title;
	JLabel getImage;

	public RTFrame() {

		setSize(800, 600);
		setTitle("Rotten Tomatoes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		image = new JPanel();
		info = new JPanel();
		
		info.setLayout(new BorderLayout());
		image.setLayout(new BorderLayout());
		//Container cont = new Container();
		//cont.setLayout(new BorderLayout());
		//info.add(cont);
		Container center = new Container();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		
		info.setBorder(new EmptyBorder(10, 10, 10, 10));
		

		id = new JLabel();
		center.add(id);

		title = new JLabel();
		center.add(title);
		info.add(center, BorderLayout.CENTER);
		
		container.add(image, BorderLayout.EAST);
		container.add(info, BorderLayout.WEST);

		getImage = new JLabel();
		image.add(getImage, BorderLayout.CENTER);

		InfoDownloadThread thread = new InfoDownloadThread(this);
		thread.start();

	}

	public static void main(String[] args) {

		RTFrame frame = new RTFrame();
		frame.setVisible(true);
	}

	public void displayInfo(GetResponse response) {
		Movies[] movies = response.getMovies();
		id.setText("ID: " + movies[0].getId());
		title.setText("Title: " + movies[0].getTitle());
		ImageDownloadThread image = new ImageDownloadThread(getImage, movies[0].getPosters().getThumbnail());
		image.start();

	}

}
