package com.event.driven.projectx.command.api.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductModel {
    private String productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer productQty;
}
