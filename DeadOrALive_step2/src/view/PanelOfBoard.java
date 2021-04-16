package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Board;

public class PanelOfBoard extends JPanel {
	private Board board;
	// 캐릭터 아이콘 만들어주기
	ImageIcon playerIcon = new ImageIcon("Character.png");
	JLabel lbl4Player = new JLabel();

	PanelOfBoard(Board board) {
		this.board = board;
		this.setLayout(null); // 어디든 만들어지도록 레이아웃 null

		lbl4Player.setIcon(playerIcon); // 아이콘 설치
		lbl4Player.setLocation(Board.WIDTH / 2 - (playerIcon.getIconWidth() / 2)
				, Board.HEIGHT - playerIcon.getIconHeight() - 50); // 아이콘 초기 위치 설정 (맨아래 중앙)
		System.out.println("Player = " + lbl4Player.getX());
		lbl4Player.setSize(200, 150); // 아이콘 size 정의

		this.add(lbl4Player);
		
		// 좌우 움직일 수 있도록 keyListener 장착  
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if (lbl4Player.getX() < -(lbl4Player.getWidth() / 7)) {
						lbl4Player.setLocation(lbl4Player.getX() + 20, lbl4Player.getY());
					}
					lbl4Player.setLocation(lbl4Player.getX() - Board.SPEED_CHARACTER, lbl4Player.getY());
					break;
				case KeyEvent.VK_RIGHT:
					if (lbl4Player.getX() > Board.WIDTH - lbl4Player.getWidth()) {
						lbl4Player.setLocation(lbl4Player.getX() - 20, lbl4Player.getY());
					}
					lbl4Player.setLocation(lbl4Player.getX() + Board.SPEED_CHARACTER, lbl4Player.getY());
					break;
				}
				
			}
		});
		this.requestFocus();
		this.setFocusable(true);
	}
}

