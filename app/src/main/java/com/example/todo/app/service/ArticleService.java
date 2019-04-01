package com.example.todo.app.service;

import java.time.LocalDateTime;

import org.seasar.doma.jdbc.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.app.dao.ArticleDao;
import com.example.todo.app.entity.Article;
import com.example.todo.app.exception.NotFoundAtricleException;
import com.example.todo.app.helper.DateHelper;
import com.example.todo.app.type.ArticleStatusType;

@Service
public class ArticleService {

    private final Config config;
    private final ArticleDao articleDao;
    private final DateHelper dateHelper;

    /** 
     * 記事作成
     * 
     * @param accountId
     * @param name
     * @param description
     * @return　作成した記事ID
     */
    public Long create(Long accountId, String name, String description) {
        LocalDateTime now = this.dateHelper.now();
        Article article = new Article(accountId, name, description, now);
        this.articleDao.insert(article);
        return article.getId();
    }

    public void update(Long id, Long accountId, String name, String description) throws NotFoundAtricleException {
        this.config.getTransactionManager().required(() -> {
            Article article = this.articleDao.selectByIdAndAccountIdAndStatus(id, accountId, ArticleStatusType.OPENED)
                    .orElseThrow(NotFoundAtricleException::new);
            article.setName(name);
            article.setDescription(description);
            LocalDateTime now = this.dateHelper.now();
            article.setModified(now);
            this.articleDao.update(article);
        });
    }

    public void complete(Long id, Long accountId) throws NotFoundAtricleException {
        this.changeStatus(id, accountId, ArticleStatusType.OPENED, ArticleStatusType.COMPLETED);
    }

    public void delete(Long id, Long accountId) throws NotFoundAtricleException {
        this.changeStatus(id, accountId, ArticleStatusType.COMPLETED, ArticleStatusType.DELETED);
    }

    protected void changeStatus(Long id, Long accountId, ArticleStatusType from, ArticleStatusType to)
            throws NotFoundAtricleException {
        this.config.getTransactionManager().required(() -> {
            Article article = this.articleDao.selectByIdAndAccountIdAndStatus(id, accountId, from)
                    .orElseThrow(NotFoundAtricleException::new);
            LocalDateTime now = this.dateHelper.now();
            article.setModified(now);
            article.setStatus(to);
            this.articleDao.update(article);
        });
    }

    @Autowired
    public ArticleService(Config config, ArticleDao articleDao, DateHelper dateHelper) {
        this.config = config;
        this.articleDao = articleDao;
        this.dateHelper = dateHelper;
    }
}
