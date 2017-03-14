package chat.controller;


import chat.view.ChatFrame;
import chat.model.CTECTwitter;
import chat.model.ChatBot;
import chat.view.ChatViewer;
public class ChatController 

{
	
	private ChatBot stupidBot;
	private ChatViewer chatView;
	private ChatFrame baseFrame;
	private CTECTwitter tweetBot;
	
	
	public ChatController()
	{
		stupidBot = new ChatBot("Italian");
		tweetBot = new CTECTwitter(this);
		chatView = new ChatViewer();
		baseFrame = new ChatFrame(this);
	}
	
	public void start() 
	{
		
		//String response = chatView.collectResponse("talking to you ");
		
		//while(stupidBot.lengthChecker(response))
	//	{
	//		chatView.displayMessage(useChatbotCheckers(response));
	//		response = chatView.collectResponse("You're interested in " + response);
	//	}
		
	}
	
	
	public String useChatbotCheckers(String input)
	{
		String answer = "";
		if(!stupidBot.quitChecker(input))
		{
			if (stupidBot.contentChecker(input))
			{
				answer += "\nYou know my special secret\n";
			}
			if(stupidBot.memeChecker(input))
			{
				answer += "\nI can has memes?\n";
			}
			if(stupidBot.politicalTopicChecker(input))
			{
				answer += "\nAn answer?\n";
			}
		
			if(!stupidBot.lengthChecker(answer))
			{
				answer += "Sorry, I don't know about " + input;
			}
			
			int canBeRandom = (int)(Math.random() * 7);
			if (canBeRandom % 7 == 0)
			{
				answer += randomTopicGenerator();
			}
		}
		else
		{
			chatView.displayMessage("thanks for playing");
			System.exit(0);
		}
		
		
		return answer;
	}
	
	public String randomTopicGenerator()
	{
		String randomTopic = "";
		int random = (int)(Math.random() * 7);
		switch(random)
		{
		
		case 0:
			randomTopic = "Did you see the new _____ video?";
			break;
		case 1:
			randomTopic = "Polotics right.";
			break;
		case 2:
			
		
		
		
		}
		
		return randomTopic;
		
	}
	
	public void handleErrors(Exception newException)
	{
		
		chatView.displayMessage("An error has occured. Detailed results nest.");
		chatView.displayMessage(newException.getMessage());		
		
	}
	
	public String searchTwitter(String name)
	{
		String results = tweetBot.getMostPopularWord(name);
		return results;
	}
	
	public String bf1Search(String wantedTopic)
	{
		String results = tweetBot.tweetsBF1(wantedTopic);
		return results;
	}
	
	public void useTwitter(String text)
	{
		tweetBot.sendTweet(text);
	}
	
	public ChatViewer getPopUp()
	{
		return chatView;
	}
	
	public ChatBot getChatbot()
	{
		return stupidBot;
	}
	
	public ChatFrame getBaseFrame()
	{
		return baseFrame;
	}
	
	
	
	
}
