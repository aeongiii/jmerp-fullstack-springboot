use jm_erp;

INSERT INTO mg_account_mg(Account_Code, Account_name, Account_num, Account_Manager) VALUES
(2208162517, '하람', '010-1234-1234', 'John Doe'),
(1108729123,'우리소','010-1111-1222', '우삼겹'),
(1108729457,'도축소','010-5782-5622', '김지현'),
(7868729123,'어땟소','010-1689-1789', '민지화'),
(468794213,'맛있소','010-9987-2278', '재민'),
(9986513242,'돈부리','010-6678-4863', '우연희'),
(488679962,'돈우축','010-9495-1508', '김예지'),
(33245887,'농협','010-1587-1338', '이세상'),
(144889933,'수입소','010-6687-1984', '조현기'),
(1058876997,'형제물산','010-1918-9876','김형제');

select * from mg_item_regi;
	insert into mg_item_regi(item_code,itemname,item_cost,item_reciving,item_type) values
	('A001','닭가슴살스테이크',2300,'23-01-23','가공식품'),
	('B001','스팀닭가슴살',2000,'23-01-24','가공식품'),
	('C001','불고기닭가슴살볶음밥',2500,'23-01-26','가공식품');

insert into mg_item_regi(item_code,itemname,item_cost,item_reciving,item_type) values
	('A001','닭가슴살스테이크',2300,'23-01-23','가공식품');
    
select * from mg_sn;
insert into mg_sn(wms_item_code,item_name,sn_date) values
('A001','닭가슴살스테이크','2024-01-28'),
('B001','스팀닭가슴살','2024-01-30'),
('C001','불고기닭가슴살볶음밥','2024-01-22');



select * from mg_wms;
insert into mg_wms(ware_name, ware_location, ware_reciving, ware_relese, item_name, wms_item_code, item_cost, item_number, item_type) values
('점문','수원시 팔달로 뭐시기', '2024-01-29',null,'닭가슴살 스테이크','A001',2300,100,'가공식품'),
('점문','수원시 팔달로 뭐시기', null, '2024-01-25','닭가슴살 스테이크','A001',2300,100,'가공식품');

select * from mg_use_self;
insert into mg_use_self(us_date,us_name,use_num,use_ware_name) values
('2024-02-02','김밥',2,'점문');