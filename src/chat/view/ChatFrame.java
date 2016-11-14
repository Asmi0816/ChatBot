package chat.view;
import chat.controller.ChatController;
import javax.swing.*;
import java.awt.Dimension;
public class ChatFrame extends JFrame
{
		private ChatController baseController;
		private ChatPanel basePanel;
		
		public ChatFrame (ChatController baseController)
		{
			super();
			this.baseController = baseController;
			basePanel = new ChatPanel(baseController);
			
			setupFrame();
		}
		
		private void setupFrame()
		{
			this.setContentPane(basePanel);
			this.setTitle("You got served sucker");
			this.setSize(new Dimension(600, 400));
			this.setResizable(false);
			this.setVisible(true);
			
		}
	
}
