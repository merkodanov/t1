--changeset merkodanov:1
insert into client (first_name, middle_name, last_name) values ('Maddie', 'Gregorius', 'Hillum');
insert into client (first_name, middle_name, last_name) values ('Mario', 'Murielle', 'Lyfield');
insert into client (first_name, middle_name, last_name) values ('Ade', 'Ashly', 'Leggott');
insert into client (first_name, middle_name, last_name) values ('Silvanus', 'Donna', 'Falkus');
insert into client (first_name, middle_name, last_name) values ('Vachel', 'Zuzana', 'Truluck');
insert into client (first_name, middle_name, last_name) values ('Dulcinea', 'Theressa', 'Duffil');
insert into client (first_name, middle_name, last_name) values ('Tome', 'Monica', 'Vedikhov');
insert into client (first_name, middle_name, last_name) values ('Cicely', 'Myriam', 'Klewi');
insert into client (first_name, middle_name, last_name) values ('Anders', 'Xaviera', 'Menlow');
insert into client (first_name, middle_name, last_name) values ('Opalina', 'Jehanna', 'Doding');
insert into client (first_name, middle_name, last_name) values ('Sadella', 'Moselle', 'Hamon');
insert into client (first_name, middle_name, last_name) values ('Sunshine', 'Shellysheldon', 'Peidro');
insert into client (first_name, middle_name, last_name) values ('Shari', 'Shaun', 'Greener');
insert into client (first_name, middle_name, last_name) values ('Ellie', 'Carol-jean', 'Hallgath');
insert into client (first_name, middle_name, last_name) values ('Abrahan', 'Westbrook', 'Greneham');
insert into client (first_name, middle_name, last_name) values ('Redford', 'Nerita', 'Stollenberg');
insert into client (first_name, middle_name, last_name) values ('Lavina', 'Sadie', 'MacCracken');
insert into client (first_name, middle_name, last_name) values ('Lyn', 'Celina', 'Lacer');
insert into client (first_name, middle_name, last_name) values ('Elmira', 'Charisse', 'Tidder');
insert into client (first_name, middle_name, last_name) values ('Vicki', 'Arleen', 'Fennell');
insert into client (first_name, middle_name, last_name) values ('Mitzi', 'Oriana', 'Klehyn');
insert into client (first_name, middle_name, last_name) values ('Vergil', 'Maxim', 'Niess');
insert into client (first_name, middle_name, last_name) values ('Ariel', 'Silvester', 'Shemwell');
insert into client (first_name, middle_name, last_name) values ('Tessi', 'Kore', 'Castagnaro');
insert into client (first_name, middle_name, last_name) values ('Fabiano', 'Eartha', 'Craigheid');
insert into client (first_name, middle_name, last_name) values ('Winny', 'Erna', 'Silcocks');
insert into client (first_name, middle_name, last_name) values ('Bendite', 'Kelsy', 'Caudray');
insert into client (first_name, middle_name, last_name) values ('Lorant', 'Nannette', 'Jump');
insert into client (first_name, middle_name, last_name) values ('Odette', 'Mill', 'Rossi');
insert into client (first_name, middle_name, last_name) values ('Gert', 'Victoir', 'Noraway');
insert into client (first_name, middle_name, last_name) values ('Ber', 'Helaina', 'Breede');
insert into client (first_name, middle_name, last_name) values ('Ramsey', 'Anselma', 'Orht');
insert into client (first_name, middle_name, last_name) values ('Skylar', 'Hallsy', 'Meak');
insert into client (first_name, middle_name, last_name) values ('Fred', 'Ericha', 'Cappel');
insert into client (first_name, middle_name, last_name) values ('Pearline', 'Jesse', 'Hay');
insert into client (first_name, middle_name, last_name) values ('Marwin', 'Elana', 'Warboy');
insert into client (first_name, middle_name, last_name) values ('Byran', 'Harv', 'Knightsbridge');
insert into client (first_name, middle_name, last_name) values ('Sterling', 'Ondrea', 'Risman');
insert into client (first_name, middle_name, last_name) values ('Clementia', 'Korney', 'Barstock');
insert into client (first_name, middle_name, last_name) values ('Ferrell', 'Darn', 'Paal');
insert into client (first_name, middle_name, last_name) values ('Nerte', 'Randene', 'Ralph');
insert into client (first_name, middle_name, last_name) values ('Sonya', 'Saul', 'Bowring');
insert into client (first_name, middle_name, last_name) values ('Lory', 'Pietra', 'Larwell');
insert into client (first_name, middle_name, last_name) values ('Caryn', 'Fancy', 'Mahaddy');
insert into client (first_name, middle_name, last_name) values ('Winn', 'Cherin', 'Duddan');
insert into client (first_name, middle_name, last_name) values ('Frances', 'Willis', 'Emanson');
insert into client (first_name, middle_name, last_name) values ('Allis', 'Brook', 'Disdel');
insert into client (first_name, middle_name, last_name) values ('Marci', 'Nanice', 'Jedrachowicz');
insert into client (first_name, middle_name, last_name) values ('Reinold', 'Land', 'Decruse');
insert into client (first_name, middle_name, last_name) values ('Kennie', 'Zonnya', 'Halleday');
--changeset merkodanov:2
INSERT INTO account (balance, type, client_id)
SELECT
    (random() * 10000)::DECIMAL,
    (ARRAY['AUTO', 'PERSONAL', 'BUSINESS', 'MORTGAGE'])[floor(random() * 4 + 1)::INT],
    gs.client_id
FROM
    generate_series(1, 50) AS gs(client_id);
--changeset merkodanov:3
INSERT INTO transaction (total_amount, time, account_id)
SELECT
    (random() * 1000)::DECIMAL,
    now() - (random() * interval '30 days'),
    gs.account_id
FROM
    generate_series(1, 50) AS gs(account_id);