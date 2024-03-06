package com.nakahama.simpenbackend.User.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
// @NoArgsConstructor
// @AllArgsConstructor
@Entity
@DiscriminatorValue("Akademik")
public class Akademik extends UserModel {
}
