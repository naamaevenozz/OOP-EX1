public class HumanPlayer implements Player{
    public HumanPlayer()
    {}

    @Override
    public void playTurn(Board board, Mark mark){
        System.out.println("Player" + mark + ", type coordinates:");
        while (true){
            int input = KeyboardInput.readInt();
            int row = input / 10;
            int col = input % 10;

            if ((row < 0 || row >= board.getSize()) || (col < 0 || col >= board.getSize()))
            {
                System.out.println
                        ("Invalid mark position. Please choose a valid position: ");
                continue;
            }

            if (!board.putMark(mark, row, col)) {
                System.out.println
                        ("Mark position is already occupied. Please choose a valid position:");
                continue;
            }
            return;

        }
    }
}