package com.vass.tienda.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vass.tienda.config.JacksonConfig;
import com.vass.tienda.entity.Prices;
import com.vass.tienda.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService{

    private final static Integer UNO = 1;

    private final PriceRepository priceRepository;

    @Override
    public List<Prices> getAllPrices() {
        return priceRepository.findAll();
    }

    @Override
    public Prices getPrice(Long id) {
        return priceRepository.findById(id).orElse(null);
    }

    @Override
    public Prices getParticularPrice(LocalDateTime date, Long productId, Long brandId) {

        List<Prices> pricesList = this.getAllPrices().stream().filter(prices -> {
            return prices.getProduct_id().equals(productId) && prices.getBrand_id().equals(brandId);
        }).toList();
        //Filtering the price list with date and priority
        Prices filteredPrice= this.findPriceWithinRange(date,pricesList);
        //Creating the particular Price ask in the challenge
        return Prices.builder()
                .product_id(filteredPrice.getProduct_id())
                .brand_id(filteredPrice.getBrand_id())
                .price_list(filteredPrice.getPrice_list())
                .start_date(filteredPrice.getStart_date())
                .end_date(filteredPrice.getEnd_date())
                .price(filteredPrice.getPrice())
                .build();
    }

    private Prices findPriceWithinRange(LocalDateTime testDate , List<Prices> pricesList) {
        //Filtering the date
        List<Prices> filteredPriceList= pricesList.stream().filter(prices -> {
            return prices.getStart_date().isBefore(testDate) && prices.getEnd_date().isAfter(testDate);
        }).toList();
        //Filter the priority
        if(filteredPriceList.size()>UNO)
        {
            return filteredPriceList.stream().max(Comparator.comparing(Prices::getPriority)).get();
        }
        return filteredPriceList.get(0);
    }

}
