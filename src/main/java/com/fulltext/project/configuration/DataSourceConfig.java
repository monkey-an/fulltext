package com.fulltext.project.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/2/3.
 *
 * @author anlu.
 */

@Configuration
@MapperScan(basePackages = "com.fulltext.project.dao", sqlSessionTemplateRef  = "fulltextSqlSessionTemplate")
public class DataSourceConfig {
    @Bean(name = "fulltextDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.fulltext")
    @Primary
    public DataSource getDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "fulltextSqlSessionFactory")
    @Primary
    public SqlSessionFactory fulltextSqlSessionFactory(@Qualifier("fulltextDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));

        return bean.getObject();
    }

    @Bean(name = "fulltext")
    @Primary
    public DataSourceTransactionManager fulltextTransactionManager(@Qualifier("fulltextDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean(name = "fulltextSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate fulltextSqlSessionTemplate(@Qualifier("fulltextSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
