package wordle.gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class Grid extends JPanel {

	private final JLabel[][] cells;

	// variáveis de estilo
	private final int GAP = 5;
	private final int PADDING = 50;
	private final Font textFont = new Font("Helvetica", Font.BOLD, 44);
	private final Color GREEN = new Color(1,154,1);
	private final Color YELLOW = new Color(255,196,37);
	private final Border BORDER_GRAY = BorderFactory.createLineBorder(Color.DARK_GRAY);
	private final Border BORDER_GREEN = BorderFactory.createLineBorder(GREEN);
	private final Border BORDER_YELLOW = BorderFactory.createLineBorder(YELLOW);

	public Grid(final int WORD_LENGTH, final int CHANCES) {

		GridLayout grid = new GridLayout(CHANCES, WORD_LENGTH, GAP, GAP);
		cells = new JLabel[CHANCES][WORD_LENGTH];

		setLayout(grid);
		setBackground(Color.BLACK);
		setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));

		for (int i = 0; i < CHANCES; i++) {
			for (int j = 0; j < WORD_LENGTH; j++) {
				cells[i][j] = new JLabel();
				cells[i][j].setFont(textFont);
				cells[i][j].setForeground(Color.WHITE);
				cells[i][j].setBackground(Color.BLACK);
				cells[i][j].setOpaque(true);
				cells[i][j].setBorder(BORDER_GRAY);
				cells[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				add(cells[i][j]);
			}
		}

	}

	public void updateGrid(String guess, char[] feedback, int attempt) {
		final char green = 'o';
		final char yellow = '%';
		final char gray = 'x';

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
					cells[attempt][i].setBackground(Color.DARK_GRAY);
					cells[attempt][i].setText(String.valueOf(guess.charAt(i)));
					break;
			}
		}

	}
}
