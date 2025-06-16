Feature: Tambah Kontrak Siswa Praxis

  Background:
    Given pengguna berada di halaman login
    When pengguna mengisi email dan password yang benar
    And menekan tombol login
    Then pengguna diarahkan ke halaman dashboard
    When pengguna klik tombol konsumsi
    Then pengguna diarahkan ke halaman monitoring konsumsi

  Scenario: Berhasil menambah kontrak siswa dengan data valid
    And pengguna mengklik tombol Pendapatan
    And pengguna memilih link Praxis Academy
    And pengguna mengklik tombol Tambah Kontrak Praxis
    And pengguna mengisi dan memilih NISN
    And pengguna mengisi semua field tagihan
    And pengguna mengisi catatan
    And pengguna mengunggah file kontrak PDF yang valid berukuran < 5 MB
    Then sistem menampilkan notifikasi bahwa kontrak siswa berhasil ditambahkan
