# select 문
select * from bom;
select * from pd_res;
select * from pd_wo;
select * from pd_work_history;
select * from pd_rs;

# 초기 Insert 문
Insert into bom(prod_code, prod_name,prod_line, raw_mat_num, unit, num, type, raw_num, work_time)
values("A001", "닭가슴살 스테이크", "제조", 3, "EA", 1, "가공품", 1, 1), 
("A002", "닭가슴살", null, 1, "EA", 1, "원재료", 1, 1),
("A003", "야채", null, 1, "EA", 1, "원재료", 1, 1),
("A004", "불고기 소스", null, 1, "EA", 1, "원재료", 1, 1);

Insert into bom(prod_code, prod_name,prod_line, raw_mat_num, unit, num, type, raw_num, work_time)
values ("B001", "스팀 닭가슴살", "제조", 3, "EA", 1, "가공품", 1, 1),
("B002", "닭가슴살", null, 1, "EA", 1, "원재료", 1, 1),
("B003", "설탕", null, 1, "EA", 1, "원재료", 1, 1),
("C001", "불고기 닭가슴살 볶음밥", "제조", 3, "EA", 1, "가공품", 1, 1),
("C002", "쌀", null, 1, "EA", 1, "원재료", 1, 1),
("C003", "쇠고기", null, 1, "EA", 1, "원재료", 1, 1),
("C004", "곤약", null, 1, "EA", 1, "원재료", 1, 1);

Insert into pd_res (res_code, do, location, res_name)
values(00001, "밥짓기", "생산공장", "압력솥"),
(00002, "스팀기", "생산공장", "스팀기"),
(00003, "굽기", "생산공장", "오븐"),
(00004, "믹싱", "생산공장", "믹싱기"),
(00005, "세척", "생산공장", "세척기");

Insert into pd_wo(order_num, manager, delivery_date,prod_code, prod_name, w_order, making, factory)
values ("2024/01/11-1", "성하빈", "20240131","A001","닭가슴살 스테이크", 1000, 500, "제조공장"),
("2024/01/11-2", "이동규", "20240131","B001","스팀 닭가슴살", 1000, 500, "제조공장"),
("2024/01/11-3", "임지환", "20240131","C001","불고기 닭가슴살 볶음밥", 1000, 500, "제조공장");

Insert into pd_work_history(prod_code, prod_name, work, work_code, work_name, num, res_name, work_time, whto)
values ("A001", "닭가슴살 스테이크", "세척", "A002", "닭가슴살", 1000,"세척기", 5,5),
("A002", "닭가슴살 스테이크", "굽기", "A002", "닭가슴살", 1000,"오븐기", 3,3),
("A003", "닭가슴살 스테이크", "세척", "A003", "야채", 1000,"세척기", 2,2),
("A004", "닭가슴살 스테이크", "세척", "A004", "불고기 소스", 1000,"세척기", 2,2);

Insert into pd_rs(rel_num, sto_num, r_from, sto, prod_name, num, prod_cost, out_src_cost, out_src_tot)
values ("2024-01-22-1", null, "창고", "사무실", "닭가슴살 스테이크", 1000, 1380000, 0, 0),
("2024-01-22-2", null, "창고", "사무실", "스팀 닭가슴살", 1000, 1200000, 0, 0),
("2024-01-22-3", null, "창고", "사무실", "불고기 닭가슴살 볶음밥", 1000, 1500000, 0, 0);

Insert into pd_cost(prod_name, kg, cost)
values ("닭가슴살", 1, 7500),
("야채", 1, 250),
("불고기 소스", 1, 180),
("설탕", 1, 200),
("쌀", 1, 100),
("쇠고기", 1, 300),
("곤약", 1, 100),
("닭가슴살 스테이크", 1, 2300),
("스팀 닭가슴살", 1, 2000),
("불고기 닭가슴살 볶음밥", 1, 2500);

 

