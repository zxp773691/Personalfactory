package edu.yuhf.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import mybatis.config.MyBatisConfig;
import mybatis.config.MyBatisMapperScannerConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
//实现WebApplicationinitializer的类都可以在web应用程序启动时被加载
public class WebInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        //该类可以注册配置文件类
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        //注册DataSourceConfig类和MvcConfig类
        ctx.register(DataSourceConfig.class,MyBatisConfig.class,MyBatisMapperScannerConfig.class,MvcConfig.class);  
        ctx.setServletContext(servletContext);    
        
        //创建一个Servlet
        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");  
        servlet.setLoadOnStartup(1);  
        
          
    }  
}
