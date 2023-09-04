package com.vass.tienda.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "tbl_prices")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotNull
    @Column(name = "brand_id")
    private Long brand_id;

    @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    @Column(name = "start_date")
    private LocalDateTime start_date;

    @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    @Column(name = "end_date")
    private LocalDateTime end_date;


    @Column(name = "price_list", nullable = false)
    private Long price_list;
    @Column(name = "product_id", nullable = false)
    private Long product_id;

    @JsonIgnore
    @Column(name = "priority")
    private Long priority;

    @Column(name = "price")
    private Double price;

    @JsonIgnore
    @Column(name = "curr")
    private String curr;

}
