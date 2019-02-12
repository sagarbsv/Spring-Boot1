

create table Customer(id bigint not null auto_increment,customer_Name varchar(255),address varchar(255),contact_Number bigint,gender varchar(255),primary key (id));
create table Sku(id bigint not null auto_increment, product_Name  varchar(255), product_Label  varchar(255),inventory_On_Hand  bigint,min_Qty_Req bigint,price bigint,primary key (id));
create table Vendor(id bigint not null auto_increment, vendor_Name  varchar(255), vendor_Email  varchar(255),vendor_Contact_No  bigint,vendor_Username  varchar(255),vendor_Address  varchar(255), primary key (id));

