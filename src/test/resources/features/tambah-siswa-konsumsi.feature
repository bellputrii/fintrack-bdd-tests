Feature: tambah siswa konsumsi

  Scenario: tambah siswa konsumsi dengan valid query
    Given pengguna berada di halaman login
    When pengguna mengisi email dan password yang benar
    And menekan tombol login
    Then pengguna diarahkan ke halaman dashboard
    When User klik tombol konsumsi
    Then User diarahkan ke halaman monitoring konsumsi
    When User klik tombol tambah siswa
    Then User diarahkan ke halaman tambah siswa konsumsi
#    And User menginputkan data konsumsi siswa
#    And User klik tombol simpan
#    Then User diarahkan ke halaman monitoring konsumsi dan data konsumsi siswa ditampilkan
