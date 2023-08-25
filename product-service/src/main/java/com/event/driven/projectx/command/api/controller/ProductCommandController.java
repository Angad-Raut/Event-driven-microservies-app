package com.event.driven.projectx.command.api.controller;

import com.event.driven.projectx.command.api.commands.CreateProductCommand;
import com.event.driven.projectx.command.api.commands.DeleteProductCommand;
import com.event.driven.projectx.command.api.commands.UpdateProductCommand;
import com.event.driven.projectx.command.api.payloads.EntityIdDto;
import com.event.driven.projectx.command.api.payloads.ProductModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class ProductCommandController {

    private CommandGateway commandGateway;

    @Autowired
    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestBody ProductModel productModel) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .productId(UUID.randomUUID().toString())
                .productName(productModel.getProductName())
                .productDescription(productModel.getProductDescription())
                .productPrice(productModel.getProductPrice())
                .productQty(productModel.getProductQty())
                .build();
        commandGateway.sendAndWait(createProductCommand);
        return "New product created successfully!!";
    }

    @PutMapping("/updateProduct")
    public String updateProduct(@RequestBody ProductModel productModel) {
        UpdateProductCommand updateProductCommand = UpdateProductCommand.builder()
                .productId(productModel.getProductId())
                .productName(productModel.getProductName())
                .productDescription(productModel.getProductDescription())
                .productPrice(productModel.getProductPrice())
                .productQty(productModel.getProductQty())
                .build();
        commandGateway.sendAndWait(updateProductCommand);
        return "Product details updated successfully!!";
    }

    @DeleteMapping("/deleteProduct")
    public String deleteProduct(@RequestBody EntityIdDto entityIdDto) {
        DeleteProductCommand deleteProductCommand = DeleteProductCommand.builder()
                .productId(entityIdDto.getEntityId())
                .build();
        commandGateway.sendAndWait(deleteProductCommand);
        return "Product deleted successfully!!";
    }
}
