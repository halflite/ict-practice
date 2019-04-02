package com.example.todo.app.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.seasar.doma.jdbc.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.todo.app.dao.ArticleDao;
import com.example.todo.app.entity.Article;
import com.example.todo.app.entity.DisplayArticle;
import com.example.todo.app.exception.NotFoundArticleException;
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

    public Map<String, Object> findById(Long id) throws NotFoundArticleException {
        Article article = this.articleDao.selectById(id)
                .orElseThrow(NotFoundArticleException::new);
        Map<String, Object> res = new HashMap<>();
        res.put("id", article.getId());
        res.put("name", article.getName());
        res.put("description", article.getDescription());
        return res;
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

    @Transactional
    public Article update(Long id, Long accountId, String name, String description) throws NotFoundArticleException {
        Long nullSafeId = Optional.ofNullable(id)
                .orElseThrow(NotFoundArticleException::new);
        Article article = this.articleDao.selectByIdAndAccountIdAndStatus(nullSafeId, accountId, ArticleStatusType.OPENED)
                .orElseThrow(NotFoundArticleException::new);
        article.setName(name);
        article.setDescription(description);
        LocalDateTime now = this.dateHelper.now();
        article.setModified(now);
        this.articleDao.update(article);
        return article;
    }

    @Transactional
    public Article complete(Long id, Long accountId) throws NotFoundArticleException {
        return this.changeStatus(id, accountId, ArticleStatusType.OPENED, ArticleStatusType.COMPLETED);
    }

    @Transactional
    public Article delete(Long id, Long accountId) throws NotFoundArticleException {
        return this.changeStatus(id, accountId, ArticleStatusType.COMPLETED, ArticleStatusType.DELETED);
    }

    /** 
     * 状態を変更します
     * 
     * @param id　記事ID
     * @param accountId 作成者アカウントID
     * @param from 変更前状態
     * @param to　変更後状態
     * @return 更新した記事
     * @throws NotFoundArticleException
     */
    protected Article changeStatus(Long id, Long accountId, ArticleStatusType from, ArticleStatusType to)
            throws NotFoundArticleException {
        Article article = this.articleDao.selectByIdAndAccountIdAndStatus(id, accountId, from)
                .orElseThrow(NotFoundArticleException::new);
        LocalDateTime now = this.dateHelper.now();
        article.setModified(now);
        article.setStatus(to);
        this.articleDao.update(article);
        return article;
    }

    @Autowired
    public ArticleService(Config config, ArticleDao articleDao, DateHelper dateHelper) {
        this.config = config;
        this.articleDao = articleDao;
        this.dateHelper = dateHelper;
    }
}
