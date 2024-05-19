package com.nakahama.simpenbackend.User.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.nakahama.simpenbackend.Kelas.model.JenisKelas;

@Setter
@Getter
// @NoArgsConstructor
// @AllArgsConstructor
@Entity
@DiscriminatorValue("Akademik")
public class Akademik extends UserModel {

    @OneToMany(mappedBy = "picAkademik", cascade = CascadeType.ALL)
    private List<JenisKelas> jenisKelas;
}
