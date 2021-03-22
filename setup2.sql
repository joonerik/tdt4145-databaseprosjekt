DROP SCHEMA `database`;

CREATE SCHEMA `database` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

CREATE TABLE IF NOT EXISTS User1 (
	Mail VARCHAR(50) NOT NULL,
	UserPassword VARCHAR(30),
	CONSTRAINT Mail_PK PRIMARY KEY (Mail)
);

CREATE TABLE IF NOT EXISTS Course (
    CourseID int NOT NULL AUTO_INCREMENT,
    Coursename VARCHAR(10),
    Term VARCHAR(3),
    CONSTRAINT CourseID_PK PRIMARY KEY (CourseID)
);

CREATE TABLE IF NOT EXISTS AttendingCourse (
	Mail VARCHAR(50) NOT NULL,
    CourseID int NOT NULL,
    CourseRole VARCHAR(30),
    CONSTRAINT AttendingCourse_PK PRIMARY KEY (Mail, CourseID),
    CONSTRAINT Mail_FK FOREIGN KEY (Mail) REFERENCES User1(Mail)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
    CONSTRAINT AttendingCourse_FK FOREIGN KEY (CourseID) REFERENCES Course(CourseID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Folder (
    CourseID int NOT NULL,
    FolderName VARCHAR(30) NOT NULL,
    CONSTRAINT Folder_PK PRIMARY KEY (CourseID, FolderName),
    CONSTRAINT Folder_FK FOREIGN KEY (CourseID) REFERENCES Course(CourseID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Subfolder (
	CourseID int NOT NULL, 
    ParentName VARCHAR(30) NOT NULL,
    SubfolderName VARCHAR(30),
    CONSTRAINT Subfolder_PK PRIMARY KEY (CourseID, SubfolderName),
    CONSTRAINT Subfolder_FK FOREIGN KEY (CourseID, ParentName) REFERENCES Folder(CourseID, FolderName)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Thread (
    CourseID int NOT NULL,
    ThreadNo int NOT NULL,
    Tag VARCHAR(30),
    ColorCode VARCHAR(30),
    CONSTRAINT Thread_PK PRIMARY KEY (CourseID, ThreadNo),
    CONSTRAINT Thread_FK FOREIGN KEY (CourseID) REFERENCES Course(CourseID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ThreadInFolder (
    CourseID int NOT NULL,
    ThreadNo int NOT NULL,
    FolderName VARCHAR(30) NOT NULL,
    CONSTRAINT ThreadInFolder_PK PRIMARY KEY (CourseID, ThreadNo, FolderName),
    CONSTRAINT ThreadInFolder_FK1 FOREIGN KEY (CourseID, FolderName) REFERENCES Folder(CourseID, FolderName)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
    CONSTRAINT ThreadInFolder_FK2 FOREIGN KEY (CourseID, ThreadNo) REFERENCES Thread(CourseID, ThreadNo)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Post (
    CourseID int NOT NULL,
    ThreadNo int NOT NULL,
    PostNo int NOT NULL,
    PostText VARCHAR(300),
    Mail VARCHAR(50) NOT NULL,
    PostType VARCHAR(30),
    CONSTRAINT Post_PK PRIMARY KEY (CourseID, ThreadNo, PostNo),
    CONSTRAINT Post_FK1 FOREIGN KEY (Mail) REFERENCES User1(Mail)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
    CONSTRAINT Post_FK2 FOREIGN KEY (CourseID, ThreadNo) REFERENCES Thread(CourseID, ThreadNo)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS OriginalPost (
    CourseID int NOT NULL,
    ThreadNo int NOT NULL,
    PostNo int NOT NULL,
    Title VARCHAR(100),
    CONSTRAINT Post_PK PRIMARY KEY (CourseID, ThreadNo, PostNo),
    CONSTRAINT Post_FK FOREIGN KEY (CourseID, ThreadNo, PostNo) REFERENCES Post(CourseID, ThreadNo, PostNo)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ReplyTo (
    ReplyCourseID int NOT NULL,
    ReplyThreadNo int NOT NULL,
    ReplyPostNo int NOT NULL,
    FollowupCourseID int NOT NULL,
    FollowupThreadNo int NOT NULL,
    FollowupPostNo int NOT NULL,
    CONSTRAINT ReplyTo_PK PRIMARY KEY (ReplyCourseID, ReplyThreadNo, ReplyPostNo),
    CONSTRAINT ReplyTo_FK1 FOREIGN KEY (ReplyCourseID, ReplyThreadNo, ReplyPostNo) REFERENCES Post(CourseID, ThreadNo, PostNo)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
    CONSTRAINT ReplyTo_FK2 FOREIGN KEY (FollowupCourseID, FollowupThreadNo, FollowupPostNo) REFERENCES Post(CourseID, ThreadNo, PostNo)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS HasLiked (
    Mail VARCHAR(50) NOT NULL,
    CourseID int NOT NULL,
    ThreadNo int NOT NULL,
    PostNo int NOT NULL,
    CONSTRAINT HasLiked_PK PRIMARY KEY (Mail, CourseID, ThreadNo, PostNo),
    CONSTRAINT HasLiked_FK1 FOREIGN KEY (Mail) REFERENCES User1(Mail)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
    CONSTRAINT HasLiked_FK2 FOREIGN KEY (CourseID, ThreadNo, PostNo) REFERENCES Post(CourseID, ThreadNo, PostNo)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS HasRead (
    Mail VARCHAR(50) NOT NULL,
    CourseID int NOT NULL,
    ThreadNo int NOT NULL,
    PostNo int NOT NULL,
    CONSTRAINT HasRead_PK PRIMARY KEY (Mail, CourseID, ThreadNo, PostNo),
    CONSTRAINT HasRead_FK1 FOREIGN KEY (Mail) REFERENCES User1(Mail)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
    CONSTRAINT HasRead_FK2 FOREIGN KEY (CourseID, ThreadNo, PostNo) REFERENCES Post(CourseID, ThreadNo, PostNo)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

INSERT INTO User1 VALUES ("martin@hotmail.com", "martin"), ("anders@hotmail.com", "anders"), ("joon@hotmail.com", "joon");

INSERT INTO Course VALUES (1, "TDT4147", "V21"), (2, "OKO1001", "H20");

INSERT INTO AttendingCourse VALUES ("martin@hotmail.com", 2, "instructor"), ("joon@hotmail.com", 1, "student"), ("anders@hotmail.com", 1, "student"), ("martin@hotmail.com", 1, "student");

INSERT INTO Folder VALUES (1, "exam answers"), (1, "project");

INSERT INTO Subfolder VALUES (1, "exam answers", "ordinary 2018"), (1, "exam answers", "LF2022"), (1, "project", "part1");

-- INSERT INTO Thread VALUES (1, 1, "Q", "red"), (1, 2, "A", "green");

-- INSERT INTO ThreadInFolder VALUES (1, 1, "exam answers"), (1, 2, "project");

-- INSERT INTO Post VALUES (1, 1, 1, "Give me answer pls", "martin@hotmail.com", "OriginalPost"),
-- 							(1, 2, 1, "Look how good I am", "anders@hotmail.com", "OriginalPost"),
-- 							(1, 2, 2, "Hahahah u virgin", "joon@hotmail.com", "InstructorAnswer");

-- INSERT INTO OriginalPost VALUES (1, 1, 1, "I need help"), (1, 2, 1, "My ER diagram is so good lmao");

-- INSERT INTO ReplyTo VALUES (1, 1, 1, 1, 1, 1);

-- INSERT INTO HasLiked VALUES ("martin@hotmail.com", 1, 1, 1), ("anders@hotmail.com", 1, 1, 1);

-- INSERT INTO HasRead VALUES ("martin@hotmail.com", 1, 1, 1), ("anders@hotmail.com", 1, 1, 1), ("joon@hotmail.com", 1, 1, 1),("joon@hotmail.com", 1, 2, 1);