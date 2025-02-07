package wordle.stats;

import wordle.gui.WordleFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StatsGui extends JFrame {

	private final WordleFrame wordleFrame;
	private final GameStats stats;

	private final JPanel mainPanel;

	private final JPanel titleSection;
	private final JLabel feedbackLabel;
	private final JLabel answerLabel;

	private final JPanel statsPanel;
	private final JLabel subtitleLabel;
	private final JPanel progressPanel;


	private final Font TITLE_FONT = new Font("Helvetica", Font.BOLD, 36);
	private final Font ANSWER_FONT = new Font("Helvetica", Font.PLAIN, 16);
	private final Font SUBTITLE_FONT = new Font("Helvetica", Font.BOLD, 27);
	private final Font STATS_FONT = new Font("Helvetica", Font.BOLD, 22);
	private final Font DESC_STATS_FONT = new Font("Helvetica", Font.PLAIN, 14);
	private final Font BUTTON_FONT = new Font("Helvetica", Font.BOLD, 18);

	public StatsGui(boolean isWin, String answer, WordleFrame wordleFrame) {
		this.wordleFrame = wordleFrame;
		this.stats = new GameStats();

		ImageIcon favicon = new ImageIcon("resources/favicon.png");
		setIconImage(favicon.getImage());

		setTitle("Wordle");
		setSize(460, 460);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
		setResizable(false);


		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		mainPanel.setBackground(Color.BLACK);
		add(mainPanel);



		titleSection = new JPanel(new BorderLayout());
		titleSection.setBackground(Color.BLACK);
		mainPanel.add(titleSection, BorderLayout.NORTH);

		feedbackLabel = new JLabel(isWin ? "Você Acertou!" : "Você Errou :(", SwingConstants.CENTER);
		feedbackLabel.setForeground(Color.WHITE);
		feedbackLabel.setFont(TITLE_FONT);
		feedbackLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		feedbackLabel.setBackground(Color.BLACK);
		feedbackLabel.setOpaque(true);

		answerLabel = new JLabel("A palavra era WORDLE!", SwingConstants.CENTER);
		answerLabel.setForeground(Color.WHITE);
		answerLabel.setFont(ANSWER_FONT);
		answerLabel.setBackground(Color.BLACK);
		answerLabel.setOpaque(true);

		titleSection.add(feedbackLabel, BorderLayout.NORTH);
		titleSection.add(answerLabel, BorderLayout.SOUTH);



		statsPanel = new JPanel(new BorderLayout());
		statsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
		statsPanel.setBackground(Color.BLACK);
		mainPanel.add(statsPanel, BorderLayout.CENTER);

		subtitleLabel = new JLabel("Progresso", SwingConstants.CENTER);
		subtitleLabel.setForeground(Color.WHITE);
		subtitleLabel.setFont(SUBTITLE_FONT);
		subtitleLabel.setBorder(BorderFactory.createEmptyBorder(60, 0, 5, 0));
		subtitleLabel.setBackground(Color.BLACK);
		subtitleLabel.setOpaque(true);
		statsPanel.add(subtitleLabel, BorderLayout.NORTH);

		progressPanel = new JPanel(new GridLayout(1, 4, 20, 20));
		progressPanel.setBackground(Color.BLACK);
		progressPanel.add(createStatsPanel(Integer.toString(stats.gamesPlayed), "partidas"));
		progressPanel.add(createStatsPanel(Integer.toString(stats.victoryPercent) + "%", "de vitórias"));
		progressPanel.add(createStatsPanel(Integer.toString(stats.streak), "sequência"));
		progressPanel.add(createStatsPanel(Integer.toString(stats.bestStreak), "melhor\nsequência"));
		statsPanel.add(progressPanel, BorderLayout.SOUTH);



		JButton playAgainButton = new JButton("Jogar Novamente");
		playAgainButton.setFont(BUTTON_FONT);
		playAgainButton.setBackground(Color.BLACK);
		playAgainButton.setForeground(Color.WHITE);
		playAgainButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		playAgainButton.setFocusPainted(false);
		playAgainButton.setPreferredSize(new Dimension(150, 40));
		playAgainButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mainPanel.add(playAgainButton, BorderLayout.SOUTH);


		playAgainButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wordleFrame.resetGame();
				dispose();
			}
		});

	}

	private JPanel createStatsPanel(String value, String description) {
		JPanel statsPanel = new JPanel();
		statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
		statsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		statsPanel.setBackground(Color.BLACK);

		JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
		valueLabel.setFont(STATS_FONT);
		valueLabel.setForeground(Color.WHITE);
		valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		valueLabel.setBackground(Color.BLACK);
		valueLabel.setOpaque(true);

		JPanel descPanel = createDescPanel(description);
		statsPanel.add(valueLabel);
		statsPanel.add(descPanel);

		return statsPanel;
	}

	private JPanel createDescPanel(String text) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.setBackground(Color.BLACK);

		String[] lines = text.split("\n");
		for (String line : lines) {
			JLabel descLabel = new JLabel(line, SwingConstants.CENTER);
			descLabel.setFont(DESC_STATS_FONT);
			descLabel.setForeground(Color.GRAY);
			descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			descLabel.setBackground(Color.BLACK);
			descLabel.setOpaque(true);
			panel.add(descLabel);
		}

		return panel;
	}

}
