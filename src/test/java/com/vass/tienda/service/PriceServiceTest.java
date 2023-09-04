package com.vass.tienda.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.vass.tienda.entity.Prices;
import com.vass.tienda.repository.PriceRepository;
import com.vass.tienda.service.PriceService;
import com.vass.tienda.service.PriceServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PriceServiceTest {

//    @MockBean
//    private PriceRepository priceRepository;

    @MockBean
    private PriceService priceService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        List<Prices> pricesList = new ArrayList<Prices>();
        Prices prices = Prices.builder()
                .id(1L)
                .brand_id(1L)
                .product_id(3555L)
                .price_list(1L)
                .start_date(LocalDateTime.parse("2020-06-14-00.00.00", DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss")))
                .end_date(LocalDateTime.parse("2020-12-31-23.59.59", DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss")))
                .price(Double.parseDouble("99.35"))
                .curr("EUR")
                .build();

        Prices prices1 = Prices.builder()
                .id(2L)
                .brand_id(1L)
                .product_id(3555L)
                .price_list(2L)
                .start_date(LocalDateTime.parse("2020-06-14-15.00.00", DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss")))
                .end_date(LocalDateTime.parse("2020-06-14-18.30.00", DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss")))
                .price(Double.parseDouble("80.55"))
                .curr("EUR")
                .build();

        Prices prices2 = Prices.builder()
                .id(3L)
                .brand_id(1L)
                .product_id(3555L)
                .price_list(3L)
                .start_date(LocalDateTime.parse("2020-06-15-00.00.00", DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss")))
                .end_date(LocalDateTime.parse("2020-06-15-11.00.00", DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss")))
                .price(Double.parseDouble("25.00"))
                .curr("EUR")
                .build();

        Prices prices3 = Prices.builder()
                .id(3L)
                .brand_id(1L)
                .product_id(3555L)
                .price_list(4L)
                .start_date(LocalDateTime.parse("2020-06-15-16.00.00", DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss")))
                .end_date(LocalDateTime.parse("2020-12-31-23.59.59", DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss")))
                .price(Double.parseDouble("15.00"))
                .curr("EUR")
                .build();

        pricesList.add(prices);
        pricesList.add(prices1);
        pricesList.add(prices2);
        pricesList.add(prices3);

        Mockito.when(priceService.getPrice(1L))
                .thenReturn(prices);
        Mockito.when(priceService.getPrice(2L))
                .thenReturn(prices1);
        Mockito.when(priceService.getPrice(3L))
                .thenReturn(prices2);
        Mockito.when(priceService.getPrice(4L))
                .thenReturn(prices3);
        Mockito.when(priceService.getAllPrices()).thenReturn(pricesList);


    }

    @Test
    public void whenValidGetID_ThenReturnPrice(){
        Prices found = priceService.getPrice(1L);
        Assertions.assertThat(found.getId()).isEqualTo(1L);
        Assertions.assertThat(found.getPrice()).isEqualTo(99.35);
        Assertions.assertThat(found.getProduct_id()).isEqualTo(3555L);

    }

    @Test
    public void whenGetAllPrices_ThenReturnListOfPrices(){
        List<Prices> founds = priceService.getAllPrices();
        Assertions.assertThat(founds.get(0).getId()).isEqualTo(1L);
        Assertions.assertThat(founds.size()).isEqualTo(4);
        Assertions.assertThat(founds.get(1).getPrice_list()).isEqualTo(2L);

    }
    @Test
    public void whenValidParamsIn_ThenReturnPriceFiltered() throws JsonProcessingException {
        //GIVEN
        LocalDateTime localDateTime= LocalDateTime.parse("2020-06-14-10.00.00", DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));
        Long productId= 3555L;
        Long brandId= 1L;
        //WHEN
        Prices prices = priceService.getParticularPrice(localDateTime,productId,brandId);
        //THEN
        Assertions.assertThat(prices.getPrice_list()).isEqualTo(priceService.getPrice(1L).getPrice_list());

    }

}
