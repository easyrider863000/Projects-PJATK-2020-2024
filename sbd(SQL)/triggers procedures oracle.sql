--1 trigger
--wyzwalacz, który nie pozwala na dodanie lub zmianę opisu zadania na pustą wartość
create or replace trigger NotAllowEmptyDescription
before insert or update on task
for each row
begin
if :new.taskdescription like ' ' then
    RAISE_APPLICATION_ERROR(-20120, 'nie pozwala na dodanie lub zmianę opisu zadania na pustą wartość');
end if;
end;

--tests 1 trigger
/*insert into task(taskid,taskname, taskdescription)
values(14,'haha',' ')*/





--2 trigger
--wyzwalacz, który usuwa projekty testowe("!!!test...") po dodaniu kolejnego projektu
create or replace trigger TestProjects
before insert on Project
begin
    delete DepartmentProject
    where ProjectId in (select ProjectId from Project where ProjectName like '!!!test%');
    delete ProjectTask
    where ProjectId in (select ProjectId from Project where ProjectName like '!!!test%');
    delete Project
    where ProjectName like '!!!test%';
end;

--tests 2 trigger
/*insert into Project(ProjectId,ProjectName)
values(123,'!!!test234gh');*/