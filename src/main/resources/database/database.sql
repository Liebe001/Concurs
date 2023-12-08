CREATE TABLE dance (id SERIAL PRIMARY KEY,
                    name varchar(128));

CREATE TABLE institutie(id SERIAL PRIMARY KEY,
                        denumire varchar(255),
                        locatie varchar(255));



CREATE TABLE persons(id SERIAL PRIMARY KEY,
                     first_name varchar(128),
                     last_name varchar(128),
                     sex  varchar(32),
                     birth_day date,
                     contact varchar(128),
                     id_dance int ,
                     id_institutie int,
                     CONSTRAINT fk_persons_dance FOREIGN KEY(id_dance)
                         REFERENCES dance (id),
                     CONSTRAINT fk_persons_institutie FOREIGN KEY (id_institutie)
                         REFERENCES institutie(id)
);
Create TABLE users (id serial primary key,
                    username varchar(32),
                    email varchar(128),
                    password varchar(255)
);

INSERT INTO dance (name) VALUES ('Ballet');
INSERT INTO dance (name) VALUES ('Hip-Hop');
INSERT INTO dance (name) VALUES ('Contemporary');
INSERT INTO dance (name) VALUES ('Salsa');
INSERT INTO dance (name) VALUES ('Ballroom');
INSERT INTO dance (name) VALUES ('Tap');
INSERT INTO dance (name) VALUES ('Breakdance');
INSERT INTO dance (name) VALUES ('Jazz');
INSERT INTO dance (name) VALUES ('Flamenco');
INSERT INTO dance (name) VALUES ('Irish Step');


INSERT INTO institutie (denumire, locatie) VALUES ('Dance Academy of New York', 'New York, USA');
INSERT INTO institutie (denumire, locatie) VALUES ('London Dance Institute', 'London, UK');
INSERT INTO institutie (denumire, locatie) VALUES ('Los Angeles Dance Center', 'Los Angeles, USA');
INSERT INTO institutie (denumire, locatie) VALUES ('Paris School of Dance', 'Paris, France');
INSERT INTO institutie (denumire, locatie) VALUES ('Sydney Dance Academy', 'Sydney, Australia');


INSERT INTO persons (first_name, last_name, sex, birth_day, contact, id_dance, id_institutie)
VALUES ('John', 'Smith', 'Male', '1990-05-15', 'john@example.com', 1, 1);

INSERT INTO persons (first_name, last_name, sex, birth_day, contact, id_dance, id_institutie)
VALUES ('Emily', 'Johnson', 'Female', '1988-08-20', 'emily@example.com', 3, 2);

INSERT INTO persons (first_name, last_name, sex, birth_day, contact, id_dance, id_institutie)
VALUES ('Michael', 'Brown', 'Male', '1995-03-10', 'michael@example.com', 2, 3);

INSERT INTO persons (first_name, last_name, sex, birth_day, contact, id_dance, id_institutie)
VALUES ('Sophia', 'Davis', 'Female', '1992-11-25', 'sophia@example.com', 5, 4);

INSERT INTO persons (first_name, last_name, sex, birth_day, contact, id_dance, id_institutie)
VALUES ('David', 'Wilson', 'Male', '1987-06-30', 'david@example.com', 6, 5);

CREATE TABLE users (id serial PRIMARY KEY ,
                    username varchar(32),
                    email varchar(128),
                    password varchar(255)
);