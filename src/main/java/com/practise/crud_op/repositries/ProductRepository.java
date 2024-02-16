package com.practise.crud_op.repositries;

import com.practise.crud_op.data_component.ProductComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <ProductComponent,Integer> {
    List<ProductComponent> findByCategory(String category);
}
