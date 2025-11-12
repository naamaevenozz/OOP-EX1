public class Board{
    private Mark[][] grid;

    public Board()
    {
        this(3);
    }
    public Board(int size)
    {
        grid = new Mark[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                grid[i][j] = Mark.BLANK;
            }
        }
    }
    public int getSize()
    {
        return grid.length;
    }

    public Mark getMark(int row, int col)
    {
        if ((row < 0 || row >= getSize()) || (col < 0 || col >= getSize()))
        {
            return Mark.BLANK;
        }
        return this.grid[row][col];
    }

    public boolean putMark(Mark mark, int row, int col)
    {
        if (this.getMark(row, col) != Mark.BLANK)
        {
            return false;
        }
        this.grid[row][col] = mark;
        return true;
    }

}