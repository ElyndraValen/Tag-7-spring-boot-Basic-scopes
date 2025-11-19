# Spring Boot Basic - Tag 7: Scopes in Spring

![Java Fleet Logo](https://img.shields.io/badge/Java%20Fleet-Systems%20Consulting-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-green)
![Java](https://img.shields.io/badge/Java-21-blue)

## 🎯 Über dieses Projekt

Dieses Projekt demonstriert alle wichtigen **Bean Scopes in Spring Boot**:
- **Singleton** (Default)
- **Prototype**
- **Request**
- **Session**
- **Application**

Erstellt von **Java Fleet Systems Consulting** für den Kurs **Spring Boot Basic - Tag 7**.

---

## 📚 Was sind Scopes?

**Scopes** in Spring bestimmen:
1. **Wie lange eine Bean-Instanz lebt**
2. **Wie viele Instanzen** Spring erstellt
3. **Wann** Spring neue Instanzen erstellt

Das ist entscheidend für:
- ✅ **Performance** (nicht zu viele Instanzen!)
- ✅ **Memory Management** (wann werden Instanzen freigegeben?)
- ✅ **Thread-Safety** (geteilte vs. isolierte Instanzen)

---

## 🚀 Quick Start

### Voraussetzungen
- ☕ **Java 21** (LTS)
- 🔧 **Maven 3.9+**
- 💻 **IDE** (IntelliJ IDEA, Eclipse, VS Code, NetBeans)

### Installation & Start

```bash
# 1. Repository klonen oder ZIP entpacken
cd SpringBootBasic-Tag7-Scopes

# 2. Dependencies installieren
mvn clean install

# 3. Anwendung starten
mvn spring-boot:run
```

**Alternativ über IDE:**
- Öffne das Projekt in deiner IDE
- Führe `SpringScopesApplication.java` aus

### Zugriff
Nach dem Start:
```
🌐 URL: http://localhost:8080
📊 Übersicht: http://localhost:8080
```

---

## 📂 Projektstruktur

```
SpringBootBasic-Tag7-Scopes/
│
├── src/main/java/com/javafleet/scopes/
│   ├── SpringScopesApplication.java        # Hauptklasse
│   ├── controller/
│   │   └── ScopeController.java            # Web Controller
│   ├── service/
│   │   ├── SingletonService.java           # Singleton Demo
│   │   ├── PrototypeService.java           # Prototype Demo
│   │   ├── RequestScopedBean.java          # Request Scope
│   │   ├── SessionScopedBean.java          # Session Scope
│   │   └── ApplicationScopedBean.java      # Application Scope
│   └── model/
│       └── Counter.java                    # Counter Bean
│
├── src/main/resources/
│   ├── templates/
│   │   ├── index.html                      # Hauptseite
│   │   ├── scope-demo.html                 # Einzelne Scope-Demo
│   │   └── comparison.html                 # Scope-Vergleich
│   ├── static/css/
│   │   └── style.css                       # Dark Orange Style
│   ├── application.properties              # Spring Config
│   └── banner.txt                          # Custom Banner
│
├── pom.xml                                  # Maven Dependencies
└── README.md                                # Diese Datei
```

---

## 🎨 Die 5 Scopes im Detail

### 🔵 1. Singleton Scope (Default)

**Was ist das?**
- ✅ **EINE** Instanz für die gesamte Anwendung
- ✅ Wird beim **ApplicationContext-Start** erstellt
- ✅ Von **allen** Threads/Requests geteilt
- ✅ **Default-Scope** in Spring

**Code-Beispiel:**
```java
@Service  // Singleton ist Default!
public class SingletonService {
    private Counter counter = new Counter();
    
    public void increment() {
        counter.increment();
    }
}
```

**Wann nutzen?**
- ✅ Services
- ✅ Repositories
- ✅ Stateless Beans
- ✅ Configuration

**Wichtig:**
- ⚠️ **Thread-Safety** beachten!
- ⚠️ Keine mutable Instance-Variablen ohne Synchronisierung!

---

### 🟢 2. Prototype Scope

**Was ist das?**
- ✅ **NEUE** Instanz bei jedem `getBean()`-Aufruf
- ✅ Jede Instanz ist **unabhängig**
- ✅ Spring verwaltet **NICHT** den Lifecycle nach Erstellung

**Code-Beispiel:**
```java
@Service
@Scope("prototype")
public class PrototypeService {
    private Counter counter = new Counter();
    
    public void increment() {
        counter.increment();
    }
}
```

**Wann nutzen?**
- ✅ Commands
- ✅ Stateful Objekte
- ✅ Tasks
- ✅ DTOs mit Logik

**Wichtig:**
- ⚠️ `@PreDestroy` wird **NICHT** automatisch aufgerufen!
- ⚠️ Kann zu **Memory-Leaks** führen wenn nicht aufgeräumt
- ⚠️ Teurer als Singleton

---

### 🟡 3. Request Scope

**Was ist das?**
- ✅ **EINE** Instanz pro HTTP-Request
- ✅ Wird nach Request-Bearbeitung verworfen
- ✅ Nur in **Web-Anwendungen** verfügbar

**Code-Beispiel:**
```java
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST,
      proxyMode = ScopedProxyMode.TARGET_CLASS)  // WICHTIG!
public class RequestScopedBean {
    private Counter counter = new Counter();
    
    public void increment() {
        counter.increment();
    }
}
```

**Wann nutzen?**
- ✅ Request-spezifische Daten
- ✅ User Input
- ✅ Request Context
- ✅ Temporäre Daten

**Wichtig:**
- ⚠️ `proxyMode = TARGET_CLASS` nötig für Injection in Singleton!
- ⚠️ Nur in Web-Context verfügbar

---

### 🟣 4. Session Scope

**Was ist das?**
- ✅ **EINE** Instanz pro HTTP-Session (pro User)
- ✅ Bleibt während der **gesamten Session** erhalten
- ✅ Nur in **Web-Anwendungen** verfügbar

**Code-Beispiel:**
```java
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION,
      proxyMode = ScopedProxyMode.TARGET_CLASS)  // WICHTIG!
public class SessionScopedBean {
    private Counter counter = new Counter();
    
    public void increment() {
        counter.increment();
    }
}
```

**Wann nutzen?**
- ✅ User-Daten
- ✅ Shopping Cart
- ✅ User Preferences
- ✅ Login State

**Wichtig:**
- ⚠️ `proxyMode = TARGET_CLASS` nötig!
- ⚠️ **Memory-Verbrauch** bei vielen aktiven Sessions
- ⚠️ Session-Timeout beachten

---

### 🔴 5. Application Scope

**Was ist das?**
- ✅ **EINE** Instanz für die gesamte Web-Anwendung
- ✅ Geteilt über **alle Sessions und Requests**
- ✅ Gebunden an den **ServletContext**
- ✅ Ähnlich wie Singleton, aber Web-spezifisch

**Code-Beispiel:**
```java
@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION,
      proxyMode = ScopedProxyMode.TARGET_CLASS)  // WICHTIG!
public class ApplicationScopedBean {
    private Counter counter = new Counter();
    
    public void increment() {
        counter.increment();
    }
}
```

**Wann nutzen?**
- ✅ Global Counters
- ✅ App-wide Caches
- ✅ Statistics
- ✅ Shared Configuration

**Wichtig:**
- ⚠️ `proxyMode = TARGET_CLASS` nötig!
- ⚠️ **Thread-Safety** beachten (wie bei Singleton)!
- ⚠️ Meist ist Singleton ausreichend

---

## 🧪 Experimente & Tests

### Experiment 1: Reload im selben Tab
**Erwartung:**
- 🔵 Singleton: Counter steigt (1 → 2 → 3)
- 🟢 Prototype: Neue Instanzen (Counter bei 1 oder 2)
- 🟡 Request: Counter bleibt bei 1 (neue Instanz!)
- 🟣 Session: Counter steigt (1 → 2 → 3)
- 🔴 Application: Counter steigt (1 → 2 → 3)

### Experiment 2: Neuer Tab (gleiche Session)
**Erwartung:**
- 🔵 Singleton: Counter steigt weiter
- 🟢 Prototype: Neue Instanzen
- 🟡 Request: Counter bei 1
- 🟣 Session: Counter steigt weiter (gleiche Session!)
- 🔴 Application: Counter steigt weiter

### Experiment 3: Inkognito-Modus (neue Session)
**Erwartung:**
- 🔵 Singleton: Counter steigt weiter
- 🟢 Prototype: Neue Instanzen
- 🟡 Request: Counter bei 1
- 🟣 Session: Counter bei 1 (NEUE Session!)
- 🔴 Application: Counter steigt weiter

---

## 💡 Best Practices

### ✅ DO's
1. **Nutze Singleton** für die meisten Beans (90%+)
2. **Achte auf Thread-Safety** bei Singleton und Application
3. **Nutze `proxyMode`** bei Web-Scopes in Singleton-Beans
4. **Dokumentiere** warum du einen bestimmten Scope nutzt
5. **Teste** das Verhalten mit mehreren Usern/Sessions

### ❌ DON'Ts
1. **Keine mutable State** in Singleton ohne Synchronisierung
2. **Nicht zu viele Prototype-Beans** (Performance!)
3. **Session-Scope nicht** für große Objekte
4. **Prototype-Beans** nicht in Singleton injizieren (ohne Provider)
5. **Application-Scope** selten nötig - meist ist Singleton besser

---

## 🔧 Technologie-Stack

- **Spring Boot**: 3.2.0
- **Java**: 21 (LTS)
- **Maven**: 3.9+
- **Thymeleaf**: Template Engine
- **Lombok**: Boilerplate Reduction
- **Spring DevTools**: Live Reload

---

## 📊 Vergleichstabelle

| Scope | Instanzen | Lebensdauer | Thread-Safe? | Web-Only? | ProxyMode nötig? |
|-------|-----------|-------------|--------------|-----------|------------------|
| **Singleton** | 1 pro Container | Container Lifetime | Ja, wenn stateless | Nein | Nein |
| **Prototype** | Neu bei getBean() | Bis GC | Nein | Nein | Nein |
| **Request** | 1 pro Request | Request Lifetime | Ja | Ja | Ja |
| **Session** | 1 pro Session | Session Lifetime | Ja | Ja | Ja |
| **Application** | 1 pro Context | Context Lifetime | Ja, wenn stateless | Ja | Ja |

---

## 🐛 Troubleshooting

### Problem: "No Scope registered for scope name 'request'"
**Lösung:** Du bist nicht in einem Web-Context. Request/Session/Application Scopes funktionieren nur in Web-Apps!

### Problem: Counter bei Session Scope wird nicht geteilt
**Lösung:** Prüfe, ob du verschiedene Session-Cookies hast. Inkognito-Modus erstellt neue Session!

### Problem: "Error creating bean with name 'scopedTarget.sessionScopedBean'"
**Lösung:** Füge `proxyMode = ScopedProxyMode.TARGET_CLASS` hinzu!

### Problem: Prototype-Beans verhalten sich wie Singleton
**Lösung:** Injizierst du sie in Singleton? Nutze `Provider<T>` oder `@Lookup`!

---

## 📝 Weitere Ressourcen

- [Spring Framework Dokumentation - Bean Scopes](https://docs.spring.io/spring-framework/reference/core/beans/factory-scopes.html)
- [Java Fleet Systems - Spring Boot Kurse](https://java-developer.online)
- [Baeldung - Spring Bean Scopes](https://www.baeldung.com/spring-bean-scopes)

---

## 👥 Über Java Fleet Systems Consulting

**Java Fleet Systems Consulting** ist dein Partner für professionelle Java-Ausbildung.

Wir bieten:
- ✅ Praxisnahe Kurse
- ✅ Erfahrene Dozenten
- ✅ Moderne Technologien
- ✅ Reale Projekte

**Webseite:** [java-developer.online](https://java-developer.online)  
**Kontakt:** support@java-developer.online

---

## 📄 Lizenz

Dieses Projekt ist Teil des Spring Boot Basic Kurses von Java Fleet Systems Consulting.

© 2024 Java Fleet Systems Consulting - Alle Rechte vorbehalten.

---

## 🎓 Nächste Schritte

Nach diesem Tag solltest du:
- ✅ Alle 5 Scopes verstehen
- ✅ Wissen, wann welcher Scope sinnvoll ist
- ✅ Thread-Safety Konzepte kennen
- ✅ `proxyMode` verstehen
- ✅ Praktische Erfahrung mit Scopes haben

**Weiter geht's mit:**
- Tag 8: Dependency Injection Deep Dive
- Tag 9: AOP (Aspect-Oriented Programming)
- Tag 10: Spring Data JPA

---

**Viel Erfolg beim Lernen! 🚀**

*Made with ❤️ by Java Fleet Systems Consulting*
