package games.rhythm.dynamic_beat;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Music extends Thread{

    private Player player;      // 자바줌 라이브러리의 Player 클래스 변수
    private boolean isLoop;     // 현재 곡이 반복재생인지 아닌지를 나타내는 변수
    private File file;          // 음악 파일을 불러올 때 사용할 File 클래스 변수
    private FileInputStream fis;    // 파일을 불러올 때 사용할 FileInputStream 클래스 변수
    private BufferedInputStream bis; // 파일을 불러올 때 사용할 BufferedInputStream 클래스 변수

    public Music(String name, boolean isLoop) {
        try {
            this.isLoop = isLoop;   // isLoop 변수를 초기화
            file = new File("music/" + name); // 음악 파일을 불러옴
            fis = new FileInputStream(file);    // 파일을 불러옴
            bis = new BufferedInputStream(fis); // 파일을 불러옴
            player = new Player(bis);           // player에 음악 파일을 넣음
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getTime() {  // 현재 실행되고 있는 음악의 재생 시간을 반환하는 함수
        if (player == null) // player가 null이라면 0을 반환
            return 0;
        return player.getPosition();    // player의 현재 재생 위치를 반환
    }

    public void close(){
        isLoop = false;     // isLoop 변수를 false로 설정
        player.close();     // player를 종료
        this.interrupt();   // 현재 스레드를 중지 상태로 만듦
    }

    @Override
    public void run() {
        try {
            do {
                player.play();                      // player를 실행
                fis = new FileInputStream(file);    // 음악 파일을 불러옴
                bis = new BufferedInputStream(fis); // 음악 파일을 불러옴
                player = new Player(bis);           // player에 음악 파일을 넣음
            } while (isLoop);                       // isLoop 변수가 true라면 반복 실행
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
