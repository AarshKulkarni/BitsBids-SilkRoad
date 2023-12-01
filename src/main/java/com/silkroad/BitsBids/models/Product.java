package com.silkroad.BitsBids.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
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
    private String productType;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private Boolean isBidable;

    @Column(nullable = false)
    private Long updatedPrice;

    @OneToMany(targetEntity = Bid.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "prodbid_fk",referencedColumnName = "productId")
    private List<Bid> previousBids;

    public Product(Long sellerId, String productName, String description, Long initialPrice,
                   String productType,String images, Long updatedPrice) {
        this.sellerId = sellerId;
        this.productName = productName;
        this.description = description;
        this.initialPrice = initialPrice;
        this.productType = productType;
        this.image = images;
        this.previousBids = new ArrayList<Bid>();
        this.isBidable = true;
        this.updatedPrice = updatedPrice;
    }
}
