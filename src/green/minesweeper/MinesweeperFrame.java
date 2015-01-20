package green.minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MinesweeperFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int COL = 30;
	private static final int ROW = 16;
	private JButton cells[][];
	private Map<JButton, Boolean> map = new HashMap<JButton, Boolean>();
	private JLabel numBombs;
	private JLabel status;

	public MinesweeperFrame() {

		setSize(10000, 500);
		setTitle("MineSweeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container game = getContentPane();
		game.setLayout(new BorderLayout());
		Container container = new Container();
		container.setLayout(new GridLayout(ROW, COL));
		game.add(container, BorderLayout.CENTER);
		Container north = new Container();
		north.setLayout(new BorderLayout());
		north.add(new JLabel("Bombs remaining"), BorderLayout.WEST);
		numBombs = new JLabel();
		north.add(numBombs, BorderLayout.CENTER);
		status = new JLabel("Game in Progress");
		north.add(status, BorderLayout.EAST);
		game.add(north, BorderLayout.NORTH);

		ActionListener next = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JButton button = (JButton) event.getSource();
				Location location;
				for (int i = 0; i < ROW; i++) {
					for (int j = 0; j < COL; j++) {
						JButton getButton = cells[i][j];
						if (getButton == button) {
							if (map.get(getButton)){
								button.setText("B");
								status.setText("Game Over");
								for (int k=0; k<ROW; k++){
									for (int l= 0; l< COL; l++){
										JButton toDisable = cells[k][l];
										if(map.get(toDisable)){
											toDisable.setBackground(Color.RED);
										}
										toDisable.setEnabled(false);
										
									}
								}
								
							}
							else {
						 location = new Location(i, j);
						 button.setText(String.valueOf(getNumBombs(location.getI(), location.getJ())));
						 break;
							}
						 
						
						}
					}
				}
				
				
			}
		};

		Random random = new Random();
		cells = new JButton[ROW][COL];
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				final JButton button = new JButton();
				cells[i][j] = button;
				button.addActionListener(next);
				container.add(button);
				// button.addActionListener(next);
				int n = random.nextInt(480);
				if (n < 99) {
					map.put(cells[i][j], true);
					//button.setBackground(Color.RED);
				} else {
					map.put(cells[i][j], false);
				}

			}
		}
	}

	public int getNumBombs(int i, int j) {

		int bombs = 0;
		if (hasBomb(i - 1, j - 1)) {
			bombs++;
		}
		if (hasBomb(i, j - 1)) {
			bombs++;
		}
		if (hasBomb(i + 1, j - 1)) {
			bombs++;
		}
		if (hasBomb(i - 1, j)) {
			bombs++;
		}
		if (hasBomb(i + 1, j)) {
			bombs++;
		}
		if (hasBomb(i - 1, j + 1)) {
			bombs++;
		}
		if (hasBomb(i, j + 1)) {
			bombs++;
		}
		if (hasBomb(i + 1, j + 1)) {
			bombs++;
		}
		return bombs;
	}

	private boolean hasBomb(int i, int j) {
		try {
			return map.get(cells[i][j]);
		} catch (Exception e) {
			return false;
		}
	}

//	public Location getLoc() {
//		Location location = null;
//		for (int i = 0; i < ROW; i++) {
//			for (int j = 0; j < COL; j++) {
//				JButton getButton = cells[i][j];
//				if (getButton == button) {
//				 location = new Location(i, j);
//				 return location;
//				
//				}
//			}
//		}
//		return location;
//
//	}

	public static void main(String[] args) {
		MinesweeperFrame frame = new MinesweeperFrame();
		frame.setVisible(true);

	}
}
