--1
--Insert into Osoba(Imie,Nazwisko,DataUrodzenia)
--values('Hello','World',GETDATE());
--Insert into Student(IdOsoba,NrIndeksu,DataRekrutacji)
--values(1001,'dfsdfsd',GETDATE());
--Insert into Osoba(Imie,Nazwisko,DataUrodzenia)
--values('Hello','Dydaktyk',GETDATE());
--Insert into Dydaktyk(IdOsoba,IdStopien)
--values(1002,4);
--select * from StopnieTytuly;

--2
--update Dydaktyk
--set Podlega=7
--where IdOsoba=1002;

--3
--update Student
--set NrIndeksu=Concat('s',NrIndeksu)
--where NrIndeksu not like 's%';


--1
--create table Miasto
--(
--	IdMiasto int primary key IDENTITY,
--	Miasto varchar(30) not null,
--)



--2
--alter table Miasto
--add IdPanstwo int REFERENCES Panstwo (IdPanstwo);


--3
--insert into Miasto(Miasto, IdPanstwo)
--values ('Warszawa',3),('Krakow',3),('Poznan',3);


--4
--alter table osoba
--add IdMiasto int REFERENCES Miasto (IdMiasto);


--5
--update Osoba
--set IdMiasto=5
--where IdOsoba between 1 and 8

--update Osoba
--set IdMiasto=6
--where IdOsoba in (10,12,14);

--update Osoba
--set IdMiasto=7
--where Imie like 'G%' or Imie like 'H%' or Imie like 'J%';

--14
--alter table Dydaktyk
--add Placa Money default(2000)

--update Dydaktyk
--set Placa=5000
--where IdStopien not in(4,5)

--update Dydaktyk
--set Placa=2500
--where IdStopien not (4,5)



--19
alter table Osoba
--drop constraint CK__Osoba__DataUrodz__6477ECF3

add check(DataUrodzenia<'1990-01-01' and DataUrodzenia>'1900-01-01')

select * from Osoba;

--drop constraint CK__Osoba__DataUrodz__619B8048
--20
--21









