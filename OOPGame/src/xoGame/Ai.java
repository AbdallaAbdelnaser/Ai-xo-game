package xoGame;

import java.util.ArrayList;

public class Ai {
	private int[] iStart = { 0, 1, 2, 0, 0, 0, 0, 0 };
	private int[] jStart = { 0, 0, 0, 0, 1, 2, 0, 2 };

	private int[] iIncrement = { 0, 0, 0, 1, 1, 1, 1, 1 };
	private int[] jIncrement = { 1, 1, 1, 0, 0, 0, 1, -1 };

	String minMax(char board[][], int depth, char player) {
		String bestMove = "";
		double bestMoveValue = Double.NEGATIVE_INFINITY;
		for (String move : availableMoves(board)) {

			char[][] copyBoard = copyBoard(board);

			copyBoard[move.charAt(0) - '0'][move.charAt(1) - '0'] = player;
			double moveValue = min(copyBoard, depth - 1, player);

			if (moveValue > bestMoveValue) {
				bestMoveValue = moveValue;
				bestMove = move;

			}

		}

		return bestMove;

	}

	public double min(char[][] board, int depth, char player) {
		char winner = Board.checkWin(board);
		char opponent = (player == 'X') ? 'O' : 'X';
		if (winner == player)
			return Double.POSITIVE_INFINITY;
		else if (winner == opponent)
			return Double.NEGATIVE_INFINITY;
		else if (winner == '0')
			return 0;

		if (depth == 0)
			return evaluate(board, player);

		double bestMoveValue = Double.POSITIVE_INFINITY;

		for (String move : availableMoves(board)) {

			char[][] copyBoard = copyBoard(board);

			copyBoard[move.charAt(0) - '0'][move.charAt(1) - '0'] = opponent;
			double moveValue = max(copyBoard, depth - 1, player);

			if (moveValue < bestMoveValue)
				bestMoveValue = moveValue;

		}

		return bestMoveValue;

	}

	public double max(char[][] board, int depth, char player) {
		char winner = Board.checkWin(board);
		char opponent = (player == 'X') ? 'O' : 'X';
		if (winner == player)
			return Double.POSITIVE_INFINITY;
		else if (winner == opponent)
			return Double.NEGATIVE_INFINITY;
		else if (winner == '0')
			return 0;

		if (depth == 0)
			return evaluate(board, player);

		double bestMoveValue = Double.NEGATIVE_INFINITY;

		for (String move : availableMoves(board)) {

			char[][] copyBoard = copyBoard(board);

			copyBoard[move.charAt(0) - '0'][move.charAt(1) - '0'] = player;
			double moveValue = min(copyBoard, depth - 1, player);

			if (moveValue > bestMoveValue)
				bestMoveValue = moveValue;

		}

		return bestMoveValue;

	}
	 private int evaluate(char[][] board, char player) {
	        int value = 0;

	        /*
	         * For each line of the eight lines
	         * */
	        for (int line = 0; line < iStart.length; line++) {

	            int i = iStart[line];
	            int j = jStart[line];


	            /*
	             * Number of squares in the line for each player
	             * */
	            int playersSquare = 0;
	            int opponentSquare = 0;

	            int emptySquares = 0;

	            int k = 0;
	            do {
	                char currentSquare = board[i][j];

	                if (currentSquare != ' ') {
	                    if (currentSquare == player)
	                        playersSquare++;
	                    else
	                        opponentSquare++;
	                } else
	                    emptySquares++;

	                i += iIncrement[line];
	                j += jIncrement[line];

	                k++;
	            } while (k < 3);

	            if (playersSquare == 2 && emptySquares == 1)
	                value += 10;
	            else if (playersSquare == 1 && emptySquares == 2)
	                value += 1;

	            if (opponentSquare == 2 && emptySquares == 1)
	                value -= 10;
	            else if (opponentSquare == 1 && emptySquares == 2)
	                value -= 1;
	        }

	        return value;
	    }
	ArrayList<String> availableMoves(char[][] board) {
		ArrayList<String> moves = new ArrayList<>();
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++)
				if (board[i][j] == ' ')
					moves.add(i + "" + j);

		return moves;
	}

	char[][] copyBoard(char board[][]) {
		char copied[][] = new char[3][3];
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++)
				copied[i][j] = board[i][j];

		return copied;

	}

}
