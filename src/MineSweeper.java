import java.util.Arrays;
import java.util.Random;

public class MineSweeper {
    // Değişkenler
    int row, col, size, mines;
    String[][] board, map;

    Random random = new Random();

    MineSweeper(int row, int col) {
        this.row = row;
        this.col = col;
        this.size = row * col;
        this.mines = (int) Math.round(this.size / 4.0);
        this.board = new String[row][col];
        this.map = new String[row][col];
    }

    public void run() {
        boardCreator(this.board);
        this.map = this.board;
        mineCreator();
        print(this.board);
        print(this.map);
    }

    public void boardCreator(String[][] arr) {
        for (String[] strings : arr) {
            Arrays.fill(strings, "-");
        }
    }

    public void mineCreator() {
        int randRow, randCol, count = 0;
        while (count < this.mines) {
            randRow = random.nextInt(row);
            randCol = random.nextInt(col);
            if (!map[randRow][randCol].equals("*")) {
                map[randRow][randCol] = "*";
                count++;
            }
        }
    }

    public void print(String[][] arr) {
        for (String[] row : arr) {
            for (String col : row) {
                System.out.print(col + "  ");
            }
            System.out.println();
        }
        System.out.println("====================");
    }


}
