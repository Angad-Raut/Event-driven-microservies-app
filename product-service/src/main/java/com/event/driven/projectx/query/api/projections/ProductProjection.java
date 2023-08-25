package com.event.driven.projectx.query.api.projections;

import com.event.driven.projectx.command.api.entity.ProductDetails;
import com.event.driven.projectx.command.api.repository.ProductRepository;
import com.event.driven.projectx.query.api.payloads.FetchProductModel;
import com.event.driven.projectx.query.api.queries.FetchProductsQuery;
import com.event.driven.projectx.query.api.queries.FetchSingleProductQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    private ProductRepository productRepository;

    @Autowired
    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<FetchProductModel> fetchAllProducts(FetchProductsQuery query) {
         List<ProductDetails> productDetailsList= productRepository.findAll();
         List<FetchProductModel> responseList = productDetailsList.stream()
                 .map(data -> FetchProductModel.builder()
                         .productId(data.getProductId())
                         .productName(data.getProductName())
                         .productDescription(data.getProductDescription())
                         .productPrice(data.getProductPrice())
                         .productQty(data.getProductQty())
                         .build())
                 .collect(Collectors.toList());
         return responseList;
    }

    @QueryHandler
    public FetchProductModel fetchProductById(FetchSingleProductQuery query) {
        ProductDetails productDetails = productRepository.findByProductId(query.getProductId());
        FetchProductModel fetchProductModel = FetchProductModel.builder()
                .productId(productDetails.getProductId())
                .productName(productDetails.getProductName())
                .productDescription(productDetails.getProductDescription())
                .productPrice(productDetails.getProductPrice())
                .productQty(productDetails.getProductQty())
                .build();
        return fetchProductModel;
    }
}
