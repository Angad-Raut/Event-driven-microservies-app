package com.event.driven.projectx.command.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product_details")
public class ProductDetails {
    @Id
    private String productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer productQty;
}
