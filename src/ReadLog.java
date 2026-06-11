import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadLog {

    private final PatternLog pattern;

    private final Map<String, Integer> attackLog = new HashMap<>();
    private static final int Max_attempte = 5 ;
    private final List<String> ipBanned = new ArrayList<>();
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
            if (ipBanned.contains(IntruderIp)) {
                return;
            }
            int nbFails = attackLog.getOrDefault(IntruderIp,0) + 1;
            attackLog.put(IntruderIp,nbFails);
            if (nbFails >= Max_attempte){
                System.out.println("ALERT UNKNOW IP TRY TO BRUT FORCE :" + IntruderIp);
                banIp(IntruderIp);
            }

        }else { System.out.println("Error");
        }
        }
    private void banIp(String ip) {
        System.out.println(" trying to block IP : " + ip);
        ProcessBuilder pb = new ProcessBuilder("iptables", "-A", "INPUT", "-s", ip, "-j", "DROP");
        try {
            Process processus = pb.start();
            int exitCode = processus.waitFor();

            if (exitCode == 0) {
                System.out.println("the IP: " + ip + " was banned");
                ipBanned.add(ip);
            } else {
                System.err.println("Error can't ban the ip");
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("[Erreur Système] Impossible d'exécuter la commande : " + e.getMessage());
        }
    }

    }



