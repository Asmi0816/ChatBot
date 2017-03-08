package chat.model;
import chat.controller.ChatController;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import java.util.List;
import java.util.ArrayList;
import twitter4j.Status;


public class CTECTwitter 
{
	private ChatController baseController;
	private Twitter twitterBot;
	private List<Status> tweetedWords;
	private List<String> ignoredWords;
	
	public CTECTwitter(ChatController baseController)
	{
		this.baseController = baseController;
		tweetedWords = new ArrayList<Status>();
		ignoredWords = new ArrayList<String>();
		twitterBot = TwitterFactory.getSingleton();
	}
	
	public void sendTweet(String textToTweet)
	{
		try
		{
			twitterBot.updateStatus(textToTweet + "@ChatbotCTEC");
		}
		catch(TwitterException tweetError)
		{
			baseController.handleErrors(tweetError);
		}
		catch(Exception otherError)
		{
			baseController.handleErrors(otherError);
		}
	}
	
	private String [] createIgnoredWordsArray()
	{
		String [] ignoredWords = {"the" + "of" + "and" + "a" + "to" + "in" + "is" + "that" + "it" + "he" +  "was" + "you" + "for" + "on" + "are" + "as" + "with" +"his" +"they"+ "at"+ "be"+
				"this"+ "have"+ "via"+ "from" +"or"+ "one"+ "had" +"by" +"but"+ "not"+ "what"+ "all" +"were" +"we"+ "RT" +"I" +"&" +"when" +"your" +"can" +"said" +"there"+
				"use" +"an"+ "each"+ "which" +"she" +"do"+ "how" +"their"+ "if"+ "will" +"up" +"about"+ "out"+ "many"+ "then"+ "them"+ "these" +"so" +"some" +"her" +
				"would"+ "make"	+"him"+	"into" +"has"+ "two" +"go"	+"see" +"no"+ "way" +"could" +"my" +"than"+ "been" +"who"	+"its" +"did" +"get"
				+"may" +"Ö" +"@" +"??" +"Im" +"me"+ "u" +"just"+ "our"+ "like"};
		
		
		return ignoredWords;
	}
	
	private void removeBoringWords()
	{
		for(int index = 0; index < tweetedWords.size(); index++)
		{
			
		}
	}
	
	private void collectTweets(String userName)
	{
		
	}
	
	public String getMostPopularWord(String userName)
	{
		return "";
	}
	
	
}
