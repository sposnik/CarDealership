[//]: # (Teraz zasymulujmy sytuację, gdzie nowy klient kupuje samochód)
[//]: # (name;surname;telephone;email;country;city;postal_code;address)
BUY_FIRST_TIME -> CUSTOMER -> Alfred;Samochodowy;+48 754 552 234;alf.samoch@gmail.com;Polska;Wrocław;20-001;Bokserska 15 -> CAR -> 1G1PE5S97B7239380 -> SALESMAN -> 73021314515
BUY_FIRST_TIME -> CUSTOMER -> Monika;Cybulska;+48 458 774 125;mon.cyb@gmail.com;Polska;Wrocław;30-001;Wesoła 24 -> CAR -> 1FT7X2B60FEA74019 -> SALESMAN -> 55091699846
BUY_FIRST_TIME -> CUSTOMER -> Tomasz;Zimnoch;+48 486 324 125;tom.zim@gmail.com;Polska;Gdynia;50-001;Wrocławska 55 -> CAR -> 1GCEC19X27Z109567 -> SALESMAN -> 55091699846
BUY_FIRST_TIME -> CUSTOMER -> Robert;Wesoły;+48 184 239 489;rob.wes@gmail.com;Polska;Wrocław;10-001;Warszawska 321 -> CAR -> 1GB6G5CG1C1105936 -> SALESMAN -> 62081825675
BUY_FIRST_TIME -> CUSTOMER -> Marek;Marek;+48 587 566 234;mar.mar@gmail.com;Polska;Zakopane;40-001;Ciepła 22 -> CAR -> 1N6BD06T45C416702 -> SALESMAN -> 62081825675

[//]: # (Teraz samochód kupuje klient, który kupował już wcześniej samochód)
BUY_AGAIN -> CUSTOMER -> alf.samoch@gmail.com -> CAR -> 2C3CDYAG2DH731952 -> SALESMAN -> 55091699846

[//]: # (Następnie klient taki przyprowadza samochód do warsztatu i dokonuje naprawy)
SERVICE_REQUEST -> CUSTOMER -> alf.samoch@gmail.com -> CAR -> 2C3CDYAG2DH731952 -> WHAT -> Koło mi odpadło 
SERVICE_REQUEST -> CUSTOMER -> tom.zim@gmail.com -> CAR -> 1GCEC19X27Z109567 -> WHAT -> Olej mi cieknie na stopy 

[//]: # (Teraz do warsztatu przychodzą klienci, którzy nie kupili samochodu u tego dealera)
SERVICE_REQUEST -> CUSTOMER -> Adrian;Paczkomat;+48 991 221 123;adr.paczk@gmail.com;Polska;Szczecin;10-001;Górna 15 -> CAR -> 1N4BA41E18C806520;BMW;Series 1;2015 -> WHAT -> Coś nie działa prawidłowo

[//]: # (Później, każdy mechanik sprawdza, czy została do niego przydzielona jakaś praca.)
[//]: # (Oczywiście w takim przypadku, pisząc oprogramowanie musielibyśmy wprowadzić jakieś reguły biznesowe, żeby odzwierciedlić proces, w którym przydzielana jest praca mechanikom.)
[//]: # (Z racji, że jest to przykład edukacyjny, uprościmy sobie całość.)
[//]: # (service_code;quantity:product_code;quantity;comment;finished)

DO_THE_SERVICE -> MECHANIC -> 52070997836 -> CAR -> 2C3CDYAG2DH731952 -> WHAT -> 11523-7310;1;58394-014;3;OK;NOT_FINISHED
DO_THE_SERVICE -> MECHANIC -> 67111396321 -> CAR -> 2C3CDYAG2DH731952 -> WHAT -> ;;55319-866;2;OK;FINISHED
DO_THE_SERVICE -> MECHANIC -> 52070997836 -> CAR -> 1GCEC19X27Z109567 -> WHAT -> 54340-777;1;0008-0407;1;OK;NOT_FINISHED
DO_THE_SERVICE -> MECHANIC -> 83011863727 -> CAR -> 1GCEC19X27Z109567 -> WHAT -> 68180-556;2;43063-180;2;OK;NOT_FINISHED
DO_THE_SERVICE -> MECHANIC -> 67111396321 -> CAR -> 1GCEC19X27Z109567 -> WHAT -> 0268-1264;2;14222-2022;1;OK;FINISHED

[//]: # (Na koniec implementacji, dopisz jeszcze możliwość wyciągnięcia historii serwisowej auta na podstawie jego numeru VIN)
