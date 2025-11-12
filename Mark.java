
import java.util.Scanner;
import java.util.Random;

public enum Mark {
    BLANK,
    X,
    O;


    public String toString() {
        if (this == BLANK){
            return null;
        }
        return this.name();
    }

}
//public class Board{
//    private Mark[][] grid;
//
//    public Board()
//    {
//        this(3);
//    }
//    public Board(int size)
//    {
//        grid = new Mark[size][size];
//        for (int i = 0; i < size; i++)
//        {
//            for (int j = 0; j < size; j++)
//            {
//                grid[i][j] = Mark.BLANK;
//            }
//        }
//    }
//    public int getSize()
//    {
//        return grid.length;
//    }
//
//    public Mark getMark(int row, int col)
//    {
//        if ((row < 0 || row >= getSize()) || (col < 0 || col >= getSize()))
//        {
//            return Mark.BLANK;
//        }
//        return this.grid[row][col];
//    }
//
//    public boolean putMark(Mark mark, int row, int col)
//    {
//        if (this.getMark(row, col) != Mark.BLANK)
//        {
//            return false;
//        }
//        this.grid[row][col] = mark;
//        return true;
//    }
//
//}

//public interface Renderer{
//    void renderBoard(Board board);
//}

//public class VoidRenderer implements Renderer{
//    public VoidRenderer(){}
//
//    @Override
//    public void renderBoard(Board board){}
//}

//public interface Player{
//    void playTurn(Board board, Mark mark);
//}

//public class HumanPlayer implements Player{
//    public HumanPlayer()
//    {
//    }
//
//    @Override
//    public void playTurn(Board board, Mark mark){
//        System.out.printIn("Player" + mark + ", type coordinates:");
//        while (true){
//            int input = KeyBoardInput.readInt();
//            int row = input / 10;
//            int col = input % 10;
//
//            if ((row < 0 || row >= getSize()) || (col < 0 || col >= getSize()))
//            {
//                System.out.printIn("Invalid mark position. Please choose a valid position: ");
//            }
//
//            if (!board.putMark(mark, row, col)) {
//                System.out.println("Mark position is already occupied. Please choose a valid position:");
//                continue;
//            }
//
//            return;
//
//        }
//    }
//}

//public class WhateverPlayer implements Player{
//    private Random random = new Random();
//
//    public WhateverPlayer(){}
//
//    @Override
//    public void playTurn(Board board, Mark mark){
//        int size = board.getSize();
//        while (true){
//            int row = random.nextInt(size);
//            int col = random.nextInt(size);
//
//            if (board.putMark(mark, row, col))
//            {
//                return;
//            }
//        }
//    }
//}

//public class NaivePlayer implements Player{
//
//    public NaivePlayer(){}
//
//    @Override
//    public void playTurn(Board board, Mark mark){
//        int size = board.getSize();
//
//        for (int row = 0; row < size; row++){
//            for (int col = 0; col < size; col++)
//            {
//                if (board.putMark(mark, row, col))
//                {
//                    return;
//                }
//            }
//        }
//    }
//}


//public class SmartPlayer implements Player{
//    private int winStreak;
//
//    public SmartPlayer(){}
//
//    @Override
//    public void playTurn(Board board, Mark mark){
//        int size = board.getSize();
//        Mark opponent = (mark == Mark.X ? Mark.O : Mark.X);
//
//    }
//}

//public class Game{
//
//    private Player playerX;
//    private Player playerO;
//    private Renderer renderer;
//
//    private Board board;
//    private int winStreak;
//
//    public Game (Player playerX,Player
//            playerO, Renderer renderer){
//        this(playerX, playerO, 3, 4, renderer);
//    }
//    public Game(Player playerX,Player
//            playerO, int size, int
//                        winStreak,Renderer renderer){
//        this.playerX = playerX;
//        this.playerO = playerO;
//        this.winStreak = winStreak;
//        this.renderer = renderer;
//        this.board = new Board(size);
//
//    }
//    public  int getWinStreak(){
//        return winStreak;
//    }
//    int getBoardSize(){
//        return board.getSize();
//    }
//    public Mark run(){
//        Mark current = Mark.X;
//
//        while (true){
//            if(current == Mark.X){
//                playerX.playTurn(board, Mark.X);
//            }
//            else {
//                playerO.playTurn(board, Mark.O);
//            }
//            renderer.renderBoard(board);
//
//            if (checkWin(current)){
//                return current;
//            }
//
//            if (isBoardFull){
//                return Mark.BLANK;
//            }
//
//            current = (current == Mark.X ? Mark.O : Mark.X);
//        }
//    }
//
//    public boolean checkWin(Mark mark){
//        int size = board.getSize();
//
//        for (int r = 0; r < size ; r++){
//            for (int c = 0; c < size ; c++){
//
//                if (board.getMark(row, col) == mark)
//                {
//                     if (checkDirection(mark, r, c, 1, 0) ||
//                     checkDirection(mark, r, c, 0, 1) ||
//                     checkDirection(mark, r, c, 1, -1) ||
//                     checkDirection(mark, r, c, 1, 1)) {
//                         return true;
//                     }
//                }
//            }
//        }
//        return false;
//    }
//
//    public boolean checkDirection(Mark mark, int row, int col, int dr, int dc){
//        int count = 0;
//        int size = board.getSize();
//
//        for (int i = 0; i < winStreak; i++) {
//            int r = row + i * dr;
//            int c = col + i * dc;
//
//            if (r < 0 || r >= size || c < 0 || c >= size){
//                return false;
//            }
//            if (board.getMark(r, c) != mark){
//                return false;
//            }
//
//            count++;
//        }
//
//        return (count == winStreak);
//    }
//
//    private boolean isBoardFull() {
//        int size = board.getSize();
//        for (int r = 0; r < size; r++) {
//            for (int c = 0; c < size; c++) {
//                if (board.getMark(r, c) == Mark.BLANK) return false;
//            }
//        }
//        return true;
//    }
//}
//public class Tournament{
//    private int rounds;
//    private Renderer renderer;
//    private Player player1;
//    private Player player2;
//    public Tournament(int rounds, Renderer renderer, Player player1, Player player2){
//        this.rounds = rounds;
//        this.renderer = renderer;
//        this.player1 = player1;
//        this.player2 = player2;
//    }
//    public void playTournament(int size, int winStreak,
//    String playerName1, String playerName2){
//        int player1Wins = 0;
//        int player2Wins = 0;
//        int ties = 0;
//
//        for (int round = 0; round < rounds; round++)
//        {
//            Game game;
//            if (round % 2 == 0){
//                game = new Game(player1, player2, renderer);
//            }else{
//                game = new Game(player2, player1, renderer);
//            }
//
//            Mark result = game.run();
//
//            if (result == Mark.X) {
//                if (i % 2 == 0) {
//                    player1Wins++;
//                } else {
//                    player2Wins++;
//                }
//            }
//            else if (result == Mark.O) {
//                if (i % 2 == 0) {
//                    player2Wins++;
//                } else {
//                    player1Wins++;
//                }
//            }
//            else {
//                ties++;
//            }
//        }
//
//        System.out.println("######### Results #########");
//        System.out.println("Player 1, " + playerName1 + " won: " + player1Wins + " rounds");
//        System.out.println("Player 2, " + playerName2 + " won: " + player2Wins + " rounds");
//        System.out.println("Ties: " + ties);
//        }
//    }

//public class PlayerFactory {
//    public PlayerFactory() {}
//    public Player buildPlayer(String type) {}
//}

//public class RendererFactory {
//    public RendererFactory() {}
//    public Renderer buildRenderer(String type, int size) {}
//}
//public static void main(String[] args) {
//
//
//    Renderer renderer = new ConsoleRenderer(3);
//    Player p1 = new HumanPlayer();
//    Player p2 = new NaivePlayer();
//
//    Game g = new Game(p1, p2, 3, 3, renderer);
//    Mark winner = g.run();
//
//    System.out.println("Winner is: " + winner);
//}
