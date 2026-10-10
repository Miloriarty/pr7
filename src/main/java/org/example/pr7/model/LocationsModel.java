package org.example.pr7.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Локация проведения мероприятий")
@Entity
@Table(name = "locations")
public class LocationsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "ID локации",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @NotBlank(message = "Location name is required")
    @Schema(
            description = "Название локации",
            example = "Conference Hall A"
    )
    private String name;

    @NotBlank(message = "Address is required")
    @Schema(
            description = "Адрес",
            example = "Main Street 15"
    )
    private String address;

    @NotBlank(message = "City is required")
    @Schema(
            description = "Город",
            example = "Prague"
    )
    private String city;

    @Positive(message = "Capacity must be greater than 0")
    @Schema(
            description = "Вместимость",
            example = "500"
    )
    private Integer capacity;

    @JsonIgnore
    @OneToMany(
            mappedBy = "location",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private List<EventsModel> events = new ArrayList<>();

    public LocationsModel() {
    }

    public LocationsModel(Long id, String name, String address, String city, Integer capacity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<EventsModel> getEvents() {
        return events;
    }

    public void setEvents(List<EventsModel> events) {
        this.events = events;
    }
}