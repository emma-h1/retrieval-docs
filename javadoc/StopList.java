/**
 * This is my code! Its goal is to make a set of stopwords
 * CS 312 - Assignment 9
 * @author Emma Heiser
 */

import java.util.HashSet;
import java.io.File;
import java.util.Scanner;

public class StopList
{
	/**
	 * the set of stopwords to not read over
	 */
	protected HashSet<String> stopwords;

	/**
	 * Creates a new empty HashSet of Strings for stopwords
	 * O(1) time complexity
	 */
	public StopList()
	{
		stopwords = new HashSet<String>();
	}

	/**
	 * Builds a Set of stopwords from words in a file
	 * O(n) time complexity
	 * @param f a given file
	 */
	public void buildList(File f)
	{
		try
		{
			Scanner scan = new Scanner(f);
			while(scan.hasNextLine())
			{
				String token = scan.nextLine();
				stopwords.add(token);
			}
		}
		catch(Exception e)
		{
			System.err.println("rip " + e);
		}
	}

	/**
	 * Determines if a word is the same as a stopword in the Set
	 * O(1) time complexity
	 * @param word a given word to compare
	 * @return a boolean if the word is a stopword
	 */
	public boolean isStopword(String word)
	{
		return stopwords.contains(word);
	}

}		
