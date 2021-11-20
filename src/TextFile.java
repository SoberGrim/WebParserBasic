import java.io.*;

public class TextFile

{
    public static void write(String fileName, String str) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), "Windows-1251")))
        {
            writer.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
