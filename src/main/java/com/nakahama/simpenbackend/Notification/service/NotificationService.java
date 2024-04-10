package com.nakahama.simpenbackend.Notification.service;

import java.util.List;
import java.util.UUID;

import com.nakahama.simpenbackend.Notification.dto.GenerateNotifDTO;
import com.nakahama.simpenbackend.Notification.dto.SetStatusNotifDTO;
import com.nakahama.simpenbackend.Notification.model.Notification;

public interface NotificationService {

    public Notification generateNotification(GenerateNotifDTO generateNotifDTO);

    public List<Notification> retreiveAllNotification();

    public Notification getNotificationById(UUID id);

    public void setNotifStatus(SetStatusNotifDTO setStatusNotifDTO);
}
