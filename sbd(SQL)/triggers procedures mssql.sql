--1 trigger
--nie ma możliwości zmiany danych do działu, w którym nie ma pracowników
go
create TRIGGER UniquePhoneDepartment
ON Department
AFTER UPDATE
AS
DECLARE @id int
DECLARE db_cursor CURSOR FOR
SELECT DepartmentId
FROM INSERTED
OPEN db_cursor
FETCH NEXT FROM db_cursor INTO @id
WHILE @@FETCH_STATUS = 0
	BEGIN
	if (select count(*) from Employee where DepartmentId=@id)<=0
		begin
			print concat('nie ma możliwości zmiany danych do działu (id=',@id,'), w którym nie ma pracowników');
			rollback
		end;
    FETCH NEXT FROM db_cursor INTO @id;
    END;
deallocate db_cursor
go

--tests 1 trigger
--err
/*update Department
set DepartmentPhone='868273616'
where DepartmentId=6;*/

--ok
/*update Department
set DepartmentPhone='868273616'
where DepartmentId=7;*/



--2 trigger
--Nie można usunąć pracownika z bazy danych
go
create TRIGGER NotAllowDeleteEmployee
ON Employee
AFTER DELETE
AS
print 'Nie można usunąć pracownika z tabeli'
rollback
go

--tests 2 trigger
/*delete Employee
where EmployeeId=12;*/



--1 procedure
--Wyświetl wszystkie osoby, które pracują w dziale "id".
GO
CREATE PROCEDURE ShowEmployeeByDepartmentId
    @id INT
AS
select * from Employee e where e.DepartmentId=@id;
GO

--tests 1 procedure
--exec ShowEmployeeByDepartmentId 1;



--2 procedure
--Dodanie nowego projektu do działu
GO
create PROCEDURE AddProjectToDepartment
    @projName nvarchar(50),
    @departId int
AS
declare @newid int = 1
while @newid in (select ProjectId from Project)
	BEGIN
        set @newid = @newid+1
    END;
declare @newid2 int = 1
while @newid2 in (select DepartmentProjectId from DepartmentProject)
	BEGIN
        set @newid2 = @newid2+1
    END;
INSERT INTO Project(ProjectId,ProjectName)
VALUES(@newid,@projName);
INSERT INTO DepartmentProject(DepartmentProjectId,DepartmentId,ProjectId)
values(@newid2,@departId,@newid);
go

--tests 2 procedure
--exec AddProjectToDepartment 'firstProj', 1;