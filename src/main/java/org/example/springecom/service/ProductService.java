package org.example.springecom.service;

import org.example.springecom.DTO.ProductResponse;
import org.example.springecom.mapper.ProductMapper;
import org.example.springecom.model.Product;
import org.example.springecom.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class ProductService {
    private static final DateTimeFormatter IMAGE_NAME_FORMAT =
            DateTimeFormatter.ofPattern("yyyyMMddHHmmSSS");

    @Autowired
    private ProductRepo productRepo;

    public List<ProductResponse> getAllProducts() {
        return productRepo.findAll().stream().map(ProductMapper::toResDTO).toList();
    }

    public Product getProductById(int id) {
        return productRepo.findById(id).orElseGet(() -> new Product(-1));
    }

    public void addProduct(Product product, MultipartFile image) throws IOException {
        String imageName = generateImageName(image);
        //get path for image
        String dir = "src/main/resources/uploads";
        Path path = Paths.get(dir, imageName);

        //save image in disk
        Files.createDirectories(path.getParent());
        Files.write(path, image.getBytes());


        product.setImageName(imageName);
        product.setImageType(image.getContentType());
        product.setImageUrl("/images/" + imageName);
        productRepo.save(product);
    }


    public byte[] getImage(String fileName) {
        try{
            Path path = Paths.get("src/main/resources/uploads", fileName);
            return Files.readAllBytes(path);
        }
        catch (Exception e){
            return new byte[0];
        }

    }


    public void updateProduct(Product oldProduct, Product product, MultipartFile image) throws Exception {
        boolean imageUpdated = false;
        if(image != null && !image.isEmpty()){
            imageUpdated = true;
            if(oldProduct.getImageName() != null) {
                //delete old image
                Path oldPath = Paths.get("src/main/resources/uploads", oldProduct.getImageName());
                Files.deleteIfExists(oldPath);
                }

            //saving new image
            //generate img name
            String imageName = generateImageName(image);
            //get path for image
            String dir = "src/main/resources/uploads";
            Path path = Paths.get(dir, imageName);

            //save image in disk
            Files.createDirectories(path.getParent());
            Files.write(path, image.getBytes());

            product.setImageName(imageName);
            product.setImageType(image.getContentType());
            product.setImageUrl("/images/" + imageName);
        }
            product.setId(oldProduct.getId());
            if(product.getName() == null) product.setName(oldProduct.getName());
            if(product.getDescription() == null) product.setDescription(oldProduct.getDescription());
            if(product.getBrand() == null) product.setBrand(oldProduct.getBrand());
            if(product.getPrice() == null) product.setPrice(oldProduct.getPrice());
            if(product.getCategory() == null) product.setCategory(oldProduct.getCategory());
            if(product.getReleaseDate() == null) product.setReleaseDate(oldProduct.getReleaseDate());
            if(product.getProductQuantity() == null) product.setProductQuantity(oldProduct.getProductQuantity());
            if(product.getProductQuantity() > 0) product.setProductAvailable(true);
            if(!imageUpdated){
                product.setImageName(oldProduct.getImageName());
                product.setImageUrl(oldProduct.getImageUrl());
                product.setImageType(oldProduct.getImageType());
            }


        productRepo.save(product);

    }

    private String generateImageName(MultipartFile image) {
        String extension = StringUtils.getFilenameExtension(image.getOriginalFilename());
        String timestamp = LocalDateTime.now().format(IMAGE_NAME_FORMAT);
        return "image" + timestamp + (extension != null ? "." + extension : "");
    }

    public Product deleteProduct(Product product) throws IOException {
        if(product.getImageName() != null){
            Path oldPath = Paths.get("src/main/resources/uploads", product.getImageName());
            Files.deleteIfExists(oldPath);

        }
        productRepo.delete(product);
        return product;
    }

    public List<Product> searchProduct(String keyword) {
        System.out.println(keyword);

        return productRepo.searchByKeyword(keyword.toLowerCase());
    }


}
