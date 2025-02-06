package wordle.gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class Grid extends JPanel {

	private final JLabel[][] cells;

	private final Font textFont = new Font("Helvetica", Font.BOLD, 44);

	private Border BORDER_GREEN;
	private Border BORDER_YELLOW;
	private Border BORDER_DARK_GRAY;
	private Border BORDER_WINNER_GRAY;

	private Color GREEN;
	private Color YELLOW;
	private Color DARK_GRAY;
	private Color WINNER_GRAY;

	public Grid(final int WORD_LENGTH, final int CHANCES, final int GAP) {

		GridLayout grid = new GridLayout(CHANCES, WORD_LENGTH, GAP, GAP);
		cells = new JLabel[CHANCES][WORD_LENGTH];

		setLayout(grid);
		setBackground(Color.BLACK);
		setBorder(new EmptyBorder(50, 50, 50, 50));

		for (int i = 0; i < CHANCES; i++) {
			for (int j = 0; j < WORD_LENGTH; j++) {

				cells[i][j] = new JLabel();
				cells[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				cells[i][j].setOpaque(true);

				cells[i][j].setFont(textFont);
				cells[i][j].setForeground(Color.WHITE);
				cells[i][j].setBackground(Color.BLACK);
				cells[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

				add(cells[i][j]);
			}
		}

	}

	public void setColors(Color correct, Color positionRight, Color wrong, Color winner) {
		GREEN = correct;
		YELLOW = positionRight;
		DARK_GRAY = wrong;
		WINNER_GRAY = winner;

		BORDER_GREEN = BorderFactory.createLineBorder(GREEN);
		BORDER_YELLOW = BorderFactory.createLineBorder(YELLOW);
		BORDER_DARK_GRAY = BorderFactory.createLineBorder(DARK_GRAY);
		BORDER_WINNER_GRAY = BorderFactory.createLineBorder(WINNER_GRAY);
	}

	public void updateGrid(String guess, char[] feedback, int attempt, boolean isWin) {
		final char green = 'o';
		final char yellow = '%';
		final char gray = 'x';

		if (isWin) {
			for (int i = attempt; i < cells.length; i ++) {
				for (int j = 0; j < cells[0].length; j++) {
					cells[attempt][j].setBackground(GREEN);
					cells[attempt][j].setText(String.valueOf(guess.charAt(j)));
					cells[attempt][j].setBorder(BORDER_GREEN);
				}
			}

			for (int i = attempt + 1; i < cells.length; i ++) {
				for (int j = 0; j < cells[0].length; j++) {
					cells[i][j].setBackground(WINNER_GRAY);
					cells[i][j].setBorder(BORDER_WINNER_GRAY);
				}
			}

		} else {
			for (int i = 0; i < cells[attempt].length; i++) {
				switch (feedback[i]) {
					case green:
						cells[attempt][i].setBackground(GREEN);
						cells[attempt][i].setText(String.valueOf(guess.charAt(i)));
						cells[attempt][i].setBorder(BORDER_GREEN);
						break;
					case yellow:
						cells[attempt][i].setBackground(YELLOW);
						cells[attempt][i].setText(String.valueOf(guess.charAt(i)));
						cells[attempt][i].setBorder(BORDER_YELLOW);
						break;
					case gray:
						cells[attempt][i].setBackground(DARK_GRAY);
						cells[attempt][i].setText(String.valueOf(guess.charAt(i)));
						cells[attempt][i].setBorder(BORDER_DARK_GRAY);
						break;
				}
			}

		}

	}

}
