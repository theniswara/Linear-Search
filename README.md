# Penjelasan

## Study Case 1
```java
import java.util.Scanner; // Mengimpor kelas Scanner untuk membaca input dari pengguna

// Kelas Mahasiswa merepresentasikan entitas mahasiswa
class Mahasiswa {
    String nim;     // Nomor Induk Mahasiswa
    String nama;    // Nama mahasiswa
    String jurusan; // Jurusan mahasiswa
    double ipk;     // Indeks Prestasi Kumulatif

    // Constructor: digunakan untuk membuat objek Mahasiswa dengan data awal
    public Mahasiswa(String nim, String nama, String jurusan, double ipk) {
        this.nim = nim;           // this.nim adalah atribut kelas, nim adalah parameter
        this.nama = nama;
        this.jurusan = jurusan;
        this.ipk = ipk;
    }

    // Override method toString untuk menampilkan data mahasiswa dalam format yang rapi
    @Override
    public String toString() {
        return String.format("NIM: %s\nNama: %s\nJurusan: %s\nIPK: %.2f", nim, nama, jurusan, ipk);
        // %.2f untuk membatasi angka desimal IPK menjadi 2 digit
    }
}

// Kelas utama berisi fungsi main dan logika pencarian mahasiswa
public class PencarianDataMahasiswa {
    public static void main(String[] args) {
        // Membuat array berisi data mahasiswa
        Mahasiswa[] daftarMahasiswa = {
            new Mahasiswa("2023001", "Budi Santoso", "Teknik Informatika", 3.75),
            new Mahasiswa("2023002", "Andi Wijaya", "Sistem Informasi", 3.50),
            new Mahasiswa("2023003", "Dewi Lestari", "Teknik Komputer", 3.90),
            new Mahasiswa("2023004", "Rina Maulana", "Teknik Informatika", 3.60),
            new Mahasiswa("2023005", "Doni Kusuma", "Manajemen Informatika", 3.25),
            new Mahasiswa("2023006", "Sinta Rahma", "Sistem Informasi", 3.85),
            new Mahasiswa("2023007", "Rudi Hermawan", "Teknik Komputer", 3.40)
        };

        Scanner scanner = new Scanner(System.in); // Membuat objek Scanner untuk input keyboard

        System.out.println("=== SISTEM PENCARIAN DATA MAHASISWA ===");
        System.out.print("Masukkan NIM Mahasiswa yang dicari: ");
        String nimCari = scanner.nextLine(); // Membaca input NIM yang ingin dicari

        // Memanggil fungsi pencarian mahasiswa berdasarkan NIM
        Mahasiswa hasilPencarian = cariMahasiswaByNIM(daftarMahasiswa, nimCari);

        System.out.println("\nHASIL PENCARIAN:");
        if (hasilPencarian != null) {
            // Jika mahasiswa ditemukan
            System.out.println("Mahasiswa ditemukan!");
            System.out.println(hasilPencarian); // Akan memanggil toString()
        } else {
            // Jika mahasiswa tidak ditemukan
            System.out.println("Mahasiswa dengan NIM " + nimCari + " tidak ditemukan.");
        }

        scanner.close(); // Menutup Scanner setelah digunakan
    }

    // Fungsi untuk mencari mahasiswa berdasarkan NIM dengan metode linear search
    public static Mahasiswa cariMahasiswaByNIM(Mahasiswa[] daftarMahasiswa, String nim) {
        for (int i = 0; i < daftarMahasiswa.length; i++) {
            // Bandingkan setiap NIM dalam array dengan NIM yang dicari
            if (daftarMahasiswa[i].nim.equals(nim)) {
                return daftarMahasiswa[i]; // Jika cocok, kembalikan objek Mahasiswa
            }
        }
        return null; // Jika tidak ditemukan, kembalikan null
    }
}

```

## Study Case 2
```java
import java.util.ArrayList; // Mengimpor ArrayList untuk menyimpan hasil pencarian dinamis
import java.util.Scanner;   // Mengimpor Scanner untuk membaca input dari pengguna

// Kelas Produk merepresentasikan entitas barang/produk
class Produk {
    String id;
    String nama;
    String kategori;
    double harga;
    int stok;

    // Constructor untuk menginisialisasi data produk
    public Produk(String id, String nama, String kategori, double harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
    }

    // Override method toString untuk menampilkan informasi produk dalam format tabel
    @Override
    public String toString() {
        return String.format("%-5s | %-25s | %-15s | Rp %.2f | Stok: %d",
                             id, nama, kategori, harga, stok);
        // %-5s, %-25s dll adalah formatting agar kolom rapi saat ditampilkan
    }
}

public class PencarianProduk {
    public static void main(String[] args) {
        // Array produk berisi data awal
        Produk[] daftarProduk = {
            new Produk("P001", "Laptop Asus VivoBook", "Elektronik", 8500000, 10),
            new Produk("P002", "Smartphone Samsung Galaxy", "Elektronik", 5000000, 15),
            new Produk("P003", "Kemeja Formal Pria", "Fashion", 250000, 50),
            new Produk("P004", "Sepatu Lari Nike", "Fashion", 1200000, 25),
            new Produk("P005", "Buku Pemrograman Java", "Buku", 150000, 30),
            new Produk("P006", "Mouse Gaming Logitech", "Elektronik", 350000, 20),
            new Produk("P007", "Novel Bumi Manusia", "Buku", 95000, 40),
            new Produk("P008", "Tas Ransel", "Fashion", 300000, 35)
        };

        Scanner scanner = new Scanner(System.in); // Membuat Scanner untuk membaca input

        System.out.println("=== SISTEM PENCARIAN PRODUK ===");
        System.out.println("Kategori tersedia: Elektronik, Fashion, Buku");

        System.out.print("Masukkan kategori produk: ");
        String kategoriCari = scanner.nextLine(); // Input kategori

        System.out.print("Masukkan harga minimum: Rp ");
        double hargaMin = scanner.nextDouble(); // Input harga minimum

        System.out.print("Masukkan harga maksimum: Rp ");
        double hargaMax = scanner.nextDouble(); // Input harga maksimum

        // Panggil fungsi pencarian berdasarkan kriteria
        ArrayList<Produk> hasilPencarian = cariProdukByKriteria(daftarProduk, kategoriCari, hargaMin, hargaMax);

        System.out.println("\nHASIL PENCARIAN:");
        System.out.println("===============================================================");
        System.out.printf("%-5s | %-25s | %-15s | %-10s | %-10s\n",
                          "ID", "Nama Produk", "Kategori", "Harga", "Stok");
        System.out.println("---------------------------------------------------------------");

        if (hasilPencarian.size() > 0) {
            // Jika ditemukan produk yang sesuai, tampilkan semuanya
            for (Produk p : hasilPencarian) {
                System.out.println(p);
            }
        } else {
            // Jika tidak ditemukan
            System.out.println("Tidak ada produk yang sesuai dengan kriteria pencarian.");
        }

        System.out.println("===============================================================");

        scanner.close(); // Menutup Scanner setelah digunakan
    }

    // Fungsi pencarian produk berdasarkan kategori dan rentang harga
    public static ArrayList<Produk> cariProdukByKriteria(Produk[] daftarProduk,
                                                         String kategori,
                                                         double hargaMin,
                                                         double hargaMax) {
        ArrayList<Produk> hasilPencarian = new ArrayList<>();

        for (int i = 0; i < daftarProduk.length; i++) {
            Produk produk = daftarProduk[i];

            // Periksa apakah kategori cocok (ignore case) dan harga dalam rentang
            if (produk.kategori.equalsIgnoreCase(kategori) &&
                produk.harga >= hargaMin &&
                produk.harga <= hargaMax) {
                hasilPencarian.add(produk); // Tambahkan produk yang sesuai ke hasil pencarian
            }
        }

        return hasilPencarian; // Kembalikan hasil pencarian
    }
}
```

### Study Case 3
```java
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
```