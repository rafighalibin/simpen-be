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
@DiscriminatorValue("Zoom")
public class Zoom extends Platform {
    @Column(name = "host_key")
    private String hostKey;

    @Column(name = "link")
    private String link;
}
