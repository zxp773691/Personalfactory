package edu.yuhf.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;


@Configuration					//声明当前类是一个配置类(替代xml文件的类)
@EnableTransactionManagement
@ComponentScan({"edu.yuhf"})		//配置扫描位置
@MapperScan("edu.yuhf.mapper")
//通过注解读取属性文件，忽略源文件找不到的情况
@PropertySource(value={"classpath:jdbc.properties"},ignoreResourceNotFound=true)
public class DataSourceConfig implements EnvironmentAware{
	
	
    private Environment env;		//引入环境系统，配合@PropertySource注解，可以直接获取相关数据

    public void setEnvironment(Environment environment) {
        this.env=environment;
    }


	@Bean(name = "dataSource")		//声明该方法将返回一个Bean，Bean将放入容器中备用。druid是alibaba的开源数据缓冲池。
	public DruidDataSource getDruidDataSource() {
		try {
			String className=env.getProperty("jdbc.className");
			String connString=env.getProperty("jdbc.connString");
			String userName=env.getProperty("jdbc.userName");
			String password=env.getProperty("jdbc.password");
			DruidDataSource ds = new DruidDataSource();
			ds.setUrl(connString);
			ds.setDriverClassName(className);
			ds.setUsername(userName);
			ds.setPassword(password);
			ds.setMaxActive(10);
			return ds;
		} catch (Exception e) {
			return null;
		}
	}
	

}
