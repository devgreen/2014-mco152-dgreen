package green.minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MinesweeperFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int COL = 30;
	private static final int ROW = 16;
	private JButton cells[][];
	private Map<JButton, List<Boolean>> map = new HashMap<JButton, List<Boolean>>();
	private JLabel numBombs;
	private JLabel status;
	private int countBombs;
	private JButton restart;
	private Container container;
	private int spacesClicked;
	int turn = 0;

	// private JLabel spaces;

	public MinesweeperFrame() {

		setSize(10000, 500);
		setTitle("MineSweeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container game = getContentPane();
		game.setLayout(new BorderLayout());
		container = new Container();
		container.setLayout(new GridLayout(ROW, COL));
		game.add(container, BorderLayout.CENTER);
		Container north = new Container();
		north.setLayout(new BoxLayout(north, BoxLayout.X_AXIS));
		north.add(new JLabel("Bombs remaining:  "), BorderLayout.WEST);
		numBombs = new JLabel("99");
		north.add(numBombs);
		status = new JLabel("    Game in Progress   ");
		north.add(status);
		restart = new JButton("   Restart  ");
		north.add(restart);
		// spaces = new JLabel("spaces");
		// north.add(spaces);
		game.add(north, BorderLayout.NORTH);
		setUp();

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
			return map.get(cells[i][j]).get(0);
		} catch (Exception e) {
			return false;
		}
	}

	public void emptySpace(int x, int y) {
		if (x < 0 || x > 15 || y < 0 || y > 29) {
			return;
		}

		if (getNumBombs(x, y) == 0 && !map.get(cells[x][y]).get(1)) {
			boolean bStatus = map.get(cells[x][y]).get(0);
			ArrayList<Boolean> values = new ArrayList<Boolean>();
			values.add(bStatus);
			values.add(true);
			map.put(cells[x][y], values);
			cells[x][y].setEnabled(false);

			// System.out.println(x + " " + y);
			spacesClicked++;

			// spaces.setText(String.valueOf(spacesClicked));
			if (spacesClicked == 381) {
				status.setText("  you won!  ");
			}
			emptySpace(x + 1, y);
			emptySpace(x - 1, y);
			emptySpace(x, y - 1);
			emptySpace(x, y + 1);
			emptySpace(x - 1, y - 1);
			emptySpace(x + 1, y + 1);
			emptySpace(x + 1, y - 1);
			emptySpace(x - 1, y + 1);
		} else if (getNumBombs(x, y) != 0) {

			if (cells[x][y].getText().equals("")) {
				// System.out.println(x + " " + y);
				spacesClicked++;
			}
			cells[x][y].setText(String.valueOf(getNumBombs((x), y)));
			cells[x][y].setEnabled(false);
			// spaces.setText(String.valueOf(spacesClicked));
			if (spacesClicked == 381) {
				status.setText("  you won!  ");
				disable();

			}
			return;
		}
	}

	public void setUp() {

		getBoard();

		ActionListener restartGame = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				reset();
				turn = 0;
				// setBombs();
			}
		};
		restart.addActionListener(restartGame);
	}

	public void getBoard() {
		ActionListener next = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

				JButton button = (JButton) event.getSource();
				if (turn == 0) {
					setBombs(button);
					turn++;
				}
				Location location;
				for (int i = 0; i < ROW; i++) {
					for (int j = 0; j < COL; j++) {
						JButton getButton = cells[i][j];
						if (getButton == button) {
							if (map.get(getButton).get(0)) {
								status.setText("    Game Over   ");
								for (int k = 0; k < ROW; k++) {
									for (int l = 0; l < COL; l++) {
										JButton toDisable = cells[k][l];
										if (map.get(toDisable).get(0)) {
											toDisable.setBackground(Color.RED);
										}
										toDisable.setEnabled(false);

									}
								}

							} else {
								location = new Location(i, j);
								int num = getNumBombs(location.getI(), location.getJ());
								if (num == 0) {
									emptySpace(location.getI(), location.getJ());
									// spacesClicked++;
									if (spacesClicked == 381) {
										status.setText("  you won!  ");
										disable();
									}
								} else {
									button.setText(String.valueOf(getNumBombs(location.getI(), location.getJ())));
									button.setEnabled(false);
									spacesClicked++;
									// spaces.setText(String.valueOf(spacesClicked));
									if (spacesClicked == 381) {
										status.setText("  you won!  ");
										disable();

									}
									break;
								}

							}

						}

					}
				}
			}

		};

		cells = new JButton[ROW][COL];
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				final JButton button = new JButton();
				cells[i][j] = button;
				button.addActionListener(next);

				button.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {

						if (e.getButton() == MouseEvent.BUTTON3) {
							JButton button = (JButton) e.getSource();
							if (button.getText().equals("?")) {
								button.setText("");
								numBombs.setText(String.valueOf(++countBombs));

							} else {
								button.setText("?");
								numBombs.setText(String.valueOf(--countBombs));

							}
						}
					}
				});

				container.add(button);

				ArrayList<Boolean> values = new ArrayList<Boolean>();
				values.add(false);
				values.add(false);
				map.put(cells[i][j], values);

			}
		}
		// setBombs();
		numBombs.setText(String.valueOf(countBombs));

	}

	public void setBombs(JButton button) {

		int setBombs = 0;
		Random random = new Random();

		while (setBombs < 99) {
			int x = random.nextInt(ROW);
			int y = random.nextInt(COL);

			if (!map.get(cells[x][y]).get(0) && cells[x][y] != button) {
				ArrayList<Boolean> values = new ArrayList<Boolean>();
				values.add(true);
				values.add(false);
				map.put(cells[x][y], values);
				// cells[x][y].setBackground(Color.RED);
				countBombs++;
				setBombs++;

			}

		}

	}

	public void reset() {
		countBombs = 0;
		spacesClicked = 0;
		// spaces.setText("");
		numBombs.setText(String.valueOf(99));
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				JButton button = cells[i][j];
				cells[i][j].setBackground(null);
				cells[i][j].setText("");
				if (!cells[i][j].isEnabled()) {
					cells[i][j].setEnabled(true);

				}
				ArrayList<Boolean> values = new ArrayList<Boolean>();
				values.add(false);
				values.add(false);
				map.put(button, values);

			}
		}
	}

	public void disable() {

		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				if (cells[i][j].isEnabled()) {
					cells[i][j].setEnabled(false);
				}

			}

		}

	}

	public static void main(String[] args) {
		MinesweeperFrame frame = new MinesweeperFrame();
		frame.setVisible(true);

	}

}
