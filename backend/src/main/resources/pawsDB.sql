DROP DATABASE IF EXISTS pawsDB;
CREATE DATABASE pawsDB;

USE pawsDB;

CREATE TABLE owners (
    id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) DEFAULT 'Anonymous', # prevent all null row
    phone VARCHAR(20),
    email VARCHAR(50)
);

CREATE TABLE animals (
    id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50), # can be a nameless person
    nametag BOOLEAN,
    species VARCHAR(50),
    breed VARCHAR(50),
    female BOOLEAN,
    height FLOAT,
    weight INT,
    age INT,
    `status` INT NOT NULL, # 1 found, 2 lost, 3 resolved
    `hash` VARCHAR(128) NOT NULL,
    `description` VARCHAR(200),
    image VARCHAR(200) DEFAULT '/images/default.jpg', # stock default
    ownerId INT NOT NULL,
    CONSTRAINT animals_owners_fk
        FOREIGN KEY (ownerId) REFERENCES owners(id)
);

CREATE TABLE locations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    latitude FLOAT NOT NULL, # user can not input this and google api can find lat/long for address or address for lat/long
    longitude FLOAT NOT NULL,
    # PRIMARY KEY(latitude, longitude),
    `name` VARCHAR(50),
    address VARCHAR(150) NOT NULL, # maybe service layer to decide conflicts with overlaps / defaults
    `description` VARCHAR(200)
);

CREATE TABLE sightings (
    id INT PRIMARY KEY AUTO_INCREMENT,
    animalId INT NOT NULL,
    locationId INT NOT NULL,
    `date` DATE NOT NULL,
    CONSTRAINT sightings_animal_fk
        FOREIGN KEY (animalId) REFERENCES animals(id),
    CONSTRAINT sightings_location_fk
        FOREIGN KEY (locationId) REFERENCES locations(id)
);

INSERT INTO owners(name, phone, email) VALUES
("Miss Crawshank", "1-305-666-6666", "hushpuppy@hotmail.com"),
("Mr. Nobody", "1-305-644-6644", "nobody123@yahoo.com"),
("Cooler", "4-489-484-3333", "idestroy@gmail.com");


INSERT INTO animals(name, nametag, species, breed, female, height, weight, age, status, hash, description, image, ownerId) 
VALUES
("Cookie", false, "poodle", "", true, 35, 57, 8, 1, '9484984948948498494h9mum8mu94m9484984u9494h', 'fluffy poodle', 'poodle.jpg', 1),
("Bookie", true, "shitsu", "", false, 31, 27, 2, 0, '9484984948948498494h9mum8meueue84984u9494h', 'fluffy shitsu', 'shitsu.jpg', 2),
("Sookie", true, "boxer", "", false, 42, 42, 12, 2, '948498494894oeoeuoaaauui8meueue84984u9494h', 'big boi', 'boxer.jpg', 3);

INSERT INTO locations (
	name,
    address,
    longitude,
    latitude,
    description)
	VALUES
	("Space","None of them",58.060417,-149.3586,"better screaming than into a pillow"),
    ("Earth","All of them (very helpful)",-56.22336,168.54247,"mostly harmless"),
    ("Kent Farm Barn","34 Country Rd Takmeom, W. VA 00000",80.08563,-160.44519,"superboy's main hang"),
    ("Olympus","Greece?",38.297634,-75.22866,"big mountain, seat of universal power, invisible: can't miss it");
    
INSERT INTO sightings (
	animalId,
    locationId,
    date
)
VALUES
	(1,3,"2021-06-01 00:00:00.000000"),
    (2,2,"2021-05-31 00:00:00.000000"),
    (3,1,"2021-01-01 00:00:00.000000");



