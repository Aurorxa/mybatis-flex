package com.github;

import com.github.domain.Account;
import com.github.mapper.AccountMapper;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.domain.table.AccountTableDef.ACCOUNT;

@Slf4j
@SpringBootTest
public class QuickStartTests {

    @Resource
    private AccountMapper accountMapper;

    @ParameterizedTest
    @ValueSource(ints = {18,19})
    public void testQuickStart(int age) {
        QueryWrapper queryWrapper = QueryWrapper
                .create()
                .where(ACCOUNT.AGE.eq(age));

        Account account = accountMapper.selectOneByQuery(queryWrapper);

        log.info("{}", account);

        Assertions.assertNotNull(account);
    }

    @ParameterizedTest
    @ValueSource(ints = {18,19})
    public void testQuickStart2(int age) {

        QueryWrapper queryWrapper = QueryWrapper
                .create()
                .select(ACCOUNT.DEFAULT_COLUMNS) // 默认字段，不包含逻辑删除字段或 large 等字段
                .where(ACCOUNT.AGE.eq(age));

        Account account = accountMapper.selectOneByQuery(queryWrapper);

        log.info("{}", account);

        Assertions.assertNotNull(account);
        Assertions.assertNotNull(account.getId());
        Assertions.assertNotNull(account.getUserName());
        Assertions.assertNotNull(account.getAge());
        Assertions.assertNotNull(account.getBirthday());
    }

    @ParameterizedTest
    @ValueSource(ints = {18,19})
    public void testQuickStart3(int age) {

        QueryWrapper queryWrapper = QueryWrapper
                .create()
                .select(ACCOUNT.ALL_COLUMNS) // 查询所有字段，类似于 *
                .where(ACCOUNT.AGE.eq(age));

        Account account = accountMapper.selectOneByQuery(queryWrapper);

        log.info("{}", account);

        Assertions.assertNotNull(account);
        Assertions.assertNotNull(account.getId());
        Assertions.assertNotNull(account.getUserName());
        Assertions.assertNotNull(account.getAge());
        Assertions.assertNotNull(account.getBirthday());
    }

    @ParameterizedTest
    @ValueSource(ints = {18,19})
    public void testQuickStart4(int age) {

        QueryWrapper queryWrapper = QueryWrapper
                .create()
                .select(ACCOUNT.AGE) // 查询指定字段
                .where(ACCOUNT.AGE.eq(age));
        Account account = accountMapper.selectOneByQuery(queryWrapper);

        log.info("{}", account);

        Assertions.assertNotNull(account);
        Assertions.assertNull(account.getId());
        Assertions.assertNull(account.getUserName());
        Assertions.assertNotNull(account.getAge());
        Assertions.assertNull(account.getBirthday());
    }

}
