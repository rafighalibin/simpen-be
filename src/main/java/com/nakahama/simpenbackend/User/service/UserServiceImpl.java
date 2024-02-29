package com.nakahama.simpenbackend.User.service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.User.model.Akademik;
import com.nakahama.simpenbackend.User.model.Operasional;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.User.repository.UserDb;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDb userDb;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    // <-- Helper method -->

    @Override
    public String generatePassword(int length) {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }
        return password.toString();
    }

    @Override
    public void addDummySuperadmin() {
        UserModel user = userDb.findByRole("superadmin");

        if (user == null) {
            UserModel superadmin = new UserModel();
            superadmin.setEmail("1234@kalananti.com");
            superadmin.setPassword(bCryptPasswordEncoder.encode("1234"));
            superadmin.setRole("superadmin");
            userDb.save(superadmin);
        }
    }

    // <-- Helper method -->

    @Override
    public boolean isDeactivate(String email) {
        if (isExist(email)) {
            UserModel user = userDb.findByEmail(email);
            if (user.isDeleted()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean isExist(String email) {
        UserModel user = userDb.findByEmail(email);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserModel addUser(String email, String role, String nama) {
        if (isDeactivate(email)) {
            UserModel user = userDb.findByEmail(email);

            user.setNama(nama);
            user.setPassword(bCryptPasswordEncoder.encode("12345"));
            user.setDeleted(false);
            user.setRole(role);
            if (role.equals("pengajar")) {
                Pengajar pengajar = new Pengajar();
                user.setPengajar(pengajar);
                user.setOperasional(null);
                user.setAkademik(null);
            }
            if (role.equals("operasional")) {
                Operasional operasional = new Operasional();
                user.setPengajar(null);
                user.setOperasional(operasional);
                user.setAkademik(null);
            }
            if (role.equals("akademik")) {
                Akademik akademik = new Akademik();
                user.setPengajar(null);
                user.setOperasional(null);
                user.setAkademik(akademik);
            }
            userDb.save(user);

            return user;
        } else {
            if (!isExist(email)) {
                UserModel user = new UserModel();

                user.setNama(nama);
                user.setEmail(email);
                user.setRole(role);
                user.setPassword(bCryptPasswordEncoder.encode("12345"));

                if (role.equals("pengajar")) {
                    Pengajar pengajar = new Pengajar();
                    user.setPengajar(pengajar);
                    user.setOperasional(null);
                    user.setAkademik(null);
                }
                if (role.equals("operasional")) {
                    Operasional operasional = new Operasional();
                    user.setPengajar(null);
                    user.setOperasional(operasional);
                    user.setAkademik(null);
                }
                if (role.equals("akademik")) {
                    Akademik akademik = new Akademik();
                    user.setPengajar(null);
                    user.setOperasional(null);
                    user.setAkademik(akademik);
                }
                userDb.save(user);

                return user;
            } else {
                return null;
            }
        }
    }

    @Override
    public UserModel getUserById(UUID id) {
        for (UserModel user : retrieveAllUser()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<UserModel> retrieveAllUser() {
        return userDb.findAll();
    }

}
