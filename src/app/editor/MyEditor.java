package app.editor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

public class MyEditor extends JFrame implements ActionListener {

  private JMenuItem itemExit;
  private JMenuItem itemInfo;
  private JMenuItem itemNew;
  private JMenuItem itemOpen;
  private JMenuItem itemSave;
  private RSyntaxTextArea textArea;

  private ImageIcon[] imgs = { new ImageIcon("images/new.png"),
      new ImageIcon("images/open.png"),
      new ImageIcon("images/save.png"),
      new ImageIcon("images/exit.png")
  };
  private JButton btnNew;
  private JButton btnOpen;
  private JButton btnSave;
  private JButton btnExit;
  private JMenuItem itemFont;

  public MyEditor(String title) {
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

    btnExit = new JButton(imgs[3]);
    btnExit.addActionListener(this);
    btnExit.setToolTipText("프로그램을 종료합니다.");
    toolBar.add(btnExit);

    add(toolBar, BorderLayout.NORTH);
  }

  private void setPanCenter() {
    JPanel panCenter = new JPanel();
    panCenter.setLayout(new BorderLayout());

    textArea = new RSyntaxTextArea(20, 60);
    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
    textArea.setCodeFoldingEnabled(true);
    RTextScrollPane sp = new RTextScrollPane(textArea);

    CompletionProvider provider = createCompletionProvider();
    AutoCompletion ac = new AutoCompletion(provider);
    ac.install(textArea);

    panCenter.add(sp);

    add(panCenter);
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
    itemExit.addActionListener(this);
    menuFile.add(itemExit);

    bar.add(menuFile);

    JMenu menuOption = new JMenu("옵션");
    itemFont = new JMenuItem("폰트");
    itemFont.addActionListener(this);
    menuOption.add(itemFont);

    menuOption.addSeparator();

    JMenuItem itemColor = new JMenuItem("Color");
    menuOption.add(itemColor);

    bar.add(menuOption);

    JMenu menuInfo = new JMenu("Info");
    itemInfo = new JMenuItem("Program Info");
    itemInfo.addActionListener(this);
    menuInfo.add(itemInfo);

    bar.add(menuInfo);

    setJMenuBar(bar);
  }

  public static void main(String[] args) {
    new MyEditor("MyEditor ver0.1");
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
    }
  }

  private void fileSave() {
    JFileChooser fc = new JFileChooser();
    fc.addChoosableFileFilter(new FileNameExtensionFilter("Java", "java"));
    fc.addChoosableFileFilter(new FileNameExtensionFilter("TEXT", "txt"));
    fc.showSaveDialog(this);
    File out = fc.getSelectedFile();

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

}
