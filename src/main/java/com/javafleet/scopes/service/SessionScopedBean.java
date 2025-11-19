package com.javafleet.scopes.service;

import com.javafleet.scopes.model.Counter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * Session Scope Demo
 * 
 * EINE Instanz pro HTTP-Session (pro User)!
 * Bleibt erhalten, solange die Session aktiv ist.
 * 
 * proxyMode = TARGET_CLASS wichtig für Injection in Singleton!
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionScopedBean {
    
    private final Counter counter;
    
    public SessionScopedBean() {
        this.counter = new Counter();
        System.out.println("🟣 SessionScopedBean erstellt für neue Session - Counter: " + counter.getInstanceId());
    }
    
    public Counter getCounter() {
        return counter;
    }
    
    public void incrementCounter() {
        counter.increment();
        System.out.println("➕ Session Counter: " + counter.getCount() + " (ID: " + counter.getInstanceId() + ")");
    }
    
    public String getInfo() {
        return String.format(
            "Session Scope - Eine Instanz pro User-Session!\n" +
            "Counter ID: %s\n" +
            "Aktueller Wert: %d\n" +
            "Erstellt um: %s\n" +
            "Bleibt erhalten während der gesamten Session!",
            counter.getInstanceId(),
            counter.getCount(),
            counter.getCreatedAt()
        );
    }
}
