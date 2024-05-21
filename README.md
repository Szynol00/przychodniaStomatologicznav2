# SmileSolutions

## Opis projektu
Aplikacja SmileSolutions jest przeznaczona do wspomagania obsługi pacjentów w małej przychodni stomatologicznej. Umożliwia przechowywanie, dodawanie, edytowanie, sortowanie oraz usuwanie danych pacjentów, lekarzy, usług i wizyt w jednym miejscu. Aplikacja jest napisana w JavaFX i korzysta z bazy danych SQLite za pomocą JDBC. Interfejs użytkownika składa się z kilku okienek umożliwiających zarządzanie danymi.

## Spis Treści
1. [Narzędzia i technologie](#narzędzia-i-technologie)
2. [Struktura bazy danych](#struktura-bazy-danych)
3. [Instalacja i konfiguracja](#instalacja-i-konfiguracja)
4. [Interfejs użytkownika](#interfejs-użytkownika)
5. [Funkcjonalności aplikacji](#funkcjonalności-aplikacji)
6. [Bezpieczeństwo](#bezpieczeństwo)
7. [Ograniczenia aplikacji](#ograniczenia-aplikacji)
8. [Licencja](#licencja)

## Narzędzia i technologie
- **Język programowania**: Java
- **Framework**: JavaFX
- **Baza danych**: SQLite
- **IDE**: IntelliJ IDEA

## Struktura bazy danych
![diagramERD](https://github.com/Szynol00/przychodniaStomatologicznav2/assets/104465225/7b2eaff8-e50b-4357-b791-f12feff7cbb0)

Baza danych SQLite składa się z czterech tabel:
1. **uslugi**: ID, nazwa, cena
2. **lekarze**: ID, imię, nazwisko, numer telefonu
3. **pacjenci**: ID, imię, nazwisko, PESEL, adres, kod pocztowy, miasto, numer telefonu
4. **wizyty**: ID wizyty, ID lekarza, ID pacjenta, ID usługi, data wizyty

## Instalacja i konfiguracja
Aby uruchomić aplikację SmileSolutions, wykonaj następujące kroki:
1. Sklonuj repozytorium lub pobierz kod źródłowy.
2. Otwórz projekt w środowisku programistycznym wspierającym JavaFX (zalecane IntelliJ IDEA).
3. Zainstaluj JavaFX SDK i skonfiguruj je w swoim IDE, aby umożliwić kompilację i uruchomienie aplikacji.
4. Zainstaluj i skonfiguruj SQLite JDBC, aby umożliwić połączenie z bazą danych.
5. Zmień ścieżkę dostępu do bazy danych w klasie `DBConnect` na ścieżkę odpowiadającą lokalizacji pliku bazy danych na twoim komputerze.
6. Skompiluj i uruchom aplikację w swoim środowisku programistycznym.

Upewnij się, że wszystkie zależności są poprawnie skonfigurowane przed uruchomieniem aplikacji, aby uniknąć problemów z uruchamianiem.


## Interfejs użytkownika
Główny widok aplikacji wyświetla najważniejsze informacje z wszystkich tabel. 
![image](https://github.com/Szynol00/przychodniaStomatologicznav2/assets/104465225/cf0acfc3-5565-4441-85fb-9855de3bc9f1)

Widok wizyty zawierajace pola tekstowe id pacjenta, id lekarza, id usługi oraz datę wizyty.
![image](https://github.com/Szynol00/przychodniaStomatologicznav2/assets/104465225/24098c4e-6029-4dab-abee-6ffab93ddfd4)

Widok pacjenci zawiera podstawowe dane o pacjentach. Również jest możliwość dodawania nowych osób, edytowanie obecnych oraz ich usuwanie. Za pomocą pola tekstowego można wyszukać pacjenta wpisując np. imię, albo nazwisko itp.  
![image](https://github.com/Szynol00/przychodniaStomatologicznav2/assets/104465225/7bc1dc8e-bf32-481a-8dc7-b931ece31090)

Widok lekarze zawiera podstawowe informacje o lekarzach takie jak unikatowe Id lekarza, imię nazwisko, numer telefonu. Również tutaj można dodawać, edytować i usuwać poszczególnych lekarzy jak i wyszukiwać za pomocą pola tekstowego „wyszukaj”.
![image](https://github.com/Szynol00/przychodniaStomatologicznav2/assets/104465225/367721f6-51c3-4e78-9fd3-b3bb199ef3e5)

Widok cennik zawiera informacje o usługach oferowanych przez przychodnię stomatologiczną, w tym ich unikalne ID, nazwę i cenę. Również tutaj można dodawać, edytować i usuwać podane usługi.
![image](https://github.com/Szynol00/przychodniaStomatologicznav2/assets/104465225/8856c7e5-b81a-4f9b-b861-c610b6b3eac2)


## Funkcjonalności aplikacji

### Dodawanie danych
We wszystkich widokach oprócz home możliwe jest dodanie nowych danych do bazy. Wystarczy wpisać dane w odpowiednie pola tekstowe. Poniżej przykład dla tabeli pacjentów:
![Dodawanie pacjenta - Krok 1](https://github.com/Szynol00/przychodniaStomatologicznav2/assets/104465225/9d6a27f3-5166-49f2-a08e-423c7d77b806)
![Dodawanie pacjenta - Krok 2](https://github.com/Szynol00/przychodniaStomatologicznav2/assets/104465225/961adf09-3b08-4f63-8d1c-8e1b6ce14d6a)
![Dodawanie pacjenta - Krok 3](https://github.com/Szynol00/przychodniaStomatologicznav2/assets/104465225/f3c5b589-d2d2-4161-b7fa-3f7b8f991095)

### Edycja danych
Aby edytować dane, kliknij lewym przyciskiem myszy na wiersz, który chcesz edytować. Wiersz zostanie podświetlony, a dane zostaną automatycznie załadowane do pola tekstowego nad tabelą:
![Edycja pacjenta - Wybór wiersza](https://github.com/Szynol00/przychodniaStomatologicznav2/assets/104465225/3a5faa7b-84a4-464b-83ad-eb88075e0bbd)

Zmień dane w odpowiednich polach tekstowych i kliknij przycisk „Edytuj pacjenta”:
![Edycja pacjenta - Edycja danych](https://github.com/Szynol00/przychodniaStomatologicznav2/assets/104465225/41970fa0-fc7c-4f20-b6b3-8157a0468848)

Po potwierdzeniu zmian, aplikacja wyświetli komunikat o pomyślnym zaktualizowaniu danych. Kliknij OK, aby dane zaktualizowały się w tabeli:
![Edycja pacjenta - Potwierdzenie](https://github.com/Szynol00/przychodniaStomatologicznav2/assets/104465225/195d1041-2028-4ab2-ac11-7499d6020173)

### Usuwanie danych: 
Aby usunąć rekod, kliknij lewym przyciskiem na dany wiesz w tabeli lub wpisz dane ręcznie do pól tekstowych. Następnie nacisnij czerwony przycik Usuń pacjenta. 
![image](https://github.com/Szynol00/przychodniaStomatologicznav2/assets/104465225/e9b2f2cf-d5c0-4c66-b98b-2ef2d07eebb8)

Po naciśnięciu przycisku ok tabela się zaktualizuje a pacjenta, którego usunęliśmy już nie będzie. 
![image](https://github.com/Szynol00/przychodniaStomatologicznav2/assets/104465225/80eb7905-ee1e-4190-80ac-6604e9038524)

### Wyszukiwanie danych:
Do wyszukania potrzebnych informacji służy pole tekstowe „wyszukaj” do którego jest możliwość wpisania informacji jakich szukamy. 
Uwaga: pole tekstowe może jednocześnie przeszukiwać jedną kolumnę np. po imieniu albo PESEL-u jednak próba wyszukania jednocześnie danej informacji typu: „imię i nazwisko” nie przyniesie pożądanego skutku. 
![image](https://github.com/Szynol00/przychodniaStomatologicznav2/assets/104465225/e26585a1-37bb-4a89-a126-19a87192e808) 
W powyższym zrzucie ekranu aplikacja wyszukała wszystkich pacjentów w tabeli mieszkających w Łodzi. 

### Sortowanie danych:
Aby posortować dane należy kliknąć lewym przyciskiem myszy na nazwę kolumny, która nas interesuje. Jest to wbudowana funkcja w tableView w której wyświetlanie są rekordy z tabeli z bazy danych. 
  
## Bezpieczeństwo
Aplikacja posiada funkcje zapewniające spójność i bezpieczeństwo danych, w tym unikalność identyfikatorów i walidację danych wejściowych.
![image](https://github.com/Szynol00/przychodniaStomatologicznav2/assets/104465225/bc6cdca7-efac-4d2d-9ec7-fd0c9f64fb44)
![image](https://github.com/Szynol00/przychodniaStomatologicznav2/assets/104465225/24fb0530-8098-4dee-8088-64a9d8ece338)
![image](https://github.com/Szynol00/przychodniaStomatologicznav2/assets/104465225/d9d3d954-6f25-409a-9a31-18bd0e861cce)


## Ograniczenia aplikacji
- Interfejs użytkownika nie jest przystosowany do bardzo małych ekranów.
- Wprowadzanie danych wymaga manualnej walidacji przez użytkownika.

## Licencja
Aplikacja jest dostępna na licencji MIT.
