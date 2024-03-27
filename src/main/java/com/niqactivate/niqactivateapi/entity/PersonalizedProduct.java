package com.niqactivate.niqactivateapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="personalized_product_list", schema = "niq_activate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalizedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "shopper_id")
    private String shopperId;


    @Column(name = "product_id")
    private String productId;


    @Column(name = "relevancy_score")
    private BigDecimal relevancyScore;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    private ProductDetails productDetails;

    public PersonalizedProduct(String shopperId, String productId, BigDecimal relevancyScore) {
        this.shopperId = shopperId;
        this.productId = productId;
        this.relevancyScore = relevancyScore;
    }
}
