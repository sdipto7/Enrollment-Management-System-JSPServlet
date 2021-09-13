CREATE TABLE course(
    id INT NOT NULL AUTO_INCREMENT,
    course_title VARCHAR(100)
    course_code VARCHAR(7),
    PRIMARY KEY("id")
);

CREATE TABLE user(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    PRIMARY KEY("id")
);

CREATE TABLE enrollment(
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    course_id INT NOT NULL,
    PRIMARY KEY("id")
);

CREATE TABLE credential (
  id INT NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(100) NOT NULL,
  password VARCHAR(45) NOT NULL,
  role VARCHAR(10) NOT NULL,
  PRIMARY KEY ("id")
);
