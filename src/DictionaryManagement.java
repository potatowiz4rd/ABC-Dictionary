import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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

    public static void addWord() throws IOException {
        Scanner sc = new Scanner(System.in);
        String target = sc.nextLine();
        String explain = sc.nextLine();
        File file = new File("E:\\Java\\CommandLine\\Dictionary.txt");
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        br.newLine();
        br.write(target + "\t");
        br.write(explain);
        System.out.println("New word:" + " " + target);
        System.out.println("Meaning:" + " " + explain);
        br.close();
        fr.close();
    }

    public static void deleteWord() throws IOException {
        System.out.println("Word to be deleted: ");
        Scanner sc = new Scanner(System.in);
        String delete = sc.nextLine() + "\t";

        File inputFile = new File("E:\\Java\\CommandLine\\Dictionary.txt");
        File tempFile = new File("E:\\Java\\CommandLine\\tempFile.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String line;
        while((line = reader.readLine()) != null) {
            if (line.startsWith(delete)) {
                continue;
            }
            writer.write(line + "\n");
        }
        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);
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
            String target = sc2.nextLine();
            String explain = sc2.nextLine();
            Word result = new Word(target, explain);
            Dictionary.WordList.add(result);
        }
    }
}
