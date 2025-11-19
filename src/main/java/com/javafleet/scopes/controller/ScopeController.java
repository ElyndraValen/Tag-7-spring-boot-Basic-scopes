package com.javafleet.scopes.controller;

import com.javafleet.scopes.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Main Controller für Scope-Demonstrationen
 */
@Controller
public class ScopeController {
    
    @Autowired
    private ApplicationContext context;
    
    @Autowired
    private SingletonService singletonService;
    
    @Autowired
    private RequestScopedBean requestScopedBean;
    
    @Autowired
    private SessionScopedBean sessionScopedBean;
    
    @Autowired
    private ApplicationScopedBean applicationScopedBean;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Spring Boot Basic - Tag 7: Scopes");
        return "index";
    }
    
    @GetMapping("/singleton")
    public String singleton(Model model) {
        singletonService.incrementCounter();
        
        model.addAttribute("scope", "Singleton");
        model.addAttribute("info", singletonService.getInfo());
        model.addAttribute("counterId", singletonService.getCounter().getInstanceId());
        model.addAttribute("count", singletonService.getCounter().getCount());
        model.addAttribute("description", 
            "Eine Instanz für die gesamte Anwendung. Wird beim Start erstellt und geteilt über alle Requests.");
        
        return "scope-demo";
    }
    
    @GetMapping("/prototype")
    public String prototype(Model model) {
        // Wir holen uns ZWEI Instanzen, um zu zeigen, dass sie unterschiedlich sind
        PrototypeService proto1 = context.getBean(PrototypeService.class);
        PrototypeService proto2 = context.getBean(PrototypeService.class);
        
        proto1.incrementCounter();
        proto2.incrementCounter();
        proto2.incrementCounter();
        
        model.addAttribute("scope", "Prototype");
        model.addAttribute("info", 
            String.format("Instanz 1: %s\nInstanz 2: %s", 
                         proto1.getInfo(), proto2.getInfo()));
        model.addAttribute("counterId", proto1.getCounter().getInstanceId());
        model.addAttribute("count", proto1.getCounter().getCount());
        model.addAttribute("description", 
            "Neue Instanz bei jedem getBean()-Aufruf. Jede Instanz ist unabhängig!");
        
        return "scope-demo";
    }
    
    @GetMapping("/request")
    public String request(Model model) {
        requestScopedBean.incrementCounter();
        
        model.addAttribute("scope", "Request");
        model.addAttribute("info", requestScopedBean.getInfo());
        model.addAttribute("counterId", requestScopedBean.getCounter().getInstanceId());
        model.addAttribute("count", requestScopedBean.getCounter().getCount());
        model.addAttribute("description", 
            "Eine Instanz pro HTTP-Request. Wird nach Request-Bearbeitung verworfen. " +
            "Bei jedem Reload wird ein neuer Counter erstellt!");
        
        return "scope-demo";
    }
    
    @GetMapping("/session")
    public String session(Model model) {
        sessionScopedBean.incrementCounter();
        
        model.addAttribute("scope", "Session");
        model.addAttribute("info", sessionScopedBean.getInfo());
        model.addAttribute("counterId", sessionScopedBean.getCounter().getInstanceId());
        model.addAttribute("count", sessionScopedBean.getCounter().getCount());
        model.addAttribute("description", 
            "Eine Instanz pro User-Session. Bleibt erhalten während der Session. " +
            "Der Counter zählt weiter, auch wenn du die Seite reloadest!");
        
        return "scope-demo";
    }
    
    @GetMapping("/application")
    public String application(Model model) {
        applicationScopedBean.incrementCounter();
        
        model.addAttribute("scope", "Application");
        model.addAttribute("info", applicationScopedBean.getInfo());
        model.addAttribute("counterId", applicationScopedBean.getCounter().getInstanceId());
        model.addAttribute("count", applicationScopedBean.getCounter().getCount());
        model.addAttribute("description", 
            "Eine Instanz für die gesamte Anwendung, geteilt über ALLE User. " +
            "Dieser Counter zählt für ALLE Besucher weiter!");
        
        return "scope-demo";
    }
    
    @GetMapping("/comparison")
    public String comparison(Model model) {
        model.addAttribute("singleton", singletonService.getInfo());
        model.addAttribute("request", requestScopedBean.getInfo());
        model.addAttribute("session", sessionScopedBean.getInfo());
        model.addAttribute("application", applicationScopedBean.getInfo());
        
        return "comparison";
    }
}
