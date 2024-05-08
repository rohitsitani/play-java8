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

#interview complex join
select contests.contest_id, contests.hacker_id, contests.name, 
sum(SS.total_submissions), 
sum(SS.total_accepted_submissions),
sum(VS.total_views),
sum(VS.total_unique_views)
from contests 
inner join colleges on contests.contest_id = colleges.contest_id 
inner join challenges on colleges.college_id = challenges.college_id 
left join 
    (select view_stats.challenge_id, sum(view_stats.total_views) as total_views ,sum(view_stats.total_unique_views) as total_unique_views from view_stats group by challenge_id) VS 
    on challenges.challenge_id = VS.challenge_id
left join 
    (select challenge_id, sum(total_submissions) as total_submissions, sum(total_accepted_submissions) as total_accepted_submissions from submission_stats group by challenge_id) SS 
    on challenges.challenge_id = SS.challenge_id
group by contests.contest_id, contests.hacker_id, contests.name 
having 
sum(SS.total_submissions) != 0 
and  sum(SS.total_accepted_submissions) != 0 
and sum(VS.total_views) != 0 
and sum(VS.total_unique_views) != 0
order by contests.contest_id;

#15 days of learning
select submission_date, hacker_id, maxsub  from (select submission_date, hacker_id, count(hacker_id) as maxsub, rank() over (order by count(hacker_id) desc) as ranking from submissions group by submission_date, hacker_id order by submission_date, count(hacker_id) desc, hacker_id) where ranking<50;