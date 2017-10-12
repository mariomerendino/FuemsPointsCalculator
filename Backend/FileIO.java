package Backend;

import java.util.*;
import java.io.*;
public class FileIO{ 
    public HashMap<Person, Integer> numPointsMap = new HashMap<Person, Integer>();
    public String ErrorLog = "";
    
    public void CalculatePoints(String filePath){
        String[] s;
        Person temp = new Person();
        int lineNum = 0;
        String line; 
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            while ((line = bufferedReader.readLine()) != null)
            {
                lineNum++;
                try{
                    s = line.split(",");
                    temp.fName = s[0];
                    temp.lName = s[1];
                    if(numPointsMap.containsKey(temp)){
                        numPointsMap.put(temp, numPointsMap.get(temp) + NumPointsForActivity(s[2], s[3]));

                    }
                    else{
                        numPointsMap.put(temp, NumPointsForActivity(s[2], s[3]));
                        
                    }
                }
                catch(Exception e){

                }
                
            }
        }
        catch(Exception e){
            PrintToError("Error Reading file");
        }
    }
    public int NumPointsForActivity(String activityName, String activityHours){
        int ans = 0;
        if(activityName == ""){

        }
        else if(activityName == ""){

        }
        else if(activityName == ""){

        }
        else{
            
        }
        return ans;
    }

    public String GetPoints(){
        String ans = "";
        Person temp; 
        Iterator it = numPointsMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            try{
                temp = (Person)pair.getKey();
                ans = "Name: " + temp.fName + temp.lName + " Points: " + pair.getValue().toString() + "\n";
            }
            catch(Exception e){

            }
            it.remove(); // avoids a ConcurrentModificationException
        }
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