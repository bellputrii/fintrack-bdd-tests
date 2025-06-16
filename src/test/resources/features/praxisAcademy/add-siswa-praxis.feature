Feature: Tambah Kontrak Siswa Praxis Academy

  Background:
    Given pengguna berada di halaman login
    When pengguna mengisi email dan password yang benar
    And menekan tombol login
    Then pengguna diarahkan ke halaman dashboard
    When pengguna klik tombol praxis academy di dashboard
    Then pengguna diarahkan ke halaman monitoring praxis academy

  Scenario: Tambah kontrak siswa praxis academy dengan data valid
    When pengguna klik tombol tambah kontrak siswa praxis
    Then pengguna diarahkan ke halaman tambah kontrak siswa praxis academy
    When pengguna menginputkan data kontrak siswa praxis academy yang valid
    And pengguna klik tombol simpan kontrak praxis
    Then pengguna diarahkan kembali ke halaman monitoring praxis academy
    And data kontrak siswa praxis academy ditampilkan di tabel

  Scenario: Tambah kontrak siswa praxis academy dengan data tidak valid
    When pengguna klik tombol tambah kontrak siswa praxis
    Then pengguna diarahkan ke halaman tambah kontrak siswa praxis academy
    When pengguna menginputkan data kontrak siswa praxis academy tanpa file
    And pengguna klik tombol simpan kontrak praxis
    Then pengguna tetap berada di halaman tambah kontrak siswa praxis academy
    And pengguna melihat pesan error validasi file wajib diisi