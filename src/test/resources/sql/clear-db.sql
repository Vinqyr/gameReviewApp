DELETE FROM review where id>0;
DELETE FROM game where id>0;
ALTER TABLE game ALTER column id RESTART;
ALTER TABLE review ALTER column id RESTART;
