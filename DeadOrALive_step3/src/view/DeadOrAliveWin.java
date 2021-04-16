package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Board;

public class DeadOrAliveWin extends JFrame {
	private Board board;
	
	public DeadOrAliveWin(Board board) {
		this.setTitle("DeadOrAlive");
		this.setSize(Board.WIDTH, Board.HEIGHT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 상단 - 점수판 왼쪽상단 배치
		JPanel pnl4Score = new JPanel(new GridLayout(0, 2));
		JLabel lbl4Score = new JLabel("점수판");
		Font s = new Font("Serif", Font.ITALIC, 30);
		lbl4Score.setFont(s);
		pnl4Score.add(lbl4Score);
		this.add(pnl4Score, BorderLayout.NORTH);

		// 중단 - 게임이 돌아갈 보드판넬 배치
		this.add(new PanelOfBoard(board), BorderLayout.CENTER);

		this.setVisible(true);
	}
}
