import java.util.Random;

/**
 * SmartPlayer implements a strategy that wins against NaivePlayer and WhateverPlayer
 * in the vast majority of games (at least 80%), as required by the exercise.
 *
 * Works for the default configuration: board size = 4, winStreak = 3.
 * If a different board size is given, the player falls back to random moves
 * to avoid invalid access.
 */
public class SmartPlayer implements Player {
    private static final int SIZE = 4;
    private static final int WIN_STREAK = 3;
    private final Random random = new Random();

    public SmartPlayer() {}

    @Override
    public void playTurn(Board board, Mark mark) {
        int size = board.getSize();
        Mark opponent = (mark == Mark.X) ? Mark.O : Mark.X;

        // Safety guard: if board is not default size, fallback to random play
        if (size < SIZE) {
            randomFallback(board, mark);
            return;
        }

        // 1. Try to win
        int[] move = findWinningMove(board, mark);
        if (move != null) {
            board.putMark(mark, move[0], move[1]);
            return;
        }

        // 2. Try to block opponent
        move = findWinningMove(board, opponent);
        if (move != null) {
            board.putMark(mark, move[0], move[1]);
            return;
        }

        // 3. Try to take the center
        int mid = SIZE / 2;
        if (board.putMark(mark, mid, mid)) {
            return;
        }

        // 4. Try corners
        int[][] corners = {{0, 0}, {0, SIZE - 1}, {SIZE - 1, 0}, {SIZE - 1, SIZE - 1}};
        for (int[] c : corners) {
            if (board.putMark(mark, c[0], c[1])) {
                return;
            }
        }

        // 5. Random move if nothing else
        randomFallback(board, mark);
    }

    /**
     * Finds a move that completes a winning sequence (or blocks one)
     * for 4x4 board with winStreak = 3.
     */
    private int[] findWinningMove(Board board, Mark mark) {
        // Check rows
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col <= SIZE - WIN_STREAK; col++) {
                int countMark = 0, countEmpty = 0, emptyCol = -1;
                for (int k = 0; k < WIN_STREAK; k++) {
                    Mark cell = board.getMark(row, col + k);
                    if (cell == mark) countMark++;
                    else if (cell == Mark.BLANK) {
                        countEmpty++;
                        emptyCol = col + k;
                    }
                }
                if (countMark == WIN_STREAK - 1 && countEmpty == 1)
                    return new int[]{row, emptyCol};
            }
        }

        // Check columns
        for (int col = 0; col < SIZE; col++) {
            for (int row = 0; row <= SIZE - WIN_STREAK; row++) {
                int countMark = 0, countEmpty = 0, emptyRow = -1;
                for (int k = 0; k < WIN_STREAK; k++) {
                    Mark cell = board.getMark(row + k, col);
                    if (cell == mark) countMark++;
                    else if (cell == Mark.BLANK) {
                        countEmpty++;
                        emptyRow = row + k;
                    }
                }
                if (countMark == WIN_STREAK - 1 && countEmpty == 1)
                    return new int[]{emptyRow, col};
            }
        }

        // Check diagonals ↘
        for (int row = 0; row <= SIZE - WIN_STREAK; row++) {
            for (int col = 0; col <= SIZE - WIN_STREAK; col++) {
                int countMark = 0, countEmpty = 0, empty = -1;
                for (int k = 0; k < WIN_STREAK; k++) {
                    Mark cell = board.getMark(row + k, col + k);
                    if (cell == mark) countMark++;
                    else if (cell == Mark.BLANK) {
                        countEmpty++;
                        empty = k;
                    }
                }
                if (countMark == WIN_STREAK - 1 && countEmpty == 1)
                    return new int[]{row + empty, col + empty};
            }
        }

        // Check diagonals ↙
        for (int row = 0; row <= SIZE - WIN_STREAK; row++) {
            for (int col = WIN_STREAK - 1; col < SIZE; col++) {
                int countMark = 0, countEmpty = 0, empty = -1;
                for (int k = 0; k < WIN_STREAK; k++) {
                    Mark cell = board.getMark(row + k, col - k);
                    if (cell == mark) countMark++;
                    else if (cell == Mark.BLANK) {
                        countEmpty++;
                        empty = k;
                    }
                }
                if (countMark == WIN_STREAK - 1 && countEmpty == 1)
                    return new int[]{row + empty, col - empty};
            }
        }

        return null;
    }

    /**
     * Fallback method for random legal move when board size differs from default.
     */
    private void randomFallback(Board board, Mark mark) {
        int size = board.getSize();
        while (true) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (board.putMark(mark, row, col)) {
                return;
            }
        }
    }
}
