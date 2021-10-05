import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    static int n = 0;
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
