package wordle.gui;

import wordle.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Grid extends JPanel {

	private JLabel[][] cells;

	private final int GAP = 5;
	private final int PADDING = 50;

	public Grid(final int WORD_LENGTH, final int CHANCES) {
		GridLayout grid = new GridLayout(CHANCES, WORD_LENGTH, GAP, GAP);
		cells = new JLabel[CHANCES][WORD_LENGTH];

		setLayout(grid);
		setBackground(Color.BLACK);
		setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));

		for (int i = 0; i < CHANCES; i++) {
			for (int j = 0; j < WORD_LENGTH; j++) {
				cells[i][j] = new JLabel();
				cells[i][j].setBackground(Color.BLACK);
				cells[i][j].setOpaque(true);
				cells[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				cells[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				add(cells[i][j]);
			}
		}
	}

	public void updateGrid() {

	}
}
