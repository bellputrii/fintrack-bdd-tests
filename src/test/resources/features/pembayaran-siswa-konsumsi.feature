Feature: Pembayaran siswa konsumsi
  Background:
    Given pengguna berada di halaman login
    When pengguna mengisi email dan password yang benar
    And menekan tombol login
    Then pengguna diarahkan ke halaman dashboard
    When pengguna klik tombol konsumsi
    Then pengguna diarahkan ke halaman monitoring konsumsi

  Scenario: Pembayaran siswa konsumsi dengan data valid
    When pengguna klik tombol pembayaran siswa
    Then pengguna diarahkan ke halaman pembayaran siswa konsumsi
    When pengguna menginputkan data pembayaran konsumsi siswa
    And pengguna klik tombol simpan pembayaran
    Then pengguna diarahkan ke halaman monitoring konsumsi
    When pengguna klik tombol riwayat pembayaran siswa
    Then pengguna diarahkan ke halaman riwayat pembayaran siswa
    And pengguna melihat data pembayaran telah tercatat

  Scenario: Pembayaran siswa konsumsi dengan data tidak valid
    When pengguna klik tombol pembayaran siswa
    Then pengguna diarahkan ke halaman pembayaran siswa konsumsi
    When pengguna menginputkan data pembayaran konsumsi siswa yang tidak valid
    And pengguna klik tombol simpan pembayaran
    Then pengguna tetap berada di halaman pembayaran siswa konsumsi
    And pengguna melihat pesan error validasi pembayaran