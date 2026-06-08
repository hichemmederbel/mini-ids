import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Thread;

public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader reader;
try {
    reader = new BufferedReader(new FileReader("/var/log/auth.log"));
    String line = reader.readLine();

    while (line != null) {
        Thread.sleep(1000);
        System.out.println(line);

    }
    reader.close();
} catch (IOException | InterruptedException e) {
    throw new RuntimeException(e);
}

    }
    }
