-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 16 oct. 2024 à 17:14
-- Version du serveur : 8.0.27
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `chatop`
--

-- --------------------------------------------------------

--
-- Structure de la table `messages`
--

DROP TABLE IF EXISTS `messages`;
CREATE TABLE IF NOT EXISTS `messages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `message` varchar(2000) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `rental_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3ce1i9w1rtics9wjwj8y5y3md` (`rental_id`),
  KEY `FK51kb7wyhdf11e4bakagg5yhub` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `messages`
--

INSERT INTO `messages` (`id`, `created_at`, `message`, `updated_at`, `rental_id`, `user_id`) VALUES
(1, '2024-10-11 15:11:07.559209', 'test', NULL, 3, 1),
(2, '2024-10-11 15:11:31.108362', 'test', NULL, 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `rentals`
--

DROP TABLE IF EXISTS `rentals`;
CREATE TABLE IF NOT EXISTS `rentals` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `picture` varchar(1000) NOT NULL,
  `price` double DEFAULT NULL,
  `surface` double DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `owner_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4wtw62gr98prm32vx6ys8pgqq` (`owner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `rentals`
--

INSERT INTO `rentals` (`id`, `created_at`, `description`, `name`, `picture`, `price`, `surface`, `updated_at`, `owner_id`) VALUES
(3, '2024-10-11 13:34:52.091497', 'lorem', 'test', '2b229b3a-6bf8-44ad-ae11-6da113c52335_4LRy0VW1qrdBf2n7I3oj--1--y6lqq.webp', 1, 1, NULL, 1);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `name` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `created_at`, `name`, `updated_at`) VALUES
(1, '2024-10-11 10:17:33.860225', 'USER', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `_user`
--

DROP TABLE IF EXISTS `_user`;
CREATE TABLE IF NOT EXISTS `_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `_user`
--

INSERT INTO `_user` (`id`, `created_at`, `email`, `name`, `password`, `updated_at`) VALUES
(1, '2024-10-11 10:17:41.245328', 'antoine.chemin.pro@outlook.fr', 'antoine', '$2a$10$V/qrxZPWMxjXbxFJ3MzabeHHm0IfaRzDK4FKUMe1QmZMRqbVQS2gm', NULL),
(2, '2024-10-11 10:29:43.837404', 'antoine.chemin@free.fr', 'antoine', '$2a$10$oluC3JCROb9s.YDqMFOzrumKyHmkc3H5m4pHJ/eLUlsGtADHl1sfy', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `_user_messages`
--

DROP TABLE IF EXISTS `_user_messages`;
CREATE TABLE IF NOT EXISTS `_user_messages` (
  `user_id` int NOT NULL,
  `messages_id` int NOT NULL,
  UNIQUE KEY `UK_8boschm205rvfp8ob6e4mqk0c` (`messages_id`),
  KEY `FK5p9u95cayjab21xy8fxkl4aa3` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `_user_rentals`
--

DROP TABLE IF EXISTS `_user_rentals`;
CREATE TABLE IF NOT EXISTS `_user_rentals` (
  `user_id` int NOT NULL,
  `rentals_id` int NOT NULL,
  UNIQUE KEY `UK_i3otvmndoir75nw9ir07oxg9l` (`rentals_id`),
  KEY `FKe2gx9qba17i6g3e4xv21lbqp9` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `_user_roles`
--

DROP TABLE IF EXISTS `_user_roles`;
CREATE TABLE IF NOT EXISTS `_user_roles` (
  `users_id` int NOT NULL,
  `roles_id` int NOT NULL,
  KEY `FKtq7v0vo9kka3qeaw2alou2j8p` (`roles_id`),
  KEY `FKkna43mk14wb08rt62w1982ki6` (`users_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `_user_roles`
--

INSERT INTO `_user_roles` (`users_id`, `roles_id`) VALUES
(1, 1),
(2, 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `FK3ce1i9w1rtics9wjwj8y5y3md` FOREIGN KEY (`rental_id`) REFERENCES `rentals` (`id`),
  ADD CONSTRAINT `FK51kb7wyhdf11e4bakagg5yhub` FOREIGN KEY (`user_id`) REFERENCES `_user` (`id`);

--
-- Contraintes pour la table `rentals`
--
ALTER TABLE `rentals`
  ADD CONSTRAINT `FK4wtw62gr98prm32vx6ys8pgqq` FOREIGN KEY (`owner_id`) REFERENCES `_user` (`id`);

--
-- Contraintes pour la table `_user_messages`
--
ALTER TABLE `_user_messages`
  ADD CONSTRAINT `FK5p9u95cayjab21xy8fxkl4aa3` FOREIGN KEY (`user_id`) REFERENCES `_user` (`id`),
  ADD CONSTRAINT `FKc9e02oakqppey2lqffyc8yu38` FOREIGN KEY (`messages_id`) REFERENCES `messages` (`id`);

--
-- Contraintes pour la table `_user_rentals`
--
ALTER TABLE `_user_rentals`
  ADD CONSTRAINT `FKe2gx9qba17i6g3e4xv21lbqp9` FOREIGN KEY (`user_id`) REFERENCES `_user` (`id`),
  ADD CONSTRAINT `FKlwtcepw8gomw6y2nssgu60e3n` FOREIGN KEY (`rentals_id`) REFERENCES `rentals` (`id`);

--
-- Contraintes pour la table `_user_roles`
--
ALTER TABLE `_user_roles`
  ADD CONSTRAINT `FKkna43mk14wb08rt62w1982ki6` FOREIGN KEY (`users_id`) REFERENCES `_user` (`id`),
  ADD CONSTRAINT `FKtq7v0vo9kka3qeaw2alou2j8p` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
