package org.example.springecom.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.processing.Pattern;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity // help to make a class as definition of table
@Data //automatically create getter and setter
@NoArgsConstructor// create constructor with no args
@AllArgsConstructor // create constructor with all arguments
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY) // generate value automatically
    private Integer id;
    private String name;
    private String description;
    private String brand;
    private Integer price;
    private String category;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date releaseDate;
    private Boolean productAvailable;
    private Integer productQuantity;
    private String imageName;
    private String imageType;
    private String imageUrl;

    public Product(Integer id) {
        this.id = id;
    }
}
