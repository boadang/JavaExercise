package View;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class View extends JFrame {
    private JTextArea textArea;
    private JButton saveButton, clearButton, directoryButton;

    public View() {
    	textArea = new JTextArea(20, 40);
        JScrollPane textScrollPane = new JScrollPane(textArea);
        add(textScrollPane, BorderLayout.CENTER);

        saveButton = new JButton("Save");
        clearButton = new JButton("Clear");
        directoryButton = new JButton("Directory");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(directoryButton);

        add(buttonPanel, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public void addSaveButtonListener(ActionListener listener) {
        saveButton.addActionListener(e -> listener.actionPerformed(e));
    }

    public void addClearButtonListener(ActionListener listener) {
    	clearButton.addActionListener(e -> listener.actionPerformed(e));
    }

    public void addDirectoryButtonListener(ActionListener listener) {
        directoryButton.addActionListener(e -> listener.actionPerformed(e));
    }

    public String getText() {
        return textArea.getText();
    }

    public void setText(String text) {
        textArea.setText(text);
    }
    
    public void clearTextData() {
    	textArea.setText("");
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void displayFileContent(File file) {
        try {
            StringBuilder content = new StringBuilder();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
            textArea.setText(content.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void updateTextAreaWithDirectories(List<String> directories) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String directory : directories) {
            stringBuilder.append(directory).append("\n");
        }
        textArea.setText(stringBuilder.toString());
    }

    public File showDirectoryChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            return null;
        }
    }
}