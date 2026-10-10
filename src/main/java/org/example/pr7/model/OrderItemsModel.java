package org.example.pr7.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Элемент заказа")
@Entity
@Table(name = "order_items")
public class OrderItemsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "ID элемента заказа",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    //@NotNull(message = "Order is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    @Schema(description = "Заказ")
    private OrdersModel order;

    //@NotNull(message = "Ticket is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_id", nullable = false)
    @Schema(description = "Билет")
    private TicketsModel ticket;

    @Positive(message = "Quantity must be greater than 0")
    @Schema(
            description = "Количество билетов",
            example = "2"
    )
    private int quantity;

    @Positive(message = "Price must be greater than 0")
    @Schema(
            description = "Стоимость",
            example = "1500"
    )
    private double price;

    @JsonIgnore
    @OneToMany(
            mappedBy = "orderItem",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private List<RegistrationsModel> registrations = new ArrayList<>();

    public OrderItemsModel() {}

    public OrderItemsModel(Long id, OrdersModel order, TicketsModel ticket, int quantity, double price) {
        this.id = id;
        this.order = order;
        this.ticket = ticket;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrdersModel getOrder() {
        return order;
    }

    public void setOrder(OrdersModel order) {
        this.order = order;
    }

    public TicketsModel getTicket() {
        return ticket;
    }

    public void setTicket(TicketsModel ticket) {
        this.ticket = ticket;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<RegistrationsModel> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(
            List<RegistrationsModel> registrations
    ) {
        this.registrations = registrations;
    }
}