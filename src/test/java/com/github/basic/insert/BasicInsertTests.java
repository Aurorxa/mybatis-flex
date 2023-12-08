package com.github.basic.insert;

import com.github.domain.Account;
import com.github.mapper.AccountMapper;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.github.domain.table.AccountTableDef.ACCOUNT;

@Slf4j
@SpringBootTest
public class BasicInsertTests {

    @Resource
    private AccountMapper accountMapper;

    /**
     * insert(entity)：插入实体类数据，不忽略 null 值
     */
    @Test
    public void testInsert() {

        String uuid = UUID.randomUUID().toString();

        Account account = new Account().setUserName(uuid);

        // 新增
        int columns = accountMapper.insert(account);

        log.info("{}", columns);

        Assertions.assertTrue(columns > 0);

        // 查询
        QueryWrapper queryWrapper = QueryWrapper.create().where(ACCOUNT.ID.eq(account.getId()));

        Account accountDB = accountMapper.selectOneByQuery(queryWrapper);
        Assertions.assertNotNull(accountDB);
        Assertions.assertNotNull(accountDB.getId());
        Assertions.assertNotNull(accountDB.getUserName());
        Assertions.assertNull(accountDB.getAge());
        Assertions.assertNull(accountDB.getBirthday());
    }

    /**
     * insert(entity,true)：插入实体类数据，忽略 null 值
     * insert(entity,false)：插入实体类数据，不忽略 null 值
     */
    @Test
    public void testInsert2() {

        String uuid = UUID.randomUUID().toString();

        Account account = new Account().setUserName(uuid);

        // 新增
        int columns = accountMapper.insert(account, true);

        log.info("{}", columns);

        Assertions.assertTrue(columns > 0);

        // 查询
        QueryWrapper queryWrapper = QueryWrapper.create().where(ACCOUNT.ID.eq(account.getId()));

        Account accountDB = accountMapper.selectOneByQuery(queryWrapper);
        Assertions.assertNotNull(accountDB);
        Assertions.assertNotNull(accountDB.getId());
        Assertions.assertNotNull(accountDB.getUserName());
        Assertions.assertNotNull(accountDB.getAge());
        Assertions.assertNull(accountDB.getBirthday());
    }

    /**
     * Selective 有选择性的
     * insertSelective(entity)：插入实体类数据，但是忽略 null 的数据，只对有值的内容进行插入。
     * 这样的好处是数据库已经配置了一些默认值，这些默认值才会生效。
     * 类似于 insert(entity,true)：插入实体类数据，忽略 null 值
     */
    @Test
    public void testInsertSelective() {
        String uuid = UUID.randomUUID().toString();

        Account account = new Account().setUserName(uuid);

        // 新增
        int columns = accountMapper.insertSelective(account);

        log.info("{}", columns);

        Assertions.assertTrue(columns > 0);

        // 查询
        QueryWrapper queryWrapper = QueryWrapper.create().where(ACCOUNT.ID.eq(account.getId()));

        Account accountDB = accountMapper.selectOneByQuery(queryWrapper);
        Assertions.assertNotNull(accountDB);
        Assertions.assertNotNull(accountDB.getId());
        Assertions.assertNotNull(accountDB.getUserName());
        Assertions.assertNotNull(accountDB.getAge());
        Assertions.assertNull(accountDB.getBirthday());
    }
}
