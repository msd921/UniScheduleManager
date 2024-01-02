CREATE TABLE IF NOT EXISTS teachers (
   id SERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL,
   surname VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS courses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(300) NOT NULL,
    teacher_id INT NOT NULL,
    FOREIGN KEY(teacher_id) REFERENCES teachers(id)	
);

CREATE TABLE IF NOT EXISTS groups (
   id SERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS course_group (
   course_id INT,
   group_id INT,
   PRIMARY KEY (course_id, group_id),
   FOREIGN KEY (course_id) REFERENCES courses(id),
   FOREIGN KEY (group_id) REFERENCES groups(id)
);

CREATE TABLE IF NOT EXISTS students (
   id SERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL,
   surname VARCHAR(50) NOT NULL,
   group_id INT NOT NULL,
   FOREIGN KEY(group_id) REFERENCES groups(id)
);

CREATE TABLE IF NOT EXISTS schedules (
   id SERIAL PRIMARY KEY,
   date TIMESTAMPTZ NOT NULL,
   course_id INT NOT NULL,
   group_id INT NOT NULL,
   FOREIGN KEY(course_id) REFERENCES courses(id),
   FOREIGN KEY(group_id) REFERENCES groups(id)
);

INSERT INTO teachers(name, surname) VALUES
   ('John','Smith'),
   ('Amanda','White'),
   ('Jonny','Cage');

INSERT INTO courses(name, description, teacher_id) VALUES
   ('Mathematics','Mathematics is an area of knowledge that includes the topics of numbers, formulas and related structures, shapes and the spaces in which they are contained, and quantities and their changes.',1),
   ('Physics','Physics is the natural science of matter, involving the study of matter, its fundamental constituents, its motion and behavior through space and time, and the related entities of energy and force.',2);

INSERT INTO groups(name) VALUES
   ('CD-1'),
   ('GY-187');

INSERT INTO students(name,surname, group_id) VALUES
   ('Jeremy','Obrien', 1),
   ('Alex','White', 1),
   ('Richard','Klaus', 2),
   ('Michael','Jackson', 2),
   ('Vanessa','Mccoy', 2);

INSERT INTO course_group (course_id, group_id) VALUES
   (1,1),
   (2,2);

INSERT INTO schedules(date,course_id,group_id) VALUES
	('2023-06-22 8:10', 1,1),
        ('2023-07-22 11:10', 2,2);