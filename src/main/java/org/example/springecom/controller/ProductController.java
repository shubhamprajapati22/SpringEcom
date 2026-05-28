package org.example.springecom.controller;

import org.example.springecom.DTO.ProductResponse;
import org.example.springecom.model.Product;
import org.example.springecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api") // all call with api comes here
//@CrossOrigin(origins = "http://localhost:5173") setup done in SecurityConfig
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("product")
    public ResponseEntity<List<ProductResponse>> allProducts(){

        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Product> productById(@PathVariable("id") int id) {

        Product product = productService.getProductById(id);
        if (product.getId() != -1)
            return ResponseEntity.ok(product);
        return ResponseEntity.notFound().build();
    }
    @PostMapping("admin/product/add")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile image) {//product only want raw json so jackson can convert to product object
//@RequestPart use to handle hard JSON or file data
        try {
            productService.addProduct(product, image);
            return ResponseEntity.ok("Added");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }


    }
    @GetMapping("images/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String fileName){
        byte[] img = productService.getImage(fileName);
        //System.out.println(img.length);
        if(img.length > 0){
            return ResponseEntity.ok(img);
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("admin/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart(required = false) MultipartFile image) throws Exception {
        Product orignalProduct = productService.getProductById(id);
        if(orignalProduct.getId() != -1){
            productService.updateProduct(orignalProduct, product, image);
        }
        return ResponseEntity.ok().body("updated successfully");

    }
    @DeleteMapping("admin/product/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id) throws IOException {
        Product product = productService.getProductById(id);
        if(product.getId() != -1) {
            return ResponseEntity.ok(productService.deleteProduct(product));

        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("product/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
        return ResponseEntity.ok(productService.searchProduct(keyword));
    }

}
