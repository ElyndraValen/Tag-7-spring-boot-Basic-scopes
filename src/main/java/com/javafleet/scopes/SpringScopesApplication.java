package com.javafleet.scopes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Basic - Tag 7: Scopes in Spring
 * 
 * Hauptanwendung für Scope-Demonstrationen
 * 
 * @author Java Fleet Systems Consulting
 * @version 1.0
 */
@SpringBootApplication
public class SpringScopesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringScopesApplication.class, args);
        System.out.println("\n" +
                "╔══════════════════════════════════════════════════════════╗\n" +
                "║   Spring Boot Basic - Tag 7: Scopes                     ║\n" +
                "║   Java Fleet Systems Consulting                         ║\n" +
                "╠══════════════════════════════════════════════════════════╣\n" +
                "║   Server gestartet!                                      ║\n" +
                "║   URL: http://localhost:8080                             ║\n" +
                "║                                                          ║\n" +
                "║   Verfügbare Endpoints:                                  ║\n" +
                "║   • /                     - Übersicht                    ║\n" +
                "║   • /singleton            - Singleton Demo               ║\n" +
                "║   • /prototype            - Prototype Demo               ║\n" +
                "║   • /request              - Request Scope Demo           ║\n" +
                "║   • /session              - Session Scope Demo           ║\n" +
                "║   • /application          - Application Scope Demo       ║\n" +
                "║   • /comparison           - Scope Vergleich              ║\n" +
                "╚══════════════════════════════════════════════════════════╝\n");
    }
}
