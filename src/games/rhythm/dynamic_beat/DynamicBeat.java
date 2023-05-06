package games.rhythm.dynamic_beat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DynamicBeat extends JFrame {

    private final Music introMusic; // 시작 음악
    private Graphics screenGraphic; // 더블 버퍼링을 위한 그래픽 객체
    private Image screenImage; // 더블 버퍼링을 위한 이미지
    private Image background; // 배경 이미지

    private JButton exitButton; // 나가기 버튼
    private JButton startButton; // 시작 버튼
    private JButton quitButton; // 종료 버튼

    private int mouseX, mouseY; // 마우스의 x, y 좌표

    private ImageIcon[] icons =
    { // 버튼 이미지
        new ImageIcon("images/new/exit.png"),
        new ImageIcon("images/new/exit_entered.png"),
        new ImageIcon("images/play.png"),
        new ImageIcon("images/start.png"),
        new ImageIcon("images/pause.png"),
        new ImageIcon("images/stop.png"),
        new ImageIcon("images/new/leftBasic.png"),
        new ImageIcon("images/new/leftEntered.png"),
        new ImageIcon("images/new/rightBasic.png"),
        new ImageIcon("images/new/rightEntered.png")
    };

    private ImageIcon[] musicTitleImage =
    {
        new ImageIcon("images/games/ive_title.png"),
        new ImageIcon("images/games/newjeans_title.png"),
        new ImageIcon("images/games/bts_title.png")
    };

    private ImageIcon[] musicMainImage =
    {
        new ImageIcon("images/games/ive_main.png"),
        new ImageIcon("images/games/newjeans_main.png"),
        new ImageIcon("images/games/bts_main.png"),
    };

    private ImageIcon[] musicSelectImage =
    {
        new ImageIcon("images/games/ive_select.png"),
        new ImageIcon("images/games/newjeans_select.png"),
        new ImageIcon("images/games/bts_select.png"),
    };

    private boolean isMainScreen;
    private JButton leftButton;
    private JButton rightButton;

    // 게임 화면에서 사용할 변수들
    private ArrayList<Track> trackList = new ArrayList<Track>();  // 곡 목록
    private Image titleImage; // 타이틀 이미지
    private Music selectedMusic; // 선택된 곡
    private Image selectedImage; // 선택된 이미지

    private int nowSelected = 0; // 현재 선택된 곡

    //===============================
    // 난이도 버튼
    //===============================
    private ImageIcon easyButtonEnteredImage = new ImageIcon("images/new/easyButtonEntered.png");
    private ImageIcon easyButtonBasicImage = new ImageIcon("images/new/easyButtonBasic.png");
    private ImageIcon hardButtonEnteredImage = new ImageIcon("images/new/hardButtonEntered.png");
    private ImageIcon hardButtonBasicImage = new ImageIcon("images/new/hardButtonBasic.png");

    private JButton easyButton = new JButton(easyButtonBasicImage);
    private JButton hardButton = new JButton(hardButtonBasicImage);

    //===============================
    // 뒤로 돌아가기 버튼
    //===============================
    private ImageIcon backButtonEnteredImage = new ImageIcon("images/new/backButtonEntered.png");
    private ImageIcon backButtonBasicImage = new ImageIcon("images/new/backButtonBasic.png");

    private JButton backButton = new JButton(backButtonBasicImage);

    /**
     * DynamicBeat 클래스 생성자
     */
    public DynamicBeat() {
        //=======================================================================================
        // 게임 창 설정 - 기본 프레임 설정
        //=======================================================================================
        setUndecorated(true);   // 기본적인 메뉴바가 보이지 않도록 설정
        setTitle("Dynamic Beat"); // 게임 이름 설정
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 1280x720 화면 크기 설정
        setResizable(false); // 사용자가 게임 창의 크기를 조절하지 못하도록 설정
        setLocationRelativeTo(null); // 게임 창이 컴퓨터 정 중앙에 뜨도록 함
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임 창을 종료했을 때 프로그램 전체가 종료되도록 설정
        setVisible(true); // 게임 창이 정상적으로 출력되도록 설정
        setBackground(new Color(0, 0, 0, 0)); // paintComponent() 메소드에서 배경을 그리기 위해 설정
        setLayout(null);

        //=======================================================================================
        // 시작 음악 설정
        //=======================================================================================
        introMusic = new Music("introMusic.mp3", true); // 음악을 가져옴
        introMusic.start();

        //=======================================================================================
        // 곡 목록 설정
        //=======================================================================================
        trackList.add(new Track("ive_title.png", "ive_select.png", "ive_main.png", "ive_cut.mp3", "ive.mp3"));
        trackList.add(new Track("newjeans_title.png", "newjeans_select.png", "newjeans_main.png", "newjeans_cut.mp3", "newjeans.mp3"));
        trackList.add(new Track("bts_title.png", "bts_select.png", "bts_main.png", "bts_cut.mp3", "bts.mp3"));

        //=======================================================================================
        // 배경 화면 설정 - 더블 버퍼링 사용
        //=======================================================================================
        background = new ImageIcon("images/introBackground.jpg").getImage(); // 이미지를 가져옴
        selectedImage =  musicSelectImage[0].getImage();
        titleImage = musicTitleImage[0].getImage();
        isMainScreen = false;

        //=======================================================================================
        // 프로그램 나가기 버튼
        //=======================================================================================
        exitButton = new JButton(icons[0]); // 종료 버튼
        exitButton.setRolloverIcon(icons[1]); // 종료 버튼에 마우스를 올렸을 때 이미지
        exitButton.setBorderPainted(false); // 버튼 테두리 설정
        exitButton.setContentAreaFilled(false); // 버튼 영역 배경 설정
        exitButton.setFocusPainted(false); // 버튼 테두리 설정
        exitButton.setBounds(1225, 25, 30, 30); // 종료 버튼의 위치와 크기 설정
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { // 마우스가 버튼 위로 올라갔을 때
                exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서가 손가락 모양으로 변경
            }

            @Override
            public void mouseExited(MouseEvent e) { // 마우스가 버튼에서 내려갔을 때
                exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 커서가 기본 모양으로 변경
            }

            @Override
            public void mousePressed(MouseEvent e) {    // 종료 버튼을 눌렀을 때
                // Music buttonEnterMusic = new Music("buttonEnterMusic.mp3", false); // 버튼 클릭
                // 효과음
                // buttonEnterMusic.start(); // 효과음 재생
                System.exit(0); // 게임 종료
            }
        });
        add(exitButton);

        //=======================================================================================
        // 프로그램 시작 버튼
        //=======================================================================================
        startButton = new JButton(icons[2]); // 종료 버튼
        startButton.setRolloverIcon(icons[3]); // 종료 버튼에 마우스를 올렸을 때 이미지
        startButton.setBorderPainted(false); // 버튼 테두리 설정
        startButton.setContentAreaFilled(false); // 버튼 영역 배경 설정
        startButton.setFocusPainted(false); // 버튼 테두리 설정
        startButton.setBounds(840, 450, 400, 119); // 종료 버튼의 위치와 크기 설정
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { // 마우스가 버튼 위로 올라갔을 때
                startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서가 손가락 모양으로 변경
            }

            @Override
            public void mouseExited(MouseEvent e) { // 마우스가 버튼에서 내려갔을 때
                startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 커서가 기본 모양으로 변경
            }

            @Override
            public void mousePressed(MouseEvent e) { // 종료 버튼을 눌렀을 때
                enterMain();
            }
        });
        add(startButton);

        //=======================================================================================
        // 프로그램 전체 나가기 버튼
        //=======================================================================================
        quitButton = new JButton(icons[4]); // 종료 버튼
        quitButton.setRolloverIcon(icons[5]); // 종료 버튼에 마우스를 올렸을 때 이미지
        quitButton.setBorderPainted(false); // 버튼 테두리 설정
        quitButton.setContentAreaFilled(false); // 버튼 영역 배경 설정
        quitButton.setFocusPainted(false); // 버튼 테두리 설정
        quitButton.setBounds(840, 580, 400, 119); // 종료 버튼의 위치와 크기 설정
        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { // 마우스가 버튼 위로 올라갔을 때
                quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서가 손가락 모양으로 변경
            }

            @Override
            public void mouseExited(MouseEvent e) { // 마우스가 버튼에서 내려갔을 때
                quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 커서가 기본 모양으로 변경
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Music buttonEnterMusic = new Music("buttonEnterMusic.mp3", false); // 버튼 클릭
                // 효과음
                // buttonEnterMusic.start(); // 효과음 재생
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.exit(0); // 게임 종료
            }
        });
        add(quitButton);

        //=======================================================================================
        // 곡 선택 왼쪽 이동 버튼
        //=======================================================================================
        leftButton = new JButton(icons[6]);
        leftButton.setRolloverIcon(icons[7]);
        leftButton.setVisible(false);
        leftButton.setBorderPainted(false); // 버튼 테두리 설정
        leftButton.setContentAreaFilled(false); // 버튼 영역 배경 설정
        leftButton.setFocusPainted(false); // 버튼 테두리 설정
        leftButton.setBounds(140, 310, 52, 52); // 종료 버튼의 위치와 크기 설정
        leftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { // 마우스가 버튼 위로 올라갔을 때
                leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서가 손가락 모양으로 변경
            }

            @Override
            public void mouseExited(MouseEvent e) { // 마우스가 버튼에서 내려갔을 때
                leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 커서가 기본 모양으로 변경
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Music buttonEnterMusic = new Music("buttonEnterMusic.mp3", false); // 버튼 클릭
                // 효과음
                // buttonEnterMusic.start(); // 효과음 재생

                // 왼쪽 버튼 이벤트
                selectLeft();
            }
        });
        add(leftButton);

        //=======================================================================================
        // 곡 선택 오른쪽 이동 버튼
        //=======================================================================================
        rightButton = new JButton(icons[8]);
        rightButton.setRolloverIcon(icons[9]);
        rightButton.setVisible(false);         // 처음엔 보이지 않음
        rightButton.setBorderPainted(false); // 버튼 테두리 설정
        rightButton.setContentAreaFilled(false); // 버튼 영역 배경 설정
        rightButton.setFocusPainted(false); // 버튼 테두리 설정
        rightButton.setBounds(1080, 310, 52, 52); // 종료 버튼의 위치와 크기 설정
        rightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { // 마우스가 버튼 위로 올라갔을 때
                rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서가 손가락 모양으로 변경
            }

            @Override
            public void mouseExited(MouseEvent e) { // 마우스가 버튼에서 내려갔을 때
                rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 커서가 기본 모양으로 변경
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Music buttonEnterMusic = new Music("buttonEnterMusic.mp3", false); // 버튼 클릭
                // 효과음
                // buttonEnterMusic.start(); // 효과음 재생

                // 오른쪽 버튼 이벤트
                selectRight();
            }
        });
        add(rightButton);

        //=======================================================================================
        // 난이도 쉬움 버튼
        //=======================================================================================
        easyButton.setVisible(false);
        easyButton.setBounds(375, 580, 250, 67);
        easyButton.setBorderPainted(false); // 버튼 테두리 설정
        easyButton.setContentAreaFilled(false); // 버튼 영역 배경 설정
        easyButton.setFocusPainted(false); // 버튼 테두리 설정
        easyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { // 마우스가 버튼 위로 올라갔을 때
                easyButton.setIcon(easyButtonEnteredImage);
                easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서가 손가락 모양으로 변경
            }

            @Override
            public void mouseExited(MouseEvent e) { // 마우스가 버튼에서 내려갔을 때
                easyButton.setIcon(easyButtonBasicImage);
                easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 커서가 기본 모양으로 변경
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // 난이도 쉬움 이벤트
                gameStart(nowSelected, "easy");
            }
        });
        add(easyButton);

        //=======================================================================================
        // 난이도 어려움 버튼
        //=======================================================================================
        hardButton.setVisible(false);
        hardButton.setBounds(655, 580, 250, 67);
        hardButton.setBorderPainted(false); // 버튼 테두리 설정
        hardButton.setContentAreaFilled(false); // 버튼 영역 배경 설정
        hardButton.setFocusPainted(false); // 버튼 테두리 설정
        hardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { // 마우스가 버튼 위로 올라갔을 때
                hardButton.setIcon(hardButtonEnteredImage);
                hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서가 손가락 모양으로 변경
            }

            @Override
            public void mouseExited(MouseEvent e) { // 마우스가 버튼에서 내려갔을 때
                hardButton.setIcon(hardButtonBasicImage);
                hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 커서가 기본 모양으로 변경
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // 난이도 어려움 이벤트
                gameStart(nowSelected, "hard");
            }
        });
        add(hardButton);

        //=======================================================================================
        // 뒤로 돌아가기 버튼
        //=======================================================================================
        backButton.setVisible(false);       // 처음엔 보이지 않음
        backButton.setBounds(20, 50, 60, 60);
        backButton.setBorderPainted(false); // 버튼 테두리 설정
        backButton.setContentAreaFilled(false); // 버튼 영역 배경 설정
        backButton.setFocusPainted(false); // 버튼 테두리 설정
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { // 마우스가 버튼 위로 올라갔을 때
                backButton.setIcon(backButtonEnteredImage);
                backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서가 손가락 모양으로 변경
            }

            @Override
            public void mouseExited(MouseEvent e) { // 마우스가 버튼에서 내려갔을 때
                backButton.setIcon(backButtonBasicImage);
                backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 커서가 기본 모양으로 변경
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // 메인 화면으로 돌아가는 버튼
                backMain();
            }
        });
        add(backButton);
    }

    /**
     * 화면에 그림을 그려주는 메소드
     * @param g the specified Graphics window
     */
    public void paint(Graphics g) {
        screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // screenImage에 화면 크기만큼의 이미지를 생성
        screenGraphic = screenImage.getGraphics(); // screenImage의 그래픽 객체를 얻어옴
        screenDraw(screenGraphic); // screenGraphic에 우리가 작성한 paint() 메소드를 넣어줌
        g.drawImage(screenImage, 0, 0, null); // screenImage를 (0, 0) 좌표에 그려줌
    }

    /**
     * 화면에 그려주는 메소드
     * @param g 그래픽 객체
     */
    public void screenDraw(Graphics g) {
        // 단순 이미지를 화면에 보여줄 때 사용
        g.drawImage(background, 0, 0, null); // background를 (0, 0) 좌표에 그려줌
        if (isMainScreen) {
            g.drawImage(selectedImage, 340, 170, null); // 시작 버튼을 (840, 450) 좌표에 그려줌
            g.drawImage(titleImage, 340, 70, null); // 종료 버튼을 (840, 580) 좌표에 그려줌
        }

        // 프레임에 add에 의해 추가된 요소를 보여주는 역할
        paintComponents(g); // 버튼을 그려줌
        this.repaint(); // paint() 메소드를 계속해서 반복해서 호출
    }

    /**
     * 음악 선택하기 메소드
     * @param nowSelected 현재 선택된 곡
     */
    public void selectTrack(int nowSelected) {
        if (selectedMusic != null) {    // 이전에 선택된 음악이 재생되고 있었다면
            selectedMusic.close();        // 음악 종료
        }
        titleImage = new ImageIcon("images/games/" + trackList.get(nowSelected).getTitleImage()).getImage(); // 선택된 곡의 타이틀 이미지를 가져옴
        selectedImage = new ImageIcon("images/games/" + trackList.get(nowSelected).getStartImage()).getImage(); // 선택된 곡의 색상 이미지를 가져옴
        selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true); // 선택된 곡의 음악을 가져옴
        selectedMusic.start();    // 음악 재생
    }

    /**
     * 왼쪽 버튼을 눌렀을 때 이벤트
      */
    public void selectLeft() {
        if (nowSelected == 0) {
            nowSelected = trackList.size() - 1;
        } else {
            nowSelected--;
        }
        selectTrack(nowSelected);
    }

    /**
     * 오른쪽 버튼을 눌렀을 때 이벤트
     */
    public void selectRight() {
        if (nowSelected == trackList.size() - 1) {
            nowSelected = 0;
        } else {
            nowSelected++;
        }
        selectTrack(nowSelected);
    }

    /**
     * 게임 시작 하기
     * @param nowSelected 현재 선택된 곡
     * @param difficulty 난이도
     */
    public void gameStart(int nowSelected, String difficulty){
        if(selectedMusic != null){
            selectedMusic.close();      // 음악 종료
        }

        isMainScreen = false;           // 메인 화면이 아님을 표시

        // 기존의 보이던 버튼을 사라지게 함
        leftButton.setVisible(false);
        rightButton.setVisible(false);
        easyButton.setVisible(false);
        hardButton.setVisible(false);

        // 게임 실행 화면으로 배경 변경하기
        background = new ImageIcon("images/games/" + trackList.get(nowSelected).getGameImage()).getImage(); // 이미지를 가져옴

        backButton.setVisible(true);    // 뒤로가기 버튼 보이게 함
    }

    /**
     * 뒤로가기 버튼을 눌렀을 때 이벤트
     */
    public void backMain() {
        isMainScreen = true;           // 메인 화면이 아님을 표시

        background = new ImageIcon("images/mainBackground2").getImage(); // 이미지를 가져옴

        // 기존의 보이던 버튼을 사라지게 함
        leftButton.setVisible(true);
        rightButton.setVisible(true);
        easyButton.setVisible(true);
        hardButton.setVisible(true);

        // 게임 실행 화면으로 배경 변경하기

        backButton.setVisible(false);    // 뒤로가기 버튼 보이게 함
        selectTrack(nowSelected);
    }

    /**
     * 게임 시작 버튼을 눌렀을 때 이벤트
     */
    public void enterMain() {

        // 게임 시작 이벤트
        startButton.setVisible(false); // 시작 버튼이 사라지도록 함
        quitButton.setVisible(false); // 종료 버튼이 사라지도록 함

        background = new ImageIcon("images/mainBackground2.jpg").getImage(); // 이미지를 가져옴

        isMainScreen = true;

        leftButton.setVisible(true);
        rightButton.setVisible(true);

        easyButton.setVisible(true);
        hardButton.setVisible(true);

        introMusic.close();                 // 시작 음악 종료

        selectTrack(0);     // 첫 번째 곡을 선택
    }
}
