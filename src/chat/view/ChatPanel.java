package chat.view;
import chat.controller.ChatController;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatPanel extends JPanel
{
	private ChatController baseController;
	private SpringLayout baseLayout;
	private JTextArea chatDisplay;
	private JTextField chatField;
	private JButton chatButton;
	private JLabel chatLabel;
	
	public ChatPanel(ChatController baseController)
	{
		super();
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		chatDisplay = new JTextArea(5, 25);
		chatField = new JTextField(25);
		chatButton = new JButton("This one has words");
		chatLabel = new JLabel("This also has words");
	
		setupChatDisplay();
		setupPanel();
		setupLayout();
		setupListeners();
	}
	private void setupChatDisplay()
	{
		chatDisplay.setEditable(false);
		chatDisplay.setEnabled(false);
		chatDisplay.setLineWrap(true);
		chatDisplay.setWrapStyleWord(true);
		
		
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setBackground(Color.GRAY);
		this.add(chatDisplay);
		this.add(chatButton);
		this.add(chatField);
		this.add(chatLabel);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.SOUTH, chatDisplay, -42, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatDisplay, -74, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatField, 63, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatButton, -136, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatField, -6, SpringLayout.NORTH, chatButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatButton, -32, SpringLayout.NORTH, chatDisplay);
		baseLayout.putConstraint(SpringLayout.WEST, chatLabel, 162, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatLabel, -6, SpringLayout.NORTH, chatField);
		
	}
	
	private void setupListeners()
	{
		
	}
}
