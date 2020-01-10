delete
from svn_log_model
where revision is not null;

select name, count(*)
from svn_log_model
group by name;

# 人均文件数统计
select author, count(*)
from svn_log_model
where author in ('hanjianxiang', 'lili', 'yangxudong', 'zhaoyue')
group by author;
# 人均 提交数统计
select author, count(*)
from (select author, name, revision
      from svn_log_model
      where author in ('hanjianxiang', 'lili', 'yangxudong', 'zhaoyue')
      group by author, name, revision) t
group by author;
#仓库提交情况 文件数
select name, count(*)
from svn_log_model
where author in ('hanjianxiang', 'lili', 'yangxudong', 'zhaoyue')
group by name;
#仓库提交情况 提交数
select name, count(*)
from (select name, revision
      from svn_log_model
      where author in ('hanjianxiang', 'lili', 'yangxudong', 'zhaoyue')
      group by name,revision) t
group by name;




select *
from svn_log_model
where author in ('hanjianxiang', 'lili', 'yangxudong', 'zhaoyue')
group by name, revision;



