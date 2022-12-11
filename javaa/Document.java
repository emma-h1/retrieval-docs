/**
 * This is my code! Its goal is to make a document from a file
 * CS 312 - Assignment 9
 * @author Emma Heiser
 */

import java.nio.file.Files;
import java.util.HashSet;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Iterator;

public class Document
{
	/**
	 * the name of the document
	 */
	protected String docName;
	
	/**
	 * the contents of the document
	 */
	protected String contents;
	
	/**
	 * a set of the non stopwords in the document
	 */
	protected HashSet<String> keywords;
	
	/**
	 * the set of stopwords
	 */
	protected StopList stopwords;

	/**
	 * Creates a new document from a file and stopwords
	 * Gets the file name, contents of files, and non-stopwords
	 * O(n) time complexity
	 * @param file a given file to turn into a document object
	 * @param stopwords a StopList of stopwords
	 */
	public Document(String file, StopList stopwords)
	{
		this.stopwords = stopwords;
		keywords = new HashSet<>();

		Path p = Paths.get(file);
		docName = p.getFileName().toString();

		try
		{
			contents = new String(Files.readAllBytes(p));
		}
		catch (Exception ex)
		{
			System.err.println("thats rough buddy " + ex);
		}


		for(Iterator<String> it = iterator(); it.hasNext();)
		{
			String word = it.next();
			word = word.toLowerCase();
			if(!stopwords.isStopword(word))
				addKeyword(word);
		}
	}

	/**
	 * Iterates over every word in a document
	 * O(n) time complexity
	 * @return an iterator for every word 
	 * in the contents of the document
	 */
	public Iterator<String> iterator()
	{
		return new Scanner(contents).useDelimiter("[^a-zA-Z']+");
	}

	/**
	 * Adds a keyword to the Set
	 * O(1) time complexity
	 * @param word the word to add
	 */
	public void addKeyword(String word)
	{
		keywords.add(word);
	}

	/**
	 * Displays the contents of a document
	 * O(1) time complexity
	 * @return the contents of this document
	 */
	public String displayContents()
	{
		return "Contents of " + docName + ": " + contents;
	}

	/**
	 * The toString representation of a document
	 * O(1) time complexity
	 * @return the file's name
	 */
	public String toString()
	{
		return docName;
	}
}
