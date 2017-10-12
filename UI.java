import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.FilenameFilter;
import java.io.IOException;
//MUST COMPILE WITH 
//javac -classpath AppleJavaExtensions.jar UI.java
//RUN WITH
//java UI
public class UI extends JFrame{
    File selectedFile;
    JButton calculate;
    JButton openFile;
    String filePath;
    ImageIcon img;
    public static void main(String[] args){
        UI x = new UI();
    }
    public UI(){
        setTitle("Fuems Point's Calculator");
        setLayout(new BorderLayout(10, 10));
        img = new ImageIcon("Images/icon.png");
        this.setIconImage(img.getImage());
        if ( exists( "com.apple.eawt.Application" ) )
        {
            com.apple.eawt.Application.getApplication().setDockIconImage( img.getImage() );
        }
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
                if(selectedFile == null){
                    JOptionPane.showMessageDialog(null, "You must select a CSV file", "ERROR:", JOptionPane.WARNING_MESSAGE); 
                }
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
            selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
    }
    public boolean exists(String className)
    {
        try {
            Class.forName( className, false, null );
            return true;
        }
        catch (ClassNotFoundException exception) {
            return false;
        }
    }
}