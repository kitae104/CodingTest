package games.rhythm.dynamic_beat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DynamicBeat extends JFrame
{

	private Graphics screenGraphic; // 더블 버퍼링을 위한 그래픽 객체
	private Image screenImage; // 더블 버퍼링을 위한 이미지
	private Image background; // 배경 이미지
	private Image selectedImage; // 선택된 이미지
	private Image titleImage; // 타이틀 이미지

	private JButton exitButton; // 나가기 버튼
	private JButton startButton; // 시작 버튼
	private JButton quitButton; // 종료 버튼

	private int mouseX, mouseY; // 마우스의 x, y 좌표

	private ImageIcon[] icons = 
		{ // 버튼 이미지
			new ImageIcon("images/off.png"), 
			new ImageIcon("images/on.png"), 
			new ImageIcon("images/play.png"),
			new ImageIcon("images/start.png"), 
			new ImageIcon("images/pause.png"), 
			new ImageIcon("images/stop.png"),
			new ImageIcon("images/left.png"), 
			new ImageIcon("images/right.png")
			
		};

	private ImageIcon[] musicImage = 
		{ 
			new ImageIcon("images/games/ive_title.png"),			
			new ImageIcon("images/games/ive_main.png"), 
			new ImageIcon("images/games/ive.png"), 
			new ImageIcon("images/games/newjeans_title.png"),
			new ImageIcon("images/games/newjeans_main.png"), 
			new ImageIcon("images/games/newjeans.png"), 
			new ImageIcon("images/games/bts_title.png"),
			new ImageIcon("images/games/bts_main.png"),
			new ImageIcon("images/games/bts.png") 
		};

	private boolean isMainScreen;
	private JButton leftButton;
	private JButton rightButton;

	public DynamicBeat()
	{
		setUndecorated(true);
		setTitle("Dynamic Beat"); // 게임 이름 설정
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 1280x720 화면 크기 설정
		setResizable(false); // 사용자가 게임 창의 크기를 조절하지 못하도록 설정
		setLocationRelativeTo(null); // 게임 창이 컴퓨터 정 중앙에 뜨도록 함
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임 창을 종료했을 때 프로그램 전체가 종료되도록 설정
		setVisible(true); // 게임 창이 정상적으로 출력되도록 설정
		setBackground(new Color(0, 0, 0, 0)); // paintComponent() 메소드에서 배경을 그리기 위해 설정
		setLayout(null);

		// 배경 화면 설정 - 더블 버퍼링 사용
		background = new ImageIcon("images/introBackground.jpg").getImage(); // 이미지를 가져옴
		selectedImage = musicImage[0].getImage();
		titleImage = musicImage[2].getImage();
		isMainScreen = false;
		
		exitButton = new JButton(icons[0]); // 종료 버튼
		// exitButton.setRolloverIcon(icons[1]); // 종료 버튼에 마우스를 올렸을 때 이미지
		exitButton.setBorderPainted(false); // 버튼 테두리 설정
		exitButton.setContentAreaFilled(false); // 버튼 영역 배경 설정
		exitButton.setFocusPainted(false); // 버튼 테두리 설정
		exitButton.setBounds(1225, 25, 30, 30); // 종료 버튼의 위치와 크기 설정
		exitButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{ // 마우스가 버튼 위로 올라갔을 때
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서가 손가락 모양으로 변경
			}

			@Override
			public void mouseExited(MouseEvent e)
			{ // 마우스가 버튼에서 내려갔을 때
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 커서가 기본 모양으로 변경
			}

			@Override
			public void mousePressed(MouseEvent e)
			{ // 종료 버튼을 눌렀을 때
				// Music buttonEnterMusic = new Music("buttonEnterMusic.mp3", false); // 버튼 클릭
				// 효과음
				// buttonEnterMusic.start(); // 효과음 재생
				System.exit(0); // 게임 종료
			}
		});
		add(exitButton);

		startButton = new JButton(icons[2]); // 종료 버튼
		startButton.setRolloverIcon(icons[3]); // 종료 버튼에 마우스를 올렸을 때 이미지
		startButton.setBorderPainted(false); // 버튼 테두리 설정
		startButton.setContentAreaFilled(false); // 버튼 영역 배경 설정
		startButton.setFocusPainted(false); // 버튼 테두리 설정
		startButton.setBounds(840, 450, 400, 119); // 종료 버튼의 위치와 크기 설정
		startButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{ // 마우스가 버튼 위로 올라갔을 때
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서가 손가락 모양으로 변경
			}

			@Override
			public void mouseExited(MouseEvent e)
			{ // 마우스가 버튼에서 내려갔을 때
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 커서가 기본 모양으로 변경
			}

			@Override
			public void mousePressed(MouseEvent e)
			{ // 종료 버튼을 눌렀을 때
				// Music buttonEnterMusic = new Music("buttonEnterMusic.mp3", false); // 버튼 클릭
				// 효과음
				// buttonEnterMusic.start(); // 효과음 재생
				// 게임 시작 이벤트
				startButton.setVisible(false); // 시작 버튼이 사라지도록 함
				quitButton.setVisible(false); // 종료 버튼이 사라지도록 함
				
				leftButton.setVisible(true);
				rightButton.setVisible(true);
				
				background = new ImageIcon("images/mainBackground2.jpg").getImage(); // 이미지를 가져옴
				isMainScreen = true;
			}
		});
		add(startButton);

		quitButton = new JButton(icons[4]); // 종료 버튼
		quitButton.setRolloverIcon(icons[5]); // 종료 버튼에 마우스를 올렸을 때 이미지
		quitButton.setBorderPainted(false); // 버튼 테두리 설정
		quitButton.setContentAreaFilled(false); // 버튼 영역 배경 설정
		quitButton.setFocusPainted(false); // 버튼 테두리 설정
		quitButton.setBounds(840, 580, 400, 119); // 종료 버튼의 위치와 크기 설정
		quitButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{ // 마우스가 버튼 위로 올라갔을 때
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서가 손가락 모양으로 변경
			}

			@Override
			public void mouseExited(MouseEvent e)
			{ // 마우스가 버튼에서 내려갔을 때
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 커서가 기본 모양으로 변경
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				// Music buttonEnterMusic = new Music("buttonEnterMusic.mp3", false); // 버튼 클릭
				// 효과음
				// buttonEnterMusic.start(); // 효과음 재생
				try
				{
					Thread.sleep(1000);
				} catch (InterruptedException ex)
				{
					ex.printStackTrace();
				}
				System.exit(0); // 게임 종료
			}
		});
		add(quitButton);

		
		leftButton = new JButton(icons[6]);
		leftButton.setVisible(false);
		leftButton.setBorderPainted(false); // 버튼 테두리 설정
		leftButton.setContentAreaFilled(false); // 버튼 영역 배경 설정
		leftButton.setFocusPainted(false); // 버튼 테두리 설정
		leftButton.setBounds(140, 310, 52, 52); // 종료 버튼의 위치와 크기 설정
		leftButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{ // 마우스가 버튼 위로 올라갔을 때
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서가 손가락 모양으로 변경
			}

			@Override
			public void mouseExited(MouseEvent e)
			{ // 마우스가 버튼에서 내려갔을 때
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 커서가 기본 모양으로 변경
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				// Music buttonEnterMusic = new Music("buttonEnterMusic.mp3", false); // 버튼 클릭
				// 효과음
				// buttonEnterMusic.start(); // 효과음 재생
				// 왼쪽 버튼 이벤트 
			}
		});
		add(leftButton);
		
		
		rightButton = new JButton(icons[7]);
		rightButton.setVisible(false);		 // 처음엔 보이지 않음 
		rightButton.setBorderPainted(false); // 버튼 테두리 설정
		rightButton.setContentAreaFilled(false); // 버튼 영역 배경 설정
		rightButton.setFocusPainted(false); // 버튼 테두리 설정
		rightButton.setBounds(1080, 310, 52, 52); // 종료 버튼의 위치와 크기 설정
		rightButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{ // 마우스가 버튼 위로 올라갔을 때
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서가 손가락 모양으로 변경
			}

			@Override
			public void mouseExited(MouseEvent e)
			{ // 마우스가 버튼에서 내려갔을 때
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 커서가 기본 모양으로 변경
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				// Music buttonEnterMusic = new Music("buttonEnterMusic.mp3", false); // 버튼 클릭
				// 효과음
				// buttonEnterMusic.start(); // 효과음 재생
				// 오른쪽 버튼 이벤트 
			}
		});
		add(rightButton);
		
		// 시작 음악 설정
		Music introMusic = new Music("introMusic.mp3", true); // 음악을 가져옴
		introMusic.start();
		// 음악을 실행
	}

	public void paint(Graphics g)
	{
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // screenImage에 화면 크기만큼의 이미지를 생성
		screenGraphic = screenImage.getGraphics(); // screenImage의 그래픽 객체를 얻어옴
		screenDraw(screenGraphic); // screenGraphic에 우리가 작성한 paint() 메소드를 넣어줌
		g.drawImage(screenImage, 0, 0, null); // screenImage를 (0, 0) 좌표에 그려줌
	}

	public void screenDraw(Graphics g)
	{
		// 단순 이미지를 화면에 보여줄 때 사용 
		g.drawImage(background, 0, 0, null); // background를 (0, 0) 좌표에 그려줌
		if (isMainScreen)
		{
			g.drawImage(selectedImage, 340, 170, null); // 시작 버튼을 (840, 450) 좌표에 그려줌
            g.drawImage(titleImage, 340, 70, null); // 종료 버튼을 (840, 580) 좌표에 그려줌
		}
		
		// 프레임에 add에 의해 추가된 요소를 보여주는 역할 
		paintComponents(g); // 버튼을 그려줌
		this.repaint(); // paint() 메소드를 계속해서 반복해서 호출
	}
}
