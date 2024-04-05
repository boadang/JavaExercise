package Model;

import java.awt.TextArea;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JTextArea;

public class Model {
    private List<String> textData;

    public Model() {
        textData = new ArrayList<>();
    }

    public void addLine(String line) {
        textData.add(line);
    }

    public void saveToFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : textData) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public void loadFromFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            textData = reader.lines().collect(Collectors.toList());
        }
    }
    
    public void setData (List<String> newData) {
    	this.textData = newData;
    }

    public List<String> getTextData() {
        return textData;
    }

    public List<String> recursiveDirectoryTraversal(File directory) {
        List<String> fileList = new ArrayList<>();
        File[] files = directory.listFiles();
        if (files != null) {
            fileList.addAll(Arrays.stream(files).filter(File::isFile).map(File::getAbsolutePath).collect(Collectors.toList()));
            
            Arrays.stream(files).filter(File::isDirectory).forEach(subDirectory -> fileList.addAll(recursiveDirectoryTraversal(subDirectory)));
        }
        return fileList;
    }
}