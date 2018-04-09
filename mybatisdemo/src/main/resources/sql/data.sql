--建表
CREATE TABLE `student` (
  `stuId` int(10) NOT NULL AUTO_INCREMENT,
  `stuName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`stuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

insert into student values('1','zhangsan');
insert into student values('2','lisi');
insert into student values('3','wangwu');