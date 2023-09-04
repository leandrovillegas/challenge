package com.vass.tienda.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vass.tienda.entity.Prices;
import com.vass.tienda.repository.PriceRepository;
import com.vass.tienda.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import java.util.List;

@RestController
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    PriceService priceService;

    @GetMapping
    public ResponseEntity<List<Prices>> listPrices(){
        List<Prices> pricesList = priceService.getAllPrices();
        if(pricesList.isEmpty()){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pricesList);
    }

    @GetMapping(value = "/particular")
    public ResponseEntity<Prices> getParticularPrice(@RequestParam(name = "date", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss") LocalDateTime  date, @RequestParam(name = "productId", required = true) Long productId, @RequestParam(name = "brandId", required = true) Long brandId) throws JsonProcessingException {
            Prices retPrice= priceService.getParticularPrice(date, productId, brandId);
            if(retPrice == null){
                return  ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(retPrice);
    }

    // Hadnle the required params
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParameter(MissingServletRequestParameterException ex) {
        String parameterName = ex.getParameterName();
        return new ResponseEntity<String>("The param '" + parameterName + "' is required.", HttpStatus.BAD_REQUEST);
    }


}
