package chat.model;

import chat.controller.ChatController;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import java.util.List;
import java.util.Scanner;

import twitter4j.Query;
import twitter4j.QueryResult;

import java.text.DecimalFormat;
import java.util.ArrayList;

import twitter4j.GeoLocation;
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
		boringWordScanner.close();
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
				tweetedWords.add(removePunctuation(word));
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
	
	public String tweetsBF1(String wantedTopic)
	{
		String results = "";
		
		Query topic = new Query(wantedTopic);
		topic.setCount(100);
		topic.setGeoCode(new GeoLocation(33.812092, -117.918974), 20, Query.MILES);
		topic.setSince("2017-3-12");
		try
		{
			QueryResult foundTweets = twitterBot.search(topic);
			results += "Count : " +foundTweets.getTweets().size() + "\n";
			for(Status tweet : foundTweets.getTweets())
			{
				if(tweet.getText().contains("http") == false)
				{
					if(tweet.getText().contains("RT") == false)
					{
					results += "@" + tweet.getUser().getName() +": " + tweet.getText() + "\n" + "\n" + "Was posted within 20 miles of Disney Land!" + "\n" + "\n" + "\n";
					}
				}
				
			}
			
		}
		catch(TwitterException error)
		{
			baseController.handleErrors(error);
			
		}
		return results;
	}

	public String getMostPopularWord(String userName) 
	{
		
		gatherTheTweets(userName);
		turnTweetsToWords();
		removeBoringWords();
		removeBlankWords();
		
		String information = "The tweetcount is " + allTheTweets.size()  + calculateTop();
		
		
		
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
		results += "\nThat means it has a percentage of " + (DecimalFormat.getPercentInstance().format(((double)popularCount)/tweetedWords.size()));
		
		return results;
	}
	
	public String removePunctuation(String currentString)
	{
		String punctuation = ".,'?!;:\" ()[]^{}<>-";
		String scrubbedString = "";
		for(int i = 0; i < currentString.length(); i++)
		{
			if(punctuation.indexOf(currentString.charAt(i)) == -1)
			{
				scrubbedString += currentString.charAt(i);
			}
		}
		return scrubbedString;
	}
}
