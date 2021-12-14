-- DML (Data Manipulation Language)

insert into car (id,make,model,year,status,color) values 
	(default, 'Toyota','Corolla', 2018, 'Purchased','Silver'),
	(default, 'Toyota','Rav4', 2018, 'Leased','Silver'),
	(default, 'Toyota','Camry', 2008, 'Purchased','Red'),
	(default, 'Nissan','Rogue', 2021, 'Available','Black'),
	(default, 'Honda','Accord', 2020,'Available','Blue'),
	(default, 'Nissan','Sentra', 2017,'Available','White'),
	(default, 'Toyota','Highlander', 2008,'Leased','Black'),
	(default, 'Toyota','Tacoma', 2020, 'Available','Black'),
	(default, 'Ford','Mustang', 2018, 'Purchased','Green'),
	(default, 'Ford','Fusion', 2019,'Available','White'),
	(default, 'Toyota','4Runner', 2021, 'Available','Green');
	
commit;

insert into person (id, full_name, username, passwd) values
(default, 'Valeda Pagnin', 'vpagnin0', 'neP2Xvpw'),
(default, 'Novelia Priestland', 'npriestland1', 'oZStYcDMRW'),
(default, 'Arabel Saunders', 'asaunders2', 'jrVwpNLFg0'),
(default, 'Dalton Eslie', 'deslie3', 'NvzVqq7WAM'),
(default, 'Murdock Folbigg', 'mfolbigg5', '0zoeBGN'),
(default, 'Caterina Baudasso', 'cbaudasso6', 'nZu7t0x'),
(default, 'Byran Ingman', 'bingman7', 'EebuAQS9J'),
(default, 'John Doe', 'itsjohnd2', 'Rffu78io'),
(default, 'Stanly Toke', 'stoke8', 'ZaNK10yr'),
(default, 'Tyrone Coyle', 'tcoyle9', 'cPkDFNCypAsC');

select * from person;


--select * from car;
--select make,color from car;
--select * from car where make='Toyota';