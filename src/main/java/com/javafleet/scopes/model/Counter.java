package com.javafleet.scopes.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Counter-Bean für Scope-Demonstrationen
 */
public class Counter {
    
    private final String instanceId;
    private int count;
    private final LocalDateTime createdAt;
    
    public Counter() {
        this.instanceId = UUID.randomUUID().toString().substring(0, 8);
        this.count = 0;
        this.createdAt = LocalDateTime.now();
        System.out.println("✨ Neue Counter-Instanz erstellt: " + instanceId);
    }
    
    public void increment() {
        count++;
    }
    
    public int getCount() {
        return count;
    }
    
    public String getInstanceId() {
        return instanceId;
    }
    
    public String getCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return createdAt.format(formatter);
    }
    
    @Override
    public String toString() {
        return String.format("Counter[id=%s, count=%d, created=%s]", 
                           instanceId, count, getCreatedAt());
    }
}
