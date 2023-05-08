package games.rhythm.dynamic_beat;

import javax.swing.*;
import java.awt.*;

public class Game extends Thread {

    //===============================
    // 게임 실행 관련 이미지
    //===============================
    private Image gameInfoImage = new ImageIcon("images/new/gameInfo.png").getImage();
    private Image judgementLineImage = new ImageIcon("images/new/judgementLine.png").getImage();
    private Image noteRouteLineImage = new ImageIcon("images/new/noteRouteLine.png").getImage();
    private Image noteBasicImage = new ImageIcon("images/new/noteBasic.png").getImage();
    private Image noteRouteSImage = new ImageIcon("images/new/noteRoute.png").getImage();
    private Image noteRouteDImage = new ImageIcon("images/new/noteRoute.png").getImage();
    private Image noteRouteFImage = new ImageIcon("images/new/noteRoute.png").getImage();
    private Image noteRouteSpace1Image = new ImageIcon("images/new/noteRoute.png").getImage();
    private Image noteRouteSpace2Image = new ImageIcon("images/new/noteRoute.png").getImage();
    private Image noteRouteJImage = new ImageIcon("images/new/noteRoute.png").getImage();
    private Image noteRouteKImage = new ImageIcon("images/new/noteRoute.png").getImage();
    private Image noteRouteLImage = new ImageIcon("images/new/noteRoute.png").getImage();

    private String titleName;       // 게임 제목
    private String difficulty;      // 게임 난이도
    private String musicTitle;      // 게임 음악 제목
    private Music gameMusic;        // 게임 음악

    /**
     * 생성자
     * @param titleName
     * @param difficulty
     */
    public Game(String titleName, String difficulty, String musicTitle) {
        this.titleName = titleName;
        this.difficulty = difficulty;
        this.musicTitle = musicTitle;
        gameMusic = new Music(this.musicTitle, false);
        gameMusic.start();
    }

    public void screenDraw(Graphics2D g){
        g.drawImage(noteRouteSImage, 228, 30, null); // 노트 라인 이미지를 (240, 30) 좌표에 그려줌
        g.drawImage(noteRouteDImage, 332, 30, null);
        g.drawImage(noteRouteFImage, 436, 30, null);
        g.drawImage(noteRouteSpace1Image, 540, 30, null);
        g.drawImage(noteRouteSpace2Image, 640, 30, null);
        g.drawImage(noteRouteJImage, 744, 30, null);
        g.drawImage(noteRouteKImage, 848, 30, null);
        g.drawImage(noteRouteLImage, 952, 30, null);

        g.drawImage(noteRouteLineImage, 224, 30, null); // 노트 라인 이미지를 (240, 30) 좌표에 그려줌
        g.drawImage(noteRouteLineImage, 328, 30, null);
        g.drawImage(noteRouteLineImage, 432, 30, null);
        g.drawImage(noteRouteLineImage, 536, 30, null);
        g.drawImage(noteRouteLineImage, 740, 30, null);
        g.drawImage(noteRouteLineImage, 844, 30, null);
        g.drawImage(noteRouteLineImage, 948, 30, null);
        g.drawImage(noteRouteLineImage, 1052, 30, null);

        g.drawImage(gameInfoImage, 0, 660, null); // 게임 정보 이미지를 (0, 660) 좌표에 그려줌
        g.drawImage(judgementLineImage, 0, 580, null); // 게임 정보 이미지를 (0, 660) 좌표에 그려줌

        g.drawImage(noteBasicImage, 228, 120, null); // 노트 이미지를 (228, 120) 좌표에 그려줌
        g.drawImage(noteBasicImage, 332, 580, null);
        g.drawImage(noteBasicImage, 436, 500, null);
        g.drawImage(noteBasicImage, 540, 340, null);
        g.drawImage(noteBasicImage, 640, 340, null);
        g.drawImage(noteBasicImage, 744, 325, null);
        g.drawImage(noteBasicImage, 848, 305, null);
        g.drawImage(noteBasicImage, 952, 305, null);

        // 노래 제목 출력
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); // 글자 깨짐 방지
        g.setColor(Color.white); // 글자 색상을 흰색으로 설정
        g.setFont(new Font("D2Coding", Font.BOLD, 30));    // 글자 폰트를 Arial, 굵게, 30 크기로 설정
        g.drawString(titleName, 20, 702);                       // (20, 702) 좌표에 titleName을 그려줌
        g.drawString(difficulty, 1190, 702);
        g.setFont(new Font("D2Coding", Font.PLAIN, 26));
        g.setColor(Color.DARK_GRAY);

        g.drawString("S", 270, 609);
        g.drawString("D", 374, 609);
        g.drawString("F", 478, 609);
        g.drawString("Space Bar", 580, 609);
        g.drawString("J", 784, 609);
        g.drawString("K", 889, 609);
        g.drawString("L", 993, 609);
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("D2Coding", Font.BOLD, 30));
        g.drawString("000000", 565, 702);


    }

    @Override
    public void run(){

    }

    public void pressS() {
        noteRouteSImage = new ImageIcon("images/new/noteRoutePressed.png").getImage();
        new Music("drum/drumSmall1.mp3", false).start();
    }

    public void releaseS() {
        noteRouteSImage = new ImageIcon("images/new/noteRoute.png").getImage();
    }

    public void pressD() {
        noteRouteDImage = new ImageIcon("images/new/noteRoutePressed.png").getImage();
        new Music("drum/drumSmall1.mp3", false).start();
    }
    public void releaseD() {
        noteRouteDImage = new ImageIcon("images/new/noteRoute.png").getImage();
    }
    public void pressF() {
        noteRouteFImage = new ImageIcon("images/new/noteRoutePressed.png").getImage();
        new Music("drum/drumSmall1.mp3", false).start();
    }
    public void releaseF() {
        noteRouteFImage = new ImageIcon("images/new/noteRoute.png").getImage();
    }
    public void pressSpace() {
        noteRouteSpace1Image = new ImageIcon("images/new/noteRoutePressed.png").getImage();
        noteRouteSpace2Image = new ImageIcon("images/new/noteRoutePressed.png").getImage();
        new Music("drum/drumBig1.mp3", false).start();
    }
    public void releaseSpace() {
        noteRouteSpace1Image = new ImageIcon("images/new/noteRoute.png").getImage();
        noteRouteSpace2Image = new ImageIcon("images/new/noteRoute.png").getImage();
    }
    public void pressJ() {
        noteRouteJImage = new ImageIcon("images/new/noteRoutePressed.png").getImage();
        new Music("drum/drumSmall1.mp3", false).start();
    }
    public void releaseJ() {
        noteRouteJImage = new ImageIcon("images/new/noteRoute.png").getImage();
    }
    public void pressK() {
        noteRouteKImage = new ImageIcon("images/new/noteRoutePressed.png").getImage();
        new Music("drum/drumSmall1.mp3", false).start();
    }
    public void releaseK() {
        noteRouteKImage = new ImageIcon("images/new/noteRoute.png").getImage();
    }
    public void pressL() {
        noteRouteLImage = new ImageIcon("images/new/noteRoutePressed.png").getImage();
        new Music("drum/drumSmall1.mp3", false).start();
    }
    public void releaseL() {
        noteRouteLImage = new ImageIcon("images/new/noteRoute.png").getImage();
    }

    /**
     * 게임 음악을 종료하고, 스레드를 종료하는 메소드
     */
    public void close() {
        gameMusic.close();      // 게임 음악 종료
        this.interrupt();       // 스레드 종료
    }
}
