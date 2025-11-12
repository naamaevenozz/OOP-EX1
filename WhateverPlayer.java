import java.util.Random;

public class WhateverPlayer implements Player{
    private Random random = new Random();

    public WhateverPlayer(){}

    @Override
    public void playTurn(Board board, Mark mark){
        int size = board.getSize();
        while (true){
            int row = random.nextInt(size);
            int col = random.nextInt(size);

            if (board.putMark(mark, row, col))
            {
                return;
            }
        }
    }
}
