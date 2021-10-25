import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class InternetConnection {
    /**
     * Send request to website.
     *
     * @param word Word requested for meaning;
     * @return Meaning.
     */
    public String getOnlineData(String word) {
        String data = "";
        String decodedData = "";
        try {
            URL url = new URL("https://api.dictionaryapi.dev/api/v2/entries/en/" + word);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if (conn.getResponseCode() == 200) {
                InputStream in = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line = reader.readLine();
                while (line != null) {
                    data += line;
                    line = reader.readLine();
                }
                reader.close();
                JSONDecoder jd = new JSONDecoder();
                decodedData = jd.Decoder(data);
            } else {
                decodedData = "error";
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return decodedData;
    }

}
