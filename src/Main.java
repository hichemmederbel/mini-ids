import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Thread;

public class Main {
    public static void main(String[] args) throws IOException  {

        PatternLog pattern = new PatternLog();
        ReadLog reader = new ReadLog(pattern);
        reader.ReadingLog();
    }
}

