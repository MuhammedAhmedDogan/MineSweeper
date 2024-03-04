import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    // Değişkenler
    int row, col, size, mines, winCount;
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
        this.boardCreator(this.map);
        this.mineCreator();

        while (!isWin && !isLose) {
            this.print(this.board);
            this.print(this.map);
            this.select();
        }

        if (isWin) {
            this.print(map);
            System.out.println("Tebrikler oyunu kazandınız.");
        }

        if (isLose) {
            this.print(map);
            System.out.println("Oyun bitti. Mayına bastınız !");
        }
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
            } else if (this.map[selectedRow][selectedCol].equals("*")) {
                this.isLose = true;
                break;
            } else if (!this.board[selectedRow][selectedCol].equals("-") && !this.map[selectedRow][selectedCol].equals("*")) {
                System.out.println("Daha önce seçilmiş bir koordinat girdiniz ! Lütfen tekrar giriniz.");
            } else {
                this.countMines(selectedRow, selectedCol);
                if (++winCount == (this.size - this.mines))
                    this.isWin = true;
                break;
            }
        }
    }

    public void countMines(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            i = Math.max(i, 0);
            if (i == this.row)
                break;
            for (int j = col - 1; j <= col + 1; j++) {
                j = Math.max(j, 0);
                if (j == this.col)
                    break;
                count = map[i][j].equals("*") ? count + 1 : count;
            }
        }
        this.board[row][col] = Integer.toString(count);
        this.map[row][col] = Integer.toString(count);
    }


}
