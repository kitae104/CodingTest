package games.rhythm.dynamic_beat;

public class Main {

    public static final int SCREEN_WIDTH = 1280;  // 1280x720 화면 크기 설정
    public static final int SCREEN_HEIGHT = 720;
    public static final int NOTE_SPEED = 7;      // 노트가 떨어지는 속도
    public static final int SLEEP_TIME = 10;     // 노트가 떨어지는 주기
    public static void main(String[] args) {    	
        new DynamicBeat();
    }
}
