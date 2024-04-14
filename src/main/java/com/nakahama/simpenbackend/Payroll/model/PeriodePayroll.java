package com.nakahama.simpenbackend.Payroll.model;

import java.util.Date;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "periode_payroll")
public class PeriodePayroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bulan;

    private Integer tahun;

    private Date tanggalMulai;

    private Date tanggalSelesai;

    private Date tanggalPembayaran;

    @OneToMany
    private List<AbsenPengajar> listAbsenPengajar;

}
