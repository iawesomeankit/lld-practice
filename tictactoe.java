import java.util.Scanner;
public class Main {
	public static class Player {
		private String name;
		private char symbol;
		
		public Player(String name, char symbol) {
			this.name = name;
			this.symbol = symbol;
		}
		
		public String getName() {
			return name;
		}
		
		public char getSymbol() {
			return symbol;
		}
	}

	public static class Board {
		private char[][] grid;
		private int size;
		
		public Board(int size) {
			this.size = size;
			grid = new char[size][size];
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					grid[i][j] = '-';
				}
			}
		}
		
		public boolean placeMark(int row, int col, char symbol) {
			if (grid[row][col] == '-') {
				grid[row][col] = symbol;
				return true;
			}
			return false;
		}
		
		public void printBoard() {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					System.out.print(grid[i][j] + " ");
				}
				System.out.println();
			}
		}
		
		public boolean checkWin(char symbol) {
			// Check rows and columns
			for (int i = 0; i < size; i++) {
				if (checkRow(i, symbol) || checkCol(i, symbol)) {
					return true;
				}
			}
			// Check diagonals
			return checkDiagonal(symbol);
		}
		
		private boolean checkRow(int row, char symbol) {
			for (int i = 0; i < size; i++) {
				if (grid[row][i] != symbol) return false;
			}
			return true;
		}
		
		private boolean checkCol(int col, char symbol) {
			for (int i = 0; i < size; i++) {
				if (grid[i][col] != symbol) return false;
			}
			return true;
		}
		
		private boolean checkDiagonal(char symbol) {
			boolean diagonal1 = true, diagonal2 = true;
			for (int i = 0; i < size; i++) {
				if (grid[i][i] != symbol) diagonal1 = false;
				if (grid[i][size - i - 1] != symbol) diagonal2 = false;
			}
			return diagonal1 || diagonal2;
		}
		
		public boolean isDraw() {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (grid[i][j] == '-') return false;
				}
			}
			return true;
		}
	}

	public static class Game {
		private Player[] players;
		private Board board;
		private int currentPlayer;
		
		public Game(Player player1, Player player2, int boardSize) {
			players = new Player[]{player1, player2};
			board = new Board(boardSize);
			currentPlayer = 0;
		}
		
		public void start() {
			Scanner scanner = new Scanner(System.in);
			boolean gameWon = false;
			
			while (!gameWon && !board.isDraw()) {
				board.printBoard();
				Player player = players[currentPlayer];
				System.out.println(player.getName() + "'s turn. Enter row and column (0, 1, or 2): ");
				int row = scanner.nextInt();
				int col = scanner.nextInt();
				
				if (board.placeMark(row, col, player.getSymbol())) {
					if (board.checkWin(player.getSymbol())) {
						board.printBoard();
						System.out.println(player.getName() + " wins!");
						gameWon = true;
					} else if (board.isDraw()) {
						board.printBoard();
						System.out.println("The game is a draw.");
					} else {
						switchPlayer();
					}
				} else {
					System.out.println("Cell is already occupied. Try again.");
				}
			}
			scanner.close();
		}
		
		private void switchPlayer() {
			currentPlayer = (currentPlayer + 1) % 2;
		}
}

public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter player 1 name and symbol:");
	String name1 = sc.next();
	String symbol1 = sc.next();
	Player player1 = new Player(name1, symbol1.charAt(0));
	System.out.println("Enter player 2 name and symbol:");
	String name2 = sc.next();
	String symbol2 = sc.next();
	Player player2 = new Player(name2, symbol2.charAt(0));
	Game game = new Game(player1, player2, 3);
	game.start();
}
}
