package xoGame;

import java.util.Scanner;

public class Board {
	private int round;
	private char board[][];
	private boolean isManual;
	private Player player1;
	private Player player2;
	private char playFirst;
	private String currentName;
	private char currentSympol;
	private Scanner in;

	public Board() {
	}

	public Board(boolean isManual, Player p1, Player p2, char board[][], char playFirst) {
		in = new Scanner(System.in);
		this.round = 1;
		this.isManual = isManual;
		player1 = p1;
		player2 = p2;
		this.board = board;
		this.playFirst = playFirst;
	}

	public void start() {

		System.out.println("\nRound: " + round++);
		System.out.println("WINS -- " + player1.getName() + ": " + player1.getScore() + "\n\t" + player2.getName()
				+ ": " + player2.getScore());

		if (playFirst == player1.getSympol()) {

			currentName = player1.getName();
			currentSympol = player1.getSympol();

		} else if (playFirst == player2.getSympol()) {
			currentName = player2.getName();
			currentSympol = player2.getSympol();

		}
		if(!currentName.equals("Tic-Tac-Troll"))
			printBoard();

		play();

	}

	public void printBoard() {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (j != 2)
					System.out.print("\t" + board[i][j] + "\t|");
				else
					System.out.print("\t" + board[i][j] + "\t");

			}
			if (i != 2)
				System.out.println("\n-------------------------------------------------");

		}

		System.out.println();

	}

	public void play() {

		char whoWin;
		while ((whoWin = checkWin(board)) == '0') {

			System.out.print(currentName + "'s turn:\n");
			String coor;
			if(currentName.equals("Tic-Tac-Troll"))
			{
				Ai ai=new Ai();
				String move =ai.minMax(board,9,currentSympol);
				board[move.charAt(0) - '0'][move.charAt(1) - '0'] =currentSympol ;

			}
			else 
			{
			System.out.print("Make a move (Enter Coordinate xy):");
			coor = in.next();
			if (coor.length() != 2 || coor.charAt(0) > '2' || coor.charAt(0) < '0' || coor.charAt(1) > '2'
					|| coor.charAt(1) < '0') {
				System.out.println("Cannot complete move -- Invalid coordinate entry.");
				play();
			}
			if (board[coor.charAt(0) - '0'][coor.charAt(1) - '0'] != ' ') {
				System.out.println("Cannot complete move -- The space has been played.");
				play();
			}

			board[coor.charAt(0) - '0'][coor.charAt(1) - '0'] = currentSympol;
			}
			printBoard();

			if (player1.getSympol() == currentSympol) {
				currentSympol = player2.getSympol();
				currentName = player2.getName();

			} else if (player2.getSympol() == currentSympol) {
				currentSympol = player1.getSympol();
				currentName = player1.getName();

			}

		}

		if (whoWin == '1')
			System.out.println("We have a draw!");

		else if (player1.getSympol() == whoWin) {
			System.out.println(player1.getName() + " has won the game\n");
			player1.setScore(player1.getScore() + 1);

		} else if (player2.getSympol() == whoWin) {
			System.out.println(player2.getName() + " has won the game\n");
			player2.setScore(player2.getScore() + 1);

		}
		char choice = '0';
		do {
			System.out.println("Would you like to play again? Enter y/n:");
			choice = in.next().charAt(0);
			if (choice == 'y' || choice == 'n')
				choice -= (char) (32);
			if (choice == 'N')
				return;

		} while (choice != 'Y' && choice != 'N');

		if (isManual) {

			do {
				System.out.print("Would you like to let your opponent go first? Enter y/n:");
				choice = in.next().charAt(0);
				if (choice == 'y' || choice == 'n')
					choice -= (char) (32);

			} while (choice != 'Y' && choice != 'N');

			if (choice == 'y' || choice == 'Y')
				playFirst = currentSympol;
			else
				playFirst = currentSympol == 'X' ? 'O' : 'X';

		} else
			playFirst = playFirst == 'X' ? 'O' : 'X';

		board = Game.generateBoard();
		start();

	}

	public static char checkWin(char[][]board) {

		int xCnt, oCnt = 0;
		for (int i = 0; i < 3; i++) {
			xCnt = 0;
			oCnt = 0;
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == 'X')
					xCnt++;
				else if (board[i][j] == 'O')
					oCnt++;

			}

			if (xCnt == 3)
				return 'X';
			else if (oCnt == 3)
				return 'O';

		}

		for (int i = 0; i < 3; i++) {
			xCnt = 0;
			oCnt = 0;
			for (int j = 0; j < 3; j++) {
				if (board[j][i] == 'X')
					xCnt++;
				else if (board[j][i] == 'O')
					oCnt++;

			}

			if (xCnt == 3)
				return 'X';
			else if (oCnt == 3)
				return 'O';

		}

		if (board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X'
				|| board[2][0] == 'X' && board[1][1] == 'X' && board[0][2] == 'X')
			return 'X';
		if (board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O'
				|| board[2][0] == 'O' && board[1][1] == 'O' && board[0][2] == 'O')
			return 'O';

		int space = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {

				if (board[i][j] == ' ')
					space++;

			}

		}

		if (space == 0)
			return '1';

		return '0';

	}

}
