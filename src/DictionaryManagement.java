import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary{

    static int n = 0;

    public static void insertFromFile() {
        try {
            ArrayList<String> wordsFromFile = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("dictionaries.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                wordsFromFile.add(line);
            }
            reader.close();

            for (int i = 0; i < wordsFromFile.size(); i++) {
                String[] str = wordsFromFile.get(i).split("\t");
                Word result = new Word(str[0].trim(), str[1].trim());
                Dictionary.WordList.add(result);
                System.out.println(Dictionary.WordList.get(i).getWord_target() + "\t" + Dictionary.WordList.get(i).getWord_explain());
            }
        } catch (IOException ex) {
            System.out.println("Can't load!!!!");
        }
    }

    public static void exportToFile() {
        PrintWriter writer = null;
        try {
            FileWriter write = new FileWriter("dictionaries.txt");
            writer = new PrintWriter(write);
            for (int i = 0; i < WordList.size(); i++) {
                Word outfile = WordList.get(i);
                writer.println(outfile.getWord_target() + "\t" + outfile.getWord_explain());
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void addWord() throws IOException {
        Scanner sc = new Scanner(System.in);
        String target = sc.nextLine();
        String explain = sc.nextLine();
        File file = new File("dictionaries.txt");
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        br.newLine();
        br.write(target.trim() + "\t");
        br.write(explain.trim());
        System.out.println("New word:" + " " + target.trim());
        System.out.println("Meaning:" + " " + explain.trim());
        System.out.println("Dictionary updated!");
        br.close();
        fr.close();
    }

    public static void deleteWord() throws IOException {
        System.out.println("Word to be deleted: ");
        Scanner sc = new Scanner(System.in);
        String delete = sc.nextLine().trim();

        File inputFile = new File("dictionaries.txt");
        File tempFile = new File("tempFile.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile, true));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith(delete)) {
                continue;
            }
            writer.write(line + "\n");
        }
        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);
        System.out.println("Dictionary updated!");
    }

    public static String dictionaryLookup(String target) {
        for (int i = 0; i < Dictionary.WordTargets.size(); i++) {
            if (Dictionary.WordTargets.get(i).toLowerCase().equals(target.toLowerCase())) {
                return Dictionary.WordExplains.get(i);
            }
        }
        return null;
    }

    public static void dictionaryCommandlineLookup() {
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
