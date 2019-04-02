select
  article.id as id
  , article.name as name
  , article.description as description
  , article.status as status
  , account.id as account_id
  , account.display_name as account_display_name
  , article.modified as modified
  , article.created as created
from
  article
inner join account on account.id = article.account_id
where
  article.status <> 'DELETED'
order by
  article.created desc