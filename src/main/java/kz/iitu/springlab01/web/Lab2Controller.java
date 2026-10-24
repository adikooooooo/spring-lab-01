package kz.iitu.springlab01.web;

import kz.iitu.springlab01.lifecycle.LifecycleDemo;
import kz.iitu.springlab01.notify.NotificationService;
import kz.iitu.springlab01.scope.TicketOffice;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/lab2")
public class Lab2Controller {

    private final NotificationService notifications;
    private final LifecycleDemo lifecycle;      // Поле для жизненного цикла
    private final TicketOffice ticketOffice;    // Поле для скоупов

    // Конструктор для внедрения зависимостей
    public Lab2Controller(NotificationService notifications,
                          LifecycleDemo lifecycle,
                          TicketOffice ticketOffice) {
        this.notifications = notifications;
        this.lifecycle = lifecycle;
        this.ticketOffice = ticketOffice;
    }

    @GetMapping("/notify")
    public Map<String, Object> notify(@RequestParam(defaultValue = "Hello") String text) {
        return Map.of("primary",   notifications.viaPrimary(text),
                "console",   notifications.viaConsole(text),
                "all",       notifications.viaAll(text),
                "beanNames", notifications.names());
    }

    @GetMapping("/custom")
    public Map<String, Object> custom(@RequestParam(defaultValue = "iitu spring framework") String text) {
        return Map.of("variant", 1,
                "result",  notifications.viaUpper(text));
    }

    // <--- СЮДА ВСТАВЛЯЕТСЯ МЕТОД ДЛЯ ЖИЗНЕННОГО ЦИКЛА --->
    @GetMapping("/lifecycle")
    public List<String> lifecycle() {
        return lifecycle.events();
    }

    @GetMapping("/scopes")
    public Map<String, Object> scopes() {
        return ticketOffice.demo();
    }
}