>>> MybatisPlus配置多数据源后分页失效问题日志无法打印问题：
>>>>>通过配置mybatis自己的工厂bean并且通过设置扫描目录及其配置mybatispuls自己的分页拦截器后解决
```java
@Configuration
@MapperScan(
        basePackages = {"com.aliyun.citybrain.module.basicsetting.mapper", "com.aliyun.citybrain.module.business.mapper", "com.aliyun.citybrain.module.directory.mapper",
                "com.aliyun.citybrain.module.statistics.mapper", "com.aliyun.citybrain.module.system.mapper",
                "com.aliyun.citybrain.module.topic.mapper", "com.aliyun.citybrain.module.monitor.mapper",
                "com.aliyun.citybrain.module.monitor.records.mapper",
                "com.aliyun.citybrain.module.premonitor.mapper",
                "com.aliyun.citybrain.module.premonitor.records.mapper"
        },
        sqlSessionFactoryRef = "sqlSessionFactoryPrimary",
        sqlSessionTemplateRef = "sqlSessionTemplatePrimary")

public class DataSourcePrimaryConfig {

    // 注入数据源1
    @javax.annotation.Resource
    private DataSource primaryDataSource;

    private static final String MAPPER_LOCATION = "classpath*:mapper/**/*.xml";

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactoryPrimary() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(primaryDataSource);
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION);
        sqlSessionFactoryBean.setMapperLocations(resources);

        MybatisConfiguration configuration = new MybatisConfiguration();
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        configuration.addInterceptor(interceptor);
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    @Primary
    public SqlSessionTemplate sqlSessionTemplatePrimary() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactoryPrimary());
    }


}
```
