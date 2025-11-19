package com.javafleet.scopes.service;

import com.javafleet.scopes.model.Counter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * Request Scope Demo
 * 
 * NEUE Instanz pro HTTP-Request!
 * Wird nach Request-Bearbeitung verworfen.
 * 
 * proxyMode = TARGET_CLASS wichtig für Injection in Singleton!
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopedBean {
    
    private final Counter counter;
    
    public RequestScopedBean() {
        this.counter = new Counter();
        System.out.println("🟡 RequestScopedBean erstellt für diesen Request - Counter: " + counter.getInstanceId());
    }
    
    public Counter getCounter() {
        return counter;
    }
    
    public void incrementCounter() {
        counter.increment();
        System.out.println("➕ Request Counter: " + counter.getCount() + " (ID: " + counter.getInstanceId() + ")");
    }
    
    public String getInfo() {
        return String.format(
            "Request Scope - Eine Instanz pro HTTP-Request!\n" +
            "Counter ID: %s\n" +
            "Aktueller Wert: %d\n" +
            "Erstellt um: %s\n" +
            "Wird nach diesem Request verworfen!",
            counter.getInstanceId(),
            counter.getCount(),
            counter.getCreatedAt()
        );
    }
}
