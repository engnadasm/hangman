package eg.edu.alexu.csd.datastructure.hangman.cs57;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException; //it use for read from file
import java.util.Random;
import eg.edu.alexu.csd.datastructure.hangman.IHangman; //take the shape
/**
 * @author nada
 */
public class Hangman implements IHangman {
	/**
	 * alWords is array of stings.
	 * this get them from file
	 */
	String[] alWords; //array of string of words in dictionary
	/**
	 * it is the select word of all words.
	 */
	String randomWord;
	/**
	 * it is the secret word.
	 */
	char[] currWord;
	/**
	 * the Number of error guesses.
	 */
	int maxGuesses;
	/**
	 * integers that i use.
	 */
	int i, j;
	/**
	 * to check if the user enter max number of error guesses.
	 */
	int flag = 0;
	/**
	 * number of words in the file.
	 */
	int numOfWords = 0;
	/**
	 * the final word.
	 */
	String printWord;
	/**
	 * this function to read from file.
	 */
	public void readFromFile() { //to read from file
		BufferedReader br = null;
		String line; //use it to read from file line to line
		String allwords = ""; //string which store all words in it
		try {
			br = new BufferedReader(new FileReader("dic.txt"));
			while ((line = br.readLine()) != null) {
				allwords += line + ":"; //input words
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		alWords = allwords.split(":");
	}
	@Override
	public void setDictionary(final String[] words) {
		// TODO Auto-generated method stub
		alWords = words;
		if (alWords != null) {
			for (String s : alWords) {
				if (s != null) {
					numOfWords++;
				} else {
					break;
				}
			}
		}
	}
	@Override
	public String selectRandomSecretWord() {
		// TODO Auto-generated method stub
		 if (alWords == null || numOfWords == 0) {
			 return null;
		 } else {
			 int index = new Random().nextInt(numOfWords);
		 randomWord = alWords[index];
		 if (randomWord == null) {
			 return null;
		 } else {
			 currWord = new char[randomWord.length()];
			for (i = 0; i < randomWord.length(); i++) {
				currWord[i] = '-';
				}
			return randomWord;
			}
		 }
	}
	@Override
	public String guess(final Character c) throws Exception {
		// TODO Auto-generated method stub
		Character b = null;
		Character k = null;
		if (flag == 0) {
			throw new Exception();
			}
		if (maxGuesses == 0) {
			return null;
			}
		if (c == null) {
				printWord = new String(currWord);
				return printWord;
				}
		int u = randomWord.length();
		if (randomWord.charAt(0) == ' ' || u == 0) {
			throw new Exception();
			} else {
			if (Character.isUpperCase(c)) {
	             b = Character.toLowerCase(c);
	        } else if (Character.isLowerCase(c)) {
	            b = Character.toUpperCase(c);
	        }
			int a = randomWord.indexOf(b);
			if (randomWord.indexOf(c) != -1 || a != -1) {
				for (i = 0; i < randomWord.length(); i++) {
					k = randomWord.charAt(i);
				if (c == randomWord.charAt(i) | b == k) {
					currWord[i] = randomWord.charAt(i);
					}
					}
				} else {
				maxGuesses--;
					}
			printWord = new String(currWord);
				if (maxGuesses == 0) {
					return null;
					} else {
							return printWord;
							}
		 }
	}
	@Override
	public void setMaxWrongGuesses(final Integer max) {
		// TODO Auto-generated method stub
		flag = 1;
		if (max == null) {
			maxGuesses = 1;
		} else {
		maxGuesses = max;
		}
	}
}
