package chat.controller;


import chat.view.ChatFrame;
import chat.model.Chatbot;
import chat.view.ChatViewer;
public class ChatController 

{
	
	private Chatbot stupidBot;
	private ChatViewer chatView;
	private ChatFrame baseFrame;
	
	
	public ChatController()
	{
		stupidBot = new Chatbot("Italian");
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
		
		
		
		
		
		}
		
		return randomTopic;
		
	}
	
	public Chatbot getChatbot()
	{
		return stupidBot;
	}
	
	public ChatFrame getBaseFrame()
	{
		return baseFrame;
	}
	
	
	
	
}
