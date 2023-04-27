package app.myeditor;

import javax.swing.*;
import java.awt.*;

public class DemoApp extends JFrame {


    public DemoApp() {
        setRootPane(new DemoRootPane());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("RSTA Language Support Demo Application");
        pack();
    }


    /**
     * Called when we are made visible.  Here we request that the
     * {@code RSyntaxTextArea} is given focus.
     *
     * @param visible Whether this frame should be visible.
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            ((DemoRootPane)getRootPane()).focusTextArea();
        }
    }


    /**
     * Application entry point.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.
                        getSystemLookAndFeelClassName());
                //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace(); // Never happens
            }
            Toolkit.getDefaultToolkit().setDynamicLayout(true);
            new DemoApp().setVisible(true);
        });
    }


}
