package app.editor;

import org.fife.ui.autocomplete.*;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class JavaEditor extends JFrame implements ActionListener {

  private JMenuItem itemExit;
  private JMenuItem itemInfo;
  private JMenuItem itemNew;
  private JMenuItem itemOpen;
  private JMenuItem itemSave;
  private RSyntaxTextArea textArea;

  private ImageIcon[] imgs = { new ImageIcon("images/new.png"),
      new ImageIcon("images/open.png"),
      new ImageIcon("images/save.png"),
      new ImageIcon("images/exit.png"),
      new ImageIcon("images/compile.png"),
      new ImageIcon("images/run.png")
  };
  private JButton btnNew;
  private JButton btnOpen;
  private JButton btnSave;
  private JButton btnExit;
  private JMenuItem itemFont;
  private JMenuItem itemCompile;
  private JMenuItem itemRun;
  private JButton btnCompile;
  private JButton btnRun;
  private File out;
  private JTextArea resultArea;

  public JavaEditor(String title) {
    setTitle(title);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(700, 500);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());

    setMenuBar();

    setToolBar();

    setPanCenter();

    setVisible(true);
  }

  private void setToolBar() {
    JToolBar toolBar = new JToolBar("내 툴바");

    btnNew = new JButton(imgs[0]);
    btnNew.addActionListener(this);
    toolBar.add(btnNew);

    btnOpen = new JButton(imgs[1]);
    btnOpen.addActionListener(this);
    toolBar.add(btnOpen);

    btnSave = new JButton(imgs[2]);
    btnSave.addActionListener(this);
    toolBar.add(btnSave);

    toolBar.addSeparator();

    btnCompile = new JButton(imgs[4]);
    btnCompile.addActionListener(this);
    toolBar.add(btnCompile);

    btnRun = new JButton(imgs[5]);
    btnRun.addActionListener(this);
    toolBar.add(btnRun);

    toolBar.addSeparator();
    btnExit = new JButton(imgs[3]);
    btnExit.addActionListener(this);
    btnExit.setToolTipText("프로그램을 종료합니다.");
    toolBar.add(btnExit);

    add(toolBar, BorderLayout.NORTH);
  }

  private void setPanCenter() {
    JPanel panCenter = new JPanel();
    panCenter.setLayout(new BorderLayout());

    setVerticalSplitPanel(panCenter);

//    textArea = new RSyntaxTextArea(20, 60);
//    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
//    textArea.setCodeFoldingEnabled(true);
//    RTextScrollPane sp = new RTextScrollPane(textArea);
//
//    CompletionProvider provider = createCompletionProvider();
//    AutoCompletion ac = new AutoCompletion(provider);
//    ac.install(textArea);
//
//    panCenter.add(sp);

    add(panCenter);
  }

  private void setVerticalSplitPanel(JPanel panCenter) {
    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    splitPane.setDividerLocation(300);
    splitPane.setDividerSize(5);
    splitPane.setOneTouchExpandable(true);

    textArea = new RSyntaxTextArea(20, 60);
    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
    textArea.setCodeFoldingEnabled(true);
    RTextScrollPane sp = new RTextScrollPane(textArea);

    CompletionProvider provider = createCompletionProvider();
    AutoCompletion ac = new AutoCompletion(provider);
    ac.install(textArea);

    splitPane.setTopComponent(sp);

    resultArea = new JTextArea();
    resultArea.setLineWrap(true);
    resultArea.setWrapStyleWord(true);
    JScrollPane sp2 = new JScrollPane(resultArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    splitPane.setBottomComponent(sp2);

    panCenter.add(splitPane);
  }

  private CompletionProvider createCompletionProvider() {
    DefaultCompletionProvider provider = new DefaultCompletionProvider();

    // a straightforward word completion.
    provider.addCompletion(new BasicCompletion(provider, "abstract"));
    provider.addCompletion(new BasicCompletion(provider, "assert"));
    provider.addCompletion(new BasicCompletion(provider, "break"));
    provider.addCompletion(new BasicCompletion(provider, "case"));
    provider.addCompletion(new BasicCompletion(provider, "class"));
    provider.addCompletion(new BasicCompletion(provider, "public"));

    // ... etc ...
    provider.addCompletion(new BasicCompletion(provider, "transient"));
    provider.addCompletion(new BasicCompletion(provider, "try"));
    provider.addCompletion(new BasicCompletion(provider, "void"));
    provider.addCompletion(new BasicCompletion(provider, "volatile"));
    provider.addCompletion(new BasicCompletion(provider, "while"));
    provider.addCompletion(new BasicCompletion(provider, "for"));

    // require the input text to be the same thing as the replacement text.
    provider.addCompletion(new ShorthandCompletion(provider, "sysout",
        "System.out.println(", "System.out.println("));
    provider.addCompletion(new ShorthandCompletion(provider, "syserr",
        "System.err.println(", "System.err.println("));
    provider.addCompletion(new ShorthandCompletion(provider, "main",
        "public static void main(String args[])", "public static void main(String args[])"));

    return provider;
  }

  private void setMenuBar() {
    JMenuBar bar = new JMenuBar();
    JMenu menuFile = new JMenu("파일");

    itemNew = new JMenuItem("새파일");
    itemNew.addActionListener(this);
    menuFile.add(itemNew);

    itemOpen = new JMenuItem("열기");
    itemOpen.addActionListener(this);
    menuFile.add(itemOpen);

    itemSave = new JMenuItem("저장");
    itemSave.addActionListener(this);
    menuFile.add(itemSave);

    menuFile.addSeparator();

    itemExit = new JMenuItem("나가기");
    itemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
    itemExit.addActionListener(this);
    menuFile.add(itemExit);

    bar.add(menuFile);

    JMenu menuRun = new JMenu("실행");
    itemCompile = new JMenuItem("컴파일");
    itemCompile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.CTRL_MASK));
    itemCompile.addActionListener(this);
    menuRun.add(itemCompile);

    itemRun = new JMenuItem("실행");
    itemRun.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, ActionEvent.CTRL_MASK));
    itemRun.addActionListener(this);
    menuRun.add(itemRun);

    bar.add(menuRun);

    JMenu menuOption = new JMenu("옵션");
    itemFont = new JMenuItem("폰트");
    itemFont.addActionListener(this);
    menuOption.add(itemFont);

    menuOption.addSeparator();

    JMenuItem itemColor = new JMenuItem("Color");
    menuOption.add(itemColor);

    bar.add(menuOption);

    JMenu menuInfo = new JMenu("정보");
    itemInfo = new JMenuItem("프로그램 정보");
    itemInfo.addActionListener(this);
    menuInfo.add(itemInfo);

    bar.add(menuInfo);

    setJMenuBar(bar);
  }

  public static void main(String[] args) {
    new JavaEditor("Simple Java Editor ver0.1");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object obj = e.getSource();
    if (obj == itemExit || obj == btnExit) {
      if ((JOptionPane.showConfirmDialog(
          this, "정말 나갈까요?",
          "프로그램 종료", JOptionPane.YES_NO_OPTION,
          JOptionPane.WARNING_MESSAGE)) == JOptionPane.YES_OPTION) {
        System.exit(0);
      }
    } else if (obj == itemInfo) {
      JOptionPane.showMessageDialog(
          this,
          "간단한 에디터 프로그램",
          "프로그램 정보",
          JOptionPane.ERROR_MESSAGE);
    } else if (obj == itemNew || obj == btnNew) {

    } else if (obj == itemOpen || obj == btnOpen) {
      fileOpen();
    } else if (obj == itemSave || obj == btnSave) {
      fileSave();
    } else if(obj == itemFont){
      showFont();
    } else if(obj == itemCompile || obj == btnCompile){
      compile();
    } else if(obj == itemRun || obj == btnRun){
      run();
    }
  }

  private void showFont() {
    JFontChooser fc = new JFontChooser();
    fc.show();
    Font font = fc.getFont();
    textArea.setFont(font);
    resultArea.setFont(font);
  }


  private void fileSave() {
    JFileChooser fc = new JFileChooser();
    fc.addChoosableFileFilter(new FileNameExtensionFilter("Java", "java"));
    fc.addChoosableFileFilter(new FileNameExtensionFilter("TEXT", "txt"));
    fc.showSaveDialog(this);
    out = fc.getSelectedFile();

    BufferedWriter bw = null;

    try {
      bw = new BufferedWriter(new FileWriter(out));
      String str = textArea.getText();
      bw.write(str);
    } catch (Exception e1) {
      e1.printStackTrace();
    } finally {
      try {
        bw.close();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    }
  }

  private void fileOpen() {
    JFileChooser fc = new JFileChooser();
    fc.addChoosableFileFilter(new FileNameExtensionFilter("Java", "java"));
    fc.addChoosableFileFilter(new FileNameExtensionFilter("TEXT", "txt"));     
    fc.showOpenDialog(this);

    File in = fc.getSelectedFile();
    BufferedReader br = null;
    String line = null;
    try {
      br = new BufferedReader(new FileReader(in));
      while ((line = br.readLine()) != null) {
        textArea.append(line + "\n");
      }
    } catch (Exception e1) {
      e1.printStackTrace();
    } finally {
      try {
        br.close();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    }
  }

  private void compile() {
    try {
      
      resultArea.setText("");

      // Compile the file using javac
      Process process = Runtime.getRuntime().exec("javac " + out.getAbsolutePath());
      int exitCode = process.waitFor();

      // Display the output
      if (exitCode == 0) {
        resultArea.append("Compilation Successful");
      } else {
        InputStream errorStream = process.getErrorStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(errorStream));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
          sb.append(line);
          sb.append("\n");
        }
        resultArea.append("Compilation failed:\n" + sb.toString());
      }
    } catch (Exception ex) {
      resultArea.append("Exception: " + ex.getMessage());
    }
  }
  
  private void run()
  {
    try {

      resultArea.setText("");
      String path = out.getAbsolutePath();
      path = path.substring(0, path.lastIndexOf("."));
      System.out.println(path);

      // Compile the file using javac
      String command = "java " + out.getAbsolutePath();

      // 명령어 실행
      Process process = Runtime.getRuntime().exec(command);

      InputStream inputStream = process.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      String line;
      while ((line = reader.readLine()) != null) {
        resultArea.append(line + "\n");
      }
      // 실행 오류 출력
      InputStream errorStream = process.getErrorStream();
      BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
      String errorLine;
      while ((errorLine = errorReader.readLine()) != null) {
        resultArea.append(errorLine + "\n");
      }
      // 프로세스 실행이 완료될 때까지 기다림
      process.waitFor();
      // 프로세스의 종료 코드 출력 (0은 정상 종료를 의미)
      resultArea.append("Exit value: " + process.exitValue() + "\n");
    } catch (Exception ex) {
      resultArea.append("Exception: " + ex.getMessage());
    }

  }
}
