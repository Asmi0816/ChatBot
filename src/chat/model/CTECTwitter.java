package chat.model;

import chat.controller.ChatController;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;


import twitter4j.Paging;
import twitter4j.Status;

public class CTECTwitter {
	private ChatController baseController;
	private Twitter twitterBot;
	private List<String> tweetedWords;
	private List<Status> allTheTweets;
	

	public CTECTwitter(ChatController baseController) {
		this.baseController = baseController;
		this.tweetedWords = new ArrayList<String>();
		this.allTheTweets = new ArrayList<Status>();
		twitterBot = TwitterFactory.getSingleton();
	}

	public void sendTweet(String textToTweet)
	{
		try 
		{
			twitterBot.updateStatus(textToTweet + " @ChatbotCTEC");
		} 
		catch (TwitterException tweetError) 
		{
			baseController.handleErrors(tweetError);
		} 
		catch (Exception otherError) 
		{
			baseController.handleErrors(otherError);
		}
	}

	private String[] createIgnoredWordsArray() 
	{
		String [] boringWords;
		int wordCount = 0;
		
		Scanner boringWordScanner = new Scanner(this.getClass().getResourceAsStream("commonWords.txt"));
		while(boringWordScanner.hasNextLine())
		{
			boringWordScanner.nextLine();
			wordCount++;
		}
		boringWordScanner.close();
		
		boringWords = new String[wordCount];
		
		boringWordScanner = new Scanner(this.getClass().getResourceAsStream("commonWords.txt"));
		
		for(int index = 0; index < boringWords.length; index++)
		{
			boringWords[index] = boringWordScanner.next();
		}
		
		return boringWords;
	}

	private void removeBlankWords() 
	{
		for (int index = 0; index < tweetedWords.size(); index++) 
		{
			if (tweetedWords.get(index).trim().equals("")) 
			{
				tweetedWords.remove(index);
				index--;
			}
		}
	}
	
	private void gatherTheTweets(String user)
	{
		tweetedWords.clear();
		allTheTweets.clear();
		int pageCount = 1;
		Paging statusPage = new Paging(1,200);
		
		while(pageCount <= 10)
		{
			
			try
			{
			allTheTweets.addAll(twitterBot.getUserTimeline(user, statusPage));
			}
			catch(TwitterException twitterError)
			{
				baseController.handleErrors(twitterError);
			}
			
			
			pageCount++;
		}
	}
	
	private void turnTweetsToWords()
	{
		for(Status currentTweet : allTheTweets)
		{
			String tweetText = currentTweet.getText();
			String [] tweetWords = tweetText.split(" ");
			for(String word :  tweetWords)
			{
				tweetedWords.add(word);
			}
		}
	}
	
	
	private void removeBoringWords() 
	{
		String [] boringWords = createIgnoredWordsArray();
		for(int index = 0; index < tweetedWords.size(); index++)
		{
			for(int boringIndex = 0; boringIndex < boringWords.length; boringIndex++)
			{
				if(tweetedWords.get(index).equalsIgnoreCase(boringWords[boringIndex]))
				{
					tweetedWords.remove(index);
					index--;
					boringIndex = boringWords.length;
				}
			}
		}

	}


	public String getMostPopularWord(String userName) 
	{
		
		gatherTheTweets(userName);
		turnTweetsToWords();
		removeBoringWords();
		removeBlankWords();
		
		String information = "The tweetcount is " + allTheTweets.size() + " and the most popular word for " + userName + " is " + calculateTop();
		
		
		return information;
	}
	
	public String calculateTop()
	{
		String results = "";
		String topWord = "";
		int mostPopularIndex = 0;
		int popularCount = 0;
		for(int index = 0; index < tweetedWords.size(); index++)
		{
			int currentPopularity = 0;
			for(int searched = index + 1; searched < tweetedWords.size(); searched++)
			{
				
				if(tweetedWords.get(index).equalsIgnoreCase(tweetedWords.get(searched)))
				{
					currentPopularity++;
				}
			}
			
			if(currentPopularity > popularCount)
			{
				popularCount = currentPopularity;
				mostPopularIndex = index;
				topWord = tweetedWords.get(mostPopularIndex);
			}
			currentPopularity = 0;
		}
		results += " the most popular word was " + topWord + ", and it occured " + popularCount + " times.";
		results += "\nThat means it has a percentage of " + ((double)popularCount)/tweetedWords.size() * 100 + "%";
		
		return results;
	}

}
