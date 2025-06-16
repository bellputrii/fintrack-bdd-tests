Feature: Logout dari Fintrack

  Scenario: Logout berhasil
    Given pengguna berada di halaman login
    When pengguna mengisi email dan password yang benar
    And menekan tombol login
    Then pengguna diarahkan ke halaman dashboard
    When pengguna klik tombol logout
    Then pengguna berada di halaman login setelah logout