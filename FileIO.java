import java.util.*;
import java.io.*;

public class FileIO{
    public String[] typesOfActivity = {};
    public int[] valueOfActivity = {}; 
    public HashMap<Person, Integer> numPointsMap = new HashMap<Person, Integer>();

    public void CalculatePoints(String filePath){
        String[] s;
        Person temp;
        int currentPoints;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        while ((line = bufferedReader.readLine()) != null)
        {
            s = line.split(",");
            temp.fName = s[0];
            temp.lName = s[1];
            if(numPointsMap.containsKey(temp)){
                numPointsMap.put(Temp, numPointsMap.get(temp) + NumPointsForActivity(s[2], s[3]));

            }
            else{
                numPointsMap.put(Temp, NumPointsForActivity(s[2], s[3]));
                
            }
            //Add to hashmap if not already there
            //collect point
        }
    }
    public int NumPointsForActivity(String activityName, String activityHours){
        int ans = 0;

        return ans;
    }

    public String GetPoints(){
        String ans = "";
        Iterator it = numPointsMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            ans = "Name: " + pair.getKey().fName + pair.getKey().lName + " Points: " + pair.getValue().toString() + "\n";
            it.remove(); // avoids a ConcurrentModificationException
        }
        return ans;
    }
}