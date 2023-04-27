package app.compiler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SwingCompiler extends JFrame {
    private JTextArea codeTextArea;
    private JButton compileButton;
    private JLabel outputLabel;

    public SwingCompiler() {
        setTitle("Swing Compiler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Create the code input text area
        codeTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(codeTextArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create the compile button
        compileButton = new JButton("Compile");
        compileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                compile();
            }
        });
        getContentPane().add(compileButton, BorderLayout.SOUTH);

        // Create the output label
        outputLabel = new JLabel();
        getContentPane().add(outputLabel, BorderLayout.NORTH);
    }

    private void compile() {
        try {
            // Write the code to a temporary file
            File tempFile = new File("result/Test.java");
            PrintWriter writer = new PrintWriter(tempFile);
            writer.print(codeTextArea.getText());
            writer.close();

            System.out.println("Wrote code to " + tempFile.getAbsolutePath());

            // Compile the file using javac
            Process process = Runtime.getRuntime().exec("javac " + tempFile.getAbsolutePath());
            int exitCode = process.waitFor();

            // Display the output
            if (exitCode == 0) {
                outputLabel.setText("Compilation successful.");
            } else {
                InputStream errorStream = process.getErrorStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(errorStream));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }
                outputLabel.setText("Compilation failed:\n" + sb.toString());
            }
        } catch (Exception ex) {
            outputLabel.setText("Exception: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingCompiler compiler = new SwingCompiler();
        compiler.setVisible(true);
    }
}
