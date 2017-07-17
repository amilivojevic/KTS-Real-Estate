
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
--INSERT INTO `user`(`id`,`account_number`,`address`,`birth_date`,`city`,`country`,`email`,`image_url`,`name`,`password`,`phone_number`,`role`,`surname`,`username`,`authority_id`)VALUES(2,0000,"Bul. Oslobodjenja 1","01.01.2000.","Novi Sad","Serbia","default@gmail.com","images/img1.jpg","verifyer default","$2a$10$SmLd5yKXvmiPdb03xmDoJO.0O/urG3vLsYT5W2MB5UadLCr8V7BoG","021 111 111","VERIFYER","verifyer","ver",3);