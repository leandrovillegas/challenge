package com.vass.tienda.repository;

import com.vass.tienda.entity.Prices;
import com.vass.tienda.repository.PriceRepository;
import com.vass.tienda.service.PriceService;
import com.vass.tienda.service.PriceServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class PriceRepositoryTest {

    @Mock
    private PriceRepository priceRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        List<Prices> pricesList = new ArrayList<Prices>();
        Prices prices = Prices.builder()
                .id(1L)
                .brand_id(1L)
                .product_id(3555L)
                .price_list(1L)
                .start_date(LocalDateTime.now())
                .end_date(LocalDateTime.now().plus(1, ChronoUnit.DAYS))
                .price(Double.parseDouble("123.55"))
                .curr("EUR")
                .build();

        Prices prices1 = Prices.builder()
                .id(2L)
                .brand_id(1L)
                .product_id(3555L)
                .price_list(2L)
                .start_date(LocalDateTime.now().plus(2, ChronoUnit.DAYS))
                .end_date(LocalDateTime.now().plus(3, ChronoUnit.DAYS))
                .price(Double.parseDouble("80.55"))
                .curr("EUR")
                .build();

        pricesList.add(prices);
        pricesList.add(prices1);
        Mockito.when(priceRepository.findAll()).
                thenReturn(pricesList);
        Mockito.when(priceRepository.findById(1L)).
                thenReturn(Optional.ofNullable(prices));
        Mockito.when(priceRepository.findById(2L)).
                thenReturn(Optional.ofNullable(prices1));
    }


    @Test
    public void whenFindByAll_thenReturnListOfPrices(){
        List<Prices> pricesList= priceRepository.findAll();
        Assertions.assertThat(pricesList.size()).isEqualTo(2);
        Assertions.assertThat(pricesList.get(0).getProduct_id()).isEqualTo(3555L);


    }
    @Test
    public void whenFindById_thenReturnPrice(){
        Optional<Prices> prices= priceRepository.findById(1L);
        Optional<Prices> prices1= priceRepository.findById(2L);
        Assertions.assertThat(123.55).isEqualTo(prices.get().getPrice());
        Assertions.assertThat(prices.get().getId()).isEqualTo(1L);
        Assertions.assertThat(prices.get().getPrice_list()).isEqualTo(1L);
        Assertions.assertThat(prices1.get().getId()).isEqualTo(2L);
        Assertions.assertThat(prices1.get().getPrice_list()).isEqualTo(2L);
        Assertions.assertThat(80.55).isEqualTo(prices1.get().getPrice());
    }

}

