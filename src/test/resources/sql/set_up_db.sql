DELETE FROM review where id>0;
DELETE FROM game where id>0;
ALTER TABLE game ALTER column id RESTART;
ALTER TABLE review ALTER column id RESTART;


INSERT INTO game (name, developer, genre, release_date)
values ('DOS2','LARIAN','HORROR','2017-9-17');
INSERT INTO game (name, developer, genre, release_date)
values ('Portal 2','VOLVO','MOBA','2011-4-18');
INSERT INTO game (name, developer, genre, release_date)
values ('Nioh','Team NINJA','ACTION','2017-11-7');
