package Backend;

import java.util.*;
import java.io.*;
public class FileIO{ 
    public HashMap<String, Integer> numPointsMap = new HashMap<String, Integer>();
    public String ErrorLog = "";
    private int lineNum;
    public void CalculatePoints(String filePath){
        String[] s;
        Person temp = new Person();
        String key = new String();
        lineNum = 0;
        String line; 
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null)
            {
                lineNum++;
                try{
                    s = line.split(",");
                    temp.fName = s[3];
                    temp.lName = s[2];
                    key = temp.fName+","+temp.lName;
                    key = key.replaceAll("\\s","");

                    if(numPointsMap.containsKey(key)){
                        numPointsMap.put(key, numPointsMap.get(key) + NumPointsForActivity(s));

                    }
                    else{
                        numPointsMap.put(key, NumPointsForActivity(s));
                        
                    }
                }
                catch(Exception e){
                    PrintToError("Error");
                }
                
            }
        }
        catch(Exception e){
            PrintToError("Error Reading file");
        }
    }
    public int NumPointsForActivity(String[] line){
        String activityName = line[5];
        System.out.println(activityName);
        int ans = 0;
        if(activityName.equals("Standby")){
            ans = 1;
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
        System.out.println(ans);
        return ans;
    }

    public void PrintToError(String message){
        ErrorLog =  message + "\n";
    }
    public String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }
}