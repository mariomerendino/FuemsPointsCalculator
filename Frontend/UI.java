package Frontend;
import Backend.*;
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
    FileIO io;
    
    File selectedFile;
    JButton calculate;
    JButton openFile;
    JButton error;
    String filePath;
    ImageIcon img;
    JLabel points;
    JLabel status;
    JTextArea area;
    JScrollPane areaScrollPane;
    public UI(){
        io = new FileIO();
        setTitle("Fuems Point's Calculator");
        setLayout(new BorderLayout(10, 10));
        img = new ImageIcon(getClass().getResource("/icon.png"));
        this.setIconImage(img.getImage());
        if ( exists( "com.apple.eawt.Application" ) )
        {
            try{
            com.apple.eawt.Application.getApplication().setDockIconImage( img.getImage() );
            }
            catch(Exception e){
                io.PrintToError("Cannot load Doc Icon.");
            }
        }
        area = new JTextArea(20,50);
        areaScrollPane = new JScrollPane(area);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        areaScrollPane.setPreferredSize(new Dimension(500, 300));
        area.setText(" ");
        area.setEditable(false);
        setVisible(true);
        setSize(600,400);
        add(NorthPanel(), BorderLayout.NORTH);
        add(centerPanel(), BorderLayout.CENTER);
        add(southPanel(), BorderLayout.SOUTH);
        
    }
    public JPanel NorthPanel(){
        JPanel North = new JPanel();        
        openFile = new JButton("Open File");        
        calculate = new JButton("Calculate Points");
        calculate.setVisible(false);        
        calculate.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                io.CalculatePoints(selectedFile.getAbsolutePath());
                area.setText(io.GetPoints());
            }
        });
        openFile.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                pickFile();
                calculate.setVisible(true);
                
            }
        });
        North.setLayout(new FlowLayout());

        ImageIcon imageIcon = new ImageIcon(img.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));

        JLabel icon = new JLabel();
        icon.setIcon(imageIcon);
        North.add(icon);
        North.add(openFile);
        North.add(calculate);
        return North;
    }
    public JPanel southPanel(){
        JPanel south = new JPanel();
        error = new JButton("Error Log");
        error.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame popup = errorLog();
            }
        });
        south.add(error);
        return south;
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
    public JFrame errorLog(){
        JFrame panel = new JFrame(); 
        panel.setTitle("Fuems Point's Calculator Error Log");
        panel.setLayout(new BorderLayout(10, 10));
        panel.setVisible(true);
        panel.setSize(600,400);
        panel.add(new Label(io.ErrorLog),BorderLayout.CENTER);
        return panel;
    }
    public JPanel centerPanel(){
        JPanel center = new JPanel();
        center.setVisible(true);
        
        //JScrollPane scrollPane = new JScrollPane(area, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        center.add(areaScrollPane);
        
        return center;        
    }
    
}