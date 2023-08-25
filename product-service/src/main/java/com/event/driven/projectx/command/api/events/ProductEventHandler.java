package com.event.driven.projectx.command.api.events;

import com.event.driven.projectx.command.api.entity.ProductDetails;
import com.event.driven.projectx.command.api.repository.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductEventHandler {

    private ProductRepository productRepository;

    @Autowired
    public ProductEventHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void onCreate(ProductCreatedEvent event) {
        ProductDetails productDetails = new ProductDetails();
        BeanUtils.copyProperties(event,productDetails);
        productRepository.save(productDetails);
    }

    @EventHandler
    public void onUpdate(UpdateProductEvent event) {
        ProductDetails productDetails = new ProductDetails();
        BeanUtils.copyProperties(event,productDetails);
        productRepository.save(productDetails);
    }

    @EventHandler
    public void onDelete(DeleteProductEvent event) {
        productRepository.deleteById(event.getProductId());
    }
}
