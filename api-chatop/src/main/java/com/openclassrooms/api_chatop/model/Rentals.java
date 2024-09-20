package com.openclassrooms.api_chatop.model;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
@EqualsAndHashCode()
public class Rentals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant created_at; 

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updated_at;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surface", nullable = false)
    private Integer surface;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "picture", nullable = false)
    private String picture;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner_id;

    @OneToMany(mappedBy = "rental")
    private List<Messages> messages;

    public Rentals(Integer id) {
        this.id = id;
    }

}