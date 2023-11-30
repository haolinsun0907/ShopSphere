package com.shop.demo.Product;

import com.shop.demo.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }
    @PostMapping
    public void createNewProduct(@Valid @RequestBody Product product){
        productService.createNewProduct(product);
    }
    @DeleteMapping(path = "{productId}")
    public void deleteProduct(@PathVariable("productId") String productId){
        productService.deleteProduct(productId);

    }

    @PutMapping(path="{productId}")
    public void updateProduct(
            @PathVariable("productId") String productId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) double price,
            @RequestParam(required = false) double quantity,
            @RequestParam(required = false) String url
    ){
        productService.updateProduct(productId,name, description,price,quantity,url);
    }

}
