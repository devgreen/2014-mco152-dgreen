package green.connect4;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Connect4Frame extends JFrame {

	public Connect4Frame() {

		// GridLayout board = new GridLayout(6,7);
		setSize(800, 600);
		setTitle("Connect 4");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// Container container = getContentPane();
		Container rows = getContentPane();
		rows.setLayout(new GridLayout(6, 7));

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JButton button = (JButton) event.getSource();
				button.setBackground(Color.PINK);
			}

		};

		for (int i = 0; i < 42; i++) {
			final JButton button = new JButton("Button " + i);
			rows.add(button);

			button.addActionListener(listener);

		}
	}

	public static void main(String[] args) {

		Connect4Frame frame = new Connect4Frame();
		frame.setVisible(true);

	}

}
