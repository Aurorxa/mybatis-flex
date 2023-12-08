package com.github;

import com.github.domain.Account;
import com.github.mapper.AccountMapper;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.domain.table.AccountTableDef.ACCOUNT;

@Slf4j
@SpringBootTest
public class QuickStartTests {

    @Resource
    private AccountMapper accountMapper;

    @Test
    public void testQuickStart() {
        QueryWrapper queryWrapper = QueryWrapper
                .create()
                .select()
                .where(ACCOUNT.AGE.eq(18));

        Account account = accountMapper.selectOneByQuery(queryWrapper);

        log.info("{}", account);
    }

}
