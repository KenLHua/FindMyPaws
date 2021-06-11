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
    `name` VARCHAR(50),
    nametag BOOLEAN,
    species VARCHAR(50),
    breed VARCHAR(50),
    color VARCHAR(15),
    female BOOLEAN,
    height FLOAT,
    weight INT,
    age INT,
    date DATE,
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


INSERT INTO animals(name, color, date, nametag, species, breed, female, height, weight, age, status, hash, description, image, ownerId) 
VALUES
("Cookie", "blue", "2021-06-01", false, "dog", "poodle", true, 35, 57, 8, 1, '9484984948948498494h9mum8mu94m9484984u9494h', 'fluffy poodle', 'poodle.jpg', 1),
("Bookie", "green", "2021-06-01", true, "dog", "shitsu", false, 31, 27, 2, 0, '9484984948948498494h9mum8meueue84984u9494h', 'fluffy shitsu', 'shitsu.jpg', 2),
("Sookie", "brown", "2021-06-01", true, "cat", "coon", false, 42, 42, 12, 2, '948498494894oeoeuoaaauui8meueue84984u9494h', 'big boi', 'boxer.jpg', 3);

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
    (2,3,"2021-05-31 00:00:00.000000"),
    (3,3,"2021-01-01 00:00:00.000000"),
    (3, 3, "2021-06-07");



INSERT INTO locations(name, latitude, longitude, address, description)
VALUES
("Chicago", 41.8818,	-87.6231, "Chicago, IL, USA", "Cool city where the cool kids work"),
("Schaumburg", 		42.033,	-88.083, "Schaumburg, IL, USA", "Near chicago"),
("Dude's apartment", 		41.908409,	-87.648003, "1457 N Halsted St, Chicago, IL 60642", "Random apt"),
("Dude's apartment 2", 		41.871590,	-87.624298, "808 S Michigan Ave, Chicago, IL 60605", "Near grant Park"),
("Dude's apartment 3", 		41.629620,	-87.564080, "250 Yates Ave, Calumet City, IL 60409", "South side Chicago"),
("Chicago Spot 1", 		41.711880,	-87.858765, "", "Middle side Chicago"),
("Plainfield", 		41.635973,	-88.243286, "", "Middle side Chicago"),
("Geneva", 		41.875696,	-88.311951, "", "Convention?"),
("Wheaton", 		41.849514,	-88.086731, "", "Whole wheaton, its healthy"),
("Milwaukee", 		43.022722,	-87.916992, "", "Its a city i guess"),
("Water", 		41.863425,	-87.508301, "", "Walking on water"),
("Palatine", 		42.114525,	-88.036537, "Palatine, IL, USA", "Near chicago"),
("Beijing", 			39.913818,	116.363625, "Beijing, China", "China's capital"),
("Tampa", 27.964157, 	-82.452606, "Tampa,FL,USA", "Lmao Will gonna work here"),
("NY", 40.730610, 		-73.935242, "NY,NY,USA", "City that never sleeps"),
("Los Angeles", 	34.0522,	-118.243, "LA, CA, USA", "Calfiornia time"),
("Sydney", 	-33.865143,	151.209900, "Sydney,NSW, Australia", "Where the kangaroos live"),
("Seoul", 	37.532600,		127.024612, "Seoul, Korea", "Kpop city"),
("North Korea", 	39.019444,		125.738052, "North Korea", "Your pet is gone"),
("New Zealand", 	-36.848461,	174.763336, "LA, CA, USA", "COVID free");


INSERT INTO owners(name, phone, email)
VALUES
("Kentito" , "28002564443",	"Kentito@email.com"),
("Will" , "",	"Will@gmail.com"),
("Tyler" , "",	"Tyler@miami.com"),
("Lester" , "123",	"Lester@time.com"),
("Daniel" , "911",	"TolentinoBoy@gmail.com"),
("Ken" , "18002564444",	"Ken@email.com");

INSERT INTO animals(name, color, date, nametag, species, breed, female, height, weight, age, status, hash, description, image, ownerId)
VALUES
("Zookie", "Color", "2021-06-11", false, "dog", "poodle", true, 40, 20, 4, 1, '123', 'scrunchy poodle', 'poodle.jpg', 4),
("Lookie", "Color", "2021-06-06", false, "dog", "poodle", true, 10, 10, 3, 1, '123', 'curly poodle', 'poodle.jpg', 5),
("Pookie", "Brown", "2021-06-07", false, "dog", "poodle", true, 20, 15, 2, 2, '123', 'poopie poodle', 'poodle.jpg', 6),
("Rookie", "Color", "2021-06-08", false, "dog", "poodle", true, 30, 20, 5, 2, '123', 'new poodle', 'poodle.jpg', 7),
("Mookie", "Color", "2021-06-05", false, "dog", "poodle", true, 10, 5, 1, 1, '123', 'cowy poodle', 'poodle.jpg', 8),
("Dookie", "Brown", "2021-06-09", false, "dog", "poodle", true, 20, 10, 1, 2, '123', 'nasty poodle', 'poodle.jpg', 9);

INSERT INTO sightings (
	animalId,
    locationId,
    date
)
VALUES
	(1,10,"2021-06-01 00:00:00.000000"),
    (2,16,"2021-06-01 00:00:00.000000"),
    (3,9,"2021-06-01 00:00:00.000000"),
    (4,3,"2021-06-01 00:00:00.000000"),
    (5,5,"2021-05-31 00:00:00.000000"),
    (5,7,"2021-06-01 00:00:00.000000"),
    (5,8,"2021-06-02 00:00:00.000000"),
    (6,6,"2021-05-20 00:00:00.000000"),
    (1,10,"2021-06-01 00:00:00.000000"),
    (7, 3, "2021-06-07"),
    (8, 11, "2021-06-07");