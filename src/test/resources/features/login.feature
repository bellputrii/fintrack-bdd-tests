Feature: Login ke Fintrack

  Scenario: Login berhasil
    Given pengguna berada di halaman login
    When pengguna mengisi email dan password yang benar
    And menekan tombol login
    Then pengguna diarahkan ke halaman dashboard

  Scenario: Login gagal karena password salah
    Given pengguna berada di halaman login
    When pengguna mengisi email yang valid
    And pengguna mengisi password yang salah
    And menekan tombol login
    Then pengguna melihat pesan kesalahan "Password salah"

  Scenario: Login gagal karena email tidak terdaftar
    Given pengguna berada di halaman login
    When pengguna mengisi email yang tidak terdaftar
    And pengguna mengisi password apa saja
    And menekan tombol login
    Then pengguna melihat pesan kesalahan "Email tidak ditemukan"
