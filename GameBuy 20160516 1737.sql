-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.12-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema gamebuy
--

CREATE DATABASE IF NOT EXISTS gamebuy;
USE gamebuy;

--
-- Definition of table `customers`
--

DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `id` char(10) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(20) NOT NULL,
  `address` varchar(120) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `birthday` date NOT NULL,
  `status` int(10) unsigned NOT NULL DEFAULT '0',
  `bonus` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`email`),
  UNIQUE KEY `Index_unique_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customers`
--

/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` (`id`,`email`,`password`,`address`,`phone`,`name`,`birthday`,`status`,`bonus`) VALUES 
 ('A126161787','dawei.wang@gamil.com','b22126','台北市復興北路99號16F','0987666066','王大偉','1966-01-06',0,0),
 ('A268785841','meily.wang@gmail.com','a987654321','新北市新店區北新路三段123號','0912456879','王美麗','1996-05-21',0,0);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;


--
-- Definition of table `product`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `unitprice` double NOT NULL,
  `stock` int(10) unsigned NOT NULL,
  `url` varchar(50) NOT NULL,
  `description` varchar(500) NOT NULL,
  `bonus` int(10) unsigned NOT NULL,
  `status` int(10) unsigned NOT NULL,
  `preferentialprice` double NOT NULL,
  `issuedate` date NOT NULL,
  `platform` varchar(10) NOT NULL,
  `gametype` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_u` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product`
--

/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `products` ( id ,`name`,`unitprice`,`stock`,`url`,`description`,`bonus`,`status`,`preferentialprice`,`issuedate`,`platform`,`gametype`) VALUES 
 (1,'巫師 3：狂獵 - 血與酒',1290,10,'圖片位置','．「《巫師 3：狂獵 - 血與酒》中文版」一套。 　※ 收錄內容物如下： 　-《巫師 3：狂獵 - 血與酒》中文版 遊戲下載碼 　- 昆特牌指南 　- 昆特牌「Nilfgaard」牌卡 　- 昆特牌「Northern Realms」牌卡 　- 昆特牌代幣 　- 收藏盒',20,0,1290,'2016-05-31','PC','TRADITIONAL_CHINESE_SOFTWARE'),
 (2,'《毀滅戰士》中文一般版',1790,50,'wwww','系統需求：  作業系統：Windows 7 / 8 / 10 之 64 位元作業系統 CPU：Intel Core i7-3770 / AMD FX-8350 同等級或以上 CPU 記憶體：8 GB 硬碟空間：55 GB 顯示卡：NVIDIA GTX 970 或 AMD Radeon R9 290 4GB 系列以上晶片組， 　　　　且具 4 GB 記憶體之顯示卡 光碟機：DVD-ROM 雙層光碟機 ',50,0,1790,'2016-05-13','PSFOUR','TRADITIONAL_CHINESE_SOFTWARE');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
