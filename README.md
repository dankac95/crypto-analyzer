# Opis projektu CryptoAnalyzer

Projekt bedzie docelowo zbieral co jakis czas (np. co minute/30 sek) informacje o kursach kryptowalut.

Uzytkownik ma miec mozliwosc prowadzenia swojego tzw. portfela. Bedzie mogl wpisywac/aktuyalizowac ile ma danej walutuy aktualnie wykupiionej
(zarowno krypto jak i gotowka w wybranej w konfiguracji walucie).

1. Uzytkownik moze sprawdzic biezaca wartosc (wg. aktualnych kursow) swoich aktywow.
2. Sprawdzic w czasie jak wygladal kurs danej kryptowal.
3. ....
4. [MQ] Mozliwosc wysylki notyfikacji/alertow na zadane warunki (email/SMS)
5. Mozliwosc zaplanowania automatycznej wymiany zadanej ilosci wybranej waluty w wybranym czasie lub po przekroczeniu danego kursu

## Parametry konfiguracyjne (application.properties)
* waluta konwersji (PLN/USD)
* klucz API do CoinPlayer
* interwal pobierania kursow walut 