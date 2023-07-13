drop table if exists STOCK;
drop table if exists WAREHOUSE;

create table WAREHOUSE (
	id INT AUTO_INCREMENT PRIMARY KEY,
	warehouse_name VARCHAR(50)
);


create table STOCK (
	id INT AUTO_INCREMENT PRIMARY KEY,
	stock_name VARCHAR(50),
	quantity INT,
	warehouse_id INT,
	FOREIGN KEY (warehouse_id) REFERENCES WAREHOUSE(id)
);
