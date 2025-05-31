Feature: Pembayaran Tagihan Siswa Praxis

  Background:
    Given pengguna sudah mengakses halaman monitoring Praxis

  Scenario: Berhasil melakukan pembayaran dengan data lengkap dan benar
    When pengguna klik tombol Bayar pada baris siswa yang ingin dicatat pembayarannya
    And pengguna memilih tanggal pembayaran "2025-05-21"
    And pengguna memasukkan nominal pembayaran:
      | Jenis       | Nominal   |
      | KBM         | 1000000   |
      | Pemeliharaan| 1000000   |
      | SPP         | 1000000   |
      | Sumbangan   | 1000000   |
    And pengguna memasukkan catatan "Tagihan SPP diprioritaskan karena mendesak"
    Then pembayaran berhasil disimpan

  Scenario: Gagal melakukan pembayaran karena nominal KBM tidak diisi
    When pengguna klik tombol Bayar pada baris siswa yang ingin dicatat pembayarannya
    And pengguna tidak mengisi tanggal pembayaran
    And pengguna memasukkan nominal pembayaran:
      | Jenis       | Nominal   |
      | KBM         | 2500000   |
      | Pemeliharaan| 1000000   |
      | SPP         | 1000000   |
      | Sumbangan   | -         |
    And pengguna memasukkan catatan "Tagihan SPP diprioritaskan karena mendesak"
    Then pengguna melihat pesan error "Tanggal pembayaran harus diisi"
    And data pembayaran tidak tersimpan
