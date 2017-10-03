import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.FilenameFilter;
import java.io.IOException;


public class UI extends JFrame{
    JButton calculate;
    JButton openFile;
    String filePath;
    public static void main(String[] args){
        UI x = new UI();
    }
    public UI(){
        setTitle("Fuems Point's Calculator");
        setLayout(new BorderLayout(10, 10));
        setVisible(true);
        setSize(600,400);
    
        add(centerPanel(), BorderLayout.CENTER);
    }
    public JPanel centerPanel(){
        calculate = new JButton("Calculate Points");
        openFile = new JButton("Open File");
        calculate.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        openFile.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                pickFile();
            }
        });
        JPanel center = new JPanel();
        center.setLayout(new FlowLayout());
        center.add(openFile);
        center.add(calculate);
        return center;
    }
    public void pickFile(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma Seperated Values", "csv", "csv");
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
    }
}