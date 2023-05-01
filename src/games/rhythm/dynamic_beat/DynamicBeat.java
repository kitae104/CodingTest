package games.rhythm.dynamic_beat;

import javax.swing.*;
import java.awt.*;

public class DynamicBeat extends JFrame {

    private Image screenImage;              // 더블 버퍼링을 위한 이미지
    private Graphics screenGraphic;         // 더블 버퍼링을 위한 그래픽 객체
    private Image introBackground;          // 배경 이미지

    public DynamicBeat() {
        setTitle("Dynamic Beat");                           // 게임 이름 설정
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);   // 1280x720 화면 크기 설정
        setResizable(false);                                // 사용자가 게임 창의 크기를 조절하지 못하도록 설정
        setLocationRelativeTo(null);                        // 게임 창이 컴퓨터 정 중앙에 뜨도록 함
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // 게임 창을 종료했을 때 프로그램 전체가 종료되도록 설정
        setVisible(true);                                   // 게임 창이 정상적으로 출력되도록 설정

        // 배경 화면 설정 - 더블 버퍼링 사용
        introBackground = new ImageIcon("images/introBackground.jpg").getImage();   // 이미지를 가져옴

        // 시작 음악 설정
        Music introMusic = new Music("introMusic.mp3", true);                  // 음악을 가져옴
        introMusic.start();                                                                     // 음악을 실행
    }

    public void paint(Graphics g){
        screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // screenImage에 화면 크기만큼의 이미지를 생성
        screenGraphic = screenImage.getGraphics();                          // screenImage의 그래픽 객체를 얻어옴
        screenDraw(screenGraphic);                                          // screenGraphic에 우리가 작성한 paint() 메소드를 넣어줌
        g.drawImage(screenImage, 0, 0, null);           // screenImage를 (0, 0) 좌표에 그려줌
    }

    public void screenDraw(Graphics g) {
        g.drawImage(introBackground, 0, 0, null);       // introBackground를 (0, 0) 좌표에 그려줌
        this.repaint();                                                     // paint() 메소드를 계속해서 반복해서 호출
    }
}
