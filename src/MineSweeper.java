import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper { // Değerlendirme formu 5
    // Değişkenler. MineSweeper sınıfının nitelikleri
    int row, col, size, mines, winCount; // Oyun tahtasının boyutları, mayın sayısı ve kazanma durumunu hesaplamak bir sayaç tanımlandı.
    boolean isWin = false, isLose = false; // Oyunun kazanıldığını ya da kaybedildiğini belirleyen boolean değişkenler.
    String[][] board, map; // Kullanıcıya gösterilen oyun tahtası ve oyun boyunca hesaplamalar için kullanılan, oyun sonunda gösterilen mayın konumları.
    Random random = new Random(); // Mayın konumlarını rastgele belirlemek için Random sınıfından bir random nesnesi oluşturuldu.
    Scanner scan = new Scanner(System.in); // Kullanıcıdan veri almak için Scanner sınıfından scan nesnesi oluşturuldu.

    // MineSweeper sınıfının constructor metodu. Kullanıcıdan alınan satır ve sütun değerleri ile oyun tahtasının niteliklerini oluşturur.
    MineSweeper(int row, int col) {
        this.row = row;
        this.col = col;
        this.size = row * col;
        this.mines = (int) Math.round(this.size / 4.0); // Tahta büyüklüğüne göre mayın sayısı hesaplandı.
        this.board = new String[row][col];
        this.map = new String[row][col];
    }

    // Oyunu çalıştıran run metodu. ( Değerlendirme formu 6 )
    public void run() {
        this.boardCreator(this.board);
        this.boardCreator(this.map);
        this.mineCreator();

        // Değerlendirme formu 11
        while (!isWin && !isLose) {
            this.print(this.board);
            this.select();
        }
        // Değerlendirme formu 15
        System.out.println("Mayınların konumu");
        this.print(map);
        String conclusion = isWin ? "Tebrikler oyunu kazandınız." : "Oyun bitti. Mayına bastınız !";
        System.out.println(conclusion);
    }

    // Boş oyun tahtasını oluşturan boardCreator metodu.
    public void boardCreator(String[][] arr) {
        for (String[] strings : arr) {
            Arrays.fill(strings, "-");
        }
    }

    // Tahtanın 1/4'ü sayıda mayın üretip rastgele yerleştiren mineCreator metodu. (Değerlendirme formu 8 )
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

    // Oyun tahtasını ekrana bastıran print metodu.
    public void print(String[][] arr) {
        for (String[] row : arr) {
            for (String col : row) {
                System.out.print(col + "  ");
            }
            System.out.println();
        }
        System.out.println("====================");
    }

    // Kullanıcıdan işaretlemek istediği koordinatları alan ve uygunluğunu kontrol eden select metodu. ( Değerlendirme formu 9, 10 )
    public void select() {
        for (; ; ) {
            System.out.println("Tahtada açmak istediğiniz koordinatları satır ve sütun olarak giriniz.");    // Değerlendirme formu 9
            System.out.print("Satır :");
            int selectedRow = scan.nextInt();
            System.out.print("Sütun :");
            int selectedCol = scan.nextInt();

            if (selectedRow < 0 || selectedCol < 0 || selectedRow >= this.row || selectedCol >= this.col) { // Değerlendirme formu 10
                System.out.println("Oyun tahtası dışında bir koordinat girdiniz ! Lütfen tekrar giriniz.");
            } else if (this.map[selectedRow][selectedCol].equals("*")) {                                    // Değerlendirme formu 13 ( Mayına basılma durumu )
                this.isLose = true;
                break;
            } else if (!this.board[selectedRow][selectedCol].equals("-") && !this.map[selectedRow][selectedCol].equals("*")) {
                System.out.println("Daha önce seçilmiş bir koordinat girdiniz ! Lütfen tekrar giriniz.");
            } else {
                this.countMines(selectedRow, selectedCol);
                if (++winCount == (this.size - this.mines)) // Değerlendirme formu 14 ( Oyunu kazanma durumu )
                    this.isWin = true;
                break;
            }
        }
    }

    // Girilen noktanın çevresindeki mayınları sayan countMines metodu. ( Değerlendirme formu 12 )
    // select metodu içerisinde seçilen noktada mayın olmaması durumunda çağırıldı.
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
