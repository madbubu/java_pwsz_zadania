-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 17 Paź 2016, 21:02
-- Wersja serwera: 10.1.16-MariaDB
-- Wersja PHP: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `wydatki`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kategorie`
--

CREATE TABLE `kategorie` (
  `nr_kategorii` int(11) NOT NULL,
  `kategoria` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `kategorie`
--

INSERT INTO `kategorie` (`nr_kategorii`, `kategoria`) VALUES
(1, 'Wynagrodzenie'),
(2, 'Kredyty'),
(3, 'Spozywcze'),
(4, 'Restauracje'),
(5, ''),
(6, 'Nowa kategoria');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `listawydatki`
--

CREATE TABLE `listawydatki` (
  `id_wydatek` int(11) NOT NULL,
  `data` date NOT NULL,
  `kwota` double NOT NULL,
  `opis` text NOT NULL,
  `nr_kategorii` int(11) NOT NULL,
  `nr_budzetu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `listawydatki`
--

INSERT INTO `listawydatki` (`id_wydatek`, `data`, `kwota`, `opis`, `nr_kategorii`, `nr_budzetu`) VALUES
(1, '2016-09-04', 3500, 'to jest pierwszy wydatek', 1, 1),
(2, '2016-10-17', 857, 'kredyt hipoteczny', 2, 1),
(4, '2016-10-12', 456, 'inny opis wydatku', 2, 1),
(5, '2016-10-12', 564, 'tutaj mamy opis kolejny', 1, 1),
(6, '2016-04-11', 2200, 'Wynagrodzenie Oli za marzec', 1, 1),
(9, '2016-10-15', -5000, 'do tesut', 2, 1),
(11, '2016-08-10', 7500, 'kolejna wyplata', 1, 1),
(12, '2016-10-15', 0, '', 0, 0),
(13, '2016-10-15', 546, 'PO ZMIANIE testowow', 1, 1),
(14, '2016-10-16', 0, '', 0, 0),
(15, '2016-10-16', 0, '', 0, 0),
(16, '2015-07-16', -250, 'Zakupy TESCO', 3, 1),
(17, '2016-09-13', -1500, 'rata hipoteczny', 2, 1);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `kategorie`
--
ALTER TABLE `kategorie`
  ADD PRIMARY KEY (`nr_kategorii`);

--
-- Indexes for table `listawydatki`
--
ALTER TABLE `listawydatki`
  ADD PRIMARY KEY (`id_wydatek`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `kategorie`
--
ALTER TABLE `kategorie`
  MODIFY `nr_kategorii` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT dla tabeli `listawydatki`
--
ALTER TABLE `listawydatki`
  MODIFY `id_wydatek` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
