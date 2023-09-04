package com.vass.tienda.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vass.tienda.entity.Prices;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface PriceService {
    public List<Prices> getAllPrices();
    public Prices getPrice(Long id);
    public Prices getParticularPrice(LocalDateTime date, Long productId, Long brandId) throws JsonProcessingException;

}
