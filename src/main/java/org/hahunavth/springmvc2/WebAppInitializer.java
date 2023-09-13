package org.hahunavth.springmvc2;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Creating objects of XmlWebApplicationContext
        // class
        XmlWebApplicationContext webApplicationContext
            = new XmlWebApplicationContext();
        webApplicationContext.setConfigLocation(
            "classpath:springmvc2/application-config.xml");

        // Creating a dispatcher servlet object
        DispatcherServlet dispatcherServlet
            = new DispatcherServlet(webApplicationContext);

        // Registering Dispatcher Servlet with Servlet
        // Context
        ServletRegistration
            .Dynamic myCustomDispatcherServlet
            = servletContext.addServlet(
                "myDispatcherServlet", dispatcherServlet);

        // Setting load on startup
        myCustomDispatcherServlet.setLoadOnStartup(1);

        // Adding mapping url
        myCustomDispatcherServlet.addMapping("/*");
    }
}
