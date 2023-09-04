package com.vass.tienda.repository;

import com.vass.tienda.entity.Prices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Prices,Long> {

}
