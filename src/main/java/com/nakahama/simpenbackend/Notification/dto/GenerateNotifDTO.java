package com.nakahama.simpenbackend.Notification.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenerateNotifDTO {

    UUID akunPenerima;

    String judul;

    Map<String, String> isi = new HashMap<>();

    int tipe;
}