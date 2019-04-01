select
  /*%expand*/*
from
  article
where
  id = /* id */1
  and account_id = /* accountId */2
  and status = /* status */'a'
