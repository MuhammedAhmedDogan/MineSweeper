import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    // Değişkenler
    int row, col, size, mines;
    boolean isWin = false, isLose = false;
    String[][] board, map;

    Random random = new Random();
    Scanner scan = new Scanner(System.in);

    MineSweeper(int row, int col) {
        this.row = row;
        this.col = col;
        this.size = row * col;
        this.mines = (int) Math.round(this.size / 4.0);
        this.board = new String[row][col];
        this.map = new String[row][col];
    }

    public void run() {
        this.boardCreator(this.board);
        this.map = this.board;
        this.mineCreator();
        this.print(this.board);
        this.print(this.map);
        this.select();
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
            if (!this.map[randRow][randCol].equals("*")) {
                this.map[randRow][randCol] = "*";
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

    public void select() {
        for (; ; ) {
            System.out.println("Tahtada açmak istediğiniz koordinatları satır ve sütun olarak giriniz.");
            System.out.print("Satır :");
            int selectedRow = scan.nextInt();
            System.out.print("Sütun :");
            int selectedCol = scan.nextInt();

            if (selectedRow < 0 || selectedCol < 0 || selectedRow >= this.row || selectedCol >= this.col) {
                System.out.println("Oyun tahtası dışında bir koordinat girdiniz ! Lütfen tekrar giriniz.");
            } else if (map[selectedRow][selectedCol].equals("*")) {
                this.isLose = true;
                break;
            } else if (!board[selectedRow][selectedCol].equals("-") && !map[selectedRow][selectedCol].equals("*")) {
                System.out.println("Daha önce seçilmiş bir koordinat girdiniz ! Lütfen tekrar giriniz.");
            } else {
                this.countMines(selectedRow, selectedCol);
                break;
            }
        }
    }

    public void countMines(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                count = map[i][j].equals("*") ? count + 1 : count;
            }
        }
        this.board[row][col] = Integer.toString(count);
    }


}
