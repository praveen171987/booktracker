-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.67-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema booktracker
--

CREATE DATABASE IF NOT EXISTS booktracker;
USE booktracker;

--
-- Definition of table `alt_vers`
--

DROP TABLE IF EXISTS `alt_vers`;
CREATE TABLE `alt_vers` (
  `isbn` varchar(10) default NULL,
  `alt_ver` varchar(10) default NULL,
  KEY `isbn` (`isbn`),
  CONSTRAINT `alt_vers_ibfk_1` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `alt_vers`
--

/*!40000 ALTER TABLE `alt_vers` DISABLE KEYS */;
INSERT INTO `alt_vers` (`isbn`,`alt_ver`) VALUES 
 ('0061433012','0061433020'),
 ('0061433012','0061658219'),
 ('0061433012','0061709131'),
 ('0061433012','0385613717'),
 ('0061433012','1846576946'),
 ('0061433012','0385613709'),
 ('0061474096','0061694940'),
 ('0061474096','B0015DPXKI'),
 ('0061474096','1427205906'),
 ('0316143472','0316024597'),
 ('0316143472','1600241824'),
 ('0316143472','B001AWVRYK'),
 ('0316143472','B0013TPV0Q'),
 ('0316143472','1600242316'),
 ('0345496884','0007256752'),
 ('0345496884','0007259158'),
 ('0345496884','B0010SKULK'),
 ('0345496884','0739368648'),
 ('0345501748','0345502078'),
 ('0345501748','B000Y4RS5M'),
 ('0345501748','0739357891'),
 ('0345501748','038566544X'),
 ('0345501748','1410405745'),
 ('0380798379','0380976536'),
 ('0380798379','B000W94H1M'),
 ('0380798379','B000O17D1E'),
 ('0380798379','1596060492'),
 ('0425222330','1905204841'),
 ('0425222330','1847240801'),
 ('0441013651','0441016685'),
 ('0441013651','B000OIZUIA'),
 ('0441013651','1930846258'),
 ('0441013651','B000HT2P1G'),
 ('0441013651','1841495697'),
 ('0441016197','B00125L88K'),
 ('0441016383','0143143751'),
 ('0451462289','1400106826'),
 ('0451462289','B001650UNA'),
 ('0451462289','1400156823'),
 ('0451462289','1400136822'),
 ('0553345923','0553051946'),
 ('0553345923','0553270206'),
 ('0553345923','0553374303'),
 ('0553345923','0575043253'),
 ('0553345923','B000P2XJOW'),
 ('0553345923','1433201127'),
 ('0553345923','B000F83TBO'),
 ('0553345923','0575041080'),
 ('0553345923','1433201119'),
 ('0553345923','2266081411'),
 ('0553345923','274360039X'),
 ('0553345923','1433201100'),
 ('0765304961','0765322102'),
 ('0765304961','1427205124'),
 ('0765315459','B001FY4RO0'),
 ('0765318679','0230014488'),
 ('0765318679','023001450X'),
 ('0765318679','0765358395'),
 ('0765318679','0765321009'),
 ('0765319209','B0015DTUZC'),
 ('0765320428','0007232179'),
 ('0765320428','0007232187'),
 ('0765320428','0765360225'),
 ('0765320428','0007232195'),
 ('0765358549','1857020766'),
 ('0765358549','1933368365'),
 ('0765358549','1857022173'),
 ('1555839878','1555838995'),
 ('1590200152','0553096427'),
 ('1590200152','0553374680'),
 ('1590200152','B001F7ATX0'),
 ('1590200152','B000FA5NRK'),
 ('159102594X','0575077859'),
 ('159102594X','0575079797'),
 ('159102594X','0575077867'),
 ('1591026415','0575077875'),
 ('1591026415','0575082011'),
 ('1591026415','0575077883'),
 ('1591026903','0575077891'),
 ('1591026903','0575084162'),
 ('1591026903','0575077905'),
 ('1595540857','1435281357'),
 ('1595540857','1598591622'),
 ('1595540857','1904233708'),
 ('1595540857','1904233716'),
 ('1595540857','1904233937'),
 ('1595540857','B000N6U42K'),
 ('1595540857','1602524076'),
 ('1595540857','1595540881'),
 ('1595540857','1595543295'),
 ('1595540857','B001C34T7O'),
 ('1595540857','B00164EAY6'),
 ('159554089X','1595540865'),
 ('159554089X','1598592998'),
 ('159554089X','1904233724'),
 ('159554089X','B001F0EYPG'),
 ('159554089X','190565412X'),
 ('159554089X','B001ECQ282'),
 ('159554089X','B001ECQGM4'),
 ('159554089X','1904233732'),
 ('1930846452','0441016715'),
 ('1595540873','1598594885');
/*!40000 ALTER TABLE `alt_vers` ENABLE KEYS */;


--
-- Definition of table `authors`
--

DROP TABLE IF EXISTS `authors`;
CREATE TABLE `authors` (
  `isbn` varchar(10) default NULL,
  `author` varchar(255) default NULL,
  KEY `isbn` (`isbn`),
  CONSTRAINT `authors_ibfk_1` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authors`
--

/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` (`isbn`,`author`) VALUES 
 ('1416555870','John Ringo'),
 ('1416555870','Travis S Taylor'),
 ('0061433012','Terry Pratchett'),
 ('0061474096','Neal Stephenson'),
 ('0316143472','David Sedaris'),
 ('0345496884','Naomi Novik'),
 ('0345501748','Michael Chabon'),
 ('0345507460','Jim Butcher'),
 ('0345507460','Ardian Syaf'),
 ('0380798379','Tim Powers'),
 ('0425222330','Michael Walters'),
 ('0441013651','Charles Stross'),
 ('0441016197','Jack Campbell'),
 ('0441016383','Jim Butcher'),
 ('0451462289','S.M. Stirling'),
 ('0451462564','Jim Butcher'),
 ('0553345923','John Crowley'),
 ('0765304961','Orson Scott Card'),
 ('0765315459','Karl Schroeder'),
 ('0765318679','David Bilsborough'),
 ('0765319209','Tobias S. Buckell'),
 ('0765320428','Stephen Hunt'),
 ('0765358549','Martin Millar'),
 ('0809572354','Michael Cisco'),
 ('1416555919','John Ringo'),
 ('1416555919','Julie Cochrane'),
 ('1555839878','Douglas Clegg'),
 ('1590200152','John Crowley'),
 ('159102594X','Joe Abercrombie'),
 ('1591026415','Joe Abercrombie'),
 ('1591026903','Joe Abercrombie'),
 ('1595540857','Stephen R. Lawhead'),
 ('159554089X','Stephen R. Lawhead'),
 ('1930846452','Charles Stross'),
 ('1595540873','Stephen R. Lawhead');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;


--
-- Definition of table `book`
--

DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `isbn` varchar(10) NOT NULL,
  `title` text,
  `amaz_rating` decimal(4,3) unsigned default NULL,
  `pub_date` varchar(10) default NULL,
  `pages` int(10) unsigned default NULL,
  `small_url` text,
  `medium_url` text,
  `large_url` text,
  PRIMARY KEY  (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book`
--

/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`isbn`,`title`,`amaz_rating`,`pub_date`,`pages`,`small_url`,`medium_url`,`large_url`) VALUES 
 ('0061433012','Nation','4.500','2008-10-01',384,'http://ecx.images-amazon.com/images/I/51saresqz4L._SL75_.jpg','http://ecx.images-amazon.com/images/I/51saresqz4L._SL160_.jpg','http://ecx.images-amazon.com/images/I/51saresqz4L._SL500_.jpg'),
 ('0061474096','Anathem','4.500','2008-09-01',960,'http://ecx.images-amazon.com/images/I/51PpF7ZgT5L._SL75_.jpg','http://ecx.images-amazon.com/images/I/51PpF7ZgT5L._SL160_.jpg','http://ecx.images-amazon.com/images/I/51PpF7ZgT5L._SL500_.jpg'),
 ('0316143472','When You Are Engulfed in Flames','4.000','2008-06-03',336,'http://ecx.images-amazon.com/images/I/51rI%2BNF4VwL._SL75_.jpg','http://ecx.images-amazon.com/images/I/51rI%2BNF4VwL._SL160_.jpg','http://ecx.images-amazon.com/images/I/51rI%2BNF4VwL._SL500_.jpg'),
 ('0345496884','Victory of Eagles (Temeraire, Book 5)','4.000','2008-07-08',342,'http://ecx.images-amazon.com/images/I/51YcHRfZmiL._SL75_.jpg','http://ecx.images-amazon.com/images/I/51YcHRfZmiL._SL160_.jpg','http://ecx.images-amazon.com/images/I/51YcHRfZmiL._SL500_.jpg'),
 ('0345501748','Gentlemen of the Road: A Tale of Adventure','4.000','2007-10-30',224,'http://ecx.images-amazon.com/images/I/51koJqkIVzL._SL75_.jpg','http://ecx.images-amazon.com/images/I/51koJqkIVzL._SL160_.jpg','http://ecx.images-amazon.com/images/I/51koJqkIVzL._SL500_.jpg'),
 ('0345507460','The Dresden Files: Welcome to the Jungle',NULL,'2008-10-14',160,'http://ecx.images-amazon.com/images/I/51BF5JHd4HL._SL75_.jpg','http://ecx.images-amazon.com/images/I/51BF5JHd4HL._SL160_.jpg','http://ecx.images-amazon.com/images/I/51BF5JHd4HL._SL500_.jpg'),
 ('0380798379','Three Days to Never','3.500','2007-12-01',416,'http://ecx.images-amazon.com/images/I/51s63Gv8gCL._SL75_.jpg','http://ecx.images-amazon.com/images/I/51s63Gv8gCL._SL160_.jpg','http://ecx.images-amazon.com/images/I/51s63Gv8gCL._SL500_.jpg'),
 ('0425222330','The Shadow Walker','4.500','2008-08-05',352,'http://ecx.images-amazon.com/images/I/41y-1Kk5SDL._SL75_.jpg','http://ecx.images-amazon.com/images/I/41y-1Kk5SDL._SL160_.jpg','http://ecx.images-amazon.com/images/I/41y-1Kk5SDL._SL500_.jpg'),
 ('0441013651','The Atrocity Archives','4.500','2006-01-03',368,'http://ecx.images-amazon.com/images/I/51JMJ2RAXML._SL75_.jpg','http://ecx.images-amazon.com/images/I/51JMJ2RAXML._SL160_.jpg','http://ecx.images-amazon.com/images/I/51JMJ2RAXML._SL500_.jpg'),
 ('0441016197','Valiant (The Lost Fleet, Book 4 of 6)','4.000','2008-06-24',304,'http://ecx.images-amazon.com/images/I/51U1p2Amu5L._SL75_.jpg','http://ecx.images-amazon.com/images/I/51U1p2Amu5L._SL160_.jpg','http://ecx.images-amazon.com/images/I/51U1p2Amu5L._SL500_.jpg'),
 ('0441016383','Princeps\' Fury (Codex Alera, Book 5)',NULL,'2008-11-25',384,NULL,NULL,NULL),
 ('0451462289','The Scourge of God: A Novel of the Change','4.500','2008-09-02',464,'http://ecx.images-amazon.com/images/I/51aecAcqCvL._SL75_.jpg','http://ecx.images-amazon.com/images/I/51aecAcqCvL._SL160_.jpg','http://ecx.images-amazon.com/images/I/51aecAcqCvL._SL500_.jpg'),
 ('0451462564','Turn Coat (The Dresden Files, Book 11)',NULL,'2009-04-07',432,NULL,NULL,NULL),
 ('0553345923','Aegypt','4.500','1989-02-01',NULL,'http://ecx.images-amazon.com/images/I/514roxYi%2BVL._SL75_.jpg','http://ecx.images-amazon.com/images/I/514roxYi%2BVL._SL160_.jpg','http://ecx.images-amazon.com/images/I/514roxYi%2BVL._SL500_.jpg'),
 ('0765304961','Ender in Exile (Ender)',NULL,'2008-11-11',384,'http://ecx.images-amazon.com/images/I/5183vhsemJL._SL75_.jpg','http://ecx.images-amazon.com/images/I/5183vhsemJL._SL160_.jpg','http://ecx.images-amazon.com/images/I/5183vhsemJL._SL500_.jpg'),
 ('0765315459','Pirate Sun: Book Three of Virga','5.000','2008-08-05',320,'http://ecx.images-amazon.com/images/I/51%2BFmz9kVGL._SL75_.jpg','http://ecx.images-amazon.com/images/I/51%2BFmz9kVGL._SL160_.jpg','http://ecx.images-amazon.com/images/I/51%2BFmz9kVGL._SL500_.jpg'),
 ('0765318679','The Wanderer\'s Tale (Annals of Lindormyn)','3.000','2007-07-10',448,'http://ecx.images-amazon.com/images/I/4143INVMhOL._SL75_.jpg','http://ecx.images-amazon.com/images/I/4143INVMhOL._SL160_.jpg','http://ecx.images-amazon.com/images/I/4143INVMhOL._SL500_.jpg'),
 ('0765319209','Sly Mongoose','5.000','2008-08-19',320,'http://ecx.images-amazon.com/images/I/51GyCv0d9-L._SL75_.jpg','http://ecx.images-amazon.com/images/I/51GyCv0d9-L._SL160_.jpg','http://ecx.images-amazon.com/images/I/51GyCv0d9-L._SL500_.jpg'),
 ('0765320428','The Court of the Air','5.000','2008-06-10',592,'http://ecx.images-amazon.com/images/I/51VI-JI1fWL._SL75_.jpg','http://ecx.images-amazon.com/images/I/51VI-JI1fWL._SL160_.jpg','http://ecx.images-amazon.com/images/I/51VI-JI1fWL._SL500_.jpg'),
 ('0765358549','The Good Fairies of New York','4.000','2008-06-03',288,'http://ecx.images-amazon.com/images/I/510VDzht9xL._SL75_.jpg','http://ecx.images-amazon.com/images/I/510VDzht9xL._SL160_.jpg','http://ecx.images-amazon.com/images/I/510VDzht9xL._SL500_.jpg'),
 ('0809572354','The Traitor','3.500','2007-08-01',152,'http://ecx.images-amazon.com/images/I/51QuRdAgyzL._SL75_.jpg','http://ecx.images-amazon.com/images/I/51QuRdAgyzL._SL160_.jpg','http://ecx.images-amazon.com/images/I/51QuRdAgyzL._SL500_.jpg'),
 ('1416555870','Claws that Catch (Looking Glass, Book 4)',NULL,'2008-11-04',416,'http://ecx.images-amazon.com/images/I/51nV1%2BgHkdL._SL75_.jpg','http://ecx.images-amazon.com/images/I/51nV1%2BgHkdL._SL160_.jpg','http://ecx.images-amazon.com/images/I/51nV1%2BgHkdL._SL500_.jpg'),
 ('1416555919','Honor of the Clan (The Posleen War)',NULL,'2009-01-08',448,'http://ecx.images-amazon.com/images/I/51yhVBras0L._SL75_.jpg','http://ecx.images-amazon.com/images/I/51yhVBras0L._SL160_.jpg','http://ecx.images-amazon.com/images/I/51yhVBras0L._SL500_.jpg'),
 ('1555839878','Mordred, Bastard Son (Mordred Trilogy)','4.500','2007-02-01',260,'http://ecx.images-amazon.com/images/I/41apb-X%2BJWL._SL75_.jpg','http://ecx.images-amazon.com/images/I/41apb-X%2BJWL._SL160_.jpg','http://ecx.images-amazon.com/images/I/41apb-X%2BJWL._SL500_.jpg'),
 ('1590200152','Love  &  Sleep (Aegypt)','4.000','2008-01-29',512,'http://ecx.images-amazon.com/images/I/515235X1r7L._SL75_.jpg','http://ecx.images-amazon.com/images/I/515235X1r7L._SL160_.jpg','http://ecx.images-amazon.com/images/I/515235X1r7L._SL500_.jpg'),
 ('159102594X','The Blade Itself (The First Law: Book One)','4.500','2007-09-06',531,'http://ecx.images-amazon.com/images/I/51JQxyyzNNL._SL75_.jpg','http://ecx.images-amazon.com/images/I/51JQxyyzNNL._SL160_.jpg','http://ecx.images-amazon.com/images/I/51JQxyyzNNL._SL500_.jpg'),
 ('1591026415','Before They Are Hanged (The First Law: Book Two)','4.500','2008-03-25',543,'http://ecx.images-amazon.com/images/I/51FkKpfEZAL._SL75_.jpg','http://ecx.images-amazon.com/images/I/51FkKpfEZAL._SL160_.jpg','http://ecx.images-amazon.com/images/I/51FkKpfEZAL._SL500_.jpg'),
 ('1591026903','Last Argument of Kings (First Law: Book Three)','4.000','2008-09-23',639,'http://ecx.images-amazon.com/images/I/51PX2x67-ZL._SL75_.jpg','http://ecx.images-amazon.com/images/I/51PX2x67-ZL._SL160_.jpg','http://ecx.images-amazon.com/images/I/51PX2x67-ZL._SL500_.jpg'),
 ('1595540857','Hood (King Raven Trilogy, Book 1)','4.500','2006-09-05',496,'http://ecx.images-amazon.com/images/I/51yM8cOC4iL._SL75_.jpg','http://ecx.images-amazon.com/images/I/51yM8cOC4iL._SL160_.jpg','http://ecx.images-amazon.com/images/I/51yM8cOC4iL._SL500_.jpg'),
 ('1595540873','Tuck',NULL,'2009-02-10',496,NULL,NULL,NULL),
 ('159554089X','Scarlet (The King Raven, Book 2)','4.500','2008-06-10',464,'http://ecx.images-amazon.com/images/I/51yYy6tjdlL._SL75_.jpg','http://ecx.images-amazon.com/images/I/51yYy6tjdlL._SL160_.jpg','http://ecx.images-amazon.com/images/I/51yYy6tjdlL._SL500_.jpg'),
 ('1930846452','The Jennifer Morgue','4.500','2006-11',340,'http://ecx.images-amazon.com/images/I/51C9564HW3L._SL75_.jpg','http://ecx.images-amazon.com/images/I/51C9564HW3L._SL160_.jpg','http://ecx.images-amazon.com/images/I/51C9564HW3L._SL500_.jpg');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;


--
-- Definition of table `carlweb`
--

DROP TABLE IF EXISTS `carlweb`;
CREATE TABLE `carlweb` (
  `isbn` varchar(10) NOT NULL,
  `carlweb_id` int(11) NOT NULL,
  PRIMARY KEY  (`isbn`),
  CONSTRAINT `carlweb_ibfk_1` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `carlweb`
--

/*!40000 ALTER TABLE `carlweb` DISABLE KEYS */;
/*!40000 ALTER TABLE `carlweb` ENABLE KEYS */;


--
-- Definition of table `lib_entry`
--

DROP TABLE IF EXISTS `lib_entry`;
CREATE TABLE `lib_entry` (
  `lib_id` int(10) unsigned NOT NULL auto_increment,
  `username` varchar(30) NOT NULL,
  `isbn` varchar(10) NOT NULL,
  `user_rating` decimal(4,3) unsigned default NULL,
  `date_added` date NOT NULL,
  `date_started` date default NULL,
  `date_finished` date default NULL,
  PRIMARY KEY  (`lib_id`),
  KEY `isbn` (`isbn`),
  KEY `username` (`username`),
  CONSTRAINT `lib_entry_ibfk_1` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`),
  CONSTRAINT `lib_entry_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lib_entry`
--

/*!40000 ALTER TABLE `lib_entry` DISABLE KEYS */;
INSERT INTO `lib_entry` (`lib_id`,`username`,`isbn`,`user_rating`,`date_added`,`date_started`,`date_finished`) VALUES 
 (1,'apple','1416555870',NULL,'2008-09-17',NULL,NULL),
 (2,'apple','0061433012',NULL,'2008-09-17',NULL,NULL),
 (3,'apple','0061474096',NULL,'2008-09-17',NULL,NULL),
 (4,'apple','0316143472',NULL,'2008-09-17',NULL,NULL),
 (5,'apple','0345496884',NULL,'2008-09-17',NULL,NULL),
 (6,'apple','0345501748',NULL,'2008-09-17',NULL,NULL),
 (7,'apple','0345507460',NULL,'2008-09-17',NULL,NULL),
 (8,'apple','0380798379',NULL,'2008-09-17',NULL,NULL),
 (9,'apple','0425222330',NULL,'2008-09-17',NULL,NULL),
 (10,'apple','0441013651',NULL,'2008-09-17',NULL,NULL),
 (11,'apple','0441016197',NULL,'2008-09-17',NULL,NULL),
 (12,'apple','0441016383',NULL,'2008-09-17',NULL,NULL),
 (13,'apple','0451462289',NULL,'2008-09-17',NULL,NULL),
 (14,'apple','0451462564',NULL,'2008-09-17',NULL,NULL),
 (15,'apple','0553345923',NULL,'2008-09-17',NULL,NULL),
 (16,'apple','0765304961',NULL,'2008-09-17',NULL,NULL),
 (17,'apple','0765315459',NULL,'2008-09-17',NULL,NULL),
 (18,'apple','0765318679',NULL,'2008-09-17',NULL,NULL),
 (19,'apple','0765319209',NULL,'2008-09-17',NULL,NULL),
 (20,'apple','0765320428',NULL,'2008-09-17',NULL,NULL),
 (21,'apple','0765358549',NULL,'2008-09-17',NULL,NULL),
 (22,'apple','0809572354',NULL,'2008-09-17',NULL,NULL),
 (23,'apple','1416555870',NULL,'2008-09-17',NULL,NULL),
 (24,'apple','1416555919',NULL,'2008-09-17',NULL,NULL),
 (25,'apple','1555839878',NULL,'2008-09-17',NULL,NULL),
 (26,'apple','1590200152',NULL,'2008-09-17',NULL,NULL),
 (27,'apple','159102594X',NULL,'2008-09-17',NULL,NULL),
 (28,'apple','1591026415',NULL,'2008-09-17',NULL,NULL),
 (29,'apple','1591026903',NULL,'2008-09-17',NULL,NULL),
 (30,'apple','1595540857',NULL,'2008-09-17',NULL,NULL),
 (31,'apple','159554089X',NULL,'2008-09-17',NULL,NULL),
 (32,'apple','1930846452',NULL,'2008-09-17',NULL,NULL),
 (33,'apple','1595540873',NULL,'2008-09-17',NULL,NULL);
/*!40000 ALTER TABLE `lib_entry` ENABLE KEYS */;


--
-- Definition of table `playlist_entry`
--

DROP TABLE IF EXISTS `playlist_entry`;
CREATE TABLE `playlist_entry` (
  `playlist_id` int(10) unsigned NOT NULL auto_increment,
  `entry_id` int(10) unsigned NOT NULL,
  `playlist_name` varchar(30) default NULL,
  `order_num` int(10) unsigned default NULL,
  `username` varchar(30) NOT NULL,
  PRIMARY KEY  (`playlist_id`),
  KEY `entry_id` (`entry_id`),
  KEY `FK_playlist_entry_2` (`username`),
  CONSTRAINT `FK_playlist_entry_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  CONSTRAINT `playlist_entry_ibfk_1` FOREIGN KEY (`entry_id`) REFERENCES `lib_entry` (`lib_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `playlist_entry`
--

/*!40000 ALTER TABLE `playlist_entry` DISABLE KEYS */;
INSERT INTO `playlist_entry` (`playlist_id`,`entry_id`,`playlist_name`,`order_num`,`username`) VALUES 
 (2,2,'read',1,'apple'),
 (3,3,'read',2,'apple'),
 (4,4,'read',3,'apple'),
 (5,7,'read',4,'apple');
/*!40000 ALTER TABLE `playlist_entry` ENABLE KEYS */;


--
-- Definition of table `tags`
--

DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `isbn` varchar(10) default NULL,
  `tag` varchar(255) default NULL,
  KEY `isbn` (`isbn`),
  CONSTRAINT `tags_ibfk_1` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tags`
--

/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` (`isbn`,`tag`) VALUES 
 ('1416555870','military science fiction'),
 ('1416555870','john ringo'),
 ('1416555870','looking glass series'),
 ('1416555870','multiverse'),
 ('1416555870','string theory'),
 ('0061433012','terry pratchett'),
 ('0061433012','pratchett'),
 ('0061433012','fantasy'),
 ('0061433012','discworld'),
 ('0061433012','fiction'),
 ('0061474096','neal stephenson'),
 ('0061474096','science fiction'),
 ('0061474096','geek'),
 ('0061474096','book'),
 ('0061474096','toread'),
 ('0316143472','david sedaris'),
 ('0316143472','humor'),
 ('0316143472','comedy'),
 ('0316143472','amazing read'),
 ('0316143472','bestseller'),
 ('0345496884','naomi novik'),
 ('0345496884','temeraire'),
 ('0345496884','dragons'),
 ('0345496884','historical fantasy'),
 ('0345496884','alternate history'),
 ('0345501748','chabon'),
 ('0345501748','adventure'),
 ('0345501748','historical fiction'),
 ('0345501748','khazar empire'),
 ('0345501748','jews'),
 ('0345507460','dresden files'),
 ('0345507460','harry dresden'),
 ('0345507460','jim butcher'),
 ('0345507460','the dresden files'),
 ('0345507460','urban dark fantasy'),
 ('0380798379','tim powers'),
 ('0380798379','time travel'),
 ('0380798379','charlie chaplin'),
 ('0380798379','einstein'),
 ('0380798379','fantasy'),
 ('0441013651','british sf'),
 ('0441013651','charles stross'),
 ('0441013651','science fiction'),
 ('0441013651','horror'),
 ('0441013651','alternate reality'),
 ('0441016197','military science fiction'),
 ('0441016197','lost fleet'),
 ('0441016197','sf military'),
 ('0441016197','excellent war science fiction'),
 ('0441016197','jack campbell'),
 ('0441016383','codex alera'),
 ('0441016383','jim butcher'),
 ('0441016383','epic fantasy'),
 ('0441016383','alera'),
 ('0441016383','fantasy'),
 ('0451462289','alternate history'),
 ('0451462289','sm stirling'),
 ('0451462289','post-apocalyptic'),
 ('0451462289','military science fiction'),
 ('0451462289','science fiction'),
 ('0451462564','harry dresden'),
 ('0451462564','jim butcher'),
 ('0451462564','dresden files'),
 ('0451462564','the dresden files'),
 ('0451462564','urban dark fantasy'),
 ('0765304961','orson scott card'),
 ('0765304961','ender'),
 ('0765304961','enders saga'),
 ('0765304961','ender series'),
 ('0765304961','science fiction'),
 ('0765315459','karl schroeder'),
 ('0765315459','hard sf'),
 ('0765315459','science fiction'),
 ('0765315459','airships'),
 ('0765315459','epic space opera'),
 ('0765318679','british fantasy'),
 ('0765318679','epic fantasy'),
 ('0765318679','fantasy'),
 ('0765318679','best books of 2007 so far'),
 ('0765318679','good characters'),
 ('0765319209','science fiction'),
 ('0765319209','adventure'),
 ('0765319209','epic space opera'),
 ('0765319209','democracy'),
 ('0765319209','dreds'),
 ('0765320428','steampunk'),
 ('0765320428','fantasy'),
 ('0765320428','adventure'),
 ('0765320428','british'),
 ('0765320428','lord of the rings'),
 ('0765358549','fairies'),
 ('0765358549','new york'),
 ('0765358549','crohns disease'),
 ('0765358549','fantasy'),
 ('0765358549','scotland'),
 ('0809572354','dark fantasy'),
 ('0809572354','lit'),
 ('1416555919','military science fiction'),
 ('1416555919','sf military'),
 ('1416555919','adventure'),
 ('1416555919','john ringo'),
 ('1416555919','space opera'),
 ('1555839878','arthurian legend'),
 ('1555839878','lancelot'),
 ('1555839878','gay'),
 ('1555839878','gay romance'),
 ('1555839878','alternate history'),
 ('1590200152','john crowley'),
 ('159102594X','epic fantasy'),
 ('159102594X','good characters'),
 ('159102594X','fantasy'),
 ('159102594X','sword and sorcery'),
 ('159102594X','black humor'),
 ('1591026415','fantasy'),
 ('1591026415','good characters'),
 ('1591026415','brilliant'),
 ('1591026415','epic fantasy'),
 ('1591026415','joe abercrombie'),
 ('1591026903','dark fantasy'),
 ('1591026903','brilliant'),
 ('1591026903','epic fantasy'),
 ('1591026903','fantasy series'),
 ('1591026903','adventure'),
 ('1595540857','robin hood'),
 ('1595540857','lawhead'),
 ('1595540857','adventure'),
 ('1595540857','legend'),
 ('1595540857','historical fiction'),
 ('159554089X','robin hood'),
 ('159554089X','stephen r lawhead'),
 ('159554089X','historical fiction'),
 ('159554089X','fantasy'),
 ('159554089X','adventure'),
 ('1930846452','humor'),
 ('1930846452','charles stross'),
 ('1930846452','hp lovecraft'),
 ('1930846452','horror'),
 ('1930846452','contemporary fantasy'),
 ('1595540873','stephen r lawhead'),
 ('1595540873','christian fantasy'),
 ('1595540873','historical fiction');
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `email` varchar(255) default NULL,
  `date_joined` date NOT NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`username`,`password`,`email`,`date_joined`) VALUES 
 ('apple','banana','cranberry','2008-09-16');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


--
-- Definition of procedure `addToPlaylist`
--

DROP PROCEDURE IF EXISTS `addToPlaylist`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addToPlaylist`(entryId INT UNSIGNED, playlistName VARCHAR(30))
BEGIN
   DECLARE newOrderNum INT UNSIGNED;
   DECLARE curUserName VARCHAR(30);
   SELECT username INTO curUserName FROM lib_entry WHERE lib_id = entryId;
   SELECT count(*) INTO newOrderNum FROM playlist_entry WHERE playlist_name = playlistName AND username = curUserName;
   SET newOrderNum = newOrderNum+1;
   INSERT INTO playlist_entry VALUES(null, entryId, playlistName, newOrderNum, curUserName);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `deletePlaylistEntry`
--

DROP PROCEDURE IF EXISTS `deletePlaylistEntry`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deletePlaylistEntry`(playlistId INT UNSIGNED)
BEGIN
   DECLARE playlistName VARCHAR(30);
   SELECT playlist_name INTO playlistName FROM playlist_entry WHERE playlist_id = playlistId;
   CALL movePlaylistEntry(playlistId,(SELECT COUNT(*) from playlist_entry WHERE playlist_name = playlistName));
   DELETE FROM playlist_entry WHERE playlist_id = playlistId;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `getData`
--

DROP PROCEDURE IF EXISTS `getData`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getData`(userName VARCHAR(30), playlistName VARCHAR(30), taglist TEXT)
BEGIN
   DECLARE i INT;
   DECLARE temp TEXT;
   DECLARE done BOOLEAN;

   CREATE TEMPORARY TABLE hasTags(
      tag VARCHAR(30)
   );

   IF NOT taglist IS NULL THEN
      SET i = LOCATE(',',taglist)+1;
      SET temp = taglist;
      SET done = false;

      WHILE NOT done DO
         INSERT INTO hasTags VALUES(SUBSTRING_INDEX(temp,',',1));
         SET i = LOCATE(',',temp)+1;
         IF i=1 THEN SET done = true; END IF;
         SET temp = SUBSTRING(temp, i);
      END WHILE;

      IF playlistName IS NULL THEN      /*library search with limiting tags*/
         SELECT DISTINCT a.isbn, title, (select GROUP_CONCAT(author SEPARATOR ', ') from booktracker.authors where authors.isbn=a.isbn) as author, amaz_rating, pub_date, pages, small_url, medium_url, large_url
            FROM book a, lib_entry b, tags c
               WHERE a.isbn = b.isbn
               AND b.username = userName
               AND c.isbn = a.isbn
               AND c.isbn IN (SELECT DISTINCT isbn
                     FROM booktracker.tags AS PS1
                        WHERE NOT EXISTS
                          (SELECT * FROM booktracker.hasTags AS ht
                             WHERE NOT EXISTS
                                (SELECT *
                                   FROM booktracker.tags AS PS2
                                      WHERE (PS1.isbn = PS2.isbn)
                                      AND (PS2.tag = ht.tag)
                                )
                          )
                  );
      ELSE                             /*playlist search with limiting tags*/
         SELECT DISTINCT a.isbn, title, (select GROUP_CONCAT(author SEPARATOR ', ') from booktracker.authors where authors.isbn=a.isbn) as author, amaz_rating, pub_date, pages, small_url, medium_url, large_url
            FROM book a, lib_entry b, tags c, playlist_entry d
               WHERE a.isbn = b.isbn
               AND b.lib_id = d.entry_id
               AND d.playlist_name = playlistName
               AND b.username = userName
               AND c.isbn = a.isbn
               AND c.isbn IN (SELECT DISTINCT isbn
                     FROM booktracker.tags AS PS1
                        WHERE NOT EXISTS
                          (SELECT * FROM booktracker.hasTags AS ht
                             WHERE NOT EXISTS
                                (SELECT *
                                   FROM booktracker.tags AS PS2
                                      WHERE (PS1.isbn = PS2.isbn)
                                      AND (PS2.tag = ht.tag)
                                )
                          )
                  );
      END IF;
   ELSE                              /*library search, no limiting tags*/
      IF playlistName IS NULL THEN
         SELECT DISTINCT a.isbn, title, (select GROUP_CONCAT(author SEPARATOR ', ') from booktracker.authors where authors.isbn=a.isbn) as author, amaz_rating, pub_date, pages, small_url, medium_url, large_url
            FROM book a, lib_entry b
               WHERE a.isbn = b.isbn
               AND b.username = userName;
      ELSE                        /*playlist search, no limiting tags*/
         SELECT DISTINCT a.isbn, title, (select GROUP_CONCAT(author SEPARATOR ', ') from booktracker.authors where authors.isbn=a.isbn) as author, amaz_rating, pub_date, pages, small_url, medium_url, large_url
            FROM book a, lib_entry b, playlist_entry c
               WHERE a.isbn = b.isbn
               AND b.lib_id = c.entry_id
               AND c.playlist_name = playlistName
               AND b.username = userName;
      END IF;
   END IF;

   DROP TABLE hasTags;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `getTags`
--

DROP PROCEDURE IF EXISTS `getTags`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getTags`(userName VARCHAR(30), playlistName VARCHAR(30), taglist TEXT)
BEGIN
   DECLARE i INT;
   DECLARE temp TEXT;
   DECLARE done BOOLEAN;

   CREATE TEMPORARY TABLE hasTags(
      tag VARCHAR(30)
   );

   IF NOT taglist IS NULL THEN
      SET i = LOCATE(',',taglist)+1;
      SET temp = taglist;
      SET done = false;

      WHILE NOT done DO
         INSERT INTO hasTags VALUES(SUBSTRING_INDEX(temp,',',1));
         SET i = LOCATE(',',temp)+1;
         IF i=1 THEN SET done = true; END IF;
         SET temp = SUBSTRING(temp, i);
      END WHILE;

      IF playlistName IS NULL THEN      /*library search with limiting tags*/
         SELECT tag, num FROM
            (SELECT tag, count(*) as num FROM booktracker.tags WHERE isbn IN
               (SELECT isbn FROM booktracker.lib_entry WHERE username = userName AND isbn IN
                  (SELECT DISTINCT isbn
                     FROM booktracker.tags AS PS1
                        WHERE NOT EXISTS
                          (SELECT * FROM booktracker.hasTags AS ht
                             WHERE NOT EXISTS
                                (SELECT *
                                   FROM booktracker.tags AS PS2
                                      WHERE (PS1.isbn = PS2.isbn)
                                      AND (PS2.tag = ht.tag)
                                )
                          )
                  )
               )
            GROUP BY tag ORDER BY num DESC LIMIT 50) as tab
         ORDER BY tag ASC;
      ELSE                             /*playlist search with limiting tags*/
         SELECT tag, num FROM
            (SELECT tag, count(*) as num FROM booktracker.tags WHERE isbn IN
               (SELECT isbn from booktracker.lib_entry a, booktracker.playlist_entry b
               WHERE a.lib_id = b.entry_id
               AND a.username = userName
               AND b.playlist_name = playlistName
               AND isbn IN
                  (SELECT DISTINCT isbn
                     FROM booktracker.tags AS PS1
                        WHERE NOT EXISTS
                          (SELECT * FROM booktracker.hasTags AS ht
                             WHERE NOT EXISTS
                                (SELECT *
                                   FROM booktracker.tags AS PS2
                                      WHERE (PS1.isbn = PS2.isbn)
                                      AND (PS2.tag = ht.tag)
                                )
                          )
                  )
               )
            GROUP BY tag ORDER BY num DESC LIMIT 50) as tab
         ORDER BY tag ASC;
      END IF;
   ELSE                              /*library search, no limiting tags*/
      IF playlistName IS NULL THEN
         SELECT tag, num FROM
            (SELECT tag, count(*) as num FROM booktracker.tags WHERE isbn IN
               (SELECT isbn FROM booktracker.lib_entry WHERE username = userName)
               GROUP BY tag ORDER BY num DESC LIMIT 50) as tab
            ORDER BY tag ASC;
      ELSE                        /*playlist search, no limiting tags*/
         SELECT tag, num FROM
            (SELECT tag, count(*) as num FROM booktracker.tags WHERE isbn IN
               (SELECT isbn from booktracker.lib_entry a, booktracker.playlist_entry b
               WHERE a.lib_id = b.entry_id
               AND a.username = userName
               AND b.playlist_name = playlistName)
               GROUP BY tag ORDER BY num DESC LIMIT 50) as tab
            ORDER BY tag ASC;
      END IF;
   END IF;

   DROP TABLE hasTags;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `insertAltVers`
--

DROP PROCEDURE IF EXISTS `insertAltVers`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertAltVers`(isbn VARCHAR(10), altVers TEXT)
BEGIN
   DECLARE i INT;
   DECLARE temp TEXT;
   DECLARE done BOOLEAN;

   IF NOT altVers IS NULL THEN
      SET i = LOCATE(',',altVers)+1;
      SET temp = altVers;
      SET done = false;

      WHILE NOT done DO
         INSERT INTO alt_vers VALUES(isbn,SUBSTRING_INDEX(temp,',',1));
         SET i = LOCATE(',',temp)+1;
         IF i=1 THEN SET done = true; END IF;
         SET temp = SUBSTRING(temp, i);
      END WHILE;
   END IF;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `insertAuthors`
--

DROP PROCEDURE IF EXISTS `insertAuthors`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertAuthors`(isbn VARCHAR(10), authors TEXT)
BEGIN
   DECLARE i INT;
   DECLARE temp TEXT;
   DECLARE done BOOLEAN;


   IF NOT authors IS NULL THEN
      SET i = LOCATE(',',authors)+1;
      SET temp = authors;
      SET done = false;


      WHILE NOT done DO
         INSERT INTO authors VALUES(isbn,SUBSTRING_INDEX(temp,',',1));
         SET i = LOCATE(',',temp)+1;
         IF i=1 THEN SET done = true; END IF;
         SET temp = SUBSTRING(temp, i);
      END WHILE;
   END IF;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `insertBook`
--

DROP PROCEDURE IF EXISTS `insertBook`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertBook`(
        isbn VARCHAR(10), title TEXT, amaz_rating DECIMAL(4,3), pub_date VARCHAR(10),
        pages INTEGER, small_url TEXT, medium_url TEXT, large_url TEXT,
        authors TEXT, altVers TEXT, tags TEXT)
BEGIN
   INSERT INTO book VALUES(isbn, title, amaz_rating, pub_date, pages, small_url, medium_url, large_url);
   CALL insertAltVers(isbn, altVers);
   CALL insertAuthors(isbn, authors);
   CALL insertTags(isbn, tags);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `insertTags`
--

DROP PROCEDURE IF EXISTS `insertTags`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertTags`(isbn VARCHAR(10), tags TEXT)
BEGIN
   DECLARE i INT;
   DECLARE temp TEXT;
   DECLARE done BOOLEAN;

   IF NOT tags IS NULL THEN
      SET i = LOCATE(',',tags)+1;
      SET temp = tags;
      SET done = false;

      WHILE NOT done DO
         INSERT INTO tags VALUES(isbn,SUBSTRING_INDEX(temp,',',1));
         SET i = LOCATE(',',temp)+1;
         IF i=1 THEN SET done = true; END IF;
         SET temp = SUBSTRING(temp, i);
     END WHILE;
   END IF;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `movePlaylistEntry`
--

DROP PROCEDURE IF EXISTS `movePlaylistEntry`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `movePlaylistEntry`(playlistId INT UNSIGNED, newIndex INT UNSIGNED)
BEGIN

   DECLARE oldIndex INT UNSIGNED;
   DECLARE temp INT UNSIGNED;
   DECLARE playlistName VARCHAR(30);

   SELECT order_num INTO oldIndex FROM playlist_entry WHERE playlist_id = playlistId;
   SELECT playlist_name INTO playlistName FROM playlist_entry WHERE playlist_id = playlistId;

   IF newIndex > oldIndex THEN
      WHILE newIndex > oldIndex DO
         SET temp = oldIndex;
         UPDATE playlist_entry SET order_num = oldIndex WHERE order_num = oldIndex+1 AND playlist_name = playlistName;
         SET oldIndex = oldIndex+1;
      END WHILE;
      UPDATE playlist_entry SET order_num = newIndex WHERE playlist_id = playlistId;
   ELSEIF newIndex <= oldIndex THEN
      WHILE newIndex < oldIndex DO
         SET temp = oldIndex;
         UPDATE playlist_entry SET order_num = oldIndex+1 WHERE order_num = oldIndex AND playlist_name = playlistName;
         SET oldIndex = oldIndex-1;
      END WHILE;
      UPDATE playlist_entry SET order_num = newIndex WHERE playlist_id = playlistId;
   END IF;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
