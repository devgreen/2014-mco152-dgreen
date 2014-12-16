package green.boggle;

import java.awt.Container;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BoggleBoard extends JFrame {

	public BoggleBoard() {
		GridLayout board = new GridLayout(4, 4);
		setSize(800, 600);
		setTitle("Boggle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		Container rows = getContentPane();
		rows.setLayout(board);
		Random r = new Random();

		for (int i = 0; i < 16; i++) {

			/*
			 * String letter = Integer.toString(r.nextInt(26) + 'a');
			 * rows.add(new JButton (letter));
			 */

			String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ";

			int character = (int) (Math.random() * 26);

			String s = alphabet.substring(character, character + 1);

			rows.add(new JButton("Button " + i + " \n" + s));
			//rows.setLocation(i, i+1);

		}
	}

	public static void main(String[] args) {
		BoggleBoard boggle = new BoggleBoard();
		boggle.setVisible(true);

	}

}
