package com.example.todo.app.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

import com.example.todo.app.annotation.AutowiredDomaConfig;
import com.example.todo.app.entity.Account;

/**
 * accountテーブルのDAO
 */
@Dao
@AutowiredDomaConfig
public interface AccountDao {

    /**
     * @param id
     * @return the Account entity
     */
    @Select
    Account selectById(Long id);

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(Account entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(Account entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(Account entity);
}