CREATE TABLE merchant
(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    bankName VARCHAR (100) NOT NULL,
    swift VARCHAR (40) NOT NULL,
    account VARCHAR (20) NOT NULL,
    charge DECIMAL(5,2) NOT NULL,
    period SMALLINT NOT NULL,
    minSum DECIMAL (19,2) NOT NULL,
    needToSend DECIMAL(19,2),
    sent DECIMAL(19,2),
    lastSent DATE,
    PRIMARY KEY (id)
);
INSERT INTO merchant
(name, charge, period, minSum , bankName , swift, account)
values
('Jim Smith ', 5.1, 1,100,'Chase Manhatten', 'AA245BXW',2479990001),
('Domby and son Co. ', 2.8, 2, 20, 'Chase Manhatten', 'AB245BXW', 2479990002),
('Victoria Shop Ltd. ', 3.4, 3, 500, 'Swedbank', 'AC245BXW', 2479990003),
('Software & Digital goods ', 4.9, 1, 160, 'Credi Leone', 'AD245BXW', 2479990004) ;
CREATE TABLE customer
(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    address VARCHAR(300) NOT NULL,
    email VARCHAR(90) NOT NULL,
    ccNo VARCHAR(20) NOT NULL,
    ccType VARCHAR(60) NOT NULL,
    maturity DATE,
    PRIMARY KEY (id)
);
INSERT INTO customer
(name, address, email, ccNo , ccType ,maturity)
values
('Dan Nelis ', 'Vosselaar st. 19, Trnaut , Belgium', 'Dan@adw.com','5600 0000 1234 1234','MasterCard', '2018-07-31'),
('Mark Wolf ', 'Olaf st. 11, Stockholm , Sweden', 'mwolf@yahoo.com','4580 0000 1111 1111','Visa', '2012-09-30'),
('Stain Brown ', 'Oxford st. 223, Stockholm , Sweden', 'stainB@yahoo.com','4580 0000 2222 2222','Visa', '2015-11-30');
CREATE TABLE payment
(
    id INT NOT NULL AUTO_INCREMENT,
    dt TIMESTAMP NOT NULL,
    merchantId INT NOT NULL,
    customerId INT NOT NULL,
    goods VARCHAR(500),
    sumPaid DECIMAL(15,2),
    chargePaid DECIMAL(15,2),
    FOREIGN KEY mer_fk (merchantId) REFERENCES merchant(id),
    FOREIGN KEY cust_fk (customerId)REFERENCES customer(id),
    PRIMARY KEY (id)
);
INSERT INTO payment
(dt , merchantId , customerId , goods, sumPaid)
values
('2018-07-16 12:00:10', 3, 1 , 'CD Europe Maps', 12.08),
('2013-08-10 10:00:12', 4, 3 , 'NOD32 Antivirus', 33.8),
('2015-09-19 11:40:13', 1, 1 , 'Railway return ticket Brussel - Paris', 255.58),
('2011-10-22 12:55:15', 1, 2 , 'Railway ticket Stockholm - Oslo', 1325.0),
('2012-11-19 09:33:16', 3, 2 , 'CD African music', 7.65),
('2016-12-13 11:30:27', 2, 1 , 'Acer computer', 654.00),
('2015-05-12 12:20:52', 4, 2 , 'NOD32 Antivirus', 33.80),
('2019-06-12 10:10:22', 4, 3 , 'MS Office', 400.23),
('2013-08-12 11:00:00', 2, 2 , 'Dell Computer', 768.00);