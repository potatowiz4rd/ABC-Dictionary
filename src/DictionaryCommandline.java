
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

    public static void dictionaryBasics() {
        DictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    public static void main(String[] args) {
        dictionaryBasics();
    }
}