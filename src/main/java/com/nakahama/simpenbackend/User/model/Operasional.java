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
@DiscriminatorValue("Operasional")
public class Operasional extends UserModel {
}
