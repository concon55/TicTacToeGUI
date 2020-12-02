package tictactoeswing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TicTacToeGUI extends JFrame implements ActionListener{
	
	static JFrame frame;
	static JButton[] board;
	static boolean playerOne;
	static int count;
	static String winner;
	
	TicTacToeGUI(){
		playerOne = true;
		count = 0;
	}
	
	/**
	 * Create buttons and add action listeners
	 * @param frame JFrame
	 * */
	public static void createUI(JFrame frame){
		TicTacToeGUI game = new TicTacToeGUI();
		JPanel panel = new JPanel();
		JPanel newGamePanel = new JPanel();
		JPanel mainPanel = new JPanel();

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		panel.setLayout(new GridLayout(3,3));
		
		//create buttons, put them in board array, add action listener, add to panel
		
		board = new JButton[9];
		for(int i =0; i<9; i++){
			board[i] = new JButton();
			board[i].setName(i+"");
			board[i].addActionListener(game);
			panel.add(board[i]);
		}
		
		mainPanel.add(panel);
		
		JButton newGame = new JButton("New Game");
		newGame.setName("-1");
		newGamePanel.setPreferredSize(new Dimension(200, 30));
		newGame.addActionListener(game);
		newGamePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		newGamePanel.add(newGame);
		mainPanel.add(newGamePanel);
		
	    frame.add(mainPanel);
	}
	
	
	/**
	 * Create the game window
	 */
	private static void createWindow(){
		frame = new JFrame("Tic Tac Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createUI(frame);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		winner = null;
		JButton button = (JButton) e.getSource();
		String btnName = button.getName();
		String btnText = button.getText();
		int index = Integer.parseInt(btnName);
		
		if(btnName.equals("-1")){
			createWindow();
		}
		else if(btnText.isEmpty()){ //slot is available
			if(playerOne){
				board[index].setText("X");
				playerOne=false;
			}else{
				board[index].setText("O");
				playerOne=true;
			}
			count++;
			winner = checkWinner(count);
			if(winner!=null){
				//end game
				JLabel endLabel;
				if(winner.equals("draw")){
					endLabel = new JLabel("Draw!", SwingConstants.CENTER);
				}else{
					endLabel = new JLabel("Congrats! "+winner+ " won!", SwingConstants.CENTER);
				}
				endLabel.setBounds(50, 25, 200, 50);
				
				JLayeredPane gameOver = new JLayeredPane();
		        gameOver.setLayout(null);
		        gameOver.setBounds(50, 50, 300, 100);
		        gameOver.setPreferredSize(new Dimension(300, 100));
		        gameOver.add(endLabel, JLayeredPane.DEFAULT_LAYER);
				this.getContentPane().add(gameOver, BorderLayout.CENTER);
				
				this.pack();
				this.setLocationRelativeTo(null);
				endLabel.setVisible(true);
				this.setVisible(true);
			}
		}	
	}
	
	
	
	/**
	 * Check the winner
	 * @return count The number of entries in board
	 * */
	static String checkWinner(int count){
		//horizontal cases
		if(!board[0].getText().isEmpty() && board[0].getText().equals(board[1].getText()) && board[0].getText().equals(board[2].getText())){
			if(board[0].getText().equals("X")) return "Player 1";
			else return "Player 2";
		}
		if(!board[3].getText().isEmpty() && board[3].getText().equals(board[4].getText()) && board[3].getText().equals(board[5].getText())){
			if(board[3].getText().equals("X")) return "Player 1";
			else return "Player 2";
		}
		if(!board[6].getText().isEmpty() && board[6].getText().equals(board[7].getText()) && board[6].getText().equals(board[8].getText())){
			if(board[6].getText().equals("X")) return "Player 1";
			else return "Player 2";
		}
		
		//vertical cases
		if(!board[0].getText().isEmpty() && board[0].getText().equals(board[3].getText()) && board[0].getText().equals(board[6].getText())){
			if(board[0].getText().equals("X")) return "Player 1";
			else return "Player 2";
		}
		if(!board[1].getText().isEmpty() && board[1].getText().equals(board[4].getText()) && board[1].getText().equals(board[7].getText())){
			if(board[1].getText().equals("X")) return "Player 1";
			else return "Player 2";
		}
		if(!board[2].getText().isEmpty() && board[2].getText().equals(board[5].getText()) && board[2].getText().equals(board[8].getText())){
			if(board[2].getText().equals("X")) return "Player 1";
			else return "Player 2";
		}
		
		//diagonal cases
		if(!board[0].getText().isEmpty() && board[0].getText().equals(board[4].getText()) && board[0].getText().equals(board[8].getText())){
			if(board[0].getText().equals("X")) return "Player 1";
			else return "Player 2";
		}
		if(!board[2].getText().isEmpty() && board[2].getText().equals(board[4].getText()) && board[2].getText().equals(board[6].getText())){
			if(board[2].getText().equals("X")) return "Player 1";
			else return "Player 2";
		}
		
		//draw
		if(count==9) return "draw";
		return null;
	}
	
	public static void main(String[] args) {
		createWindow();
	}

}
