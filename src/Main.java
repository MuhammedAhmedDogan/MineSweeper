import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int row = 2, col = 2; // Kullanıcı değer girmeden önce tanımlanmış tahta boyutlarının sınır değerleri.

        Scanner scan = new Scanner(System.in);

        // Matris boyutunu belirlemek için kullanıcıdan veri alındı. ( Değerlendirme formu 7 )
        System.out.println("Mayın Tarlası Oyununa Hoşgeldiniz.\nOyun Tahtanızın Boyutunu Ayarlayalım");
        do {
            if (row < 2 || col < 2) {
                System.out.println("Satır veya sütün sayısı 2'den küçük olamaz! Lütfen tekrar giriniz :");
            }
            System.out.print("Satır sayısı : ");
            row = scan.nextInt();
            System.out.print("Sütun sayısı : ");
            col = scan.nextInt();
            System.out.println("========================================");
        } while (row < 2 || col < 2);

        // MineSweeper sınıfından mineSweeper nesnesi üretilerek oyun başlatıldı.
        MineSweeper mineSweeper = new MineSweeper(row, col);
        mineSweeper.run();
    }
}