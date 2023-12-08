package com.github.config;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.mybatis.FlexConfiguration;
import com.mybatisflex.spring.boot.ConfigurationCustomizer;
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;
import com.mybatisflex.spring.boot.SqlSessionFactoryBeanCustomizer;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MyBatisFlexConfiguration implements ConfigurationCustomizer, MyBatisFlexCustomizer, SqlSessionFactoryBeanCustomizer {

    @Override
    public void customize(FlexConfiguration configuration) {
        configuration.setLogImpl(StdOutImpl.class);
    }

    /**
     * MyBatisFlexCustomizer 是 MyBatis-Flex 为了方便 SpringBoot 用户对 MyBatis-Flex 进行初始化而产生的接口
     * 1、FlexGlobalConfig 的全局配置
     * 2、自定义主键生成器
     * 3、多租户配置
     * 4、动态表名配置
     * 5、逻辑删除处理器配置
     * 6、自定义脱敏规则
     * 7、SQL 审计配置
     * 8、SQL 打印配置
     * 9、数据源解密器配置
     * 10、自定义数据方言配置
     * 11、...
     */
    @Override
    public void customize(FlexGlobalConfig globalConfig) {
        globalConfig.setPrintBanner(true);
    }

    @Override
    public void customize(SqlSessionFactoryBean factoryBean) {
        log.info("customize SqlSessionFactoryBean");
    }
}