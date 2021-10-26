import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    /**
     * Xuất từ ra file.
     */
    public static void exportToFile() {
        PrintWriter writer = null;
        try {
            FileWriter write = new FileWriter("dictionaries2.txt");
            writer = new PrintWriter(write);
            for (int i = 0; i < Dictionary.WordList.size(); i++) {
                Word outfile = Dictionary.WordList.get(i);
                writer.println(outfile.getWord_target() + "#" + outfile.getWord_explain());
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static int n = 0;

    /**
     * Hàm tra từ.
     */
    public static String dictionaryLookup(String target) {
        for (int i = 0; i < Dictionary.WordTargets.size(); i++) {
            if (Dictionary.WordTargets.get(i).toLowerCase().equals(target.toLowerCase())) {
                return Dictionary.WordExplains.get(i);
            }
        }
        return null;
    }

}