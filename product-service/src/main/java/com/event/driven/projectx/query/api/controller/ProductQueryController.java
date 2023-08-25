package com.event.driven.projectx.query.api.controller;
import com.event.driven.projectx.query.api.dto.SingleProductFetchRequest;
import com.event.driven.projectx.query.api.payloads.FetchProductModel;
import com.event.driven.projectx.query.api.queries.FetchProductsQuery;
import com.event.driven.projectx.query.api.queries.FetchSingleProductQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductQueryController {

    private QueryGateway queryGateway;

    @Autowired
    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/getAllProducts")
    public List<FetchProductModel> getAllProducts(){
        FetchProductsQuery fetchProductsQuery=new FetchProductsQuery();
        List<FetchProductModel> responseList = queryGateway.query(fetchProductsQuery,
                ResponseTypes.multipleInstancesOf(FetchProductModel.class))
                .join();
         return responseList;
    }

    @PostMapping("/getProductDetailsById")
    public FetchProductModel getProductById(@RequestBody SingleProductFetchRequest dto) {
        FetchSingleProductQuery fetchSingleProductQuery = new FetchSingleProductQuery(dto.getProductId());
        FetchProductModel response = queryGateway.query(fetchSingleProductQuery,ResponseTypes.instanceOf(FetchProductModel.class))
                .join();
        return response;
    }
}
