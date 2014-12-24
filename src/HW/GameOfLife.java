package HW;

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

public class GameOfLife extends JFrame {

	private static final int COL = 10;
	private static final int ROW = 10;
	private JButton cells[][];
	private Map<JButton, Boolean> map = new HashMap<JButton, Boolean>();
	private Boolean status;

	public GameOfLife() {

		Container game = getContentPane();
		game.setLayout(new BorderLayout());
		setSize(800, 600);
		setTitle("Game of Life");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		Container container = new Container();
		container.setLayout(new GridLayout(COL, ROW));

		ActionListener next = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				
				nextGeneration();
			}
		};

		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JButton button = (JButton) event.getSource();
				for (int i = 0; i < COL; i++) {
					for (int j = 0; j < ROW; j++) {
						if (map.get(cells[COL][ROW])) {
							button.setBackground(Color.GREEN);
						} else {
							button.setBackground(Color.BLACK);
						}

					}
				}

			}
		};

		JButton nextButton = new JButton("next");
		nextButton.addActionListener(next);
		game.add(nextButton, BorderLayout.WEST);
		game.add(container, BorderLayout.CENTER);

		Random random = new Random();
		cells = new JButton[COL][ROW];
		for (int i = 0; i < COL; i++) {
			for (int j = 0; j < ROW; j++) {
				final JButton button = new JButton();
				cells[i][j] = button;
				container.add(button);
				button.addActionListener(listener);
				int n = random.nextInt(100);
				if (n < 30) {
					button.setBackground(Color.GREEN);
				} else {
					button.setBackground(Color.BLACK);
				}
				Boolean cell = map.get(cells[i][j]);
				if (cell == null) {
					map.put(cells[i][j], false);

				}
			}
		}
	}

	public void nextGeneration() {

		for (int i = 0; i < COL; i++) {
			for (int j = 0; j < ROW; j++) {

				int neighbors = getNumAliveNeighbors(i, j);
				 status = map.get(cells[i][j]);
				switch (neighbors) {

				case 0:
				case 1:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
					// status = map.get(cells[i][j]);
					status = false;
					//cells[i][j].setBackground(Color.BLACK);
					
					break;
				case 2:
					//status = map.get(cells[i][j]);
					if (isAlive(i, j) == true) {
						status = true;
					} else {
						status = false;
					}
					break;
				case 3:
					//status = map.get(cells[i][j]);
					status = true;
					// status = true;
					//cells[i][j].setBackground(Color.GREEN);
					
					break;
				}
			}
		}
	}

	public int getNumAliveNeighbors(int i, int j) {

		int numAlive = 0;
		if (isAlive(i - 1, j - 1)) {
			numAlive++;
		}
		if (isAlive(i, j - 1)) {
			numAlive++;
		}
		if (isAlive(i + 1, j - 1)) {
			numAlive++;
		}
		if (isAlive(i - 1, j)) {
			numAlive++;
		}
		if (isAlive(i + 1, j)) {
			numAlive++;
		}
		if (isAlive(i - 1, j + 1)) {
			numAlive++;
		}
		if (isAlive(i, j + 1)) {
			numAlive++;
		}
		if (isAlive(i + 1, j + 1)) {
			numAlive++;
		}
		return numAlive;
	}

	private boolean isAlive(int i, int j) {
		try {
			return cells[i - 1][j - 1].getBackground() == Color.GREEN;
		} catch (Exception e) {
			return false;
		}
	}

	public static void main(String[] args) {
		GameOfLife frame = new GameOfLife();
		frame.setVisible(true);
	}
}