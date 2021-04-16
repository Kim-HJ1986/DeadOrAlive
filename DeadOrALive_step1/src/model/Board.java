package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
	// 보드 크기
	public static final int WIDTH = 1000;
	public static final int HEIGHT = (int) (WIDTH / 1.6);
	// 캐릭터 기본 위치
	public static final int START_X_POS_OF_PLAYER = WIDTH / 2;	//상수 이름은 단어별로 _ 로 구분한다
	// 한 면을 걸을 때 걸리는 총 시간(초)
	private static final int TIME = 80;
	// 면의 길이와 시간으로 산출한 속도.
	public static final int SPEED_BOOM_APPLE = HEIGHT / TIME;
	public static final int SPEED_CHARACTER = WIDTH / TIME;

	private List<Thing> things = new ArrayList<>();

	public void load(Thing thing) { // player, bomb, apple을 통칭시켜서 기억
		things.add(thing);
	}
}
