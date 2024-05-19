package com.nakahama.simpenbackend.User.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "availability")
public class Availability {
    @Id
    private UUID id = UUID.randomUUID();

    @NotNull
    @ManyToOne
    private Pengajar pengajar;

    @NotNull
    private LocalDateTime waktu;
    
}
