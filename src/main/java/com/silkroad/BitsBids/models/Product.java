package com.silkroad.BitsBids.models;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long productId;

    @Column(nullable = false)
    private Long sellerId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Long initialPrice;

    @Column(nullable = false)
    private Long updatedPrice;

    @Column(nullable = false)
    private String productType;

    @Column(nullable = false)
    private List<String> images;

    @CreationTimestamp
    private Timestamp createdOn;

    @UpdateTimestamp
    private Timestamp updatedOn;

    public Product(Long sellerId, String productName, String description, Long initialPrice, Long updatedPrice,
            String productType, List<String> images) {
        this.sellerId = sellerId;
        this.productName = productName;
        this.description = description;
        this.initialPrice = initialPrice;
        this.updatedPrice = updatedPrice;
        this.productType = productType;
        this.images = images;
    }
}
