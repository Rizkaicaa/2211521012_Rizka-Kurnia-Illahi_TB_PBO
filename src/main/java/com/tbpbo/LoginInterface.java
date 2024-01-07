package com.tbpbo;

// Interface untuk manajemen login
public interface LoginInterface {
    // Fungsi untuk mengautentikasi pengguna
    boolean authenticateAdmin(String username, String password);

    // Fungsi untuk menjalankan program setelah login berhasil
    void runProgram();
}
