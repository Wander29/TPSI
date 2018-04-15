-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Apr 10, 2018 alle 19:57
-- Versione del server: 5.7.17
-- Versione PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `5aia_venturi_annunci`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `annuncio`
--

CREATE TABLE `annuncio` (
  `CodAnnuncio` int(3) UNSIGNED NOT NULL,
  `Titolo` varchar(200) NOT NULL,
  `Data` date NOT NULL,
  `Testo` text NOT NULL,
  `FkRelatore` int(3) UNSIGNED NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `annuncio`
--

INSERT INTO `annuncio` (`CodAnnuncio`, `Titolo`, `Data`, `Testo`, `FkRelatore`) VALUES
(6, 'Che amarezza', '2018-02-21', 'Degrado a Terni, i gattini alla rivolta', 8);

-- --------------------------------------------------------

--
-- Struttura della tabella `relatore`
--

CREATE TABLE `relatore` (
  `CodRelatore` int(3) UNSIGNED NOT NULL,
  `Nome` varchar(30) NOT NULL,
  `Cognome` varchar(30) NOT NULL,
  `Ruolo` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `relatore`
--

INSERT INTO `relatore` (`CodRelatore`, `Nome`, `Cognome`, `Ruolo`) VALUES
(8, 'Luca', 'Moroni', 'Scrittore'),
(7, 'Marco', 'Riccardo', 'Giornalista');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `annuncio`
--
ALTER TABLE `annuncio`
  ADD PRIMARY KEY (`CodAnnuncio`),
  ADD KEY `FkRelatore` (`FkRelatore`);

--
-- Indici per le tabelle `relatore`
--
ALTER TABLE `relatore`
  ADD PRIMARY KEY (`CodRelatore`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `annuncio`
--
ALTER TABLE `annuncio`
  MODIFY `CodAnnuncio` int(3) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT per la tabella `relatore`
--
ALTER TABLE `relatore`
  MODIFY `CodRelatore` int(3) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
