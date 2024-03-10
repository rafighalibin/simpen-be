package com.nakahama.simpenbackend.User.service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.User.dto.User.EditDataUserRequestDTO;
import com.nakahama.simpenbackend.User.dto.User.EditUserRequestDTO;
import com.nakahama.simpenbackend.User.model.Akademik;
import com.nakahama.simpenbackend.User.model.Operasional;
import com.nakahama.simpenbackend.User.model.Pengajar;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.User.repository.AkademikDb;
import com.nakahama.simpenbackend.User.repository.OperasionalDb;
import com.nakahama.simpenbackend.User.repository.PengajarDb;
import com.nakahama.simpenbackend.User.repository.UserDb;
import com.nakahama.simpenbackend.exception.BadRequestException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDb userDb;

    @Autowired
    AkademikDb akademikDb;

    @Autowired
    OperasionalDb operasionalDb;

    @Autowired
    PengajarDb pengajarDb;

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
        for (UserModel user : userDb.findAllDeleted()) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean isExistByEmail(String email) {
        UserModel user = userDb.findByEmail(email);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserModel addUser(String email, String role, String nama) {
        if (!isExistByEmail(email)) {
            UserModel user = new UserModel();

            if (role.equals("pengajar")) {
                Pengajar pengajar = new Pengajar();
                user = pengajar;
            }
            if (role.equals("operasional")) {
                Operasional operasional = new Operasional();
                user = operasional;
            }
            if (role.equals("akademik")) {
                Akademik akademik = new Akademik();
                user = akademik;
            }

            user.setNama(nama);
            user.setEmail(email);
            user.setRole(role);
            user.setPassword(bCryptPasswordEncoder.encode("12345"));
            userDb.save(user);

            return user;
        } else {
            throw new BadRequestException("email " + email + " already exist");
        }

    }

    @Override
    public UserModel getUserById(UUID id) {
        for (UserModel user : retrieveAllUser()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        throw new BadRequestException("User with id " + id + " not found");
    }

    @Override
    public List<UserModel> retrieveAllUser() {
        return userDb.findAll();
    }

    @Override
    public Map<String, List<UserModel>> getAllUsersGroupedByCategory() {
        List<UserModel> allUsersExceptSuperadmin = userDb.findAll()
                .stream()
                .filter(user -> !"superadmin".equalsIgnoreCase(user.getRole()))
                .collect(Collectors.toList());

        Map<String, List<UserModel>> groupedUsers = allUsersExceptSuperadmin.stream()
                .collect(Collectors.groupingBy(UserModel::getRole));

        groupedUsers.forEach((role, userList) -> userList.sort(
                Comparator.comparing(user -> user.getNama() != null ? user.getNama() : "")));

        return groupedUsers;
    }

    @Override
    public void deleteUser(UUID id) {
        userDb.delete(getUserById(id));
    }

    @Override
    public UserModel updateUser(EditUserRequestDTO editUserRequestDTO) {
        UserModel user = getUserById(editUserRequestDTO.getId());
        if (editUserRequestDTO.getNama() != null) {
            user.setNama(editUserRequestDTO.getNama());
        }
        if (editUserRequestDTO.getEmail() != null) {
            user.setEmail(editUserRequestDTO.getEmail());
        }
        if (editUserRequestDTO.getPassword() != null) {
            user.setPassword(bCryptPasswordEncoder.encode(editUserRequestDTO.getPassword()));
        }
        if (editUserRequestDTO.getJenisKelamin() != null) {
            user.setJenisKelamin(editUserRequestDTO.getJenisKelamin());
        }
        if (editUserRequestDTO.getNoTelp() != null) {
            user.setNoTelp(editUserRequestDTO.getNoTelp());
        }

        userDb.save(user);
        return user;
    }

    @Override
    public UserModel editDataUser(EditDataUserRequestDTO editDataUserRequestDTO) {
        UserModel userTobeUpdated = getUserById(editDataUserRequestDTO.getId());
        // userTobeUpdated = UserMapper.toEntity(editDataUserRequestDTO,
        // userTobeUpdated);
        userDb.save(userTobeUpdated);
        return userTobeUpdated;
        // return null;

    }

}
