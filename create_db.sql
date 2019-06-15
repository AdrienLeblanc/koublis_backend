CREATE TABLE WINE (
    id INT PRIMARY KEY NOT NULL,
    appellation VARCHAR(100),
    nomChateau VARCHAR(100),
    type VARCHAR(100),
    millesime INT,
    nbBouteillesAchetees INT,
    destockage INT,
    nbBouteillesStock INT
)