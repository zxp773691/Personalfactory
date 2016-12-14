package mybatis.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {
	
	@Bean(name="mapperScannerConfigurer")
	public MapperScannerConfigurer getMapperScannerConfigurer(){
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setBasePackage("edu.yuhf.dao");
		return mapperScannerConfigurer;
	}
}
