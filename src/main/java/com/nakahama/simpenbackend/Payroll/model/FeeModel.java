package com.nakahama.simpenbackend.Payroll.model;

import java.util.UUID;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.nakahama.simpenbackend.Kelas.model.JenisKelas;
import com.nakahama.simpenbackend.Kelas.model.Program;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "fee",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"jenisKelas_id", "program_id"})}
)
public class FeeModel {
    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "jenisKelas_id", referencedColumnName = "id")
    private JenisKelas jenisKelas;

    @ManyToOne
    @JoinColumn(name = "program_id", referencedColumnName = "id")
    private Program program;

    @Column(name = "base_fee")
    private int baseFee;

    @Column(name = "student_multiplier")
    private int studentMultiplier;

    @Column(name = "max_students")
    private int maxStudents;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}
