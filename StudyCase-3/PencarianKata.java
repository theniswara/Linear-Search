import java.util.ArrayList; // Digunakan untuk menyimpan semua posisi kata yang ditemukan
import java.util.Scanner;   // Untuk membaca input dari pengguna

public class PencarianKata {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Membuat Scanner

        System.out.println("=== SISTEM PENCARIAN KATA ===");

        System.out.print("Masukkan teks: ");
        String teks = scanner.nextLine(); // Input teks sumber

        System.out.print("Masukkan kata yang dicari: ");
        String kataCari = scanner.nextLine(); // Input kata yang akan dicari

        // Panggil fungsi untuk mencari posisi kemunculan kata
        ArrayList<Integer> posisiDitemukan = cariKata(teks, kataCari);

        System.out.println("\nHASIL PENCARIAN:");
        if (posisiDitemukan.size() > 0) {
            // Jika ditemukan, tampilkan jumlah dan posisi
            System.out.println("Kata '" + kataCari + "' ditemukan sebanyak " +
                               posisiDitemukan.size() + " kali pada posisi:");

            for (int i = 0; i < posisiDitemukan.size(); i++) {
                System.out.println("- Indeks ke-" + posisiDitemukan.get(i) +
                                   " (karakter ke-" + (posisiDitemukan.get(i) + 1) + ")");
            }

            // Tampilkan teks yang diberi tanda highlight pada kata
            System.out.println("\nTeks dengan highlight:");
            tampilkanTeksHighlight(teks, kataCari, posisiDitemukan);
        } else {
            // Jika tidak ditemukan
            System.out.println("Kata '" + kataCari + "' tidak ditemukan dalam teks.");
        }

        scanner.close(); // Menutup Scanner
    }

    // Fungsi untuk mencari semua posisi kemunculan kata dalam teks (case-insensitive)
    public static ArrayList<Integer> cariKata(String teks, String kata) {
        ArrayList<Integer> posisi = new ArrayList<>();

        String teksLower = teks.toLowerCase(); // Konversi ke huruf kecil
        String kataLower = kata.toLowerCase();

        int panjangKata = kataLower.length(); // Panjang kata target
        int panjangTeks = teksLower.length(); // Panjang keseluruhan teks

        // Lakukan pencarian linear dari awal hingga akhir teks
        for (int i = 0; i <= panjangTeks - panjangKata; i++) {
            // Ambil substring sepanjang kata target
            String potongan = teksLower.substring(i, i + panjangKata);

            // Jika sama, berarti kata ditemukan
            if (potongan.equals(kataLower)) {
                posisi.add(i); // Simpan posisi indeks awal kemunculan
                // Catatan: jika ingin abaikan overlap, bisa tambahkan i += panjangKata - 1;
            }
        }

        return posisi;
    }

    // Fungsi untuk menampilkan teks dengan highlight (pakai tanda [ dan ])
    public static void tampilkanTeksHighlight(String teks, String kata, ArrayList<Integer> posisi) {
        StringBuilder hasil = new StringBuilder(teks); // Gunakan StringBuilder untuk manipulasi teks

        // Sisipkan tanda highlight dari belakang agar indeks tidak bergeser
        for (int i = posisi.size() - 1; i >= 0; i--) {
            int pos = posisi.get(i);
            hasil.insert(pos + kata.length(), "]"); // Tambah penutup setelah kata
            hasil.insert(pos, "[");                // Tambah pembuka sebelum kata
        }

        System.out.println(hasil.toString()); // Tampilkan teks dengan highlight
    }
}
