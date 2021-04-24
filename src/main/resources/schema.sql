CREATE TABLE organization (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    inn VARCHAR(10) NOT NULL,
    kpp VARCHAR(9) NOT NULL,
    address VARCHAR(100) NOT NULL,
    phone VARCHAR(11),
    is_active BIT,
);

CREATE TABLE office (
    id INT AUTO_INCREMENT PRIMARY KEY,
    org_id INT NOT NULL,
    name VARCHAR(30) NOT NULL,
    address VARCHAR(100) NOT NULL,
    phone VARCHAR(11),
    is_active BIT,
    FOREIGN KEY(org_id) REFERENCES organization(id)
);

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    second_name VARCHAR(30),
    middle_name VARCHAR(30),
    position VARCHAR(30) NOT NULL,
    phone VARCHAR(11),
    is_identified BIT,
    document_id INT,
    office_id INT,
    doc_id INT,
    country_id INT,
    FOREIGN KEY(document_id) REFERENCES document(id),
    FOREIGN KEY(office_id) REFERENCES office(id),
    FOREIGN KEY(doc_id) REFERENCES doc(id),
    FOREIGN KEY(country_id) REFERENCES country(id)
);

CREATE TABLE document (
    id INT AUTO_INCREMENT PRIMARY KEY,
    document_name VARCHAR (50),
    document_number VARCHAR (30),
    document_date DATE
);

CREATE TABLE doc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    doc_name VARCHAR (50),
    doc_code VARCHAR (10),
);

CREATE TABLE country (
    id INT AUTO_INCREMENT PRIMARY KEY,
    country_name VARCHAR (50),
    country_code VARCHAR (10),
);

