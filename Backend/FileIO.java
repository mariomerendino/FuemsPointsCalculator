package Backend;

import java.util.*;
import java.io.*;
public class FileIO{

    //HashMap containing Names, and points
    private HashMap<String, Integer> numPointsMap = new HashMap<String, Integer>();
    //error log String
    public static String ErrorLog = "<html><br>";
    //current line Number
    private int lineNum;

    //Runs through the CSV File, and adds people and points to 
    //the hashmap. 
    public void CalculatePoints(String filePath){
        numPointsMap = new HashMap<String, Integer>();
        ErrorLog = "<html><br>";
        String[] s;
        String key = new String();
        lineNum = 1;
        String line; 
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null)
            {
                lineNum++;
                try{
                    s = line.split(",");
                    
                    key = s[3]+","+s[2];
                    key = key.replaceAll("\\s","");

                    if(numPointsMap.containsKey(key)){
                        numPointsMap.put(key, numPointsMap.get(key) + NumPointsForActivity(s, lineNum));

                    }
                    else{
                        numPointsMap.put(key, NumPointsForActivity(s, lineNum));
                        
                    }
                }
                catch(Exception e){
                
                    PrintToError("Error: line (" + Integer.toString(lineNum) + ")");
                }
                
            }
        }
        catch(Exception e){
            PrintToError("Error Reading file");
        }
    }
    //Used to determine the amount of points for each entry 
    public int NumPointsForActivity(String[] line, int lineNum){
        String activityName = line[5];
        int ans = 0;
        if(activityName.equals("Standby")){
            ans = 1;
            try{
                double numberOfHours = Double.parseDouble(line[8]);
                
            }
            catch(Exception e){
                PrintToError("Error: Line (" + Integer.toString(lineNum) + ") Hours are not integers. Points not added" );
                return 0;
            }
        }
        else if(activityName.equals("CPR Class")){
            ans = 1; 
        }
        else if(activityName.equals("Driver Training")){
            ans = 1;
        }
        else if(activityName.equals("Busy Night")){
            ans = 1;
            
        }
        else if(activityName.equals("Over 24-Hour Shift")){
            ans = 1;
            
        }
        else if(activityName.equals("Goodwill")){
            ans = 1;
            
        }
        else if(activityName.equals("Decon")){
            ans = 1;
            
        }
        else if(activityName.equals("Empress Run")){
            ans = 1;
            
        }
        else{
            PrintToError("Incorrect Input");
            
        }
        return ans;
    }

    //Returns the total amount of points each student as a string in the format of:
    //Name: John Smith
    //Points: 1
    //endl. 
    public String GetPoints(){
        String ans = "";
        String temp; 
        String[] tempArray;
        Iterator it = numPointsMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            try{
                temp = (String)pair.getKey();
                tempArray = temp.split(",");
                ans = ans + "Name: " + tempArray[0] +" "+ tempArray[1] + "\nPoints: " + pair.getValue().toString() + "\n\n";
            }
            catch(Exception e){

            }
            it.remove(); 
        }
        return ans;
    }
    //Print to error log.
    public void PrintToError(String message){
        ErrorLog =  ErrorLog + message + "<br>";
    }
    //returns the file extention of any inputed file. 
    public String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }
}