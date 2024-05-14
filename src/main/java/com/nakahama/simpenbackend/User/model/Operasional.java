package com.nakahama.simpenbackend.User.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
// @NoArgsConstructor
// @AllArgsConstructor
@Entity
@DiscriminatorValue("Operasional")
public class Operasional extends UserModel {
}
