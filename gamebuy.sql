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
DROP DATABASE IF EXISTS gamebuy;
CREATE DATABASE IF NOT EXISTS gamebuy;

COMMIT;

USE gamebuy;

--
-- Definition of table  customers 
--

DROP TABLE IF EXISTS  customers ;
CREATE TABLE  customers  (
   id  char(10) NOT NULL,
   email  varchar(45) NOT NULL,
   password  varchar(20) NOT NULL,
   address  varchar(120) NOT NULL,
   phone  int(10) NOT NULL,
   name  varchar(20) NOT NULL,
   birthday  date NOT NULL,
   status  int(10) unsigned NOT NULL DEFAULT '0',
   bonus  int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY ( email ),
  UNIQUE KEY  Index_unique_id  ( id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

COMMIT;

--
-- Dumping data for table  customers 
--

/*!40000 ALTER TABLE  customers  DISABLE KEYS */;
INSERT INTO  customers  ( id , email , password , address , phone , name , birthday , status , bonus ) VALUES 
 ('A126161787','dawei.wang@gamil.com','b22126','台北市復興北路99號16F',987666066,'王大偉','1966-01-06',0,0),
 ('A268785841','meily.wang@gmail.com','a987654321','新北市新店區北新路三段123號',912456879,'王美麗','1996-05-21',0,0);
/*!40000 ALTER TABLE  customers  ENABLE KEYS */;

COMMIT;
--
-- Definition of table  gametype 
--

DROP TABLE IF EXISTS  platform ;
CREATE TABLE  platform  (
   idplatform  int(11) NOT NULL,
   name  varchar(10) NOT NULL,
   chname  varchar(15) NOT NULL,
  PRIMARY KEY ( idplatform )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
COMMIT;
--
-- Dumping data for table  platform 
--

/*!40000 ALTER TABLE  platform  DISABLE KEYS */;
INSERT INTO  platform  ( idplatform , name , chname ) VALUES 
 (0,'Download','數位遊戲'),
 (1,'PS4','PlayStation4'),
 (2,'xbox','Xbox'),
 (3,'PS3','PlayStation3'),
 (4,'WII','Wii U'),
 (5,'PC','個人電腦');
/*!40000 ALTER TABLE  platform  ENABLE KEYS */;
COMMIT;

DROP TABLE IF EXISTS  gametype ;
CREATE TABLE  gametype  (
   idgametype  int(11) NOT NULL,
   name  varchar(10) NOT NULL,
   chname  varchar(10) NOT NULL,
  PRIMARY KEY ( idgametype )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
COMMIT;
--
-- Dumping data for table  gametype 
--

/*!40000 ALTER TABLE  gametype  DISABLE KEYS */;
INSERT INTO  gametype  ( idgametype , name , chname ) VALUES 
 (0,'Software','遊戲軟體'),
 (1,'ChSoftware','中文化遊戲軟體'),
 (2,'Restricted','限制級專區');
/*!40000 ALTER TABLE  gametype  ENABLE KEYS */;
COMMIT;



DROP TABLE IF EXISTS  products ;
CREATE TABLE  products  (
   id  int(10) unsigned NOT NULL AUTO_INCREMENT,
   name  varchar(40) NOT NULL,
   unitprice  double NOT NULL,
   stock  int(10) unsigned NOT NULL,
   url  varchar(40) NOT NULL,
   description  varchar(500) NOT NULL,
   bonus  int(10) unsigned NOT NULL,
   status  int(10) unsigned NOT NULL,
   preferentialprice  double NOT NULL,
   issuedate  date NOT NULL,
   gametype_idgametype  int(11) NOT NULL,
   platform_idplatform  int(11) NOT NULL,
  PRIMARY KEY ( id ),
  UNIQUE KEY  Index_u  ( name ),
  KEY  fk_products_platform_idx  ( platform_idplatform ),
  KEY  fk_products_gametype1_idx  ( gametype_idgametype ),
  CONSTRAINT  fk_products_gametype1  FOREIGN KEY ( gametype_idgametype ) REFERENCES  gametype  ( idgametype ) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT  fk_products_platform  FOREIGN KEY ( platform_idplatform ) REFERENCES  platform  ( idplatform ) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
COMMIT;
--
-- Dumping data for table  products 
--

/*!40000 ALTER TABLE  products  DISABLE KEYS */;
INSERT INTO  products  ( id , name , unitprice , stock , url , description , bonus , status , preferentialprice , issuedate , gametype_idgametype , platform_idplatform ) VALUES 
 (1,'《看門狗 2》中文版（數位下載版）',1690,0,'images\\watch_dogs_2.JPG','　※ 請注意：本商品僅適用於 Uplay 平台，不相容其他平台。',84,1,1690,'2016-11-15',1,0),
 (2,'《火線獵殺：野境》中文版（數位下載版）',1790,0,'images\\ghost_recon.JPG','※ 請注意：本商品僅適用於 Uplay 平台，不相容其他平台。',84,1,1790,'2017-03-07',1,0),
 (3,'《為戰而生》中文豪華版（數位下載版）',2122,10,'images\\battleborn.JPG','※ 請注意：本遊戲僅支援 Windows 7 之 64 位元或更新的作業系統，且遊戲遊玩期間需全程保持',106,2,2122,'2016-05-05',1,0),
 (4,'《為戰而生》中文一般版（數位下載版）',1790,20,'images\\battleborn2.JPG','　※ 請注意：本遊戲僅支援 Windows 7 之 64 位元或更新的作業系統，且遊戲遊玩期間需全程保持',89,2,1790,'2016-05-05',1,0),
 (5,'《毀滅戰士》中文初回版（數位下載版）',1830,5,'images\\doom.JPG','　※ 請注意：本遊戲僅支援 Windows 7 / 8 / 10 之 64 位元作業系統。',90,1,1830,'2016-05-13',1,0),
 (6,'《湯姆克蘭西：全境封鎖》中文一般版（數位下載版）',1490,5,'images\\the_division.JPG','　※ 請注意：本遊戲僅支援 Windows 7 / 8.1 / 10 之 64 位元作業系統，且遊戲遊玩期間需全程',74,2,1490,'2016-03-10',1,0),
 (7,'《極地戰嚎：野蠻紀源》中文一般版（數位下載版）',1490,5,'images\\farcry_primal.JPG','　※ 請注意：本遊戲僅支援 Windows 7 / 8.1 / 10 之 64 位元作業系統。',74,2,1490,'2016-02-16',1,0),
 (8,'《湯姆克蘭西：全境封鎖》中文黃金版（數位下載版）',2490,5,'images\\the_division_goden.JPG','　※ 請注意：本遊戲僅支援 Windows 7 / 8.1 / 10 之 64 位元作業系統，且遊戲遊玩期間需全程',124,2,2490,'2016-03-10',1,0),
 (9,'《黑暗靈魂 3》中文版（數位下載版）',1290,5,'images\\dark_souls_3.JPG','　※ 請注意：本遊戲僅支援 Windows 7 / 8 / 10 之 64 位元作業系統。',64,2,1290,'2016-03-30',1,0),
 (10,'《火影忍者疾風傳：終極風暴 4》中文版（數位下載版）',1290,5,'images\\naruto4.JPG','　※ 請注意：本遊戲僅支援 Windows 7 含以上之 64 位元作業系統。',64,2,1290,'2016-02-18',1,0),
 (11,'《四海兄弟 2》英文豪華版（數位下載版）',868,5,'images\\mafia2.JPG','．「《四海兄弟 2》英文豪華版 下載序號」一組。',43,2,868,'2010-03-04',0,0),
 (12,'《四海兄弟 2》英文一般版（數位下載版）',688,5,'images\\mafia2-01.JPG','．「《四海兄弟 2》英文一般版 下載序號」一組。',34,2,688,'2010-03-04',0,0),
 (13,'《XCOM 2》追加內容：外星獵手包',299,5,'images\\xcom2.JPG','　※ 請注意：本商品需配合《XCOM 2》主程式執行，僅適用於 Steam 平台，不相容其他平台。',14,2,299,'2016-05-12',0,0),
 (14,'《為戰而生》追加內容：優惠套裝',468,5,'images\\battleborn_season_pass.JPG','　※ 請注意：本商品需配合 PC 平台之《為戰而生》主程式執行。',23,2,468,'2016-05-05',0,0),
 (15,'《WWE 2K16》英文版（數位下載版',1490,5,'images\\w2k16.JPG','　※ 請注意：本遊戲僅支援 Windows Vista SP2 / 7 / 8 之 64 位元作業系統。',74,2,1490,'2016-03-11',0,0),
 (16,'	《XCOM 2》追加內容：加強包',468,5,'images\\xcom2_pack.JPG','　※ 請注意：本商品需配合《XCOM 2》主程式執行，僅適用於 Steam 平台，不相容其他平台。',23,2,468,'2016-05-12',0,0),
 (17,'《文明帝國：超越地球 - 潮起》中文版（數位下載版）',890,5,'images\\beyond_earth.JPG','　※ 請注意：本商品需配合《文明帝國：超越地球》主程式執行，安裝及啟動遊戲需要 Steam 帳號。',44,2,890,'2015-10-16',0,0),
 (18,'	《席德梅爾：星際戰艦》+《文明帝國：超越地球》合輯（數位下載版）',1739,5,'images\\starships.JPG','．「《席德梅爾：星際戰艦》中文版 下載序號」一組。',84,2,1699,'2015-09-16',0,0),
 (19,'《惡靈進化》中文怪獸競賽版（數位下載版）',2999,5,'images\\evolve.JPG','　※ 請注意：本遊戲僅支援 Windows 7 之 64 位元版本作業系統，安裝及啟動遊戲需要 Steam 帳號。',149,2,2999,'2015-02-16',0,0),
 (20,'	《邊緣禁地：續集前傳》英文初回特典版（數位下載版）',1190,5,'images\\borderlands.JPG','．「《邊緣禁地：續集前傳》英文初回特典版 下載序號」一組。',59,2,1190,'2014-10-24',0,0),
 (21,'《上古卷軸 5：無界天際 傳奇版》英文版（數位下載版）',868,5,'images\\skyrim.JPG','　※ 請注意：本商品僅適用於 Steam 平台，不相容其他平台。',43,2,868,'2011-02-23',2,0),
 (22,'《邪靈入侵》+ Season Pass 組合包（數位下載版）',1648,5,'images\\the_evil_within.JPG','．「《邪靈入侵》+ Season Pass 組合包 下載序號」一組。',82,2,1648,'2014-10-23',2,0),
 (23,'《異塵餘生：新維加斯 終極典藏版》英文版（數位下載版）',599,0,'images\\fallout.JPG','　※ 請注意：本商品僅適用於 Steam 平台，不相容其他平台。',29,2,599,'2012-01-19',2,0),
 (24,'	《異塵餘生 3 年度版》英文版（數位下載版）',599,0,'images\\fallout3.JPG','　※ 請注意：本商品僅適用於 Steam 平台，不相容其他平台。',29,2,599,'2013-05-13',2,0),
 (25,'	《異塵餘生 典藏版》英文版（數位下載版）',599,0,'images\\fallout_classic.JPG','　※ 請注意：本商品僅適用於 Steam 平台，不相容其他平台。',29,2,599,'2013-05-13',2,0),
 (26,'《刺客教條：編年史 – 俄羅斯》中文版（數位下載版）',330,0,'images\\assassins_russia.JPG','※ 請注意：本遊戲僅支援 Windows 7 SP1 / 8 與 8.1 作業系統。',16,2,330,'2013-05-11',2,0),
 (27,'	《異塵餘生 4》中文版（數位下載版）',1830,0,'images\\fallout4.JPG','　※ 請注意：本遊戲僅支援 Windows 7 / 8 / 10 之 64 位元作業系統',91,2,1830,'2015-11-09',2,0),
 (28,'《殭屍+》英日文合版（數位下載版）',499,0,'images\\zombi.JPG','　※ 請注意：本遊戲僅支援 Windows 7 SP1 / 8.1 之 64 位元作業系統。',24,2,499,'2015-08-27',2,0),
 (29,'《虹彩六號：圍攻行動》中文黃金版（數位下載版）',2090,0,'images\\rainbowsix.JPG','　※ 請注意：本遊戲僅支援 Windows 7 / 8.1 / 10 之 64 位元作業系統。',104,2,2090,'2015-11-11',2,0),
 (30,'《生死格鬥 5 Last Round》中文版（數位下載版）',1180,0,'images\\last_round.JPG','．「《生死格鬥 5 Last Round》中文版 下載序號」一組。',59,2,1180,'2015-02-26',2,0),
 (31,'《烽火家園：革命》+ HyperX Cloud Core 電競耳機麥克風組',3180,0,'images\\homefront.JPG','．「《烽火家園：革命》英文一般版 遊戲軟體」一套。',0,1,2880,'2016-05-26',0,1),
 (32,'	《菲莉絲的鍊金工房 ～不可思議之旅的鍊金術士～》日文特別珍藏盒版',4250,0,'images\\firis.JPG','．「《菲莉絲的鍊金工房 ～不可思議之旅的鍊金術士～》日文特別珍藏盒版」一套。',0,1,4250,'2016-11-11',0,1),
 (33,'《時空幻境 緋夜傳奇》中文一般版',1790,0,'images\\tales_of_bersetia.JPG','《緋夜傳奇》是《時空幻境》系列旗艦作品最新作，並且首度採用單一女性主角的設定。故事內容將以聯合許多離島組成的大陸「密托岡特聖導王國」為舞台，女主角蓓兒貝特本來是個直率又有豐富情感的少女，但三年前的某個事件徹底改變了她的人生。她被憤怒與仇恨等負面情感包覆，失去了笑容。在這個以航海與造船技術為發展主軸的世界上，蓓兒貝特踏上了她的旅程，並逐漸成為撼動全世界的重要人物。故事也將描寫蓓兒貝特「捨棄情感、和貫徹理法者的對立」為主題，來描寫角色們在理性和情感上的各種糾葛。',0,1,1790,'2016-10-10',0,1),
 (34,'	《戰地風雲 1》中文豪華版',2590,0,'images\\battlefield1.JPG','．「《戰地風雲 1》中文豪華版」一套。',0,1,2590,'2016-10-21',0,1),
 (35,'《NBA 2K17》黃金傳奇珍藏版',3290,0,'images\\nba2k17.JPG','．「《NBA 2K17》黃金傳奇珍藏版」一套。',0,1,3290,'2016-09-20',0,1),
 (36,'	《食人巨鷹 TRICO》日文一般版',1950,0,'images\\trico.JPG','《食人巨鷹 TRICO》是一手打造《迷霧古城》、《汪達與巨像》等獨特風格遊戲而廣受全球好評的遊戲創作者上田文人領軍製作的最新作品，遊戲中玩家將扮演被神祕勢力綁架到古老遺跡的小男孩，在遺跡中遇到了像是狗卻又有滿身羽毛羽翅膀的神秘巨大生物 TRICO 並與之交心，一人一獸將合作闖蕩遺跡對抗敵人。',0,1,1950,'2016-10-25',0,1),
 (37,'《蝙蝠俠：重返阿卡漢》英文版',1590,0,'images\\batman.JPG','於新世代主機上推出的《蝙蝠俠：重返阿卡漢》，將同時收錄有 PS3 / Xbox 360 時期兩款《蝙蝠俠：阿卡漢》系列遊戲《蝙蝠俠：小丑大逃亡》與《蝙蝠俠：阿卡漢城市》，且畫面則會針對新主機的硬體規格進而翻新強化。',0,1,1590,'2016-07-26',0,1),
 (38,'《戰國 BASARA 4 皇》日文版（Best Price）',790,0,'images\\basara4.JPG','本作品為《戰國 BASARA》系列的十周年紀念作品，並從過去的系列作品中挑選增加可供玩家操作的武將，追加故事劇情，以及動作部分的新要素「合戰輪盤」等內容。以 “規格外的大份量” 與 “綜藝感” 來作為其製作概念，成為能享受身處於極度混亂般戰場之樂趣的作品。另外 PS4 版將對應 Full HD 高畫質，以及每秒 60 格的繪圖表現等高水準技術。',0,1,790,'2016-07-07',0,1),
 (39,'	《鬥陣特攻》英文啟元版',1900,0,'images\\overwatch.JPG','．「《鬥陣特攻》英文啟元版」一套。《鬥陣特攻》為一款場景設定在近未來的 6 對 6 射擊遊戲，玩家可選擇加入軍人、科學家、冒險者及稀世奇人的行列，為爭奪世界各地知名地點的控制權而展開戰鬥。遊戲共有 21 位英雄，他們各自擁有獨特武器和特殊能力，例如可扭曲時間或無視物理法則；玩家要與隊友合作，一同完成遊戲目標。遊戲相當考驗團隊精神，玩家在遊戲中可以更換英雄，運用自身能力扭轉戰局。',0,2,1900,'2016-05-24',0,1),
 (40,'《跑車浪漫旅 競速》英文一般版',1850,0,'images\\sport.JPG','由 Polyphony Digital 與國際汽車聯盟（FIA）合作的競速賽車遊戲《跑車浪漫旅 競速》，是系列首款 PS4 新作，承襲《跑車浪漫旅》的真實駕駛模擬血統。遊戲中玩家將能駕駛速度最快、最受歡迎的 137 台汽車，包含來自「Vision Gran Turismo」的未來概念車款、獨佔原型車款及真實世界的車款，馳騁在 19 個地點 27 條路線的賽道上，包括知名的「東京高速公路」、賽車聖堂的「紐堡林北側賽道」及純粹競速的「北方群島高速道路」等。',0,1,1850,'2016-11-15',0,1),
 (41,'《信長之野望 創造 戰國立志傳》中文一般版',2050,0,'images\\japan.JPG','《信長之野望》系列是以戰國時代的日本為舞台，讓玩家化身為織田信長、武田信玄、上杉謙信等大名並以統一日本全國為目標的歷史戰略遊戲。本次發售的《信長之野望 創造 戰國立志傳》承襲了「創造」之名，以「生於亂世的武將們的真實感」為概念，搭載了眾多系列作中首度出現的要素，可說是集大成之作。',0,2,2050,'2016-03-24',1,1),
 (42,'《方根書簡》中文限定版（附贈預購特典）',2190,0,'images\\letter.JPG','．「《方根書簡》中文限定版」一套。《方根書簡》是以地方文化、歷史為基礎之人性群像劇的文字冒險遊戲系列「角川推理遊戲劇場」的首部作品。以坐擁美麗自然與歷史人文的日本島根縣為舞台，供玩家在追尋十五年前突然杳無音訊的筆友「文野亞彌」下落的同時解開種種謎團的推理冒險遊戲。',0,2,2150,'2016-06-16',1,1),
 (43,'《地平線：期待黎明》中文一般版',1950,0,'images\\horizon.JPG','《地平線：期待黎明》是一手打造《殺戮地帶》系列的 Guerrilla Games 開發的開放世界動作角色扮演遊戲，以末日浩劫後千年的世界為舞台，舊文明時代建立的輝煌都市如今都已成為被植物覆蓋的廢墟，整個世界被機械生物主宰，遊戲中玩家將探索機器生物之所以主宰世界的謎團。',0,1,1950,'2017-05-01',1,1),
 (44,'《樂高：復仇者聯盟》中文版',1590,0,'images\\lego.JPG','《樂高：復仇者聯盟》集結來自賣座電影《復仇者聯盟》、《復仇者聯盟 2：奧創紀元》的知名角色與故事線，玩家將扮演地球上最強大的英雄來拯救世界，重溫漫威電影宇宙中最驚奇的時刻。   ',0,2,1590,'2016-06-18',1,1),
 (45,'《惡靈古堡：保護傘公司》中文版',1200,0,'images\\residentevil.JPG','《惡靈古堡：保護傘公司》是一款以《惡靈古堡》系列世界設定為基礎的第三人稱對戰型射擊遊戲。玩家在遊戲中要扮演一個無名傭兵，在爆發病毒感染、有許多受感染者四處徘徊的「封鎖地區」中，執行各式各樣不同的任務。而且並非是和過去作品一樣，是以單人遊戲為中心，讓玩家體驗遊戲故事的作品，本作的主要內容，是讓玩家和處在同樣情況下的其他玩家，進行各種對戰、競爭。',0,2,1200,'2016-06-30',1,1),
 (46,'《靚影特務：關鍵催化》中文版',1790,0,'images\\mirrors_edge.JPG','鏡之城為一個看似優雅、高科技的大城市。但其實在城市內部深藏了驚人的祕密 – 揭開此秘密正是自由的信使費絲的責任。在《靚影特務：關鍵催化》中體驗費絲身世的故事，並在鏡之城為自由而奮戰。這龐大的世界就是您的遊樂場。從陽光普照的屋頂到陰暗髒亂的地下隧道，以第一人稱視角奔跑、攀爬並探索每個角落。將費絲流利的移動動作與靈敏的高級戰鬥技巧結合為一，排除擋在您前方的所有對手。只有充分掌握環境，您才有可能揭開隱藏鏡之城所隱藏的陰謀內幕。',0,2,1790,'2016-06-07',1,1);
/*!40000 ALTER TABLE  products  ENABLE KEYS */;
COMMIT;

DROP TABLE IF EXISTS  orders ;
CREATE TABLE  orders  (
   id  int(11) unsigned NOT NULL AUTO_INCREMENT,
   customer_email  varchar(45) NOT NULL,
   created_time  datetime NOT NULL,
   payment_type  int(10) unsigned NOT NULL,
   payment_fee  double NOT NULL DEFAULT '0',
   payment_note  varchar(100) DEFAULT NULL,
   shipping_type  int(10) unsigned NOT NULL,
   shipping_fee  double NOT NULL DEFAULT '0',
   shipping_note  varchar(100) DEFAULT NULL,
   shipping_address  varchar(100) NOT NULL,
   receiver_name  varchar(20) NOT NULL,
   receiver_email  varchar(40) NOT NULL,
   receiver_phone  varchar(20) NOT NULL,
   status  int(10) unsigned NOT NULL DEFAULT '0',
   bonus  int(10) unsigned NOT NULL,
  PRIMARY KEY ( id ) USING BTREE,
  KEY  fk_oders_customers1_idx  ( customer_email ),
  CONSTRAINT  fk_customers  FOREIGN KEY ( customer_email ) REFERENCES  customers  ( email ) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
COMMIT;
--
-- Dumping data for table  orders 
--

DROP TABLE IF EXISTS  order_item ;
CREATE TABLE  order_item  (
   order_id  int(10) unsigned NOT NULL,
   product_id  int(10) unsigned NOT NULL,
   price  double NOT NULL,
   quantity  int(10) unsigned NOT NULL,
  PRIMARY KEY ( order_id , product_id ),
  KEY  FK_order_items_products  ( product_id ),
  CONSTRAINT  FK_order_items_orders  FOREIGN KEY ( order_id ) REFERENCES  orders  ( id ),
  CONSTRAINT  FK_order_items_products  FOREIGN KEY ( product_id ) REFERENCES  products  ( id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
COMMIT;





