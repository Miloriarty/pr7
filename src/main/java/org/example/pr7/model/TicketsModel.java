package org.example.pr7.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "Билет")
@Entity
@Table(name = "tickets")
public class TicketsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "ID билета",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    @Schema(
            description = "Стоимость билета",
            example = "1500"
    )
    private double price;

    @NotNull(message = "Quantity is required")
    @PositiveOrZero(message = "Quantity cannot be negative")
    @Schema(
            description = "Количество доступных билетов",
            example = "100"
    )
    private int quantity;

    @NotBlank(message = "Ticket type is required")
    @Schema(
            description = "Тип билета",
            example = "VIP"
    )
    private String type;

    @JsonIgnore
    @OneToMany(
            mappedBy = "ticket",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private List<OrderItemsModel> orderItems = new ArrayList<>();

    public TicketsModel() {
    }

    public TicketsModel(Long id, double price, int quantity, String type) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OrderItemsModel> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemsModel> orderItems) {
        this.orderItems = orderItems;
    }
}