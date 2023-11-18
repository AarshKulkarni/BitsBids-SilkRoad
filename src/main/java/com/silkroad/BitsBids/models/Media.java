package com.silkroad.BitsBids.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Media")
public class Media {
    @Id
    private Long mediaId;
    private Long productId;
    private String productType;

}
