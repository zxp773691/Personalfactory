package mybatis.config;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.pagehelper.PageHelper;

@Configuration					//声明当前类是一个配置类(替代xml文件的类)
@EnableTransactionManagement
@MapperScan("edu.yuhf.mapper")
public class MyBatisConfig {
	
	@Resource(name="dataSource")
	public DataSource dataSource;
	
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactoryBean getSqlSessionFactory(DataSource dataSource){
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(); 
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setTypeAliasesPackage("edu.yuhf.entity");	//设置实体别名
		//set page plugin
		PageHelper pageHelper=new PageHelper();
		Properties prop=new Properties();
		prop.setProperty("dialect", "oracle");
		prop.setProperty("reasonable", "true");
		prop.setProperty("pageSizeZero", "true");
		pageHelper.setProperties(prop);
		sessionFactory.setPlugins(new Interceptor[]{pageHelper});
		try {
			sessionFactory.setMapperLocations(resourcePatternResolver.getResources("classpath*:edu/yuhf/mapper/*Mapper.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return sessionFactory;
	}
	
	@Bean
	PlatformTransactionManager transactionManager(DataSource dataSource){
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}
}
