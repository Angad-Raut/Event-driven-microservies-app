package com.event.driven.projectx.command.api.aggregate;

import com.event.driven.projectx.command.api.commands.CreateProductCommand;
import com.event.driven.projectx.command.api.commands.DeleteProductCommand;
import com.event.driven.projectx.command.api.commands.UpdateProductCommand;
import com.event.driven.projectx.command.api.events.DeleteProductEvent;
import com.event.driven.projectx.command.api.events.ProductCreatedEvent;
import com.event.driven.projectx.command.api.events.UpdateProductEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer productQty;

    public ProductAggregate(){

    }

    @CommandHandler
    public void createProduct(CreateProductCommand createProductCommand) {
        //add some validations here as well if required
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(createProductCommand,productCreatedEvent);
        AggregateLifecycle.apply(productCreatedEvent);
    }

    @CommandHandler
    public void updateProduct(UpdateProductCommand updateProductCommand) {
        //add some validations & update details
        UpdateProductEvent updateProductEvent = new UpdateProductEvent();
        BeanUtils.copyProperties(updateProductCommand,updateProductEvent);
        AggregateLifecycle.apply(updateProductEvent);
    }

    @CommandHandler
    public void deleteProduct(DeleteProductCommand deleteProductCommand) {
        //add validations & delete details
        DeleteProductEvent deleteProductEvent = new DeleteProductEvent();
        BeanUtils.copyProperties(deleteProductCommand,deleteProductEvent);
        AggregateLifecycle.apply(deleteProductEvent);
    }

    @EventSourcingHandler
    public void onCreate(ProductCreatedEvent productCreatedEvent) {
        this.productId=productCreatedEvent.getProductId();
        this.productName=productCreatedEvent.getProductName();
        this.productDescription=productCreatedEvent.getProductDescription();
        this.productPrice=productCreatedEvent.getProductPrice();
        this.productQty=productCreatedEvent.getProductQty();
    }

    @EventSourcingHandler
    public void onUpdate(UpdateProductEvent updateProductEvent) {
        this.productId=updateProductEvent.getProductId();
        this.productName=updateProductEvent.getProductName();
        this.productPrice=updateProductEvent.getProductPrice();
        this.productDescription=updateProductEvent.getProductDescription();
        this.productQty=updateProductEvent.getProductQty();
    }

    @EventSourcingHandler
    public void onDelete(DeleteProductEvent deleteProductEvent) {
        this.productId=deleteProductEvent.getProductId();
        AggregateLifecycle.markDeleted();
    }
}
