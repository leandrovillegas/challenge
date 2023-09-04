package com.vass.tienda.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
public class DefaultController {
    @RequestMapping("/**") // Capture all uris not handler
    public ResponseEntity<String> handleDefault() {
        String mensaje = "The endpoint you are trying to consume is not the correct one. Try /prices or /prices/particular";
        return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
    }
}