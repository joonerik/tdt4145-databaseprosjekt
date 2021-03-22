INSERT INTO User1 VALUES ("student0@hotmail.com", "Student_0"), ("student1@hotmail.com", "Student_1"), ("student2@hotmail.com", "Student_2"), ("student3@hotmail.com", "Student_3"), ("teacher@hotmail.com", "teacherpassword");

INSERT INTO Course VALUES (1, "TDT4147", "V21"), (2, "OKO1001", "H20");

INSERT INTO AttendingCourse VALUES ("student0@hotmail.com", 2, "instructor"), ("student2@hotmail.com", 1, "student"), ("student1@hotmail.com", 1, "student"), ("student0@hotmail.com", 1, "student");

INSERT INTO Folder VALUES (1, "Exam"), (1, "Project");

INSERT INTO Subfolder VALUES (1, "Exam", "ordinary 2018"), (1, "Exam", "LF2022"), (1, "Project", "part1");

INSERT INTO Thread VALUES (1, 1, "Q", "red"), (1, 2, "A", "green");

INSERT INTO ThreadInFolder VALUES (1, 1, "Exam"), (1, 2, "Project");

INSERT INTO Post VALUES (1, 1, 1, "Random text", "student0@hotmail.com", "OriginalPost"),
							(1, 2, 1, "Random text with WAL somewhere", "student1@hotmail.com", "OriginalPost"),
							(1, 2, 2, "Random text again", "student2@hotmail.com", "InstructorAnswer");

INSERT INTO OriginalPost VALUES (1, 1, 1, "I need help"), (1, 2, 1, "My ER diagram is so good lmao");

INSERT INTO ReplyTo VALUES (1, 1, 1, 1, 1, 1);

INSERT INTO HasLiked VALUES ("student0@hotmail.com", 1, 1, 1), ("student1@hotmail.com", 1, 1, 1);

INSERT INTO HasRead VALUES ("student0@hotmail.com", 1, 1, 1), ("student1@hotmail.com", 1, 1, 1), ("student2@hotmail.com", 1, 1, 1),("student2@hotmail.com", 1, 2, 1);
