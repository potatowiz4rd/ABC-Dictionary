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

    public static void insertFromFile() {
        try {
            ArrayList<String> wordsFromFile = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("dictionaries.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() > 0 && line.contains("@")) {
                    wordsFromFile.add(line);
                }
            }
            reader.close();

            for (int i = 0; i < wordsFromFile.size(); i++) {
                String[] str = wordsFromFile.get(i).split("@");
                Word result = new Word(str[0].trim(), str[1].trim());
                Dictionary.WordList.add(result);
                System.out.println(Dictionary.WordList.get(i).getWord_target() + "@" + Dictionary.WordList.get(i).getWord_explain());
            }
        } catch (IOException ex) {
            System.out.println("Can't load!!!!");
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
        br.write(target.trim() + "@");
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