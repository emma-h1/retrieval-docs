/**
 * This is my code! Its goal is to make and search an inverted index
 * CS 312 - Assignment 9
 * @author Emma Heiser
 */

import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

public class SearchEngine
{
	/**
	 * the inverted index of words and documents they're in
	 */
	protected HashMap<String, HashSet<Document>> invertedIndex;
	
	/**
	 * the set of documents to be indexed
	 */
	protected HashSet<Document> docSet;
	
	/**
	 * the flag to print contents of documents
	 */
	protected boolean flag;

	/**
	 * Creates the empty search engine
	 * O(1) time complexity
	 * @param docs the documents that will go in the engine
	 * @param docFlag the flag for printing contents of documents
	 */
	public SearchEngine(HashSet<Document> docs, boolean docFlag)
	{
		invertedIndex = new HashMap<>();
		docSet = docs;
		flag = docFlag;
	}

	/**
	 * Builds the inverted index for the search engine
	 * O(n^2) time complexity
	 */
	public void buildSearchEngine()
	{
		for(Document doc : docSet)
		{
			for(String keyword : doc.keywords)
			{
				HashSet<Document> e = invertedIndex.get(keyword);
				if(e == null)
				{
					e = new HashSet<>();
					invertedIndex.put(keyword, e);
				}
				
				e.add(doc);
			}
		}
	}

	/**
	 * Outputs the inverted index
	 * O(1) time complexity
	 * @return a string representation of the inverted index
	 */
	public String showIndex()
	{
		String s = "";
		for(HashMap.Entry<String, HashSet<Document>> entry 
				: invertedIndex.entrySet())
			s += entry.getKey() + " " 
				+ entry.getValue().toString() + " | ";
		return s;
	}

	/**
	 * Searches the inverted index for a certain query
	 * O(n) time complexity
	 * @param query the user inputted query
	 * @return the documents that contain the query
	 */
	public HashSet<Document> search(ArrayList<String> query)
	{
		HashSet<Document> answer = new HashSet<>();
		for(int i = 0; i < query.size(); i++)
		{
			HashSet<Document> current = invertedIndex.get(query.get(i));
			if(current == null)
			{
			}
			else
			{
				if(answer.isEmpty())
					answer.addAll(current);
				else
					answer.retainAll(current);
			}
		}
		return answer;
	}
}
