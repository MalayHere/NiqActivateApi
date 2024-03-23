package com.niqactivate.niqactivateapi.repository.interfaces;

import com.niqactivate.niqactivateapi.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, String> {


}
