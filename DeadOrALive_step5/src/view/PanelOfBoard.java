package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import controll.DeadOrAliveController;
import model.Board;

public class PanelOfBoard extends JPanel implements ActionListener {
	private Board board;
	private JLabel lbl4Score;
	private JButton btnStart;

	// 캐릭터, 사과, 폭탄, 게임종료 아이콘 만들어주기
	ImageIcon PlayerIcon = new ImageIcon("Character.png");
	ImageIcon AppleIcon = new ImageIcon("Apple.png");
	ImageIcon BombIcon = new ImageIcon("Bomb.png");
	ImageIcon gameoverIcon = new ImageIcon("Gameover.png");

	// 아이콘을 담을 라벨 만들기
	JLabel lbl4Player = new JLabel();
	JLabel lbl4Apple = new JLabel();
	JLabel lbl4Bomb = new JLabel();
	JLabel lbl4Gameover = new JLabel();

	
	private Timer timer;
	private int basicScore = DeadOrAliveController.BASIC_SCORE;

	PanelOfBoard(Board board, JLabel lbl4Score, JButton btnStart) {
		// 버튼을 눌렀을때 리스너가 실행되도록
		btnStart.addActionListener(this);
		
		// 점수를 담아줄 라벨
		this.lbl4Score = lbl4Score;
		lbl4Score.setText("" + DeadOrAliveController.BASIC_SCORE);

		this.setLayout(null); // 어디든 만들어지도록 레이아웃 null

		// 플레이어 아이콘설정
		lbl4Player.setIcon(PlayerIcon); // 아이콘 설치
		lbl4Player.setLocation(Board.WIDTH / 2 - (PlayerIcon.getIconWidth() / 2),
				Board.HEIGHT - PlayerIcon.getIconHeight() - 70); // 아이콘 초기 위치 설정 (맨아래 중앙)
		lbl4Player.setSize(200, 150); // 아이콘 size 정의
		this.add(lbl4Player);

		// 사과 아이콘 설정
		lbl4Apple.setIcon(AppleIcon); // 아이콘 설치
		lbl4Apple.setSize(100, 100); // 아이콘 size 정의
		lbl4Apple.setLocation((int) ((Board.WIDTH - lbl4Apple.getWidth()) * Math.random()), 0); // 아이콘 초기 위치 설정 보드 범위 내
		this.add(lbl4Apple);

		// 폭탄 아이콘 설정
		lbl4Bomb.setIcon(BombIcon); // 아이콘 설치
		lbl4Bomb.setSize(100, 100); // 아이콘 size 정의
		lbl4Bomb.setLocation((int) ((Board.WIDTH - lbl4Bomb.getWidth()) * Math.random()), 0); // 아이콘 초기 위치 설정 보드 범위 내 랜덤
		this.add(lbl4Bomb);

		// 게임 오버 아이콘 설정
		lbl4Gameover.setIcon(gameoverIcon);
		lbl4Gameover.setLocation(Board.WIDTH * 2, Board.HEIGHT * 2);
		lbl4Gameover.setSize(400, 300);
		this.add(lbl4Gameover);

		// 좌우 움직일 수 있도록 keyListener 장착
		
	}

	/**
	 * 플레이어의 범위 안에 폭탄이 들어오는지 확인
	 * 
	 * @param BombX
	 * @param BombY
	 * @param playerX
	 * @param playerY
	 * @return
	 */
	protected boolean checkEatBomb(int BombX, int BombY, int playerX, int playerY) {
		return (((int) (playerX + PlayerIcon.getIconWidth() / 2) >= BombX)
				&& ((int) (playerX - PlayerIcon.getIconWidth() / 2) <= BombX)
				&& ((int) (playerY + PlayerIcon.getIconHeight() / 2) >= BombY)
				&& ((int) (playerY - PlayerIcon.getIconHeight() / 2) <= BombY));
	}

	/**
	 * 플레이어의 범위 안에 사과가 들어오는지 확인
	 * 
	 * @param appleX
	 * @param appley
	 * @param playerX
	 * @param playerY
	 * @return
	 */
	protected boolean checkEatApple(int appleX, int appley, int playerX, int playerY) {
		return (((int) (playerX + PlayerIcon.getIconWidth() / 2) >= appleX)
				&& ((int) (playerX - PlayerIcon.getIconWidth() / 2) <= appleX)
				&& ((int) (playerY + PlayerIcon.getIconHeight() / 2) >= appley)
				&& ((int) (playerY - PlayerIcon.getIconHeight() / 2) <= appley));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
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
		
		timer = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 사과가 범위 벗어났을 경우 랜덤한 x좌표에서 다시 생성
				if (lbl4Apple.getY() >= Board.HEIGHT) {
					lbl4Apple.setLocation((int) ((Board.WIDTH - lbl4Apple.getWidth()) * Math.random()), 0);
				}

				// 사과와 충돌이 일어났을 경우 점수 추가
				if (checkEatApple(lbl4Apple.getX(), lbl4Apple.getY(), lbl4Player.getX(), lbl4Player.getY())) {
					lbl4Apple.setLocation((int) ((Board.WIDTH - lbl4Apple.getWidth()) * Math.random()), 0);
					// 점수 추가
					basicScore += 3;
					lbl4Score.setText("" + basicScore);
				} else
					lbl4Apple.setLocation(lbl4Apple.getX(), lbl4Apple.getY() + 10);

				// 폭탄이 범위를 벗어났을 경우 랜덤한 x좌표에서 다시 생성
				if (lbl4Bomb.getY() >= Board.HEIGHT) {
					lbl4Bomb.setLocation((int) ((Board.WIDTH - lbl4Bomb.getWidth()) * Math.random()), 0);
				}
				// 폭탄과 충돌이 일어났을 경우 점수 읽기
				if (checkEatBomb(lbl4Bomb.getX(), lbl4Bomb.getY(), lbl4Player.getX(), lbl4Player.getY())) {
					lbl4Bomb.setLocation((int) ((Board.WIDTH - lbl4Bomb.getWidth()) * Math.random()), 0);
					// 점수 잃기
					basicScore -= 5;
					lbl4Score.setText("" + basicScore);
					// 점수가 0점 밑으로 떨어졌을경우 게임 중지
					if (basicScore < 0) {
						timer.stop();
						lbl4Gameover.setLocation(Board.WIDTH / 2 - lbl4Gameover.getWidth() / 2,
								Board.HEIGHT / 2 - lbl4Gameover.getHeight() / 2);
					}
				} else
					lbl4Bomb.setLocation(lbl4Bomb.getX(), lbl4Bomb.getY() + 10);
			}

		});
		timer.start();
	}
}
