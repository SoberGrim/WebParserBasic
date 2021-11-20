import java.io.*;
import java.net.*;

public class GetHTML {

    public static StringBuilder getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-agent","Mozilla 5.0");
        try (var reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }

        return result;
    }

    public static StringBuilder postHTML(String urlToRead, byte[] postDataBytes) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty( "Content-type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setRequestProperty( "Accept", "*/*" );
        conn.setRequestProperty("User-agent","Mozilla 5.0");
        conn.setRequestProperty("Cookie", "_insert_cookie_here");
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);
        try (var reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return result;
    }
}
