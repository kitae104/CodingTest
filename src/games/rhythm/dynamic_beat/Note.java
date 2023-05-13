package games.rhythm.dynamic_beat;

import javax.swing.*;
import java.awt.*;

public class Note extends Thread {

    private Image noteBasicImage = new ImageIcon("images/new/noteBasic.png").getImage();
    private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
    private String noteType;
    private boolean proceeded = true;

    public int getY() {
        return y;                       // 노트의 y좌표를 반환
    }

    public String getNoteType() {
        return noteType;                // 노트 타입을 반환
    }
    public boolean isProceeded() {
        return proceeded;               // 노트가 판정바를 지나갔는지 확인
    }

    public void close() {
        proceeded = false;              // 노트가 판정바를 지나갔다면 proceeded를 false로 만듦
    }

    public Note(String noteType) {
        if(noteType.equals("S")) {              // 노트 타입이 S면
            x = 228;
        } else if(noteType.equals("D")) {       // 노트 타입이 D면
            x = 332;
        } else if(noteType.equals("F")) {       // 노트 타입이 F면
            x = 436;
        } else if(noteType.equals("Space")) {   // 노트 타입이 Space면
            x = 540;
        } else if(noteType.equals("J")) {       // 노트 타입이 J면
            x = 744;
        } else if(noteType.equals("K")) {       // 노트 타입이 K면
            x = 848;
        } else if(noteType.equals("L")) {       // 노트 타입이 L면
            x = 952;
        }
        this.noteType = noteType;
    }

    public void screenDraw(Graphics2D g) {
        // 노트 타입에 따라 노트 이미지를 그려줌
        if(!noteType.equals("Space")) {                             // 노트 타입이 Space가 아니면
            g.drawImage(noteBasicImage, x, y,null);
        } else {                                                    // 노트 타입이 Space면
            g.drawImage(noteBasicImage, x, y, null);
            g.drawImage(noteBasicImage, x+100, y, null);
        }
    }

    public void drop() {
        y += Main.NOTE_SPEED;

        if(y > 620) {                       // 노트가 판정바를 지나가면
            System.out.println("Miss");
            close();
        }
    }

    @Override
    public void run() {
        try{
            while(true) {
                drop();
                if(proceeded) {
                    Thread.sleep(Main.SLEEP_TIME);
                } else {
                    interrupt();        // 해당 스레드를 중지 상태로 만듦
                    break;
                }
            }
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     * 판단해주는 메소드
     */
    public String judge() {
        if(y >= 613){                       // 노트가 판정바를 지나가면
            System.out.println("Late");
            close();
            return "Late";
        } else if(y >= 600){
            System.out.println("Good");
            close();
            return "Good";
        } else if(y >= 587){
            System.out.println("Great");
            close();
            return "Great";
        } else if(y >= 573){
            System.out.println("Perfect");
            close();
            return "Perfect";
        } else if(y >= 565){
            System.out.println("Great");
            close();
            return "Great";
        } else if(y >= 550){
            System.out.println("Good");
            close();
            return "Good";
        } else if(y >= 535){
            System.out.println("Early");
            close();
            return "Early";
        }
        return "None";      // 판정이 안됐을 경우
    }
}
