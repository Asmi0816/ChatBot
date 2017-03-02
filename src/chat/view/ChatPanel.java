package chat.view;
import chat.controller.ChatController;
import chat.controller.FileController;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatPanel extends JPanel
{
	private ChatController baseController;
	private SpringLayout baseLayout;
	private JTextArea chatDisplay;
	private JTextField chatField;
	private JButton chatButton;
	private JButton loadButton;
	private JButton tButton;
	private JButton hButton;
	private JButton saveButton;
	private JLabel chatLabel;
	
	public ChatPanel(ChatController baseController)
	{
		super();
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		chatDisplay = new JTextArea(5, 25);
		
		chatField = new JTextField(25);
		
		chatButton = new JButton("This one has words");
		
		
		loadButton = new JButton("load a topic?");
		
		tButton = new JButton("Twitter may have something...");
		
		hButton = new JButton("HTML?!?");
		
		saveButton = new JButton("DO you wish to save this converstion?");
		
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
		this.setPreferredSize(new Dimension(1100, 400));
		this.setBackground(Color.GRAY);
		this.add(chatDisplay);
		this.add(chatButton);
		this.add(chatField);
		this.add(chatLabel);
		this.add(hButton);
		this.add(loadButton);
		this.add(saveButton);
		this.add(tButton);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, tButton, 7, SpringLayout.SOUTH, hButton);
		baseLayout.putConstraint(SpringLayout.NORTH, hButton, 0, SpringLayout.NORTH, loadButton);
		baseLayout.putConstraint(SpringLayout.WEST, hButton, 157, SpringLayout.EAST, loadButton);
		baseLayout.putConstraint(SpringLayout.NORTH, chatField, 6, SpringLayout.SOUTH, chatLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, chatLabel, 36, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatLabel, -425, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, saveButton, 7, SpringLayout.SOUTH, chatDisplay);
		baseLayout.putConstraint(SpringLayout.WEST, saveButton, 10, SpringLayout.WEST, chatDisplay);
		baseLayout.putConstraint(SpringLayout.NORTH, chatDisplay, 6, SpringLayout.SOUTH, tButton);
		baseLayout.putConstraint(SpringLayout.EAST, tButton, -362, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, 25, SpringLayout.SOUTH, chatButton);
		baseLayout.putConstraint(SpringLayout.WEST, loadButton, 417, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatButton, 16, SpringLayout.SOUTH, chatField);
		baseLayout.putConstraint(SpringLayout.EAST, chatButton, -400, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatField, -327, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatDisplay, -325, SpringLayout.EAST, this);
		
		
	}
	
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent click)
			{
				String userWords = chatField.getText();
				String botResponse = baseController.useChatbotCheckers(userWords);
				
				chatDisplay.setText("You said: " + userWords + "\n" + "Chatbot said: " + botResponse);
				chatField.setText("");
			}
		});
		
		hButton.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent click)
			{
				
			}
		});
		
		tButton.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent click)
			{
				
			}
		});
		
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent click)
			{
				String fileName = chatField.getText();
				
				FileController.saveFile(baseController, fileName.trim(), chatDisplay.getText());
			}
		});
		
		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent click)
			{
				
			}
		});
	}
}
