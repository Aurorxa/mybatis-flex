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

}
