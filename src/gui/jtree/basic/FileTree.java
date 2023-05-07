package gui.jtree.basic;

import java.io.File;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class FileTree {
    public static void main(String[] args) {
        JTree tree = new JTree();
        File root = new File(".");
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(root.getName());
        addChildren(root, rootNode);
        DefaultTreeModel model = new DefaultTreeModel(rootNode);
        tree.setModel(model);
        JFrame frame = new JFrame();
        frame.add(new JScrollPane(tree));
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void addChildren(File parent, DefaultMutableTreeNode parentNode) {
        if (parent.isDirectory()) {
            File[] files = parent.listFiles();
            if (files != null) {
                for (File file : files) {
                    DefaultMutableTreeNode node = new DefaultMutableTreeNode(file.getName());
                    parentNode.add(node);
                    addChildren(file, node);
                }
            }
        }
    }
}
