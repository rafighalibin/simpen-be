package com.nakahama.simpenbackend.Payroll.model;

import java.util.UUID;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.SQLDelete;

import com.nakahama.simpenbackend.Kelas.model.JenisKelas;
import com.nakahama.simpenbackend.Kelas.model.Program;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fee")
@SQLDelete(sql = "UPDATE fee SET is_deleted = true WHERE id=?")
public class FeeModel {
    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne
    private JenisKelas jenisKelas;

    @ManyToOne
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
