package Controlled;

import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import View.View;

import Model.Model;

public class Controller {
    private Model model;
    private View view;
    private File selectedDirectory;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        view.addSaveButtonListener(new SaveButtonListener());
        view.addClearButtonListener(new ClearButtonListener());
        view.addDirectoryButtonListener(new DirectoryButtonListener());
    }

    class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String[] lines = view.getText().split("\n");
            List<String> newData = Arrays.asList(lines);
            

            model.setData(newData);
            
            try {

                model.saveToFile("data.txt");
                view.showErrorMessage("Successful !");
            } catch (IOException ex) {
                view.showErrorMessage("Error saving file!");
            }
        }
    }


    class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(selectedDirectory != null) {
            	try {
            		view.clearTextData();
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showConfirmDialog(view, "Error !");
				}
            }
        }
    }

    class DirectoryButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser filechooser = new JFileChooser();
            int result = filechooser.showOpenDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
            	selectedDirectory = filechooser.getSelectedFile();
                List<String> directories = model.recursiveDirectoryTraversal(selectedDirectory);
                view.updateTextAreaWithDirectories(directories);
                view.displayFileContent(selectedDirectory);
            }
        }
    }

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
    }
}
