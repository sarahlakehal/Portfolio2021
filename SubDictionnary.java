

	/**	
	 * 
	 *  Purpose of the program: This class is to read from a text file and take in every word from it and sort them in a subdictionary
	 *	Every word is sorted alphabetically, to uppercase, without special characters and written back to a new text file.
	 *
	 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;

public class SubDictionary {

	public static ArrayList<String> data = new ArrayList<String>();

	/**
	 * This method checks for duplicates because it makes no sense to have the same word repeat in a dictionary
	 * @param list The array list taken which is filled with String elements (that are the each word in the file)
	 * @param wr Each String element in the file (represent the words of the file)
	 */
	public static boolean is_duplicate(ArrayList<String> list, String wr) {
		for (int i = 0; i < data.size(); i++) {
			if (list.contains(wr))
				return true;
		}
		return false;
	}

	/**
	 * This method is to make sure MC² is not removed from the output file as requested, since every other word with special characters are removed
	 * @param s The string that looks for MC²
	 */
	public static boolean is_Mc(String s) {
		if (s.toUpperCase() == "MC²") {
			return true;
		}
		return false;
	}

	/**
	 * This method is to get rid of the special characters as dictionaries are for words only
	 * @param s The String element in the arrayList
	 */
	public static String replace_symbols(String s) 
	{
		s = s.replaceAll("[?:,;'=!.0123456789]", "");
		return s;
	}
	

	/**
	 * This method adds every read word from the file and puts it in the arrayList, making sure to remove duplicates, special characters, making it uppercase, etc.
	 * @param data The array list taken which is filled with String elements (that are the each word in the file)
	 * @param word Each String element in the file (represent the words of the file)
	 */
	public static ArrayList<String> add_To_Data(ArrayList<String> data, String word) {

		if (!is_duplicate(data, word) || !is_Mc(word)) {
			word = replace_symbols(word);
			if(word.length() >= 1)
			{
				data.add(word.toUpperCase());
			}
	
			data.sort(null);
			return data;
		}
		else if (is_duplicate(data, word)) {
			data.remove(word);
		}
		return data;
	}
	


	public static void main(String[] args) {

		Scanner sc = null;
		PrintWriter pw = null;
		int entry_Counter = 0;
		String word = null;

		File file = new File("PersonOfTheCentury.txt");
		try {
			sc = new Scanner(new FileInputStream(file));
			pw = new PrintWriter(new FileOutputStream("SubDictionary.txt", true));

		} catch (FileNotFoundException e) {
			System.out.println("Problem opening file. Cannot proceed." + "\nProgram will terminate.");
			System.exit(0);
		}

		while (sc.hasNext()) {
			word = sc.next();
			add_To_Data(data, word);
			entry_Counter++;
		}
		while (sc.hasNext()) {
			 word = word.trim(); // remove leading and trailing whitespace
			  if (!word.equals("")) // don't write out blank lines
			  {
			      pw.write(word, 0, word.length());
			  }
		}


		pw.write("The document produced this sub-dictionary, which includes " + entry_Counter + " entries.\n");
		pw.write("\n");
		
		
		for (char i = 'A'; i <= 'Z'; i++){
			pw.write(i + "\n\n==\n");
			for (int j = 0; j < data.size() - 1; j++) {
				String st = data.get(j);
				char firstLetter = st.charAt(0);
					if(i == firstLetter){
							if (!st.equals(data.get(j+1))) {
								pw.write(st +"\n");
							}
							else
								continue;
					}
				}
			pw.write("\n");
		}
		pw.write(data.get(data.size()-1));
		pw.close();
	}

}



