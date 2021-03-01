package com.example.Silkstore20.Domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //id gets automatically generated. It is also the productCode when it is required (e.g. in productlist)

    private String title;
    private Integer price;
    private Integer quantity;

    // Join seller to user
    @ManyToOne
    @JoinColumn(name = "username")
    @JsonManagedReference
    private User seller;
    private String sellerUsername;
    private String sellerId;

    @Column(nullable = true, length = 64)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonManagedReference
    private Category category;

    public Product(String title, User seller, Integer price, Integer quantity, Category category, String imageUrl) {

        this.imageUrl = imageUrl;
        this.title = title;
        this.seller = seller;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        // Set the sellerUsername field for the object, using seller.getUsername() method
        this.setSellerUsername(seller.getUsername());
    }

public Product(){

}

// Special getter for product photos

    @Transient
    public String getPhotosImagePath() {
        if (imageUrl == null) return null;

        return "/product-photos/" + id + "/" + imageUrl;
    }

// Setters and getters
public long     getId()       { return id;     }

    public void setId       (long   id     )    { this.id     = id;     }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public User getSeller() {
        return seller;
    }

    public String getSellerUsername() {
        return seller.getUsername();
    }

    public Long getSellerId() {
        return seller.getId();
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // To String

    @Override
    public String toString() {
        if (this.category != null)
            return "Product [ title=" + title + ", seller=" + seller.getUsername() + ", price=" + price
                    + ", quantity =" + quantity + ", category=" + category + "]";
        else
            return "Product [ title=" + title + ", seller=" + seller.getUsername() + "price=" + price
                    + ", quantity =" + quantity + "]";
    }


}
