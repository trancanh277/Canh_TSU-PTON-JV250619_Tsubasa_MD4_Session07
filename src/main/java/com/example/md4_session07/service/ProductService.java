package com.example.md4_session07.service;

import com.example.md4_session07.model.dto.ProductDTO;
import com.example.md4_session07.model.entity.Product;
import com.example.md4_session07.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product save(ProductDTO productDTO) {
        Product product = convertProductDTOToEntity(productDTO);
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Product update(Long id, ProductDTO productDTO) {
        Product product = getProductById(id);
        if (product != null) {
            Product updatedProduct = convertProductDTOToEntity(productDTO);
            updatedProduct.setId(product.getId());
            try {
                return productRepository.save(updatedProduct);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else {
            return null;
        }
    }
    public boolean delete(Long id) {
        Product product = getProductById(id);
        if (product != null) {
            try {
                productRepository.delete(product);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public Product convertProductDTOToEntity(ProductDTO productDTO) {
        return Product
                .builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .description(productDTO.getDescription())
                .stock(productDTO.getStock())
                .build();
    }
}
