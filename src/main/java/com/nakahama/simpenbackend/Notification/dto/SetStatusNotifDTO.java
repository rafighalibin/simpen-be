package com.nakahama.simpenbackend.Notification.dto;

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
public class SetStatusNotifDTO {
    UUID id;

    Boolean isOpened;

    Boolean isHidden;

    Boolean isDelete;

}
