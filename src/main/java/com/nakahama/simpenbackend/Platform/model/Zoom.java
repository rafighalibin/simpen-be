package com.nakahama.simpenbackend.Platform.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
@DiscriminatorValue("Zoom")
public class Zoom extends PlatformModel{
    @Column(name = "host_key")
    private String hostKey;

    @Column(name = "link")
    private String link;
}
