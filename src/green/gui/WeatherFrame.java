package green.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class WeatherFrame extends JFrame {

	public WeatherFrame() {
		setSize(800, 600);
		setTitle("Current Weather");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		Container container = getContentPane();
		Container northContainer = new Container();
		northContainer.setLayout(new FlowLayout());
		northContainer.add(new JLabel ("Hello World"));
		northContainer.add(new JLabel ("Hello More World"));
		
		//BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
		BorderLayout layout = new BorderLayout();
		container.setLayout(layout);
		//container.add(new JLabel ("Hello World"), BorderLayout.NORTH);
		//container.add(new JLabel ("Hello More World"), BorderLayout.NORTH);
		container.add(northContainer , BorderLayout.NORTH);
		container.add(new JLabel ("This class rocks!"), BorderLayout.SOUTH);
		container.add(new JLabel ("East"), BorderLayout.EAST);
		container.add(new JLabel ("Happy Birthday"), BorderLayout.WEST);
		
		JLabel label = new JLabel("Thanks");
		label.setBackground(Color.GREEN);
		label.setOpaque(true);
		container.add(label, BorderLayout.CENTER);
		
		

	}

	public static void main(String[] args) {

		WeatherFrame frame = new WeatherFrame();
		frame.setVisible(true);

	}

}
