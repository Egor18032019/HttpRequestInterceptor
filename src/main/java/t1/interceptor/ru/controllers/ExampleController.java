package t1.interceptor.ru.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api")
public class ExampleController {
    @GetMapping
    @Operation(summary = "Пример что бы показать логирование ответа.", description = "Возвращает ответ от сервера в формате строки. ")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Ответ от сервера.");
    }
}
