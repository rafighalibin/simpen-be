package com.nakahama.simpenbackend.Platform.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.SQLDelete;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("Akademik")
public class Ruangan extends PlatformModel{
    @Column(name = "cabang")
    private String cabang;

    @Column(name = "kapasitas")
    private int kapasitas;
}
