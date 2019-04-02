package com.example.todo.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.seasar.doma.jdbc.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.app.dao.ArticleDao;
import com.example.todo.app.entity.Article;
import com.example.todo.app.entity.DisplayArticle;
import com.example.todo.app.exception.NotFoundAtricleException;
import com.example.todo.app.helper.DateHelper;
import com.example.todo.app.type.ArticleStatusType;

@Service
public class ArticleService {

    private final Config config;
    private final ArticleDao articleDao;
    private final DateHelper dateHelper;

    public List<DisplayArticle> findAll() {
        return this.articleDao.selectWithAccountOrderByCreated();
    }
    
    /** 
     * 記事作成
     * 
     * @param accountId
     * @param name
     * @param description
     * @return　作成した記事
     */
    public Article create(Long accountId, String name, String description) {
        LocalDateTime now = this.dateHelper.now();
        Article article = new Article(accountId, name, description, now);
        this.articleDao.insert(article);
        return article;
    }

    public Article update(Long id, Long accountId, String name, String description) throws NotFoundAtricleException {
        return this.config.getTransactionManager().required(() -> {
            Article article = this.articleDao.selectByIdAndAccountIdAndStatus(id, accountId, ArticleStatusType.OPENED)
                    .orElseThrow(NotFoundAtricleException::new);
            article.setName(name);
            article.setDescription(description);
            LocalDateTime now = this.dateHelper.now();
            article.setModified(now);
            this.articleDao.update(article);
            return article;
        });
    }

    public Article complete(Long id, Long accountId) throws NotFoundAtricleException {
        return this.changeStatus(id, accountId, ArticleStatusType.OPENED, ArticleStatusType.COMPLETED);
    }

    public Article delete(Long id, Long accountId) throws NotFoundAtricleException {
        return  this.changeStatus(id, accountId, ArticleStatusType.COMPLETED, ArticleStatusType.DELETED);
    }

    /** 
     * 状態を変更します
     * 
     * @param id　記事ID
     * @param accountId 作成者アカウントID
     * @param from 変更前状態
     * @param to　変更後状態
     * @return 更新した記事
     * @throws NotFoundAtricleException
     */
    protected Article changeStatus(Long id, Long accountId, ArticleStatusType from, ArticleStatusType to)
            throws NotFoundAtricleException {
        return this.config.getTransactionManager().required(() -> {
            Article article = this.articleDao.selectByIdAndAccountIdAndStatus(id, accountId, from)
                    .orElseThrow(NotFoundAtricleException::new);
            LocalDateTime now = this.dateHelper.now();
            article.setModified(now);
            article.setStatus(to);
            this.articleDao.update(article);
            return article;
        });
    }

    @Autowired
    public ArticleService(Config config, ArticleDao articleDao, DateHelper dateHelper) {
        this.config = config;
        this.articleDao = articleDao;
        this.dateHelper = dateHelper;
    }
}
