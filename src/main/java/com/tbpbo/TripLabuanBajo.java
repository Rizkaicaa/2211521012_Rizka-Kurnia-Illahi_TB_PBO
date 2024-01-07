package com.tbpbo;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

//Superclass
//implement interface IDatabase
public class TripLabuanBajo implements IDatabase {
    // deklarasi variable
    String NamaPaket;
    Integer JenisPaket;
    Integer Harga;
    
    // method menampilkan menu program
    public void menu() {
        List<String> menuOptions = new ArrayList<String>();
        menuOptions.add("1. Lihat Data Reservasi Open Trip");
        menuOptions.add("2. Tambah Reservasi Paket Open Trip");
        menuOptions.add("3. Ubah Reservasi Paket Open Trip");
        menuOptions.add("4. Batalkan Reservasi ");
        menuOptions.add("5. Cari Data Reservasi ");
        menuOptions.add("6. Keluar dari program ");
        // perulangan
        for (Iterator<String> iterator = menuOptions.iterator(); iterator.hasNext();) {
            String string = (String) iterator.next();
            System.out.println(string);
        }
    }

    // methode menampilkan menu pilihan 
    public void ListPaket() {
        System.out.println("===============================================");
        System.out.println("=========PAKET OPEN TRIP YANG TERSEDIA=========");
        System.out.println("===============================================");
        System.out.println("1. Open Trip Sailing Komodo 1 Day   Rp600.000");
        System.out.println("2. Open Trip Sailing Komodo 2D1N    Rp1.600.000");
        System.out.println("3. Open Trip Sailing Komodo 3D2N    Rp2.500.000");
        System.out.println("4. Open Trip Wae Rebo 2D1N          Rp1.800.000");
        System.out.println("5. Open Trip Wae Rebo-Kelimutu 3D2N Rp2.400.000");
        System.out.println("6. Open Trip Sumba Explore 4D3N     Rp2.600.000");
        System.out.println("===============================================");
    }

    public void JenisPaket() {
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
    }
    
    // method menambahkan data
    public void insert() throws SQLException {  
    }

    // method menampilkan data
    public void display() throws SQLException {
    }

    // method mengubah data
    public void update() throws SQLException {
    }

    // method mencari data
    public void search() throws SQLException {
    }

    // method menghapus data
    public void delete() throws SQLException {
    }
}
