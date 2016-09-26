Create database CSC411;
use CSC411;

Create table Department( dname char(64), did char(11), location char(64), pid char(11) references Professor(pid), primary key (did));
Create table Professor( SSN char(11), pid char(11), pname char(64), age integer, did char(11) references Department(did), primary key (pid));
Create table Student( SSN char(11), sid char(11), sname char(64), age integer, pid char(11) references Professor(pid), gpa real, primary key (sid));
create table Course( cid char(11), cname char(64), credits real, pid char(11) references Professor(pid), primary key (cid));
Create table Lab( lid char(11), lname char(64), did char(11) references Department(did), primary key (lid));
Create table Lab_Supervisor ( lid char(11) references Lab(lid), pid char(11) references Professor(pid), primary key (lid, pid));
Create table Student_Worker( lid char(11) references Lab(lid), sid char(11) references Student(sid), primary key (lid, sid));
Create table Courses_taken( cid char(11) references Course(cid), sid char(11) references Student(sid), primary key (cid, sid));
Create table Department_Employee( did char(11) references Department, pid char(11) references Professor(pid), primary key (did, pid));
Create table Student_Major( sid char(11) references Student(sid), did char(11) references Department(did), primary key (sid, did));
Create table Student_Organization( oid char(11), oname char(64), sid char(11) references Student(sid), primary key (oid));
Create table SO_Members(oid char(11) references Student_Organization(oid), sid char(11) references Student(sid), primary key (sid, oid));



Insert into Department (dname, did, location, pid) values ('Department1', 'd-0000-0001', 'Campus Building1', 'p-0000-0001');
Insert into Department (dname, did, location, pid) values ('Department2', 'd-0000-0002', 'Campus Building2', 'p-0000-0002');
Insert into Department (dname, did, location, pid) values ('Department3', 'd-0000-0003', 'Campus Building3', 'p-0000-0003');
Insert into Department (dname, did, location, pid) values ('Department4', 'd-0000-0004', 'Campus Building4', 'p-0000-0004');

Insert into Professor (SSN, pid, pname, age, did) values ('11111111', 'p-0000-0001', 'Professor1', 30, 'd-0000-0001');
Insert into Professor (SSN, pid, pname, age, did) values ('11111112', 'p-0000-0002', 'Professor2', 35, 'd-0000-0002');
Insert into Professor (SSN, pid, pname, age, did) values ('11111113', 'p-0000-0003', 'Professor3', 42, 'd-0000-0003');
Insert into Professor (SSN, pid, pname, age, did) values ('11111114', 'p-0000-0004', 'Professor4', 33, 'd-0000-0004');
Insert into Professor (SSN, pid, pname, age, did) values ('11111115', 'p-0000-0005', 'Professor5', 57, 'd-0000-0001');
Insert into Professor (SSN, pid, pname, age, did) values ('11111116', 'p-0000-0006', 'Professor6', 43, 'd-0000-0002');
Insert into Professor (SSN, pid, pname, age, did) values ('11111117', 'p-0000-0007', 'Professor7', 55, 'd-0000-0003');
Insert into Professor (SSN, pid, pname, age, did) values ('11111118', 'p-0000-0008', 'Professor8', 52, 'd-0000-0004');
Insert into Professor (SSN, pid, pname, age, did) values ('11111119', 'p-0000-0009', 'Professor9', 40, 'd-0000-0001');
Insert into Professor (SSN, pid, pname, age, did) values ('11111120', 'p-0000-0010', 'Professor10', 29, 'd-0000-0002');
Insert into Professor (SSN, pid, pname, age, did) values ('11111121', 'p-0000-0011', 'Professor11', 31, 'd-0000-0003');
Insert into Professor (SSN, pid, pname, age, did) values ('11111122', 'p-0000-0012', 'Professor12', 32, 'd-0000-0004');

Insert into Student (SSN, sid, sname, age, pid, gpa) values ('000000001','s-0000-0001','Student1',19,'p-0000-0007', 4.0);
Insert into Student (SSN, sid, sname, age, pid, gpa) values ('000000002','s-0000-0002','Student2',21,'p-0000-0006', 3.8);
Insert into Student (SSN, sid, sname, age, pid, gpa) values ('000000003','s-0000-0003','Student3',18,'p-0000-0005', 2.1);
Insert into Student (SSN, sid, sname, age, pid, gpa) values ('000000004','s-0000-0004','Student4',20,'p-0000-0004', 1.8);
Insert into Student (SSN, sid, sname, age, pid, gpa) values ('000000005','s-0000-0005','Student5',25,'p-0000-0003', 2.9);
Insert into Student (SSN, sid, sname, age, pid, gpa) values ('000000006','s-0000-0006','Student6',20,'p-0000-0002', 3.5);

Insert into Course(cid, cname, credits, pid) values ('c-0001-0001','Course1', 3.0,'p-0000-0005');
Insert into Course(cid, cname, credits, pid) values ('c-0001-0002','Course1', 3.0,'p-0000-0009');
Insert into Course(cid, cname, credits, pid) values ('c-0002-0001','Course3', 3.0,'p-0000-0006');
Insert into Course(cid, cname, credits, pid) values ('c-0003-0001','Course4', 1.0,'p-0000-0006');
Insert into Course(cid, cname, credits, pid) values ('c-0004-0001','Course5', 3.0,'p-0000-0007');

Insert into Lab(lid, lname, did) values ('l-0000-0001','Lab1','d-0000-0001');
Insert into Lab(lid, lname, did) values ('l-0000-0002','Lab2','d-0000-0001');
Insert into Lab(lid, lname, did) values ('l-0000-0003','Lab3','d-0000-0003');
Insert into Lab(lid, lname, did) values ('l-0000-0004','Lab4','d-0000-0004');

Insert into Lab_Supervisor(lid, pid) values('l-0000-0001','p-0000-0005');
Insert into Lab_Supervisor(lid, pid) values('l-0000-0002','p-0000-0006');
Insert into Lab_Supervisor(lid, pid) values('l-0000-0003','p-0000-0007');
Insert into Lab_Supervisor(lid, pid) values('l-0000-0004','p-0000-0008');

Insert into Student_Worker(lid, sid) values ('l-0000-0001','s-0000-0002');
Insert into Student_Worker(lid, sid) values ('l-0000-0001','s-0000-0003');
Insert into Student_Worker(lid, sid) values ('l-0000-0002','s-0000-0004');
Insert into Student_Worker(lid, sid) values ('l-0000-0003','s-0000-0005');

Insert into Course_Taken(cid, sid) values ('c-0001-0001','s-0000-0001');
Insert into Course_Taken(cid, sid) values ('c-0001-0001','s-0000-0002');
Insert into Course_Taken(cid, sid) values ('c-0001-0002','s-0000-0003');
Insert into Course_Taken(cid, sid) values ('c-0001-0001','s-0000-0004');
Insert into Course_Taken(cid, sid) values ('c-0002-0001','s-0000-0004');
Insert into Course_Taken(cid, sid) values ('c-0003-0001','s-0000-0005');
Insert into Course_Taken(cid, sid) values ('c-0004-0001','s-0000-0001');

Insert into Department_Employee(did, pid) values ('d-0000-0001','p-0000-0001');
Insert into Department_Employee(did, pid) values ('d-0000-0002','p-0000-0002');
Insert into Department_Employee(did, pid) values ('d-0000-0003','p-0000-0003');
Insert into Department_Employee(did, pid) values ('d-0000-0004','p-0000-0004');
Insert into Department_Employee(did, pid) values ('d-0000-0001','p-0000-0005');
Insert into Department_Employee(did, pid) values ('d-0000-0002','p-0000-0006');
Insert into Department_Employee(did, pid) values ('d-0000-0003','p-0000-0007');
Insert into Department_Employee(did, pid) values ('d-0000-0004','p-0000-0008');
Insert into Department_Employee(did, pid) values ('d-0000-0001','p-0000-0009');
Insert into Department_Employee(did, pid) values ('d-0000-0002','p-0000-0010');
Insert into Department_Employee(did, pid) values ('d-0000-0003','p-0000-0011');
Insert into Department_Employee(did, pid) values ('d-0000-0004','p-0000-0012');

Insert into Student_major(sid, did) values ('s-0000-0001','d-0000-0001');
Insert into Student_major(sid, did) values ('s-0000-0001','d-0000-0002');
Insert into Student_major(sid, did) values ('s-0000-0003','d-0000-0001');
Insert into Student_major(sid, did) values ('s-0000-0004','d-0000-0001');
Insert into Student_major(sid, did) values ('s-0000-0005','d-0000-0004');
Insert into Student_major(sid, did) values ('s-0000-0006','d-0000-0003');

Insert into Student_Organization(oid, oname, sid) values ('o-0000-0001','Organization1','s-0000-0001');
Insert into Student_Organization(oid, oname, sid) values ('o-0000-0002','Organization2', 's-0000-0005');

Insert into SO_Members(oid, sid) values ('o-0000-0001','s-0000-0001');
Insert into SO_Members(oid, sid) values ('o-0000-0001','s-0000-0002');
Insert into SO_Members(oid, sid) values ('o-0000-0001','s-0000-0005');
Insert into SO_Members(oid, sid) values ('o-0000-0002','s-0000-0005');
Insert into SO_Members(oid, sid) values ('o-0000-0002','s-0000-0003');

