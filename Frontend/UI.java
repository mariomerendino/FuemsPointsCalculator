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

    //Instance of File IO class, for file interaction
    FileIO io;
    
    //Selected CSV File
    File selectedFile;
    String filePath;

    //Swing Components.
    JButton calculate;
    JButton openFile;
    JButton error;
    JButton clear;
    ImageIcon img;
    JLabel points;
    JLabel status;
    JTextArea area;
    JScrollPane areaScrollPane;
    
    //Default Constructor
    //Sets up JFrame 
    public UI(){
        //instantiates file io
        io = new FileIO();
        //Jframe Setup. 
        setSize(700,450);
        setTitle("Fuems Point's Calculator");
        setLayout(new BorderLayout(10, 10));
        img = new ImageIcon(getClass().getResource("/icon.png"));
        this.setIconImage(img.getImage());

        //Checks if its a mac, if so, change doc icon
        if ( exists( "com.apple.eawt.Application" ) )
        {
            try{
            com.apple.eawt.Application.getApplication().setDockIconImage( img.getImage() );
            }
            catch(Exception e){
                io.PrintToError("Cannot load Doc Icon.");
            }
        }
        //Sets up text area with scroll bar
        //(Not added to the main Jframe until CenterPanel() is called)
        area = new JTextArea(20,50);
        areaScrollPane = new JScrollPane(area);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        areaScrollPane.setSize(new Dimension(400, 300));
        area.setText(" ");
        area.setEditable(false);

        //add North, center and south panels to the main Jframe
        add(NorthPanel(), BorderLayout.NORTH);
        add(centerPanel(), BorderLayout.CENTER);
        add(southPanel(), BorderLayout.SOUTH);
        
        setVisible(true);        
    }
    //Returns the North Panel. 
    public JPanel NorthPanel(){
        //Creates Jpanel
        JPanel North = new JPanel();  

        //setup Buttons on north panel
        openFile = new JButton("Open File");        
        calculate = new JButton("Calculate Points");
        
        calculate.setEnabled(false);
        //Calculate is clicked        
        calculate.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                //Calculate the points for the selected file
                io.CalculatePoints(selectedFile.getAbsolutePath());
                //set the text of the main text area
                area.setText(io.GetPoints());
                //disable the calculate button again
                calculate.setEnabled(false);
                clear.setEnabled(true);
            }
        });
        //Open file is clicked 
        openFile.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open file picker
                pickFile();
                
                
            }
        });
        //Sets the layout of the north panel
        North.setLayout(new FlowLayout());

        ImageIcon imageIcon = new ImageIcon(img.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JLabel icon = new JLabel();
        icon.setIcon(imageIcon);

        //add all the componenets to the north panel
        North.add(icon);
        North.add(openFile);
        North.add(calculate);
        return North;
    }
    //Creates south panel
    public JPanel southPanel(){
        JPanel south = new JPanel();
        //sets up error button
        error = new JButton("Error Log");
        //error button is pressed
        error.setEnabled(false);
        error.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                //Popup window with the error log
                JFrame popup = errorLog();
            }
        });
        //sets up clear button
        clear = new JButton("Clear");
        clear.setEnabled(false);        
        clear.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAll();
            }
        });
        south.add(clear);        
        south.add(error);
        return south;
    }
    //File picker prompt window
    public void pickFile(){
        JFileChooser fileChooser = new JFileChooser();
        //Sets filter for only CSV files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma Seperated Values", "csv", "csv");
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            calculate.setEnabled(true);
            error.setEnabled(true);
            clear.setEnabled(true);
            openFile.setEnabled(false);
        }
    }
    //Helper function for setting up the apple doc.
    //Checks what kind of machine 
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
    //Error log popup
    public JFrame errorLog(){
        JFrame panel = new JFrame(); 
        panel.setTitle("Fuems Point's Calculator Error Log");
        panel.setLayout(new BorderLayout(10, 10));
        panel.setVisible(true);
        panel.setSize(600,400);
        //Gets the error log fromthe file Io class
        panel.add(new Label(io.ErrorLog),BorderLayout.CENTER);
        return panel;
    }
    public JPanel centerPanel(){
        JPanel center = new JPanel();
        center.setVisible(true);
        center.add(areaScrollPane);
        return center;        
    }
    public void clearAll(){
        clear.setEnabled(false);
        calculate.setEnabled(false);
        error.setEnabled(false);        
        openFile.setEnabled(true);
        io.ErrorLog = "<html><br>";
        area.setText(" ");   
    }
    
}