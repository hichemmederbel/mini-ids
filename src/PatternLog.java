import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class PatternLog {
        private final Pattern AlertPattern = Pattern.compile("Failed password for .* from ([0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+)");

    public String extractAttackerIP(String line) {
             Matcher matcher = AlertPattern.matcher(line);
            if (matcher.find()) {
                return matcher.group(1);
            }
            return null;
    }
}
