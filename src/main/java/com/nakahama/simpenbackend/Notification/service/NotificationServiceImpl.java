package com.nakahama.simpenbackend.Notification.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.print.DocFlavor.READER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.Notification.dto.GenerateNotifDTO;
import com.nakahama.simpenbackend.Notification.dto.SetStatusNotifDTO;
import com.nakahama.simpenbackend.Notification.model.Notification;
import com.nakahama.simpenbackend.Notification.repository.NotificationDb;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.User.repository.UserDb;
import com.nakahama.simpenbackend.User.service.UserService;
import com.nakahama.simpenbackend.exception.BadRequestException;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    UserService userService;

    @Autowired
    UserDb userDb;

    @Autowired
    NotificationDb notificationDb;

    @Override
    public Notification generateNotification(GenerateNotifDTO generateNotifDTO) {
        Notification notification = new Notification();
        notification.setJudul(generateNotifDTO.getJudul());
        notification.setIsi(generateNotifDTO.getIsi());
        notification.setTipe(generateNotifDTO.getTipe());

        LocalDateTime expDateTime = LocalDateTime.now().plusMonths(2);
        notification.setExpirationDate(expDateTime);

        UserModel akunPenerima = userService.getUserById(generateNotifDTO.getAkunPenerima());

        notification.setAkunPenerima(akunPenerima);

        akunPenerima.getNotifikasi().add(notification);

        userDb.save(akunPenerima);
        notificationDb.save(notification);

        return notification;
    }

    @Override
    public List<Notification> retreiveAllNotification() {
        return notificationDb.findAll();
    }

    @Override
    public Notification getNotificationById(UUID id) {
        for (Notification notification : retreiveAllNotification()) {
            if (notification.getId() == id) {
                return notification;
            }
        }

        throw new BadRequestException("Notification with id " + id + " not found");

    }

    @Override
    public void setNotifStatus(SetStatusNotifDTO setStatusNotifDTO) {
        Notification notification = getNotificationById(setStatusNotifDTO.getId());

        notification.setIsOpened(setStatusNotifDTO.getIsOpened());
        notification.setIsHidden(setStatusNotifDTO.getIsHidden());
        notification.setIsDeleted(setStatusNotifDTO.getIsDelete());

        notificationDb.save(notification);

    }

}
