package com.javafleet.scopes.service;

import com.javafleet.scopes.model.Counter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Prototype Scope Demo
 * 
 * NEUE Instanz bei jedem Request vom ApplicationContext!
 * Nicht Thread-Safe, da jeder eine eigene Instanz bekommt.
 */
@Component
@Scope("prototype")
public class PrototypeService {
    
    private final Counter counter;
    
    public PrototypeService() {
        this.counter = new Counter();
        System.out.println("🟢 PrototypeService NEUE Instanz erstellt mit Counter: " + counter.getInstanceId());
    }
    
    public Counter getCounter() {
        return counter;
    }
    
    public void incrementCounter() {
        counter.increment();
        System.out.println("➕ Prototype Counter: " + counter.getCount() + " (ID: " + counter.getInstanceId() + ")");
    }
    
    public String getInfo() {
        return String.format(
            "Prototype Service - Jedes Mal eine neue Instanz!\n" +
            "Counter ID: %s\n" +
            "Aktueller Wert: %d\n" +
            "Erstellt um: %s",
            counter.getInstanceId(),
            counter.getCount(),
            counter.getCreatedAt()
        );
    }
}
