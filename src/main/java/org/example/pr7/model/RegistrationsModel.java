package org.example.pr7.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Регистрация пользователя на мероприятие")
@Entity
@Table(name = "registrations")
public class RegistrationsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "ID регистрации",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @NotNull(message = "User ID is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Schema(description = "Пользователь")
    private UsersModel user;

    @NotNull(message = "Event ID is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    @Schema(description = "Мероприятие")
    private EventsModel event;

    @NotNull(message = "OrderItem ID is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_item_id")
    @Schema(description = "Элемент заказа")
    private OrderItemsModel orderItem;

    @Schema(
            description = "Дата регистрации",
            example = "2026-05-31T12:30:00"
    )
    private LocalDateTime registrationDate;

    @NotBlank(message = "Status is required")
    @Schema(
            description = "Статус регистрации",
            example = "CONFIRMED"
    )
    private String status;

    public RegistrationsModel() {
    }

    public RegistrationsModel(
            Long id, UsersModel user, EventsModel event, OrderItemsModel orderItem,
            LocalDateTime registrationDate, String status
    ) {
        this.id = id;
        this.user = user;
        this.event = event;
        this.orderItem = orderItem;
        this.registrationDate = registrationDate;
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

    public EventsModel getEvent() {
        return event;
    }

    public void setEvent(EventsModel event) {
        this.event = event;
    }

    public OrderItemsModel getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItemsModel orderItem) {
        this.orderItem = orderItem;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}