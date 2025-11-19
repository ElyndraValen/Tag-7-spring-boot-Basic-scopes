package com.javafleet.scopes.service;

import com.javafleet.scopes.model.Counter;
import org.springframework.stereotype.Service;

/**
 * Singleton Scope Demo
 * 
 * EINE Instanz für die gesamte Anwendung!
 * Default Scope in Spring - wird beim Start erstellt.
 */
@Service
public class SingletonService {
    
    private final Counter counter;
    
    public SingletonService() {
        this.counter = new Counter();
        System.out.println("🔵 SingletonService initialisiert mit Counter: " + counter.getInstanceId());
    }
    
    public Counter getCounter() {
        return counter;
    }
    
    public void incrementCounter() {
        counter.increment();
        System.out.println("➕ Singleton Counter: " + counter.getCount() + " (ID: " + counter.getInstanceId() + ")");
    }
    
    public String getInfo() {
        return String.format(
            "Singleton Service - Immer dieselbe Instanz!\n" +
            "Counter ID: %s\n" +
            "Aktueller Wert: %d\n" +
            "Erstellt um: %s",
            counter.getInstanceId(),
            counter.getCount(),
            counter.getCreatedAt()
        );
    }
}
