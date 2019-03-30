package com.example.todo.app.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.todo.app.entity.Article;

/**
 * articleテーブルのDAO
 */
@Dao
@ConfigAutowireable
public interface ArticleDao {

    /**
     * @param id
     * @return the Article entity
     */
    @Select
    Article selectById(Long id);

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(Article entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(Article entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(Article entity);
}