
INSERT INTO `location` (`id`, `city`, `city_area`, `has_lat_long`, `latitude`, `longitude`, `state`, `street`, `street_number`, `zip_code`) VALUES (1, 'Novi Sad', NULL, '\0', 1, 1, 'Serbia', 'Mileve Maric', '52', NULL);
INSERT INTO `location` (`id`, `city`, `city_area`, `has_lat_long`, `latitude`, `longitude`, `state`, `street`, `street_number`, `zip_code`) VALUES (2, 'Novi Sad', NULL, '\0', 44.77403648591521, 20.43680191040039, 'Serbia', NULL, NULL, NULL);
INSERT INTO `location` (`id`, `city`, `city_area`, `has_lat_long`, `latitude`, `longitude`, `state`, `street`, `street_number`, `zip_code`) VALUES (3, 'Dubai', NULL, '\0', 0, 0, '', NULL, NULL, NULL);
INSERT INTO `location` (`id`, `city`, `city_area`, `has_lat_long`, `latitude`, `longitude`, `state`, `street`, `street_number`, `zip_code`) VALUES (4, 'Dubai', NULL, '\0', 0, 0, NULL, NULL, NULL, NULL);

insert into authority (name) values ('ROLE_ADMIN');
insert into authority (name) values ('ROLE_OWNER');
insert into authority (name) values ('ROLE_VERIFYER');