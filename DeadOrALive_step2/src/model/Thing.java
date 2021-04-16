package model;

import controll.DeadOrAliveController;

public abstract class Thing {
	// 위치
	private int xPos, yPos;
	// 속도
	private double velocity;
	// 점수 
	private int score;
	
	/** 플레이어의 상위클래스 생성자 */
	public Thing(int xPos, int yPos, double velocity, int score) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.velocity = velocity;
		this.score = score;
	}

	/** 사과, 폭탄의 상위클래스 생성자*/
	public Thing(int yPos, double velocity, int score) {
		this.xPos = (int) (Math.random() * Board.WIDTH); // x 좌표는 보드 width값 이내의 랜덤값을 줌.
		this.yPos = yPos;
		this.velocity = velocity;
		this.score = score;
	}
	
}
