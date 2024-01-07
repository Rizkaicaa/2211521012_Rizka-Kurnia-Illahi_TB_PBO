package com.tbpbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

//inherit (SUBCLASS) dari kelas Menu
public class Reservasi extends TripLabuanBajo implements TotalPriceCalculator{

    // Atribut untuk data reservasi
    String NamaPemesan;
    String NoHp;
    String TanggalKeberangkatan;
    Integer JumlahPeserta;
    Integer TotalHarga;
    Integer Bayar;
    Integer Kembalian;

    //Construktor
    public Reservasi() {
        NamaPemesan = "";
        NoHp = "";
        TanggalKeberangkatan = "";
        JumlahPeserta = 0;
        TotalHarga = 0;
        Bayar = 0;
        Kembalian = 0;
    }
    
    Scanner input = new Scanner(System.in);
    // variable koneksi
    static Connection conn;

    
    @Override
    //Method Insert untuk menambahkan/Create Reservasi
    public void insert() throws SQLException {
        // link database
        String url = "jdbc:mysql://localhost:3306/labuan_bajo";
        // exception
        try {
            ListPaket();
            System.out.println("\n------------Inputkan Data Reservasi------------");
            // Input Nama Pemesan
            System.out.print("Nama Pemesan\t\t\t: ");
            NamaPemesan = input.next();

            // Input No HP
            System.out.print("No Hp\t\t\t\t: ");
            NoHp = input.next();

            // Input Tanggal berangkat
            System.out.print("Tanggal Berangkat\t\t: ");
            TanggalKeberangkatan = input.next();

            
            // Input JenisPaket
            System.out.print("Pilih Jenis Paket  (1/2/3/4/5/6): ");
            JenisPaket = input.nextInt();
            
            // Paket Wisata yang ada
            if (JenisPaket == 1) {
                NamaPaket = "Open Trip Sailing Komodo 1 Day";
                Harga = 600000;
                System.out.println("Nama Paket\t\t\t: " + NamaPaket);
                System.out.println("Harga\t\t\t\t: Rp" + Harga);
            } else if (JenisPaket == 2) {
                NamaPaket = "Open Trip Sailing Komodo 2D1N";
                Harga = 1600000;
                System.out.println("Nama Paket\t\t\t: " + NamaPaket);
                System.out.println("Harga\t\t\t\t: Rp" + Harga);
            } else if (JenisPaket == 3) {
                NamaPaket = "Open Trip Sailing Komodo 3D2N";
                Harga = 2500000;
                System.out.println("Nama Paket\t\t\t: " + NamaPaket);
                System.out.println("Harga\t\t\t\t: Rp" + Harga);
            } else if (JenisPaket == 4) {
                NamaPaket = "Open Trip Wae Rebo 2D1N";
                Harga = 1800000;
                System.out.println("Nama Paket\t\t\t: " + NamaPaket);
                System.out.println("Harga\t\t\t\t: Rp" + Harga);
            } else if (JenisPaket == 5) {
                NamaPaket = "Open Trip Wae Rebo-Kelimutu 3D2N";
                Harga = 2400000;
                System.out.println("Nama Paket\t\t\t: " + NamaPaket);
                System.out.println("Harga\t\t\t\t: Rp" + Harga);
            } else if (JenisPaket == 6) {
                NamaPaket = "Open Trip Sumba Explore 4D3N";
                Harga = 2600000;
                System.out.println("Nama Paket\t\t\t: " + NamaPaket);
                System.out.println("Harga\t\t\t\t: Rp" + Harga);
            } else {
                System.out.println("Pilihan Paket Tidak tersedia");
            }

            // Input Jumlah Pax/orang
            System.out.print("Jumlah Peserta\t\t\t: ");
            int JumlahPeserta = input.nextInt();

            // Proses Matematika (Total Harga dan Kembalian)
            TotalHarga = calculateTotalPrice(JumlahPeserta, Harga);
            System.out.println("Total Harga\t\t\t: Rp" + TotalHarga);
            int Bayar;
            do {
                System.out.print("Uang yang diberikan pemesan\t: Rp");
                Bayar = input.nextInt();
                if (Bayar < TotalHarga) {
                    System.out.println("Uang Anda Tidak Cukup. Silakan Masukkan Jumlah Uang yang Mencukupi.");
                }
            } while (Bayar < TotalHarga);

            Kembalian = Bayar - TotalHarga; //PROSES
            System.out.println("Kembalian\t\t\t: Rp" + Kembalian);
            System.out.println("---------------------------------------------");

            // SQL Query masukkan data ke database
            String sql = "INSERT INTO reservasi (NamaPemesan, NoHp, TanggalKeberangkatan, NamaPaket, JenisPaket, JumlahPeserta, Harga, TotalHarga, Bayar, Kembalian) VALUES ('"
                    + NamaPemesan + "','" + NoHp + "','" + TanggalKeberangkatan + "','" + NamaPaket + "','" + JenisPaket
                    + "','" + JumlahPeserta + "','" + Harga + "','" + TotalHarga + "','" + Bayar + "','" + Kembalian
                    + "')";

            // connect ke database
            conn = DriverManager.getConnection(url, "root", "");
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Berhasil Menambahkan Data Reservasi!");
        }

        // exception database
        catch (SQLException e) {
            System.err.println("Terjadi Kesalahan Input Data! SQL: " + e.getMessage());
        }
        // exception tipe data inputan
        catch (InputMismatchException e) {
            System.err.println("Inputan Harus Berupa Angka: " + e.getMessage());
        }

    }

    @Override
    public int calculateTotalPrice(int jumlahPeserta, int harga) {
        return jumlahPeserta * harga; //proses matematika
    }

    @Override
    //Method Display untuk manampilkan Data Reservasi
    public void display() throws SQLException {
        // link database
        String url = "jdbc:mysql://localhost:3306/labuan_bajo";

        try {
            // SQL Query
            String sql = "SELECT * FROM reservasi";
            // connect ke database
            conn = DriverManager.getConnection(url, "root", "");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if (result.next()) {
                System.out.print("\n-----------Data Reservasi Labuan Bajo----------- ");
                do {
                    // Menampilkan data di database
                    System.out.print("\nID Reservasi\t\t: ");
                    System.out.print(result.getInt("IdReservasi"));
                    System.out.print("\nNama Pemesan\t\t: ");
                    System.out.print(result.getString("NamaPemesan"));
                    System.out.print("\nNo Hp\t\t\t: ");
                    System.out.print(result.getString("NoHp"));
                    System.out.print("\nTanggal Keberangkatan\t: ");
                    System.out.print(result.getString("TanggalKeberangkatan"));
                    System.out.print("\nJenis Paket\t\t: ");
                    System.out.print(result.getInt("JenisPaket"));
                    System.out.print("\nNama Paket\t\t: ");
                    System.out.print(result.getString("NamaPaket"));
                    System.out.print("\nJumlah Peserta\t\t: ");
                    System.out.print(result.getString("JumlahPeserta"));
                    System.out.print("\nHarga\t\t\t: Rp");
                    System.out.print(result.getInt("Harga"));
                    System.out.print("\nTotal Harga\t\t: Rp");
                    System.out.print(result.getInt("TotalHarga"));
                    System.out.print("\nBayar\t\t\t: Rp");
                    System.out.print(result.getInt("Bayar"));
                    System.out.print("\nKembalian\t\t: Rp");
                    System.out.print(result.getInt("Kembalian"));
                    System.out.print("\n");
                } while (result.next());
            } else {
                System.out.println("Belum ada riwayat pendaftaran reservasi");
            }

        } catch (SQLException e) {
            System.err.println("Terjadi Kesalahan");
        } catch (InputMismatchException e) {
            System.err.println("Program Error");
        }
    }

    //Method Update untuk mangubah atau memperbari Data Reservasi
    @Override
    public void update() {
        System.out.print("\n--------------Ubah Data Reservasi---------------");

        try {
            display();
            // link database
            String url = "jdbc:mysql://localhost:3306/labuan_bajo";
            System.out.print("\nMasukkan IdReservasi yang akan di ubah : ");
            // Menerima inputan IDReservasi Baru
            Integer IdReservasi = Integer.parseInt(input.next());
            // SQL Query
            String sql = "SELECT * FROM reservasi  WHERE IdReservasi = " + IdReservasi;
            // Connect ke Database
            conn = DriverManager.getConnection(url, "root", "");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if (result.next()) {
                System.out.println("Pilih opsi untuk diubah:");
                System.out.println("1. Nama Pemesan");
                System.out.println("2. Tanggal Keberangkatan");
                System.out.print("Masukkan pilihan (1/2): ");
                int option = input.nextInt();

                switch (option) {
                    case 1:
                        System.out.print("Ubah Nama Pemesan [" + result.getString("NamaPemesan") + "]\t: ");
                        String NamaPemesan = input.next();

                        // Perbarui data menggunakan pernyataan UPDATE yang benar
                        sql = "UPDATE reservasi SET NamaPemesan='" + NamaPemesan + "' WHERE IdReservasi=" + IdReservasi;

                        if (statement.executeUpdate(sql) > 0) {
                            System.out.println("Berhasil memperbaharui data (IdReservasi: " + IdReservasi + ")");
                        }
                        break;

                    case 2:
                        System.out.print(
                                "Ubah Tanggal Keberangkatan [" + result.getString("TanggalKeberangkatan") + "]\t: ");
                        String TanggalKeberangkatan = input.next();

                        
                        sql = "UPDATE reservasi SET TanggalKeberangkatan='" + TanggalKeberangkatan
                                + "' WHERE IdReservasi=" + IdReservasi;

                        if (statement.executeUpdate(sql) > 0) {
                            System.out.println("Berhasil memperbaharui data (IdReservasi: " + IdReservasi + ")");
                        }
                        break;

                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } else {
                System.out.println("Data reservasi dengan ID Reservasi " + IdReservasi + " tidak ditemukan");
            }
        }
        // SQL Exception
        catch (SQLException e) {
            System.err.println("Terjadi kesalahan dalam mengedit data");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void search() throws SQLException {
        System.out.print("\n--------------Cari Data Reservasi---------------");
        System.out.println("");
        // Link Database
        String url = "jdbc:mysql://localhost:3306/labuan_bajo";
        try {
            System.out.print("Masukkan IdReservasi yang ingin dicari : ");
            // Membaca input IdReservasi dan membersihkan buffer
            Integer keyword = Integer.parseInt(input.next());
            input.nextLine(); // Membersihkan buffer

            // Connect ke Database
            conn = DriverManager.getConnection(url, "root", "");
            Statement statement = conn.createStatement();
            // SQL Query Search(Select)
            String sql = "SELECT * FROM reservasi WHERE IdReservasi LIKE '%" + keyword + "%'";
            ResultSet result = statement.executeQuery(sql);
            // Cek apakah data ditemukan atau tidak
            boolean dataFound = false;

            // Menampilkan Data di Database yang di cari
            while (result.next()) {
                dataFound = true;
                System.out.print("\nData Reservasi Ditemukan ");
                System.out.println("---------------------------------------------");
                System.out.print("\nID Reservasi\t\t: ");
                System.out.print(result.getInt("IdReservasi"));
                System.out.print("\nNama Pemesan\t\t: ");
                System.out.print(result.getString("NamaPemesan"));
                System.out.print("\nNo Hp\t\t\t: ");
                System.out.print(result.getString("NoHp"));
                System.out.print("\nTanggal Keberangkatan\t: ");
                System.out.print(result.getString("TanggalKeberangkatan"));
                System.out.print("\nJenis Paket\t\t: ");
                System.out.print(result.getInt("JenisPaket"));
                System.out.print("\nNama Paket\t\t: ");
                System.out.print(result.getString("NamaPaket"));
                System.out.print("\nJumlah Peserta\t\t: ");
                System.out.print(result.getString("JumlahPeserta"));
                System.out.print("\nHarga\t\t\t: Rp");
                System.out.print(result.getInt("Harga"));
                System.out.print("\nTotal Harga\t\t: Rp");
                System.out.print(result.getInt("TotalHarga"));
                System.out.print("\nBayar\t\t\t: Rp");
                System.out.print(result.getInt("Bayar"));
                System.out.print("\nKembalian\t\t: Rp");
                System.out.print(result.getInt("Kembalian"));
                System.out.print("\n");
            }

            // Menampilkan pesan jika data tidak ditemukan
            if (!dataFound) {
                System.out.println("Data tidak ditemukan untuk ID Reservasi " + keyword);
            }
        }
        // Exception SQL
        catch (SQLException e) {
            System.err.println("Terjadi kesalahan");
        }
        // Exception Program
        catch (InputMismatchException e) {
            System.err.println("Program Eror");
        }
    }

    //Method delete untuk membatalkan  Data Reservasi
    @Override
    public void delete() throws SQLException {
        // Link Database
        String url = "jdbc:mysql://localhost:3306/labuan_bajo";
        try {
            // Memanggil methode display
            display();
            System.out.print("\nMasukan ID Reservasi yang akan dibatalkan : ");
            Integer IdReservasi = Integer.parseInt(input.nextLine());
            // SQL Query
            String sql = "DELETE FROM reservasi WHERE IdReservasi = " + IdReservasi;
            // Connect ke Database
            conn = DriverManager.getConnection(url, "root", "");
            Statement statement = conn.createStatement();
            // ResultSet result = statement.executeQuery(sql);

            if (statement.executeUpdate(sql) > 0) {
                System.out.println("Berhasil membatalkan reservasi (ID Reservasi " + IdReservasi + ")");
            } else {
                System.out.println("ID Reservasi tidak ditemukan");
            }
        }
        catch (SQLException e) {
            System.out.println("Terjadi Kesalahan Dalam Menghapus Data!");
        } 
        catch (Exception e) {
            System.out.println("Masukkan Data yang Benar!");
        }
    }
}
