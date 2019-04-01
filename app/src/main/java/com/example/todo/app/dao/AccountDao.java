package com.example.todo.app.dao;

import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.todo.app.entity.Account;

/**
 */
@Dao
@ConfigAutowireable
public interface AccountDao {

    /**
     * @param id
     * @return the Account entity
     */
    @Select
    Account selectById(Long id);

    /**
     * @param username
     * @return the Account entity
     */
    @Select
    Optional<Account> selectByUsername(String username);

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