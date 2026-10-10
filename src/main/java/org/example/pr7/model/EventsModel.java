package org.example.pr7.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Мероприятие")
@Entity
@Table(name = "events")
public class EventsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "ID мероприятия",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @NotBlank(message = "Title is required")
    @Schema(
            description = "Название мероприятия",
            example = "Spring Conference 2026"
    )
    private String title;

    @Schema(
            description = "Описание мероприятия",
            example = "Большая конференция по Spring Framework"
    )
    private String description;

    @NotBlank(message = "Start date is required")
    @Schema(
            description = "Дата начала",
            example = "2026-07-15"
    )
    private String startDate;

    @NotBlank(message = "End date is required")
    @Schema(
            description = "Дата окончания",
            example = "2026-07-20"
    )
    private String endDate;

    @NotNull(message = "Location is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private LocationsModel location;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organizer_id")
    private UsersModel organizer;

    @NotNull(message = "Category is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private CategoriesModel category;

    @Positive(message = "Max participants must be greater than 0")
    @Schema(
            description = "Максимальное количество участников",
            example = "100"
    )
    private Integer maxParticipants;

    @JsonIgnore
    @OneToMany(
            mappedBy = "event",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private List<RegistrationsModel> registrations = new ArrayList<>();

    public EventsModel() {
    }

    public EventsModel(
            Long id, String title, String description, String startDate,
            String endDate, LocationsModel location, UsersModel organizer,
            CategoriesModel category, Integer maxParticipants
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.organizer = organizer;
        this.category = category;
        this.maxParticipants = maxParticipants;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public LocationsModel getLocation() { return location; }
    public void setLocation(LocationsModel location) { this.location = location; }

    public UsersModel getOrganizer() { return organizer; }
    public void setOrganizer(UsersModel organizer) { this.organizer = organizer; }

    public CategoriesModel getCategory() { return category; }
    public void setCategory(CategoriesModel category) { this.category = category; }

    public Integer getMaxParticipants() { return maxParticipants; }
    public void setMaxParticipants(Integer maxParticipants) { this.maxParticipants = maxParticipants; }
}