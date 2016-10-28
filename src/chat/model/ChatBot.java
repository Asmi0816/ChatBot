package chat.model;

import java.util.ArrayList;

/**
 * Base version of the 2015 Chatbot class. Only stub methods are provided.
 * Students will complete methods as part * of the project. * 
 * @author Arick Smith
 * @version 1.0 10/14/15
 */
public class Chatbot 
{
	private ArrayList<String> memesList;
	private ArrayList<String> politicalTopicList;
	private String userName;
	private String content;
	

	/**
	 * * Creates an instance of the Chatbot with the supplied username. * @param
	 * userName The username for the chatbot.
	 */
	public Chatbot(String userName) 
	{
		this.memesList = new ArrayList<String>();
		this.politicalTopicList = new ArrayList<String>();
		this.userName = userName;
		this.content = new String("itallian");
		this.buildMemesList();
		buildPoliticalTopicsList();
		
		
	}
	
	

	private void buildMemesList() 
	{
		memesList.add("john cena");
		memesList.add("doge");
		memesList.add("cute animals");
		memesList.add("grumpy cat");
		memesList.add("dat boi");
		memesList.add("willy wonka");
		memesList.add("harambe");
		memesList.add("fresh avacado");
		memesList.add("you're not really fine.");
		memesList.add("stop it");
		memesList.add("Rhon Swansen");
		memesList.add("Batman");
		memesList.add("clowns");
		memesList.add("spongebob");
		memesList.add("cat");
		memesList.add("21");
		memesList.add("Office");
		memesList.add("doctor");
		
	}

	private void buildPoliticalTopicsList() 
	{
		politicalTopicList.add("Democrat");
		politicalTopicList.add("Republican");
		politicalTopicList.add("11/8/16");
		politicalTopicList.add("conservative");
		politicalTopicList.add("Clinton");
		politicalTopicList.add("liberal");
		politicalTopicList.add("Trump");
		politicalTopicList.add("Kaine");
		politicalTopicList.add("Pence");
		politicalTopicList.add("Stein");
		politicalTopicList.add("Johnson");
		politicalTopicList.add("election");
		politicalTopicList.add("Trump wall");
		politicalTopicList.add("deleted emails");
		politicalTopicList.add("Foreign Policy");
		politicalTopicList.add("Free trade");
		politicalTopicList.add("War & Peace");
		politicalTopicList.add("Gun control");
		politicalTopicList.add("Hillary");
	}

	/**
	 * * Checks the length of the supplied string. Returns false if the supplied
	 * String is empty or null, otherwise returns true. * @param currentInput * @return
	 * A true or false based on the length of the supplied String.
	 */
	public boolean lengthChecker(String currentInput)
	{
		boolean hasLength = false;
		
		if(currentInput != null && currentInput.length() > 0)
		{
			hasLength = true;
		}
		
		return hasLength;
	}

	/**
	 * * Checks if the supplied String matches the content area for this Chatbot
	 * instance.
	 * 
	 * @param currentInput
	 *            The supplied String to be checked. * @return Whether it
	 *            matches the content area.
	 */
	public boolean contentChecker(String currentInput) 
	{
		boolean hasContent = false;
		
		
		
		if (currentInput != null && currentInput.toLowerCase().contains(content.toLowerCase()))
		{
			hasContent = true;
		}
		
		return hasContent;
	}

	/**
	 * * Checks if supplied String matches ANY of the topics in the
	 * politicalTopicsList. Returns true if it does find a match and false if it
	 * does not match.
	 * 
	 * @param currentInput
	 *            The supplied String to be checked. * @return Whether the
	 *            String is contained in the ArrayList.
	 */
	public boolean politicalTopicChecker(String currentInput)
	{
		boolean hasPolitical = false;
		
		for (int index = 0; index < politicalTopicList.size(); index++)
		{
			if (currentInput != null && currentInput.equals(politicalTopicList.get(index)))
			{
				hasPolitical = true;
			}
		}
		
		return hasPolitical;
	}

	/**
	 * * Checks to see that the supplied String value is in the current
	 * memesList variable.
	 * 
	 * @param currentInput
	 *            The supplied String to be checked. * @return Whether the
	 *            supplied String is a recognized meme.
	 */
	public boolean memeChecker(String currentInput)
	{
		boolean hasMeme = false;
		
		for (int position = 0; position < memesList.size(); position++)
		{
			if (currentInput != null && currentInput.equalsIgnoreCase(memesList.get(position)))
			{
				hasMeme = true;
			}
		}
		
		return hasMeme;
	}
	
	
	public boolean quitChecker(String currentInput)
	{
		boolean didQuit = false;
		if (currentInput != null && !currentInput.equalsIgnoreCase("exit") && currentInput.equalsIgnoreCase("Quit"))
		{
			didQuit = true;
		}
		
		return didQuit;
	}

	
	public boolean keyboardMashChecker(String currentInput)
	{
		boolean didMash = false;
		if (currentInput != null && !currentInput.equalsIgnoreCase("S.D.F.") && !currentInput.equalsIgnoreCase("derf") && currentInput.equals("sdf") || currentInput.equals("dfg") || currentInput.equals("cvb") || currentInput.equals(",./"))
		{
			didMash = true;
		}
		return didMash;
	}
	/**
	 * * Returns the username of this Chatbot instance. * @return The username
	 * of the Chatbot.
	 */
	public String getUserName() 
	{
		return userName;
	}

	/**
	 * * Returns the content area for this Chatbot instance. * @return The
	 * content area for this Chatbot instance.
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * * Getter method for the memesList object. * @return The reference to the
	 * meme list.
	 */
	public ArrayList<String> getMemesList() 
	{
		return memesList;
	}

	/**
	 * * Getter method for the politicalTopicList object. * @return The
	 * reference to the political topic list.
	 */
	public ArrayList<String> getPoliticalTopicList() 
	{
		return politicalTopicList;
	}

	/**
	 * * Updates the content area for this Chatbot instance. * @param content
	 * The updated value for the content area.
	 */
	public void setContent(String content) 
	{
	 this.content = content;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

}
