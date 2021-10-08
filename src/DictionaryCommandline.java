import java.io.IOException;
import java.util.Scanner;

public class DictionaryCommandline {
    public static void showAllWords() {
        System.out.println("No    |English          |Vietnamese");
        for (int i = 0; i < DictionaryManagement.n; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 0){
                    System.out.print(i+1);
                }
                if (j==1){
                    System.out.print("     |");
                }
                if (j==2){
                    System.out.print(Dictionary.WordList.get(i).getWord_target());
                }
                if (j==3){
                    System.out.print("           |");
                }
                if (j==4){
                    System.out.println(Dictionary.WordList.get(i).getWord_explain());
                }
            }
        }
    }

    public static void dictionarySearcher() {
        System.out.print("Word searched: ");
        Scanner sc = new Scanner(System.in);
        String wordSearch = sc.nextLine();
        for (int i = 0; i < Dictionary.WordList.size(); i++) {
            //String temp = Dictionary.WordList.get(i).getWord_target();
            if (Dictionary.WordList.get(i).getWord_target().startsWith(wordSearch)) {
                System.out.println(Dictionary.WordList.get(i).getWord_target());
            }
        }
    }

    public static void dictionaryBasics() {
        DictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    public static void dictionaryAdvanced() throws IOException {
        DictionaryManagement.insertFromFile();
        //showAllWords();
        //DictionaryManagement.dictionaryLookup();
        //DictionaryManagement.addWord();
        DictionaryManagement.deleteWord();
    }

    public static void main(String[] args) throws IOException {
        dictionaryAdvanced();
        //showAllWords();
        //dictionarySearcher();
    }
}