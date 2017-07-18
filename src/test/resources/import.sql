
INSERT INTO `location` (`id`, `city`, `city_area`, `has_lat_long`, `latitude`, `longitude`, `state`, `street`, `street_number`, `zip_code`) VALUES (1, 'Novi Sad', NULL, '\0', 1, 1, 'Serbia', 'Mileve Maric', '52', NULL);
INSERT INTO `location` (`id`, `city`, `city_area`, `has_lat_long`, `latitude`, `longitude`, `state`, `street`, `street_number`, `zip_code`) VALUES (2, 'Novi Sad', NULL, '\0', 44.77403648591521, 20.43680191040039, 'Serbia', NULL, NULL, NULL);
INSERT INTO `location` (`id`, `city`, `city_area`, `has_lat_long`, `latitude`, `longitude`, `state`, `street`, `street_number`, `zip_code`) VALUES (3, 'Dubai', NULL, '\0', 0, 0, '', NULL, NULL, NULL);
INSERT INTO `location` (`id`, `city`, `city_area`, `has_lat_long`, `latitude`, `longitude`, `state`, `street`, `street_number`, `zip_code`) VALUES (4, 'Dubai', NULL, '\0', 0, 0, NULL, NULL, NULL, NULL);

insert into authority (name) values ('ROLE_ADMIN');
insert into authority (name) values ('ROLE_OWNER');
insert into authority (name) values ('ROLE_VERIFYER');

--password: admin
--usernmae: admin
INSERT INTO `user`(`id`,`account_number`,`address`,`birth_date`,`city`,`country`,`email`,`image_url`,`name`,`password`,`phone_number`,`role`,`surname`,`username`,`authority_id`)VALUES(1,0000,"Bul. Oslobodjenja 1","01.01.2000.","Novi Sad","Serbia","default@gmail.com","images/img1.jpg","admin default","$2a$10$2nUwyYdXRxhKio6aVrwsVOXlUqSs9XnsIndPiyT.3AphhvZ/UYBta","021 111 111","SYS_ADMIN","admin","admin",1);
INSERT INTO `user`(`id`,`account_number`,`address`,`birth_date`,`city`,`country`,`email`,`image_url`,`name`,`password`,`phone_number`,`role`,`surname`,`username`,`authority_id`)VALUES(2,0012300,"Trg Majke Jevrosime 11","17.07.2017.","Wroclaw","Poland","nina@gmail.com","images/img2.jpg","pera","$2a$10$2nUwyYdXRxhKio6aVrwsVOXlUqSs9XnsIndPiyT.3AphhvZ/UYBta","06415896325","SYS_ADMIN","peric","admin2",1);


--password: ver
--username: ver
INSERT INTO `user`(`id`,`account_number`,`address`,`birth_date`,`city`,`country`,`email`,`image_url`,`name`,`password`,`phone_number`,`role`,`surname`,`username`,`authority_id`)VALUES(3,0000,"Bul. Oslobodjenja 1","01.01.2000.","Novi Sad","Serbia","default@gmail.com","images/img1.jpg","verifyer default","$2a$10$SmLd5yKXvmiPdb03xmDoJO.0O/urG3vLsYT5W2MB5UadLCr8V7BoG","021 111 111","VERIFYER","verifyer","ver",3);

-- owner

INSERT INTO `user`(`id`,`account_number`,`address`,`birth_date`,`city`,`country`,`email`,`image_url`,`name`,`password`,`phone_number`,`role`,`surname`,`username`,`authority_id`)VALUES(9,59856,"Jovana Ducica 12","07.07.2010.","Beograd","Serbia","sandra1@gmail.com","images/img2.jpg","sandra","$2a$10$Qt3BU0OfliX1n2wGSE7E6.Evy9JryYxQp5VbW2FohZyAuv/bej1cq","021 333 555","OWNER","Jaimez","g",2);
INSERT INTO `user`(`id`,`account_number`,`address`,`birth_date`,`city`,`country`,`email`,`image_url`,`name`,`password`,`phone_number`,`role`,`surname`,`username`,`authority_id`)VALUES(4,3696,"Reja 54","01.01.2000.","Wroclaw","Poland","marko@gmail.com","images/img1.jpg","marko","$2a$10$HeR9A53PD1jmyIJn1IyZOe5o4zu./hc7LZuPV41Ie84prg2JVfo2O","021 111 111","OWNER","Markovic","a",2);
INSERT INTO `user`(`id`,`account_number`,`address`,`birth_date`,`city`,`country`,`email`,`image_url`,`name`,`password`,`phone_number`,`role`,`surname`,`username`,`authority_id`)VALUES(6,59856,"Jovana Ducica 12","07.07.2010.","Beograd","Serbia","sandra1@gmail.com","images/img2.jpg","sandra","$10$F6kOHZqMxHKbAOG5QIZcVuufoNb0pyQ0puIpsrWtMiD2YDZfCj4x.","021 333 555","OWNER","Jaimez","m",2);

--(company password for all: b)
INSERT INTO `user`(`id`,`account_number`,`address`,`birth_date`,`city`,`country`,`email`,`image_url`,`name`,`password`,`phone_number`,`role`,`surname`,`username`,`authority_id`)VALUES(5,59856,"Jovana Ducica 12","07.07.2010.","Beograd","Serbia","sandra1@gmail.com","images/img2.jpg","sandra","$2a$10$35W57GqfVd6vWgaS5bswoeRlAqZJXXLCm7RjW30iIelVV2gZWMUga","021 333 555","OWNER","Jaimez","b",2);
INSERT INTO `user`(`id`,`account_number`,`address`,`birth_date`,`city`,`country`,`email`,`image_url`,`name`,`password`,`phone_number`,`role`,`surname`,`username`,`authority_id`)VALUES(15,59856,"Jovana Ducica 12","07.07.2010.","Beograd","Serbia","sandra1@gmail.com","images/img2.jpg","nina","$2a$10$35W57GqfVd6vWgaS5bswoeRlAqZJXXLCm7RjW30iIelVV2gZWMUga","021 333 555","OWNER","simic","bb",2);
INSERT INTO `user`(`id`,`account_number`,`address`,`birth_date`,`city`,`country`,`email`,`image_url`,`name`,`password`,`phone_number`,`role`,`surname`,`username`,`authority_id`)VALUES(25,59856,"Jovana Ducica 12","07.07.2010.","Beograd","Serbia","sandra1@gmail.com","images/img2.jpg","nikola","$2a$10$35W57GqfVd6vWgaS5bswoeRlAqZJXXLCm7RjW30iIelVV2gZWMUga","021 333 555","OWNER","lukic","bbb",2);


INSERT INTO `owner`(`id`)VALUES(9);
INSERT INTO `owner`(`id`)VALUES(4);
INSERT INTO `owner`(`id`)VALUES(5);
INSERT INTO `owner`(`id`)VALUES(15);
INSERT INTO `owner`(`id`)VALUES(25);
INSERT INTO `owner`(`id`)VALUES(6);


INSERT INTO `company`(`approved`, `fax`,`pib`, `site`, `id` )VALUES(true, "fax259945121", "PIB1", "nista", 5);
INSERT INTO `company`(`approved`, `fax`,`pib`, `site`, `id` )VALUES(false, "fax252222222", "PIB2", "nesto", 15);
INSERT INTO `company`(`approved`, `fax`,`pib`, `site`, `id` )VALUES(true, "fax253355663", "PIB3", "nista", 25);

INSERT INTO `private_account_in_company`(`id`, `approved`,`company`, `owner` )VALUES(1, true, 5, 9);
INSERT INTO `private_account_in_company`(`id`, `approved`,`company`, `owner` )VALUES(2, false, 5, 4);
INSERT INTO `private_account_in_company`(`id`, `approved`,`company`, `owner` )VALUES(3, false, 5, 6);

-- real estate
INSERT INTO `kts_project_test`.`real_estate` (`id`, `area`, `bathrooms_number`, `construction_year`, `description`, `furniture`, `heating_type`, `image_url`, `parking`, `rooms_number`, `rs_type`, `address`, `owner`) VALUES (2, 0, 1, 'adsf', 'xfbnz', true, 'CENTRAL', 'images/img1.jpg', true, 1, "HOUSE", 1, 4);

-- advertisement
INSERT INTO `advertisement`(`id`,`announcement_date`,`currency`,`ending_date`,`phone_number`,`price`,`state`,`title`,`type`,`update_date`,`owner`,`real_estate`)VALUES(1,"01.01.2017","RSD","01.06.2017","021400198","10000","WAITING","Add title","RENT","02.02.2018",4,2);

-- verifier report
INSERT INTO `kts_project_test`.`verifier_report` (`id`, `banning_reason`, `date`, `description`, `advertisement`, `verifier`) VALUES ('1', 'SOLD', '01.01.2000', 'smaras', '1', '3');
