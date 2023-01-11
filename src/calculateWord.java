import java.io.*;
import java.util.*;

public class calculateWord {
	public static Vector <String> words = new Vector <String>();

	public static boolean isRealWord (String word, int numLetters) throws IOException {
		initializeArray(numLetters);
		word = word.toUpperCase();
		if (words.contains(word)) {
			return true;
		} else return false;
	}
	public static void initializeArray (int numLetters) throws IOException {
		if (numLetters == 4) {
			BufferedReader stdin = new BufferedReader (new FileReader ("4LetterWords"));
			while (stdin.readLine()!= null) {
				words.add(stdin.readLine());
			}
			stdin.close();
		} else if (numLetters == 5) {
			BufferedReader stdin = new BufferedReader (new FileReader ("5LetterWords"));
			while (stdin.readLine()!= null) {
				words.add(stdin.readLine());
			}
			stdin.close();
		} if (numLetters == 6) {
			BufferedReader stdin = new BufferedReader (new FileReader ("6LetterWords"));
			while (stdin.readLine()!= null) {
				words.add(stdin.readLine());
			}
			stdin.close();
		} if (numLetters == 7) {
			BufferedReader stdin = new BufferedReader (new FileReader ("7LetterWords"));
			while (stdin.readLine()!= null) {
				words.add(stdin.readLine());
			}
			stdin.close();
		}
	}
	public static String getWord (int numLetters) throws IOException{
		//Vector <String> words = new Vector <String>();
		initializeArray(numLetters);
		String secretWord = words.get((int)(Math.random() * words.size()));
		secretWord = secretWord.toUpperCase();
		return secretWord;
	}
	
	public static void emptyArray () { // after the game finishes so you can switch the number of letters in each word
		words.clear();
	}
	
	public static int [] checkWord(String secretWord, String guess, int numLetters) {
		int [] results = new int [numLetters];
		Arrays.fill(results, 0);
		for (int i = 0; i < numLetters; i++) {
			if (secretWord.charAt(i) == (guess.charAt(i))) {
				results [i] = 2; // correct letter in correct position, turns green
			} else if (secretWord.contains(""+guess.charAt(i))) {
				results [i] = 1; // correct letter in wrong position, turns yellow
			}
		}
		return results;
	}
	
	
    public static void main (String str[]) throws IOException {
    }
}
