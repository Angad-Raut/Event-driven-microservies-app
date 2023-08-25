package com.event.driven.projectx.command.api.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateProductCommand {
    @TargetAggregateIdentifier
    private String productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer productQty;
}
