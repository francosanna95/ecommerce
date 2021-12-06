package com.mindhub.ecommerce.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "idsGenerator")
    @TableGenerator(name = "idsGenerator.product", table = "ProducstIdsGenerator",
           pkColumnName = "id", pkColumnValue = "Product", valueColumnName = "productsIds")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userHistory_id")
    private User userHistory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    public UserProduct() {
    }

    public UserProduct(User user, Product product) {
        this.user = user;
        this.product = product;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonIgnore
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public User getUserHistory() {
        return userHistory;
    }

    public void setUserHistory(User userHistory) {
        this.userHistory = userHistory;
    }
}
