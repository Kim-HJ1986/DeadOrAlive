package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
	// 보드 크기
	public static final int HEIGHT = 1000;
	public static final int WIDTH = (int) (HEIGHT / 1.6);
	// 캐릭터 기본 위치
	public static final int XPOSOFCHARACTER = WIDTH / 2;
	// 한 면을 걸을 때 걸리는 총 시간(초)
	private static final int TIME_BOMB_APPLE = 200;
	private static final int TIME_CHARACTER = 30;
	// 면의 길이와 시간으로 산출한 속도.
	public static final int SPEED_BOMB_APPLE = HEIGHT / TIME_BOMB_APPLE;
	public static final int SPEED_PLAYER = WIDTH / TIME_CHARACTER;

	private List<Thing> things = new ArrayList<>();

	public void load(Thing thing) { // player, bomb, apple을 통칭시켜서 기억
		things.add(thing);
	}
}
