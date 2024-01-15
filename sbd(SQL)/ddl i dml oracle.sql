
--create all tables
-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2021-06-11 08:51:31.035

-- tables
-- Table: Department


CREATE TABLE Department (
    DepartmentId int  NOT NULL,
    DepartmentName varchar2(100)  NOT NULL,
    DepartmentPhone varchar2(10)  NOT NULL,
    CONSTRAINT Department_pk PRIMARY KEY (DepartmentId)
) ;

-- Table: DepartmentProject
CREATE TABLE DepartmentProject (
    DepartmentProjectId int  NOT NULL,
    DepartmentId int  NOT NULL,
    ProjectId int  NOT NULL,
    CONSTRAINT DepartmentProject_pk PRIMARY KEY (DepartmentProjectId)
) ;

-- Table: Employee
CREATE TABLE Employee (
    EmployeeId int  NOT NULL,
    Surname varchar2(20)  NOT NULL,
    Name varchar2(20)  NOT NULL,
    DepartmentId int  NOT NULL,
    CONSTRAINT Employee_pk PRIMARY KEY (EmployeeId)
) ;

-- Table: Project
CREATE TABLE Project (
    ProjectId int  NOT NULL,
    ProjectName varchar2(50)  NOT NULL,
    CONSTRAINT Project_pk PRIMARY KEY (ProjectId)
) ;

-- Table: ProjectTask
CREATE TABLE ProjectTask (
    ProjectTaskId int  NOT NULL,
    ProjectId int  NOT NULL,
    TaskId int  NOT NULL,
    CONSTRAINT ProjectTask_pk PRIMARY KEY (ProjectTaskId)
) ;

-- Table: Task
CREATE TABLE Task (
    TaskId int  NOT NULL,
    TaskName varchar2(50)  NOT NULL,
    TaskDescription varchar2(500)  NOT NULL,
    CONSTRAINT Task_pk PRIMARY KEY (TaskId)
) ;

-- foreign keys
-- Reference: DepartmentProject_Department (table: DepartmentProject)
ALTER TABLE DepartmentProject ADD CONSTRAINT DepartmentProject_Department
    FOREIGN KEY (DepartmentId)
    REFERENCES Department (DepartmentId);

-- Reference: DepartmentProject_Project (table: DepartmentProject)
ALTER TABLE DepartmentProject ADD CONSTRAINT DepartmentProject_Project
    FOREIGN KEY (ProjectId)
    REFERENCES Project (ProjectId);

-- Reference: Employee_Department (table: Employee)
ALTER TABLE Employee ADD CONSTRAINT Employee_Department
    FOREIGN KEY (DepartmentId)
    REFERENCES Department (DepartmentId);

-- Reference: ProjectTask_Project (table: ProjectTask)
ALTER TABLE ProjectTask ADD CONSTRAINT ProjectTask_Project
    FOREIGN KEY (ProjectId)
    REFERENCES Project (ProjectId);

-- Reference: ProjectTask_Task (table: ProjectTask)
ALTER TABLE ProjectTask ADD CONSTRAINT ProjectTask_Task
    FOREIGN KEY (TaskId)
    REFERENCES Task (TaskId);

-- End of file.



--fill all tables
Insert into department (DepartmentId, DepartmentName, DepartmentPhone) Values (1,'Management', '828162889');
Insert into department (DepartmentId, DepartmentName, DepartmentPhone) Values (2,'Marketing', '824861789');
Insert into department (DepartmentId, DepartmentName, DepartmentPhone) Values (3,'Operations', '828168244');
Insert into department (DepartmentId, DepartmentName, DepartmentPhone) Values (4,'Finance', '444447819');
Insert into department (DepartmentId, DepartmentName, DepartmentPhone) Values (5,'Sales', '124568169');
Insert into department (DepartmentId, DepartmentName, DepartmentPhone) Values (6,'Human Resources', '868273616');
Insert into department (DepartmentId, DepartmentName, DepartmentPhone) Values (7,'Purchase', '862768262');

Insert into employee (EmployeeId, Name, Surname, DepartmentId) Values (1,'Lana', 'Rhoades',1);
Insert into employee (EmployeeId, Name, Surname, DepartmentId) Values (2,'Abella', 'Danger',2);
Insert into employee (EmployeeId, Name, Surname, DepartmentId) Values (3,'Eva', 'Elfie',3);
Insert into employee (EmployeeId, Name, Surname, DepartmentId) Values (4,'Riley', 'Reid',4);
Insert into employee (EmployeeId, Name, Surname, DepartmentId) Values (5,'Mia', 'Khalifa',5);
Insert into employee (EmployeeId, Name, Surname, DepartmentId) Values (6,'Mini', 'Diva',1);
Insert into employee (EmployeeId, Name, Surname, DepartmentId) Values (7,'Mia', 'Malkova',1);
Insert into employee (EmployeeId, Name, Surname, DepartmentId) Values (8,'Lena', 'Paul',3);
Insert into employee (EmployeeId, Name, Surname, DepartmentId) Values (9,'Angela', 'White',5);
Insert into employee (EmployeeId, Name, Surname, DepartmentId) Values (10,'Emily', 'Willis',1);
Insert into employee (EmployeeId, Name, Surname, DepartmentId) Values (11,'Brandi', 'Love',7);
Insert into employee (EmployeeId, Name, Surname, DepartmentId) Values (12,'Nicole', 'Aniston',1);

Insert into project (ProjectId, ProjectName) Values (1,'AVN');
Insert into project (ProjectId, ProjectName) Values (2,'XBiz');
Insert into project (ProjectId, ProjectName) Values (3,'XStarNews');
Insert into project (ProjectId, ProjectName) Values (4,'AV-open');
Insert into project (ProjectId, ProjectName) Values (5,'German-Adult-News');

Insert into departmentproject (DepartmentProjectId, DepartmentId, ProjectId) Values (1,1,1);
Insert into departmentproject (DepartmentProjectId, DepartmentId, ProjectId) Values (2,2,2);
Insert into departmentproject (DepartmentProjectId, DepartmentId, ProjectId) Values (3,3,3);
Insert into departmentproject (DepartmentProjectId, DepartmentId, ProjectId) Values (4,4,4);
Insert into departmentproject (DepartmentProjectId, DepartmentId, ProjectId) Values (5,5,3);
Insert into departmentproject (DepartmentProjectId, DepartmentId, ProjectId) Values (6,2,1);
Insert into departmentproject (DepartmentProjectId, DepartmentId, ProjectId) Values (7,7,2);
Insert into departmentproject (DepartmentProjectId, DepartmentId, ProjectId) Values (8,4,3);
Insert into departmentproject (DepartmentProjectId, DepartmentId, ProjectId) Values (9,3,4);
Insert into departmentproject (DepartmentProjectId, DepartmentId, ProjectId) Values (10,5,2);
Insert into departmentproject (DepartmentProjectId, DepartmentId, ProjectId) Values (11,5,1);
Insert into departmentproject (DepartmentProjectId, DepartmentId, ProjectId) Values (12,1,2);
Insert into departmentproject (DepartmentProjectId, DepartmentId, ProjectId) Values (13,1,3);
Insert into departmentproject (DepartmentProjectId, DepartmentId, ProjectId) Values (14,1,4);
Insert into departmentproject (DepartmentProjectId, DepartmentId, ProjectId) Values (15,4,1);
Insert into departmentproject (DepartmentProjectId, DepartmentId, ProjectId) Values (16,3,1);

Insert into task (TaskId, TaskName, TaskDescription) Values (1,'Creating a Business Plan','Department directors and managers usually create business plans prior to the start of a new fiscal year.');
Insert into task (TaskId, TaskName, TaskDescription) Values (2,'Selling Products','Sales reps call on current customers.');
Insert into task (TaskId, TaskName, TaskDescription) Values (3,'Managing Employees','Employee management is an ongoing business task.');

Insert into projecttask (ProjectTaskId, ProjectId, TaskId) Values (1,1,1);
Insert into projecttask (ProjectTaskId, ProjectId, TaskId) Values (2,2,1);
Insert into projecttask (ProjectTaskId, ProjectId, TaskId) Values (3,5,2);
Insert into projecttask (ProjectTaskId, ProjectId, TaskId) Values (4,3,2);
Insert into projecttask (ProjectTaskId, ProjectId, TaskId) Values (5,1,3);



--print all tables
select * from department;
select * from employee;
select * from project;
select * from departmentproject;
select * from task;
select * from projecttask;