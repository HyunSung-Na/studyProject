INSERT INTO users(seq,name,email,password) VALUES (null,'tester00','test00@gmail.com','$2a$10$mzF7/rMylsnxxwNcTsJTEOFhh1iaHv3xVox.vpf6JQybEhE4jDZI.');
INSERT INTO users(seq,name,email,password) VALUES (null,'tester01','test01@gmail.com','$2a$10$Mu/akK4gI.2RHm7BQo/kAO1cng2TUgxpoP.zBbPOeccVGP4lKVGYy');
INSERT INTO users(seq,name,email,password) VALUES (null,'tester02','test02@gmail.com','$2a$10$hO38hmoHN1k7Zm3vm95C2eZEtSOaiI/6xZrRAx8l0e78i9.NK8bHG');

INSERT INTO posts(seq,user_seq,contents,like_count,comment_count,create_at,title) VALUES (null,1,'test01 first post',1,1,'2019-03-01 13:10:00','test01');
INSERT INTO posts(seq,user_seq,contents,like_count,comment_count,create_at,title) VALUES (null,1,'test01 second post',0,0,'2019-03-12 09:45:00','test02');
INSERT INTO posts(seq,user_seq,contents,like_count,comment_count,create_at,title) VALUES (null,1,'test01 third post',0,0,'2019-03-20 19:05:00','test03');
INSERT INTO posts(seq,user_seq,contents,like_count,comment_count,create_at,title) VALUES (null,2,'test02 post',0,1,'2019-03-20 15:13:20','test04');

INSERT INTO post_comments(seq,user_seq,post_seq,contents,create_at) VALUES (null,1,1,'first comment','2019-03-01 13:15:00');
INSERT INTO post_comments(seq,user_seq,post_seq,contents,create_at) VALUES (null,2,4,'first comment','2019-03-01 13:15:00');

INSERT INTO likes(seq,user_seq,post_seq,create_at) VALUES (null,1,1,'2019-03-01 15:10:00');

INSERT INTO study(seq,user_seq,title,shortDescription,fullDescription,publishDateTime,zones) VALUES (null,1,'first study','test01 first post','first study seoul','2019-03-01 13:10:00','seoul');
INSERT INTO study(seq,user_seq,title,shortDescription,fullDescription,publishDateTime,zones) VALUES (null,1,'second study','test01 second post','second study busan','2019-03-12 09:45:00','busan');
INSERT INTO study(seq,user_seq,title,shortDescription,fullDescription,publishDateTime,zones) VALUES (null,1,'third study','test01 third post','third study buchen','2019-03-20 19:05:00','buchen');
INSERT INTO study(seq,user_seq,title,shortDescription,fullDescription,publishDateTime,zones) VALUES (null,1,'first study','test02 post','my first study gangnam','2019-03-01 13:10:00','gangnam');

INSERT INTO study_comment(seq,user_seq,study_seq,comments,create_at) VALUES (null,1,1,'first comment','2019-03-01 13:15:00');
INSERT INTO study_comment(seq,user_seq,study_seq,comments,create_at) VALUES (null,2,4,'first comment','2019-03-01 13:15:00');
