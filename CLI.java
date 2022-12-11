/**
 * This is my code! Its goal is to set up a search
 * engine and support a search
 * CS 312 - Assignment 9
 * @author Emma Heiser
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.io.File;
import java.util.Scanner;

public class CLI
{
	protected String [] args;
	protected StopList stopwords;
	protected Boolean flag;
	protected HashSet<Document> docSet;

	/**
	 * Creates a new command line interface
	 * O(1) time complexity
	 * @param a the command line input
	 */
	public CLI(String [] a)
	{
		args = a;
		stopwords = new StopList();
		flag = false;
		docSet = new HashSet<>();
	}

	/**
	 * Prints format input for CLI
	 * O(1) time complexity
	 */
	private void usage()
	{
		System.out.println("Usage: [-d] stoplist documents");
	}

	/**
	 * Parses the command line input to setup 
	 * parameters for search engine
	 * processes stoplist and document files
	 * decide if contents of documents should be outputted
	 * O(n) time complexity
	 */
	public void parse()
	{
		if(args.length == 0)
		{
			usage();
			return;
		}

		int i = 0;

		if(args[i].equals("-d"))
		{
			flag = true;
			i++;

			if(args.length == i)
			{
				usage();
				return;
			}
		}

		File stop = new File(args[i]);
		stopwords.buildList(stop);

		i++;
		if(args.length == i)
		{
			usage();
			return;
		}
		
		while(i < args.length)
		{
			docSet.add(new Document(args[i], stopwords));
			i++;
		}

	}

	/**
	 * The main driver for the search engine.
	 * Creates a new search engine, asks user for a query, and prints
	 * documents containing query
	 * O(1) time complexity
	 * @param args command line argument
	 */
	public static void main(String [] args)
	{
		CLI c = new CLI(args);
		c.parse();

		long startTime = System.currentTimeMillis();
		SearchEngine se = new SearchEngine(c.docSet, c.flag);
		se.buildSearchEngine();
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("@@ Indexing the documents took " 
				+ elapsedTime + "ms");

		boolean repeat = true;

		while(repeat)
		{
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter query: ");
	        	String input = scan.nextLine();

			ArrayList<String> debug = new ArrayList<>();
			debug.add("@@debug");
			ArrayList<String> query = new ArrayList<>();

			for(String word : input.split(" "))
			{
				if(!c.stopwords.isStopword(word))
					query.add(word);
			}

			if(query.isEmpty())
				System.out.println("Error. Enter different query");
			else if(query.equals(debug))
				System.out.println(se.showIndex());
			else
			{
				long startTime2 = System.currentTimeMillis();
				HashSet<Document> answer = se.search(query);
				long stopTime2 = System.currentTimeMillis();
                		long elapsedTime2 = stopTime2 - startTime2;
                		
				System.out.println("@@ processing the queries took " 
						+ elapsedTime2 + "ms");
				
				if(c.flag)
				{
					for(Document doc : answer)
						System.out.println(doc + ": " 
								+ doc.displayContents());
				}
				else
					System.out.println(answer);
				System.out.println("--- found in " + 
						(answer == null ? 0 : answer.size()) 
						+ " documents");
			}
		}
	}
}

