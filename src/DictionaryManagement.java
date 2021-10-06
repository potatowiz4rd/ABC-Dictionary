import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class DictionaryManagement {
    static int n = 0;

    public static void insertFromFile() throws IOException {
        ArrayList<String> wordsFromFile = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Java\\CommandLine\\Dictionary.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            wordsFromFile.add(line);
        }
        reader.close();

        for (int i = 0; i < wordsFromFile.size(); i++) {
            String[] str = wordsFromFile.get(i).split("\t");
            Word result = new Word(str[0], str[1]);
            Dictionary.WordList.add(result);
            System.out.println(Dictionary.WordList.get(i).getWord_target() + "\t" + Dictionary.WordList.get(i).getWord_explain());
        }

    }

    public static void dictionaryLookup() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Search for word:");
        String wordLookup = sc.nextLine();
        for (int i = 0; i < Dictionary.WordList.size(); i++) {
            if (wordLookup.equals(Dictionary.WordList.get(i).getWord_target())) {
                System.out.println(Dictionary.WordList.get(i).getWord_explain());
            }
        }
    }

    public static void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            Scanner sc2 = new Scanner(System.in);
            String target;
            String explain;
            target = sc2.nextLine();
            explain = sc2.nextLine();
            Word result = new Word(target, explain);
            Dictionary.WordList.add(result);
        }
    }
}
