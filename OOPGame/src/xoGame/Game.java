package xoGame;

import java.util.Scanner;

public class Game {

	public void play() {
		Scanner in = new Scanner(System.in);
		System.out.println("-------------------------\n// Classic Tic-Tac-Toe //\n-------------------------\n");
		System.out
				.print("Select Game Mode:\n\t(1) Single Player\n\t(2) Local Multiplayer\n?-> Enter an option number:");
		int choice = 0;
		String fPlayerName, sPlayerName = null;
		char fSympol, goFirst, sSympol = '0';
		boolean isManual = false;
		try {

			choice = in.nextInt();

			if (choice != 1 && choice != 2)
				throw new Exception();



			System.out.print("Enter Player 1's name:");
			fPlayerName = in.next();

			do {
				System.out.print("Choose your symbol! Enter 'X' or 'O':");
				fSympol = in.next().charAt(0);
				if (fSympol == 'x' || fSympol == 'o')
					fSympol = (char) (fSympol - 32);
				System.out.print("Would you like to go first? Enter y/n:");

				goFirst = in.next().charAt(0);
				if (goFirst == 'y' || goFirst == 'n')
					goFirst = (char) (goFirst - 32);

			} while (!(fSympol == 'X' && goFirst == 'Y' || fSympol == 'O' && goFirst == 'Y'
					|| fSympol == 'X' && goFirst == 'N' || fSympol == 'O' && goFirst == 'N'));

			if(choice==1)
				sPlayerName ="Tic-Tac-Troll";
			else 
			{
				System.out.print("Enter Player 2's name:");
				sPlayerName = in.next();
			}
			
			if (fSympol == 'x' || fSympol == 'X')
				sSympol = 'O';
			else
				sSympol = 'X';
			System.out.print("Select Turn Switch:\n\t(1) Alternating\n\t(2) Manual\n?-> Enter an option number:");

			choice = in.nextInt();

			if (choice != 1 && choice != 2)
				throw new Exception();
			if (choice == 2)
				isManual = true;

			if (goFirst == 'Y')
				goFirst = fSympol;
			else
				goFirst = sSympol;

			Board b = new Board(isManual, new Player(fPlayerName, fSympol), new Player(sPlayerName, sSympol),
					generateBoard(), goFirst);
			b.start();

		} catch (Exception e) {
			System.out.println("Invalid Option.");
			play();
		}

	}

	public static char[][] generateBoard() {

		char board[][] = new char[3][3];

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				board[i][j] = ' ';

		return board;

	}

}
