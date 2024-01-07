package com.tbpbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

//Kelas utama yang berfungsi sebagai driver program untuk manajemen reservasi Open Trip di Labuan Bajo.
//Mengimplementasikan interface LoginInterface untuk melakukan autentikasi admin.

public class App implements LoginInterface {
    // Atribut untuk menyimpan koneksi database
    static Connection databaseConnection;
    // Atribut untuk menentukan apakah program akan berlanjut atau tidak
    private boolean lanjut = true;
    // deklarasi variabel input / Objek Scanner untuk menerima input dari pengguna
    private Scanner input = new Scanner(System.in);

    @Override
    public boolean authenticateAdmin(String username, String password) {
        // autentikasi login dengan method string "equals"
        return username.equals("admin") && password.equals("admin123");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        App app = new App();
        // method string ToUpperCase
        System.out.println("================" + "Selamat Datang".toUpperCase() + "================");
        System.out.print("Inputkan Username\t: ");
        String username = input.nextLine();
        System.out.print("Inputkan Password\t: ");
        String password = input.nextLine();

        if (app.authenticateAdmin(username, password)) {
            System.out.println("Login berhasil");
            app.runProgram();
        } else {
            System.out.println("Username atau Password salah. Program Berhenti");
        }
        input.close();
    }

    @Override
    public void runProgram() {
        String pilihan;
        String url = "jdbc:mysql://localhost:3306/labuan_bajo";
        String user = "root";
        String password = "";
        // Method Date
        DateFormat formatTanggal = new SimpleDateFormat("dd MMMM yyyy");
        DateFormat formatJam = new SimpleDateFormat("HH:mm:ss");

        try {
            // Mencari dan memuat driver JDBC untuk MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Membuat koneksi ke database MySQL
            databaseConnection = DriverManager.getConnection(url, user, password);
            System.out.println("Class Driver ditemukan!");

            Reservasi reservasi = new Reservasi();

            while (lanjut) {
                System.out.println("==============================================");
                System.out.println("       LABUAN BAJO BY PESONA INDONESIA");
                java.util.Date tanggal = new java.util.Date();
                System.out.println("\t\t" + formatTanggal.format(tanggal));
                System.out.println("\t\t  " + formatJam.format(tanggal) + " WIB");
                System.out.println("---------------------------------------------");
                reservasi.menu();

                System.out.print("\nPilih menu program (1/2/3/4/5/6): ");
                pilihan = input.next();

                // Pengunaan CRUD
                switch (pilihan) {
                    case "1":
                        reservasi.display();
                        break;
                    case "2":
                        reservasi.insert();
                        break;
                    case "3":
                        reservasi.update();
                        break;
                    case "4":
                        reservasi.delete();
                        break;
                    case "5":
                        reservasi.search();
                        break;
                    case "6":
                        System.out.println("\nKeluar dari program. Program selesai.");
                        break;
                    default:
                        System.out.println("\nPilihan tidak ditemukan. Silakan pilih [1-5]");
                        pilihan = input.next();
                }

                System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
                pilihan = input.next();
                lanjut = pilihan.equalsIgnoreCase("y"); // method String
            }
            System.out.println("Program Selesai");
        } // Exception handling
        catch (ClassNotFoundException ex) {
            System.err.println("Driver Error: " + ex.getMessage());
            ex.printStackTrace();
        } // Exception handling
        catch (SQLException e) {
            System.err.println("Koneksi Tidak Berhasil : " + e.getMessage());
        } finally {
            try {
                if (databaseConnection != null) {
                    databaseConnection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            input.close();
        }
    }

}
