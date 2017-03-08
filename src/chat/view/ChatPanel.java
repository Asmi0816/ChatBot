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
	private JScrollPane chatPane;
	private JTextArea chatDisplay;
	private JTextField chatField;
	private JButton chatButton;
	private JButton loadButton;
	private JButton tButton;
	private JButton hButton;
	private JButton searchTButton;
	private JButton saveButton;
	private JLabel chatLabel;
	
	public ChatPanel(ChatController baseController)
	{
		super();
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		
		chatField = new JTextField(25);
		baseLayout.putConstraint(SpringLayout.WEST, chatField, 283, SpringLayout.WEST, this);
		
		chatButton = new JButton("This one has words");
		
		chatPane = new JScrollPane();
		searchTButton = new JButton("Check twitter");
		
		
		loadButton = new JButton("load a topic?");
		
		
		tButton = new JButton("Post to twitter.");
		
		chatDisplay = new JTextArea(5, 25);
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
		chatPane.setViewportView(chatDisplay);
		chatPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		chatPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setPreferredSize(new Dimension(900, 800));
		this.setBackground(Color.GRAY);
		this.add(chatButton);
		this.add(chatField);
		this.add(chatLabel);
		this.add(searchTButton);
		this.add(hButton);
		this.add(loadButton);
		this.add(saveButton);
		this.add(tButton);
		this.add(chatPane);
		
		
		
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, chatDisplay, 6, SpringLayout.SOUTH, tButton);
		baseLayout.putConstraint(SpringLayout.WEST, chatDisplay, 0, SpringLayout.WEST, chatField);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatPane, -100, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, searchTButton, 382, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, hButton, 154, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, 0, SpringLayout.NORTH, hButton);
		baseLayout.putConstraint(SpringLayout.EAST, hButton, -10, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatLabel, 372, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatLabel, -6, SpringLayout.NORTH, chatField);
		baseLayout.putConstraint(SpringLayout.NORTH, searchTButton, 62, SpringLayout.SOUTH, saveButton);
		baseLayout.putConstraint(SpringLayout.WEST, saveButton, 303, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatPane, 400, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, tButton, 0, SpringLayout.EAST, searchTButton);
		baseLayout.putConstraint(SpringLayout.NORTH, tButton, 0, SpringLayout.NORTH, hButton);
		baseLayout.putConstraint(SpringLayout.NORTH, saveButton, 26, SpringLayout.SOUTH, tButton);
		baseLayout.putConstraint(SpringLayout.WEST, chatPane, 200, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatPane, -200, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatButton, 90, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatButton, -377, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatField, -6, SpringLayout.NORTH, chatButton);
		baseLayout.putConstraint(SpringLayout.WEST, loadButton, 10, SpringLayout.WEST, this);
		
	}
	
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent click)
			{
				String userWords = chatField.getText();
				String botResponse = baseController.useChatbotCheckers(userWords);
				String currentText = chatDisplay.getText();
				
				chatDisplay.setText("You said: " + userWords + "\n" + "Chatbot says: " + botResponse + "\n" + currentText);
				chatDisplay.setCaretPosition(chatDisplay.getCaretPosition());
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
				baseController.useTwitter(chatField.getText());
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
				String fileName = chatField.getText() + ".txt";
				String saved = FileController.readFile(baseController, fileName);
				chatDisplay.setText(saved);
			}
		});
		searchTButton.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent click)
			{
				String userName = chatField.getText();
				chatDisplay.setText(baseController.searchTwitter(userName));
			}
		});
	}
}
