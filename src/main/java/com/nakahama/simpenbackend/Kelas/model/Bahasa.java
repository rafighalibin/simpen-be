package com.nakahama.simpenbackend.Kelas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bahasa")
public class Bahasa {
    @Id
    @NotNull
    private String nama;
}
