package com.soap.springboot;

import com.soap.springboot.endpoint.AuthorEndpoint;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext appContext){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(appContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    // localhost:8080/ws/author.wsdl
    @Bean(name = "author")
    public DefaultWsdl11Definition authorWsdl11Definition(XsdSchema authorSchema){
        DefaultWsdl11Definition authorWsdl11Definition = new DefaultWsdl11Definition();
        authorWsdl11Definition.setPortTypeName("AuthorPort");
        authorWsdl11Definition.setLocationUri("/ws");
        authorWsdl11Definition.setTargetNamespace(AuthorEndpoint.NAMESPACE_URI);
        authorWsdl11Definition.setSchema(authorSchema);
        return authorWsdl11Definition;
    }

    @Bean
    public XsdSchema authorSchema(){
        return new SimpleXsdSchema(new ClassPathResource("/xsd/author.xsd"));
    }

    // localhost:8080/ws/book.wsdl
    @Bean(name = "book")
    public DefaultWsdl11Definition bookWsdl11Definition(XsdSchema bookSchema){
        DefaultWsdl11Definition bookWsdl11Definition = new DefaultWsdl11Definition();
        bookWsdl11Definition.setPortTypeName("BookPort");
        bookWsdl11Definition.setLocationUri("/ws");
        bookWsdl11Definition.setTargetNamespace(AuthorEndpoint.NAMESPACE_URI);
        bookWsdl11Definition.setSchema(bookSchema);
        return bookWsdl11Definition;
    }

    @Bean
    public XsdSchema bookSchema(){
        return new SimpleXsdSchema(new ClassPathResource("/xsd/book.xsd"));
    }
}
