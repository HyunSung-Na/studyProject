DROP TABLE IF EXISTS users CASCADE;

--- mariadb
CREATE TABLE study_infinite.users (
                                         seq bigint PRIMARY KEY auto_increment,
                                         nickname varchar(50) NOT NULL UNIQUE,
                                         email varchar(50) NOT NULL UNIQUE,
                                         password varchar(255) NOT NULL,
                                         login_count int NOT NULL default 0,
                                         create_at datetime default now() NOT null,
                                         last_login_at datetime default now()
);

CREATE TABLE study_infinite.posts (
                                               seq bigint auto_increment PRIMARY KEY,
                                               user_seq bigint NOT NULL,
                                               contents varchar(500) NOT NULL,
                                               like_count int NOT NULL DEFAULT 0,
                                               comment_count int NOT NULL DEFAULT 0,
                                               create_at DATETIME DEFAULT now() NOT null,
                                               title varchar(100) NOT NULL
);

CREATE TABLE study_infinite.post_comments (
                                             seq bigint auto_increment primary key,
                                             user_seq bigint NOT NULL,
                                             post_seq bigint NOT NULL,
                                             contents varchar(500) NOT NULL,
                                             create_at datetime default now() NOT null
);

CREATE TABLE study_infinite.likes (
                                          seq bigint auto_increment primary key,
                                          user_seq bigint NOT NULL,
                                          post_seq bigint NOT NULL,
                                          create_at datetime default now() NOT null
);

CREATE TABLE study_infinite.study (
                                          seq bigint auto_increment primary key,
                                          user_seq bigint NOT NULL,
                                          title varchar(100) NOT NULL,
                                          shortDescription varchar(100) NOT NULL,
                                          fullDescription varchar(1000) NOT NULL,
                                          publishDateTime datetime default now() NOT null,
                                          zones varchar(50) NOT NULL
);

CREATE TABLE study_infinite.study_comment (
                                        seq bigint auto_increment primary key,
                                        user_seq bigint NOT NULL,
                                        study_seq bigint NOT NULL,
                                        comments varchar(500) NOT NULL,
                                        create_at datetime default now() NOT null
);

ALTER table study_infinite.posts add foreign key (user_seq) references users(seq) ON DELETE RESTRICT ON UPDATE RESTRICT
;
ALTER table study_infinite.post_comments add foreign key (user_seq) references users(seq) ON DELETE RESTRICT ON UPDATE RESTRICT
;
ALTER table study_infinite.post_comments add foreign key (post_seq) references posts(seq) ON DELETE CASCADE ON UPDATE CASCADE
;
ALTER table study_infinite.likes add foreign key (user_seq) references users(seq) ON DELETE RESTRICT ON UPDATE RESTRICT
;
ALTER table study_infinite.likes add foreign key (post_seq) references posts(seq) ON DELETE CASCADE ON UPDATE CASCADE
;
ALTER table study_infinite.study add foreign key (user_seq) references users(seq) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER table study_infinite.study_comment add foreign key (user_seq) references users(seq) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER table study_infinite.study_comment add foreign key (study_seq) references study(seq) ON DELETE CASCADE ON UPDATE CASCADE
;