import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadLog {

    private final PatternLog pattern;

    private final Map<String, Integer> attackLog = new HashMap<>();
    private static final int Max_attempte = 5 ;

    public ReadLog(PatternLog patternLog) {
        this.pattern = patternLog;
    }

    public void ReadingLog(){
   BufferedReader reader;
   try {
    reader = new BufferedReader(new FileReader("/var/log/auth.log"));
    String line = reader.readLine();
    while (line != null) {
        line = reader.readLine();
        Thread.sleep(100);
        System.out.println(line);
    }
    reader.close();
}
    catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);

        }
    }
    public void LineAnalysis(String line){
        String IntruderIp = pattern.extractAttackerIP(line);

        if(IntruderIp != null){
            int nbFails = attackLog.getOrDefault(IntruderIp,0) + 1;
            attackLog.put(IntruderIp,nbFails);
            if (nbFails >= Max_attempte){
                System.out.println("ALERT UNKNOW IP TRY TO BRUT FORCE :" + IntruderIp);
            }

        }else { System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        }
        }


    }



