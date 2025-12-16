package com.javafleet.scopes.service;

import com.javafleet.scopes.model.Counter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * Application Scope Demo
 * 
 * EINE Instanz für die gesamte Web-Anwendung!
 * Shared über alle Sessions und Requests.
 * Ähnlich wie Singleton, aber speziell für ServletContext.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ApplicationScopedBean {
    
    private final Counter counter;
    
    public ApplicationScopedBean() {
        this.counter = new Counter();
        System.out.println("🔴 ApplicationScopedBean erstellt - Counter: " + counter.getInstanceId());
    }
    
    public Counter getCounter() {
        return counter;
    }
    
    public void incrementCounter() {
        counter.increment();
        System.out.println("➕ Application Counter: " + counter.getCount() + " (ID: " + counter.getInstanceId() + ")");
    }
    
    public String getInfo() {
        return String.format(
            "Application Scope - Eine Instanz für alle User!\n" +
            "Counter ID: %s\n" +
            "Aktueller Wert: %d\n" +
            "Erstellt um: %s\n" +
            "Geteilt über alle Sessions!",
            counter.getInstanceId(),
            counter.getCount(),
            counter.getCreatedAt()
        );
    }
}
