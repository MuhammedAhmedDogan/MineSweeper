import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int row = 2, col = 2;

        Scanner scan = new Scanner(System.in);

        System.out.println("Mayın Tarlasına Hoşgeldiniz. Oyun Tahtanızın Boyutunu Ayarlayalım");
        do {
            if (row < 2 || col < 2) {
                System.out.println("====================");
                System.out.println("Satır veya sütün sayısı 2'den küçük olamaz! Lütfen tekrar giriniz :");
            }
            System.out.print("Satır sayısı :");
            row = scan.nextInt();
            System.out.print("Sütun sayısı :");
            col = scan.nextInt();
        } while (row < 2 || col < 2);

        MineSweeper mineSweeper = new MineSweeper(row, col);
        mineSweeper.run();

    }
}