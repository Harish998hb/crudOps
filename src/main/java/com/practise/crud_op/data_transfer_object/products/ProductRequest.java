package com.practise.crud_op.data_transfer_object.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductRequest {
    @JsonProperty("productName")
    String productName;
    @JsonProperty("productDescription")
    String productDescription;
    @JsonProperty("img")
    String img;
    @JsonProperty("price")
    Integer price;
    @JsonProperty("brand")
    String brand;
    @JsonProperty("category")
    String category;
}
