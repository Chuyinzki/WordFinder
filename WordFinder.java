import java.util.*;
import java.io.*;

/*Used to 'Hack' https://www.hackthissite.org/missions/prog/1/index.php 
and it totes worked!*/

public class WordFinder {
	 public static void main(String[] args) {
        String retString = "";
        Scanner user_input = new Scanner( System.in );
        List<String> scrambledWords = new ArrayList<String>();
        List<String> dictionaryWords = new ArrayList<String>();
        int maxWords = 10;
        int totalWords = 0;

        System.out.println("Please enter the list of scrambled " + 
        	"words to be checked against the dictionary: ");

        //Add scrambled words to List
        String scrambledWord;
        while(totalWords < maxWords && user_input.hasNext() ){
        	scrambledWord =  user_input.next();
        	scrambledWords.add(scrambledWord);
        	totalWords++;
        }

        //Add dictionary words to other List
        String dictWord;
        int numLines = 0;
		try {
		    InputStream fis = new FileInputStream("wordlist.txt");
		    InputStreamReader isr = new InputStreamReader(fis);
		    BufferedReader br = new BufferedReader(isr);
		    while ((dictWord = br.readLine()) != null) {
		        System.out.println("Word is #" + ++numLines + " " + dictWord);
		        dictionaryWords.add(dictWord);
		    }
		    br.close();
		} catch (IOException e){
		    System.out.println("Error: " + e.getMessage());
		}
		
        while(scrambledWords.size() > 0){
        	boolean wordFound = false;
        	scrambledWord = scrambledWords.remove(0);
        	char[] scrambledWordChars = scrambledWord.toCharArray();
        	Arrays.sort(scrambledWordChars);
        	for(int i = 0; i < dictionaryWords.size(); i++){
        		dictWord = dictionaryWords.get(i);
        		if(scrambledWord.length() != dictWord.length()) continue;
        		char[] dictWordChars = dictWord.toCharArray();
        		Arrays.sort(dictWordChars);
        		if(Arrays.equals(scrambledWordChars, dictWordChars)){
        			if(scrambledWords.size() != 0) retString += dictWord + ",";
        			else retString += dictWord;
        		}
        	}
        }
        System.out.println(retString);
    }
}