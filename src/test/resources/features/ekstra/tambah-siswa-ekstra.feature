Feature: Fitur Ekstrakurikuler


  Background:
    Given pengguna berada di halaman login
    When pengguna mengisi email dan password yang benar
    And menekan tombol login
    Then pengguna diarahkan ke halaman dashboard

  Scenario: Tambah ekstrakurikuler siswa
    When User klik tombol ekstrakurikuler
    Then User diarahkan ke halaman monitoring Ekstrakurikuler
    When User klik tombol tambah siswa
    Then User diarahkan ke halaman tambah siswa ekstrakurikuler
    When User menginputkan data ekstra siswa
    Then User diarahkan kembali ke halaman monitoring Ekstrakurikuler

  Scenario: Gagal menambahkan ekstrakurikuler karena form tidak lengkap
    When User klik tombol ekstrakurikuler
    Then User diarahkan ke halaman monitoring Ekstrakurikuler
    When User klik tombol tambah siswa
    Then User diarahkan ke halaman tambah siswa ekstrakurikuler
    When User menginputkan data ekstra siswa yang tidak lengkap
    Then sistem menampilkan pesan kesalahan validasi