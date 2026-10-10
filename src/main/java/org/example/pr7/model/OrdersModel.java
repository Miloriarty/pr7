package org.example.pr7.model;
import org.example.pr7.model.UsersModel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Заказ")
@Entity
@Table(name = "orders")
public class OrdersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "ID заказа",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    //@NotNull(message = "User is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @Schema(description = "Пользователь")
    private UsersModel user;

    @Schema(
            description = "Дата оформления заказа",
            example = "2026-05-30T12:30:00"
    )
    private LocalDateTime orderDate;

    @Positive(message = "Total price must be greater than 0")
    @Schema(
            description = "Общая стоимость заказа",
            example = "4999.99"
    )
    private double totalPrice;

    //@NotBlank(message = "Status is required")
    @Schema(
            description = "Статус заказа",
            example = "PAID"
    )
    private String status;

    @JsonIgnore
    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private List<OrderItemsModel> orderItems = new ArrayList<>();

    public OrdersModel() {
    }

    public OrdersModel(Long id, UsersModel user, LocalDateTime orderDate, double totalPrice, String status) {
        this.id = id;
        this.user = user;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsersModel getUser() {
        return user;
    }

    public void setUser(UsersModel user) {
        this.user = user;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItemsModel> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(
            List<OrderItemsModel> orderItems
    ) {
        this.orderItems = orderItems;
    }
}