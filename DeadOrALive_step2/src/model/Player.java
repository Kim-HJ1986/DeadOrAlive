package model;

public class Player extends Thing {

	/**
	 * @param xPos 수평위치
	 * @param velocity 좌우 움직이는 속도
	 * @param score 기본 점수
	 */
	public Player(int xPos, double velocity, int score) {
		super(xPos, 0,  velocity, score);
	}
}
