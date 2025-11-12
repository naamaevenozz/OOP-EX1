public class Game{

    private Player playerX;
    private Player playerO;
    private Renderer renderer;

    private Board board;
    private int winStreak;

    public Game (Player playerX,Player
            playerO, Renderer renderer){
        this(playerX, playerO, 4, 3, renderer);
    }
    public Game(Player playerX,Player
            playerO, int size, int
                        winStreak,Renderer renderer){
        this.playerX = playerX;
        this.playerO = playerO;
        this.winStreak = winStreak;
        this.renderer = renderer;
        this.board = new Board(size);

    }
    public  int getWinStreak(){
        return winStreak;
    }
    int getBoardSize(){
        return board.getSize();
    }
    public Mark run(){
        Mark current = Mark.X;

        while (true){
            if(current == Mark.X){
                playerX.playTurn(board, Mark.X);
            }
            else {
                playerO.playTurn(board, Mark.O);
            }
            renderer.renderBoard(board);

            if (checkWin(current)){
                return current;
            }

            if (isBoardFull()){
                return Mark.BLANK;
            }

            current = (current == Mark.X ? Mark.O : Mark.X);
        }
    }

    public boolean checkWin(Mark mark){
        int size = board.getSize();

        for (int row = 0; row < size ; row++){
            for (int col = 0; col < size ; col++){

                if (board.getMark(row, col) == mark)
                {
                    if (checkDirection(mark, row, col, 1, 0) ||
                            checkDirection(mark, row, col, 0, 1) ||
                            checkDirection(mark, row, col, 1, -1) ||
                            checkDirection(mark, row, col, 1, 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkDirection(Mark mark, int row, int col, int dr, int dc){
        int count = 0;
        int size = board.getSize();

        for (int i = 0; i < winStreak; i++) {
            int r = row + i * dr;
            int c = col + i * dc;

            if (r < 0 || r >= size || c < 0 || c >= size){
                return false;
            }
            if (board.getMark(r, c) != mark){
                return false;
            }

            count++;
        }

        return (count == winStreak);
    }

    private boolean isBoardFull() {
        int size = board.getSize();
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (board.getMark(r, c) == Mark.BLANK) return false;
            }
        }
        return true;
    }
}