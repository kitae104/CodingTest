package gui.jtree.basic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class JTreeExample extends JFrame implements TreeSelectionListener {

    private JTextArea textArea;

    public JTreeExample() {
        super("JTree Example");

        // JTree 생성
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode folder1 = new DefaultMutableTreeNode("Folder 1");
        DefaultMutableTreeNode file1 = new DefaultMutableTreeNode("File 1");
        DefaultMutableTreeNode file2 = new DefaultMutableTreeNode("File 2");
        folder1.add(file1);
        folder1.add(file2);
        root.add(folder1);
        JTree tree = new JTree(root);
        tree.addTreeSelectionListener(this);

        // JTextArea 생성
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        // 프레임에 추가
        add(tree, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        // 프레임 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        // JTree에서 선택된 노드 가져오기
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                e.getPath().getLastPathComponent();

        // 선택된 노드가 파일인 경우
        if (node.isLeaf()) {
            String fileName = node.toString();
            try {
                // 파일 읽어오기
                File file = new File(fileName);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }
                reader.close();

                // JTextArea에 파일 내용 표시
                textArea.setText(sb.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new JTreeExample();
    }
}
