package edu.yuhf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@EnableWebMvc			//开启MVC注解功能
public class MvcConfig  extends WebMvcConfigurerAdapter{
	
	//静态资源映射
	//addResourceHandler()方法指定url，该url后连接静态文件，对应着addResourceLoaction()方法目录所对应的静态文件
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("/img/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/layer/**").addResourceLocations("/layer/");
    }
	@Bean		//声明视图解析器，在返回逻辑视图名后拼装物理视图名
	public UrlBasedViewResolver getViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}
}
