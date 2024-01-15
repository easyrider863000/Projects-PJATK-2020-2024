--4
--select Imie, Nazwisko, DataUrodzenia from Osoba
--order by year(DataUrodzenia) desc, Nazwisko;

--5
--select CONCAT(Imie,' ',Nazwisko) as 'Pracownicy i studenci' from Osoba
--order by Nazwisko;

--7
--select distinct Imie from Osoba
--order by Imie;
--8
--select distinct year(DataRekrutacji) as 'Lata rekrutacji' from Student
--order by year(DataRekrutacji) desc;
--9
--select s.IdOsoba, DATEDIFF(day, s.DataRekrutacji,GETDATE()) as 'days',
--	DATEDIFF(MONTH, s.DataRekrutacji,GETDATE()) as 'months',
--	DATEDIFF(YEAR, s.DataRekrutacji,GETDATE()) as 'years' from Student s;






--2
--select * from Osoba
--where Imie like '%A%'
--and Imie not like '%B%';


--3
--select * from Osoba
--where LEN(Imie)=5;


--5
--select * from Osoba
--where DataUrodzenia is null;










--3
--select o.Imie,o.Nazwisko from Dydaktyk d
--inner join Osoba o on o.IdOsoba=d.IdOsoba
--inner join Student s on s.IdOsoba=o.IdOsoba;

--4
--select o.Imie,o.Nazwisko,s.DataRekrutacji from Osoba o
--inner join Student s on s.IdOsoba=o.IdOsoba
--where DataRekrutacji>='2012-07-01' and DataRekrutacji<='2012-09-30';

--7
--select o.Imie, o.Nazwisko from StopnieTytuly st
--inner join Dydaktyk d on d.IdStopien=st.IdStopien
--inner join Osoba o on o.IdOsoba=d.IdOsoba
--where st.Stopien like 'Doktor';

--8
--select os.Imie,os.Nazwisko from Student s
--inner join Ocena o on o.IdStudent=s.IdOsoba
--inner join Osoba os on os.IdOsoba=s.IdOsoba
--inner join Przedmiot p on p.IdPrzedmiot=o.IdPrzedmiot
--where year(DataWystawienia) like '2013'
--and Ocena = 2
--and p.Przedmiot like 'Analiza matematyczna I';


--9
--select Imie,Nazwisko,Stopien from Dydaktyk d
--inner join Osoba o on o.IdOsoba=d.IdOsoba
--left join StopnieTytuly s on s.IdStopien=d.IdStopien;

--14
--select o.Imie as 'Imie Studenta',
--		o.Nazwisko as 'Nazwisko Studenta',
--		o2.Imie as 'Imie Dydaktyka',
--		o2.Nazwisko as 'Nazwisko Dydaktyka',
--		oc.Ocena
--from Student s
--inner join Osoba o on s.IdOsoba=o.IdOsoba
--inner join Osoba o2 on o2.IdOsoba=o2.IdOsoba
--inner join Dydaktyk d on d.IdOsoba=o2.IdOsoba
--inner join Ocena oc on oc.IdDydaktyk=d.IdOsoba and oc.IdStudent=s.IdOsoba

--19
--select * from Student s
--inner join Osoba o on s.IdOsoba = o.IdOsoba
--where o.Imie like '%a' and
--(year(DataRekrutacji) like '2012' or o.Imie like 'B%');

--20

select count(s.NrIndeksu) from Student s
where year(s.DataRekrutacji) like '2012'











