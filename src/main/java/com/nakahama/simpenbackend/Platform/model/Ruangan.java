package com.nakahama.simpenbackend.Platform.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("Ruangan")
public class Ruangan extends Platform {
    @Column(name = "cabang")
    private String cabang;

    @Column(name = "kapasitas")
    private int kapasitas;
}
