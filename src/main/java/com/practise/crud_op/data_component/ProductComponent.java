package com.practise.crud_op.data_component;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="products")
public class ProductComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private  String productName;
    private  String productDescription;
    private  String img;
    private  Integer price;
    private  String category;
    private  String brand;

}
