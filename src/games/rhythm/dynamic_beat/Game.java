package games.rhythm.dynamic_beat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game extends Thread {

    //===============================
    // 게임 실행 관련 이미지
    //===============================
    private Image gameInfoImage = new ImageIcon("images/new/gameInfo.png").getImage();
    private Image judgementLineImage = new ImageIcon("images/new/judgementLine.png").getImage();
    private Image noteRouteLineImage = new ImageIcon("images/new/noteRouteLine.png").getImage();
    private Image noteRouteSImage = new ImageIcon("images/new/noteRoute.png").getImage();
    private Image noteRouteDImage = new ImageIcon("images/new/noteRoute.png").getImage();
    private Image noteRouteFImage = new ImageIcon("images/new/noteRoute.png").getImage();
    private Image noteRouteSpace1Image = new ImageIcon("images/new/noteRoute.png").getImage();
    private Image noteRouteSpace2Image = new ImageIcon("images/new/noteRoute.png").getImage();
    private Image noteRouteJImage = new ImageIcon("images/new/noteRoute.png").getImage();
    private Image noteRouteKImage = new ImageIcon("images/new/noteRoute.png").getImage();
    private Image noteRouteLImage = new ImageIcon("images/new/noteRoute.png").getImage();
    private Image blueFlareImage;
    private Image judgeImage;

    //===============================
    // 키 패드 이미지
    //===============================
    private Image keyPadSImage = new ImageIcon("images/new/keyPadBasic.png").getImage();
    private Image keyPadDImage = new ImageIcon("images/new/keyPadBasic.png").getImage();
    private Image keyPadFImage = new ImageIcon("images/new/keyPadBasic.png").getImage();
    private Image keyPadSpace1Image = new ImageIcon("images/new/keyPadBasic.png").getImage();
    private Image keyPadSpace2Image = new ImageIcon("images/new/keyPadBasic.png").getImage();
    private Image keyPadJImage = new ImageIcon("images/new/keyPadBasic.png").getImage();
    private Image keyPadKImage = new ImageIcon("images/new/keyPadBasic.png").getImage();
    private Image keyPadLImage = new ImageIcon("images/new/keyPadBasic.png").getImage();

    //===============================
    // 게임 실행 관련 변수
    //===============================
    private String titleName;       // 게임 제목
    private String difficulty;      // 게임 난이도
    private String musicTitle;      // 게임 음악 제목
    private Music gameMusic;        // 게임 음악

    ArrayList<Note> noteList = new ArrayList<Note>(); // 노트를 담을 리스트

    /**
     * 생성자
     *
     * @param titleName
     * @param difficulty
     */
    public Game(String titleName, String difficulty, String musicTitle) {
        this.titleName = titleName;
        this.difficulty = difficulty;
        this.musicTitle = musicTitle;
        gameMusic = new Music(this.musicTitle, false);

    }

    public void screenDraw(Graphics2D g) {
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

        // 판단 선 그리기
        g.drawImage(gameInfoImage, 0, 660, null); // 게임 정보 이미지를 (0, 660) 좌표에 그려줌
        g.drawImage(judgementLineImage, 0, 580, null); // 게임 정보 이미지를 (0, 660) 좌표에 그려줌

        // 노트는 판단선 위로 올라오므로 판단선을 먼저 그려줘야 함
        for (int i = 0; i < noteList.size(); i++) {
            Note note = noteList.get(i);
            if(note.getY() > 620) {
                judgeImage = new ImageIcon("images/new/judgeMiss.png").getImage();
            }

            if(!note.isProceeded()) {
                noteList.remove(i);         // 노트가 판정선을 지나가지 않았다면 노트를 제거
                i--;
            } else {
                note.screenDraw(g);         // 노트가 판정선을 지나갔다면 노트를 그려줌
            }
        }

        // 노래 제목 출력
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); // 글자 깨짐 방지
        g.setColor(Color.white); // 글자 색상을 흰색으로 설정
        g.setFont(new Font("D2Coding", Font.BOLD, 30));  // 글자 폰트를 Arial, 굵게, 30 크기로 설정
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

        g.drawImage(blueFlareImage, 320, 300, null);
        g.drawImage(judgeImage, 530, 430, null);
        g.drawImage(keyPadSImage, 228, 580, null);
        g.drawImage(keyPadDImage, 332, 580, null);
        g.drawImage(keyPadFImage, 436, 580, null);
        g.drawImage(keyPadSpace1Image, 540, 580, null);
        g.drawImage(keyPadSpace2Image, 640, 580, null);
        g.drawImage(keyPadJImage, 744, 580, null);
        g.drawImage(keyPadKImage, 848, 580, null);
        g.drawImage(keyPadLImage, 952, 580, null);
    }

    @Override
    public void run() {
        dropNotes();
    }

    public void pressS() {
        judge("S");
        noteRouteSImage = new ImageIcon("images/new/noteRoutePressed.png").getImage();
        keyPadSImage = new ImageIcon("images/new/keyPadPressed.png").getImage();
        new Music("drum/drumSmall1.mp3", false).start();
    }

    public void releaseS() {
        noteRouteSImage = new ImageIcon("images/new/noteRoute.png").getImage();
        keyPadSImage = new ImageIcon("images/new/keyPadBasic.png").getImage();
    }

    public void pressD() {
        judge("D");
        noteRouteDImage = new ImageIcon("images/new/noteRoutePressed.png").getImage();
        keyPadDImage = new ImageIcon("images/new/keyPadPressed.png").getImage();
        new Music("drum/drumSmall1.mp3", false).start();
    }

    public void releaseD() {
        noteRouteDImage = new ImageIcon("images/new/noteRoute.png").getImage();
        keyPadDImage = new ImageIcon("images/new/keyPadBasic.png").getImage();
    }

    public void pressF() {
        judge("F");
        noteRouteFImage = new ImageIcon("images/new/noteRoutePressed.png").getImage();
        keyPadFImage = new ImageIcon("images/new/keyPadPressed.png").getImage();
        new Music("drum/drumSmall1.mp3", false).start();
    }

    public void releaseF() {
        noteRouteFImage = new ImageIcon("images/new/noteRoute.png").getImage();
        keyPadFImage = new ImageIcon("images/new/keyPadBasic.png").getImage();
    }

    public void pressSpace() {
        judge("Space");
        noteRouteSpace1Image = new ImageIcon("images/new/noteRoutePressed.png").getImage();
        noteRouteSpace2Image = new ImageIcon("images/new/noteRoutePressed.png").getImage();
        keyPadSpace1Image = new ImageIcon("images/new/keyPadPressed.png").getImage();
        keyPadSpace2Image = new ImageIcon("images/new/keyPadPressed.png").getImage();
        new Music("drum/drumBig1.mp3", false).start();
    }

    public void releaseSpace() {
        noteRouteSpace1Image = new ImageIcon("images/new/noteRoute.png").getImage();
        noteRouteSpace2Image = new ImageIcon("images/new/noteRoute.png").getImage();
        keyPadSpace1Image = new ImageIcon("images/new/keyPadBasic.png").getImage();
        keyPadSpace2Image = new ImageIcon("images/new/keyPadBasic.png").getImage();
    }

    public void pressJ() {
        judge("J");
        noteRouteJImage = new ImageIcon("images/new/noteRoutePressed.png").getImage();
        keyPadJImage = new ImageIcon("images/new/keyPadPressed.png").getImage();
        new Music("drum/drumSmall1.mp3", false).start();
    }

    public void releaseJ() {
        noteRouteJImage = new ImageIcon("images/new/noteRoute.png").getImage();
        keyPadJImage = new ImageIcon("images/new/keyPadBasic.png").getImage();
    }

    public void pressK() {
        judge("K");
        noteRouteKImage = new ImageIcon("images/new/noteRoutePressed.png").getImage();
        keyPadKImage = new ImageIcon("images/new/keyPadPressed.png").getImage();
        new Music("drum/drumSmall1.mp3", false).start();
    }

    public void releaseK() {
        noteRouteKImage = new ImageIcon("images/new/noteRoute.png").getImage();
        keyPadKImage = new ImageIcon("images/new/keyPadBasic.png").getImage();
    }

    public void pressL() {
        judge("L");
        noteRouteLImage = new ImageIcon("images/new/noteRoutePressed.png").getImage();
        keyPadLImage = new ImageIcon("images/new/keyPadPressed.png").getImage();
        new Music("drum/drumSmall1.mp3", false).start();
    }

    public void releaseL() {
        noteRouteLImage = new ImageIcon("images/new/noteRoute.png").getImage();
        keyPadLImage = new ImageIcon("images/new/keyPadBasic.png").getImage();
    }

    /**
     * 게임 음악을 종료하고, 스레드를 종료하는 메소드
     */
    public void close() {
        gameMusic.close();      // 게임 음악 종료
        this.interrupt();       // 스레드 종료
    }

    public void dropNotes() {
        Beat[] beats = null;

        if(titleName.equals("IAM - IVE") && difficulty.equals("Easy")){
            int startTime = 4460 - Main.REACH_TIME * 1000;
            int gap = 125;
            beats = new Beat[]{
                    new Beat(startTime,"S"),
                    new Beat(startTime+gap*2, "S"),
                    new Beat(startTime+gap*4, "D"),
                    new Beat(startTime+gap*6, "F"),
                    new Beat(startTime+gap*8, "Space"),
                    new Beat(startTime+gap*10, "J"),
                    new Beat(startTime+gap*12, "K"),
                    new Beat(startTime+gap*14, "L"),
                    new Beat(startTime+gap*18, "Space"),
                    new Beat(startTime+gap*20, "Space"),
                    new Beat(startTime+gap*22, "Space"),
                    new Beat(startTime+gap*24, "K"),
                    new Beat(startTime+gap*26, "J"),
                    new Beat(startTime+gap*28, "K"),
                    new Beat(startTime+gap*30, "J"),
                    new Beat(startTime+gap*32, "K"),
                    new Beat(startTime+gap*36, "S"),
                    new Beat(startTime+gap*38, "D"),
                    new Beat(startTime+gap*40, "S"),
                    new Beat(startTime+gap*42, "D"),
                    new Beat(startTime+gap*44, "S"),
                    new Beat(startTime+gap*46, "D"),
                    new Beat(startTime+gap*48, "S"),
                    new Beat(startTime+gap*50, "D"),
                    new Beat(startTime+gap*52, "S"),
                    new Beat(startTime+gap*54, "D"),
                    new Beat(startTime+gap*56, "S"),
                    new Beat(startTime+gap*58, "D"),
                    new Beat(startTime+gap*60, "S"),
                    new Beat(startTime+gap*62, "D"),
                    new Beat(startTime+gap*64, "S"),

            };
        } else if(titleName.equals("IAM - IVE") && difficulty.equals("Hard")){
            int startTime = 1000;
            beats = new Beat[]{
                new Beat(startTime, "Space"),
            };
        } else if(titleName.equals("Ditto-New Jeans")&& difficulty.equals("Easy")){
            int startTime = 1000;
            beats = new Beat[]{
                new Beat(startTime, "Space"),
            };
        } else if(titleName.equals("Ditto-New Jeans")&& difficulty.equals("Hard")){
            int startTime = 1000;
            beats = new Beat[]{
                new Beat(startTime, "Space"),
            };
        } else if(titleName.equals("Dynamite-BTS")&& difficulty.equals("Easy")){
            int startTime = 1000;
            beats = new Beat[]{
                    new Beat(startTime, "Space"),
            };
        } else if(titleName.equals("Dynamite-BTS")&& difficulty.equals("Hard")){
            int startTime = 1000;
            beats = new Beat[]{
                    new Beat(startTime, "Space"),
            };
        }
        // 게임 음악이 시작되기 전에 노트가 생성되는 것을 방지하기 위해 1초 정도 쉬어줌
        gameMusic.start();

        int i = 0;
        while(i < beats.length && !isInterrupted()) {
            boolean dropped = false;
            if (beats[i].getTime() <= gameMusic.getTime()) {
                Note note = new Note(beats[i].getNoteName());
                note.start();
                noteList.add(note);
                i++;
                dropped = true;
            }
            if(!dropped) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 노트 판정을 실행하는 메소드
     * @param input
     */
    public void judge(String input) {
        for(int i = 0; i < noteList.size(); i++) {
            Note note = noteList.get(i);
            if(input.equals(note.getNoteType())) {      // 노트 판정이 성공했을 때
                judgeEvent(note.judge());
                break;
            }
        }
    }

    public void judgeEvent(String judge){
        if(!judge.equals("None")) {
            blueFlareImage = new ImageIcon("images/new/blueFlare.png").getImage();
        }

        if(judge.equals("Miss")) {
            judgeImage = new ImageIcon("images/new/judgeMiss.png").getImage();
        } else if(judge.equals("Late")) {
            judgeImage = new ImageIcon("images/new/judgeLate.png").getImage();
        } else if(judge.equals("Good")) {
            judgeImage = new ImageIcon("images/new/judgeGood.png").getImage();
        } else if(judge.equals("Great")) {
            judgeImage = new ImageIcon("images/new/judgeGreat.png").getImage();
        } else if(judge.equals("Perfect")) {
            judgeImage = new ImageIcon("images/new/judgePerfect.png").getImage();
        } else if(judge.equals("Early")) {
            judgeImage = new ImageIcon("images/new/judgeEarly.png").getImage();
        }
        //judgeImageY = 580;
        //new Music("drum/judge.mp3", false).start();
    }




}
