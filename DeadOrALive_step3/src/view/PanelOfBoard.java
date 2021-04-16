package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import controll.DeadOrAliveController;
import model.Board;
import model.Thing;

public class PanelOfBoard extends JPanel {
	private Board board;
	private JLabel lbl4Score;

	// 캐릭터, 사과, 폭탄, 게임종료 아이콘 만들어주기
	ImageIcon PlayerIcon = new ImageIcon("Character.png");
	ImageIcon AppleIcon = new ImageIcon("Apple.png");
	ImageIcon BombIcon = new ImageIcon("Bomb.png");

	// 아이콘을 담을 라벨 만들기
	JLabel lbl4Player = new JLabel();
	JLabel lbl4Apple = new JLabel();
	JLabel lbl4Bomb = new JLabel();

	private Timer timer;

	PanelOfBoard(Board board) {
		this.setLayout(null); // 어디든 만들어지도록 레이아웃 null

		// 플레이어 아이콘설정
		lbl4Player.setIcon(PlayerIcon); // 아이콘 설치
		lbl4Player.setLocation(Board.WIDTH / 2 - (PlayerIcon.getIconWidth() / 2),
				Board.HEIGHT - PlayerIcon.getIconHeight() - 70); // 아이콘 초기 위치 설정 (맨아래 중앙)
		lbl4Player.setSize(200, 150); // 아이콘 size 정의
		this.add(lbl4Player);

		// 사과 아이콘 설정
		lbl4Apple.setIcon(AppleIcon); // 아이콘 설치
		lbl4Apple.setLocation((int) ((Board.WIDTH - lbl4Apple.getWidth()) * Math.random()) , 0); // 아이콘 초기 위치 설정 보드 범위 내
		lbl4Apple.setSize(100, 100); // 아이콘 size 정의
		this.add(lbl4Apple);

		// 폭탄 아이콘 설정
		lbl4Bomb.setIcon(BombIcon); // 아이콘 설치
		lbl4Bomb.setLocation((int) ((Board.WIDTH - lbl4Bomb.getWidth()) * Math.random()), 0); // 아이콘 초기 위치 설정 보드 범위 내 랜덤
		lbl4Bomb.setSize(100, 100); // 아이콘 size 정의
		this.add(lbl4Bomb);

		// 좌우 움직일 수 있도록 keyListener 장착
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if (lbl4Player.getX() < 0) {
						lbl4Player.setLocation(lbl4Player.getX() + 20, lbl4Player.getY());
					}
					lbl4Player.setLocation(lbl4Player.getX() - Board.SPEED_PLAYER, lbl4Player.getY());
					break;
				case KeyEvent.VK_RIGHT:
					if (lbl4Player.getX() > Board.WIDTH - lbl4Player.getWidth() + 20) {
						lbl4Player.setLocation(lbl4Player.getX() - 20, lbl4Player.getY());
					}
					lbl4Player.setLocation(lbl4Player.getX() + Board.SPEED_PLAYER, lbl4Player.getY());
					break;
				}
			}
		});
		this.requestFocus();
		this.setFocusable(true);

		// 스윙 타이머를 이용하여 떨어지는 사과와 폭탄 이벤트 만들기
		timer = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 사과가 범위 벗어났을 경우 랜덤한 x좌표에서 다시 생성
				if (lbl4Apple.getY() >= Board.HEIGHT) {
					lbl4Apple.setLocation((int) ((Board.WIDTH - lbl4Apple.getWidth()) * Math.random()), 0);
				} else
					lbl4Apple.setLocation(lbl4Apple.getX(), lbl4Apple.getY() + Board.SPEED_BOMB_APPLE);

				// 폭탄이 범위를 벗어났을 경우 랜덤한 x좌표에서 다시 생성
				if (lbl4Bomb.getY() >= Board.HEIGHT) {
					lbl4Bomb.setLocation((int) ((Board.WIDTH - lbl4Bomb.getWidth()) * Math.random()), 0);
				} else
					lbl4Bomb.setLocation(lbl4Bomb.getX(), lbl4Bomb.getY() + Board.SPEED_BOMB_APPLE);
			}
		});
		timer.start();
	}
}
