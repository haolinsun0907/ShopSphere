package com.shop.demo.Service;

import com.shop.demo.Model.Product;
import com.shop.demo.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@Service
public class ProductService {
    private final ProductRepo productRepository;
    @Autowired
    public ProductService(ProductRepo productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    public void createNewProduct(Product product){
        Optional<Product> productOptional = productRepository.findUserByProductName(product.getProductName());
        if(productOptional.isPresent()){
            throw new IllegalStateException("product name taken");
        }
        productRepository.save(product);

    }
    @Transactional
    public void deleteProduct(String productId) {
        boolean exists = productRepository.existsById(productId);
        if (!exists){
            throw new IllegalStateException("product with id"+ productId +"does not exists");

        }
        productRepository.deleteById(productId);
    }

    public void updateProduct(String productId,
                              String name,
                              String description,
                              double price,
                              double quantity,
                              String url) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new IllegalStateException("User with id "+productId+"dose not exist"));
        if (name != null && !name.isEmpty() && !Objects.equals(product.getProductName(),name)){

            product.setProductName(name);

        }
        if (price>=0 && !Objects.equals(product.getProductPrice(),price)){

            product.setProductName(name);

        }
        if (description != null && !description.isEmpty() && !Objects.equals(product.getProductDescription(),description)){

            product.setProductDescription(description);

        }
        if (quantity >=0 && !Objects.equals(product.getProductStockQuantity(),quantity)){

            product.setProductStockQuantity(quantity);

        }
        if (url != null && !url.isEmpty() && !Objects.equals(product.getProductImageUrl(),url)){

            product.setProductImageUrl(url);

        }
        product.setUpdateAt();
    }
}
