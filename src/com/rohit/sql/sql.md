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

#interviews - complex join
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

-- HINT: here the innermost sums are grouped as subqueries and flattened against challenge id to avoid cardinal multiplication issue when joined to previous results

#15 days of learning
With t1 as
(
    select submission_date, 
    count(distinct(hacker_id)) as cnt_d_hid
     from submissions 
     group by submission_date 
     order by submission_date
)
,  
t2  as
(
    select submission_date, hacker_id, 
    count(hacker_id) as cnt_hid,
    row_number() over (partition by submission_date order by count(hacker_id) desc, hacker_id) rank
     from submissions 
     group by submission_date, hacker_id
)
,
t3 as
(
    select * 
    from t2 
    where rank =1
)
select t3.submission_date, t1.cnt_d_hid, t3.hacker_id, hackers.name
from t1
inner join t3 
on t3.submission_date = t1.submission_date 
left join hackers
on t3.hacker_id = hackers.hacker_id
;

real answer
2016-03-01 112 81314 Denise
2016-03-02 59 39091 Ruby
2016-03-03 51 18105 Roy
2016-03-04 49 533 Patrick
2016-03-05 49 7891 Stephanie
2016-03-06 49 84307 Evelyn
2016-03-07 35 80682 Deborah
2016-03-08 35 10985 Timothy
2016-03-09 35 31221 Susan
2016-03-10 35 43192 Bobby
2016-03-11 35 3178 Melissa
2016-03-12 35 54967 Kenneth
2016-03-13 35 30061 Julia
2016-03-14 35 32353 Rose
2016-03-15 35 27789 Helen

my answer
2016-03-01 112 81314 Denise
2016-03-02 144 39091 Ruby
2016-03-03 122 18105 Roy
2016-03-04 136 533 Patrick
2016-03-05 144 7891 Stephanie
2016-03-06 140 84307 Evelyn
2016-03-07 101 80682 Deborah
2016-03-08 147 10985 Timothy
2016-03-09 154 31221 Susan
2016-03-10 108 43192 Bobby
2016-03-11 117 3178 Melissa
2016-03-12 107 54967 Kenneth
2016-03-13 90 30061 Julia
2016-03-14 146 32353 Rose
2016-03-15 117 27789 Helen

#SQL project planning
with t1 as 
(select start_date
from projects
where start_date not in (select end_date from projects) order by start_date)
,
t2 as 
(select end_date
from projects
where end_date not in (select start_date from projects) order by end_date)
,
t11 as 
(select t1.*, rownum as rownum1 from t1)
,
t22 as 
(select t2.*, rownum as rownum2 from t2)
select start_date, end_date from t11 inner join t22 on t11.rownum1 = t22.rownum2 order by (t22.end_date - t11.start_date), start_date
;
