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
        for (UserModel user : userDb.findAllIncludingDeleted()) {
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
        if (isDeactivate(email)) {
            UserModel user = userDb.findByEmailIncludingDeleted(email);

            user.setNama(nama);
            user.setPassword(bCryptPasswordEncoder.encode("12345"));
            user.setDeleted(false);
            user.setRole(role);
            if (role.equals("pengajar")) {
                Pengajar pengajar = new Pengajar();
                user.setPengajar(pengajar);
                pengajar.setUser(user);
            }
            if (role.equals("operasional")) {
                Operasional operasional = new Operasional();
                operasional.setUser(user);
                user.setOperasional(operasional);
            }
            if (role.equals("akademik")) {
                Akademik akademik = new Akademik();
                akademik.setUser(user);
                user.setAkademik(akademik);
            }
            userDb.save(user);

            return user;
        } else {
            if (!isExistByEmail(email)) {
                UserModel user = new UserModel();

                user.setNama(nama);
                user.setEmail(email);
                user.setRole(role);
                user.setPassword(bCryptPasswordEncoder.encode("12345"));

                if (role.equals("pengajar")) {
                    Pengajar pengajar = new Pengajar();
                    pengajar.setUser(user);
                    user.setPengajar(pengajar);
                }
                if (role.equals("operasional")) {
                    Operasional operasional = new Operasional();
                    operasional.setUser(user);
                    user.setOperasional(operasional);
                }
                if (role.equals("akademik")) {
                    Akademik akademik = new Akademik();
                    akademik.setUser(user);
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
        if (user != null) {
            user.setNama(editUserRequestDTO.getNama());
            user.setEmail(editUserRequestDTO.getEmail());
            user.setPassword(editUserRequestDTO.getPassword());
            user.setJenisKelamin(editUserRequestDTO.getJenisKelamin());
            user.setNoTelp(editUserRequestDTO.getNoTelp());

            if (!user.getRole().equals(editUserRequestDTO.getRole())) {
                if (user.getAkademik() != null) {
                    akademikDb.deleteById(user.getAkademik().getId());
                }
                if (user.getPengajar() != null) {
                    pengajarDb.deleteById(user.getPengajar().getId());
                }
                if (user.getOperasional() != null) {
                    operasionalDb.deleteById(user.getOperasional().getId());
                }

                if (editUserRequestDTO.getRole().equals("pengajar")) {
                    Pengajar pengajar = new Pengajar();
                    pengajar.setUser(user);
                    user.setPengajar(pengajar);
                } else if (editUserRequestDTO.getRole().equals("operasional")) {
                    Operasional operasional = new Operasional();
                    operasional.setUser(user);
                    user.setOperasional(operasional);
                } else if (editUserRequestDTO.getRole().equals("akademik")) {
                    Akademik akademik = new Akademik();
                    akademik.setUser(user);
                    user.setAkademik(akademik);
                }
                user.setRole(editUserRequestDTO.getRole());
            }

            userDb.save(user);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public UserModel editDataUser(EditDataUserRequestDTO editDataUserRequestDTO) {
        UserModel user = getUserById(editDataUserRequestDTO.getId());
        if (user != null) {
            user.setPassword(editDataUserRequestDTO.getPassword());
            user.setAlamatKTP(editDataUserRequestDTO.getAlamatKTP());
            user.setDomisiliKota(editDataUserRequestDTO.getDomisiliKota());
            user.setFotoDiri(editDataUserRequestDTO.getFotoDiri());
            user.setEmailPribadi(editDataUserRequestDTO.getEmailPribadi());
            user.setBackupPhoneNum(editDataUserRequestDTO.getBackupPhoneNum());
            user.setNoRekeningBank(editDataUserRequestDTO.getNoRekeningBank());
            user.setNamaBank(editDataUserRequestDTO.getNamaBank());
            user.setNamaPemilikRekening(editDataUserRequestDTO.getNamaPemilikRekening());
            user.setFotoBukuTabungan(editDataUserRequestDTO.getFotoBukuTabungan());
            user.setPendidikanTerakhir(editDataUserRequestDTO.getPendidikanTerakhir());
            user.setTanggalMasukKontrak(editDataUserRequestDTO.getTanggalMasukKontrak());
            user.setPekerjaanLainnya(editDataUserRequestDTO.getPekerjaanLainnya());
            user.setNIK(editDataUserRequestDTO.getNIK());
            user.setFotoKTP(editDataUserRequestDTO.getFotoKTP());
            user.setNPWP(editDataUserRequestDTO.getNPWP());
            user.setFotoNPWP(editDataUserRequestDTO.getFotoNPWP());
            user.setNamaKontakDarurat(editDataUserRequestDTO.getNamaKontakDarurat());
            user.setNomorTelpKontakDarurat(editDataUserRequestDTO.getNomorTelpKontakDarurat());

            userDb.save(user);
            return user;
        } else {
            return null;
        }
    }

}
