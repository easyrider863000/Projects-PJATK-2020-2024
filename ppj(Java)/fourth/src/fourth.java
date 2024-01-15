import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fourth {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new FileReader("serverLog.txt")))
        {
            String s;
            Pattern pattern = Pattern.compile("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");;
            Matcher matcher;
            int i = 0;
            String[] strings;
            while((s=br.readLine())!=null){
                matcher = pattern.matcher(s);
                if (matcher.find()) {
                    i++;
                    strings = matcher.group().split("\\.");
                    System.out.println(i+" "+Integer.toHexString(Integer.parseInt(strings[0]))
                            +":"+Integer.toHexString(Integer.parseInt(strings[1]))
                            +":"+Integer.toHexString(Integer.parseInt(strings[2]))
                            +":"+Integer.toHexString(Integer.parseInt(strings[3])));
                }
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
