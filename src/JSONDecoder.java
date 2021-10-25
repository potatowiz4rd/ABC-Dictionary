import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JSONDecoder {
    public String Decoder(String data) {
        String deData = "";
        try {
            Object obj = JSONValue.parse(data);

            JSONArray ja = (JSONArray) obj;
            JSONObject jo = (JSONObject) ja.get(0);
            deData = deData + "Word: " + jo.get("word");

            JSONArray phonetic = (JSONArray) jo.get("phonetics");
            JSONObject text = (JSONObject) phonetic.get(0);
            deData = deData + "\n Phonetic: " + text.get("text");

            JSONArray meaning = (JSONArray) jo.get("meanings");

            JSONObject PartOfSpeech = (JSONObject) meaning.get(0);
            deData = deData + "\n Part Of Speech: " + PartOfSpeech.get("partOfSpeech");
            JSONObject definitions = (JSONObject) meaning.get(0);
            JSONArray definition = (JSONArray) definitions.get("definitions");
            JSONObject def = (JSONObject) definition.get(0);
            deData = deData + "\n + Definiton: " + def.get("definition");
            deData = deData + "\n + Example: " + def.get("example");
            JSONArray ex = (JSONArray) def.get("synonyms");
            if (!(ex == null)) {
                deData = deData + "\n + Synonyms: ";
                for (int i = 0; i < ex.size(); i++) {
                    if (i == 0) {
                        deData = deData + ex.get(i);
                    } else {
                        deData = deData + ", " + ex.get(i);
                    }
                }
                deData = deData + ".";
            }

            JSONObject PartOfSpeech2 = (JSONObject) meaning.get(1);
            deData = deData + "\n Part Of Speech 2: " + PartOfSpeech2.get("partOfSpeech");
            JSONObject definitions2 = (JSONObject) meaning.get(1);
            JSONArray definition2 = (JSONArray) definitions2.get("definitions");
            JSONObject def2 = (JSONObject) definition2.get(0);
            deData = deData + "\n + Definiton: " + def2.get("definition");
            deData = deData + "\n + Example: " + def2.get("example");
            JSONArray ex2 = (JSONArray) def.get("synonyms");
            if (!(ex2 == null)) {
                deData = deData + "\n + Synonyms: ";
                for (int i = 0; i < ex2.size(); i++) {
                    if (i == 0) {
                        deData = deData + ex2.get(i);
                    } else {
                        deData = deData + ", " + ex2.get(i);
                    }
                }
                deData = deData + ".";
            }

        } catch (Exception e) {
            //deData = "error";
        }
        return deData;
    }
}
