#Placements (same table is joined twice)
select name from students, friends, packages p1, packages p2 where students.id = friends.id and students.id = p1.id and friends.friend_id = p2.id and p1.salary < p2.salary order by p2.salary;

#The Pads (union 2 different results)
(select concat (name, concat (concat ('(', substr(occupation, 0,1)),')')) from occupations) union (select concat ('There are a total of ', concat (concat (cc, ' '), concat (lower(occupation), 's.'))) from (select count(*) as cc, occupation from occupations group by occupation));

#weather observation 5 (use row num to get first or last)
select city, a from (select city, length(city) as a from station order by length (city) desc, city) where rownum = 1
union
select city, b from (select city, length(city) as b from station order by length(city) asc, city) where rownum = 1; 

#symetric pairs
select a.x, a.y from functions a, functions b where a.x = b.y and b.x=a.y group by a.x, a.y having a.x<a.y or count(a.x)>1 order by a.x ;