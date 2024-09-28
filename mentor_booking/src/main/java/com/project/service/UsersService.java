package com.project.service;

import com.project.dto.Response;
import com.project.dto.RoleDTO;
import com.project.dto.UsersDTO;
import com.project.exception.OurException;
import com.project.model.Role;
import com.project.model.Users;
import com.project.model.Students;
import com.project.model.Mentors;
import com.project.repository.MentorsRepository;
import com.project.repository.RoleRepository;
import com.project.repository.StudentsRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.repository.UsersRepository;
import com.project.ultis.JWTUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Thịnh Đạt
 */
@Service
public class UsersService{

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MentorsRepository mentorsRepository;

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private JWTUtils jWTUltis;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Phương thức taọ mới user
     *
     * @param registerRequest
     * @return Đối tượng Response chứa thông tin về kết quả tạo user
     */
    public Response createUser(Response registerRequest) {
        Response response = new Response();
        try {
            // Kiểm tra nếu username hoặc email đã tồn tại
            if (usersRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
                throw new OurException("Username already exists");
            }
            if (usersRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
                throw new OurException("Email already exists");
            }
            // Lấy vai trò người dùng từ request (ADMIN/STUDENT/MENTOR)
            Role role = roleRepository.findByRoleName(registerRequest.getRole())
                    .orElseThrow(() -> new OurException("No role name: " + registerRequest.getRoleDTO().getRoleName()));
            // Mã hóa mật khẩu
            String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

            // Tạo đối tượng User mới
            Users newUser = new Users();
            newUser.setUsername(registerRequest.getUsername());
            newUser.setEmail(registerRequest.getEmail());
            newUser.setPassword(encodedPassword);
            newUser.setDateCreated(LocalDateTime.now());
            newUser.setRole(role);

            // Lưu người dùng vào database
            usersRepository.save(newUser);

            if (role.getRoleName().equalsIgnoreCase("STUDENT")) {
                Students student = new Students();
                student.setUser(newUser);
                student.setDateCreated(LocalDate.now());
                student.setAClass(null); // Để class_id null
                student.setGroup(null); // Để group_id null
                studentsRepository.save(student);
                newUser.setStudent(student);
                usersRepository.save(newUser);
            }

            if (role.getRoleName().equalsIgnoreCase("MENTOR")) {
                Mentors mentor = new Mentors();
                mentor.setUser(newUser);
                mentor.setDateCreated(LocalDate.now());
                mentorsRepository.save(mentor);
                newUser.setMentor(mentor);
                usersRepository.save(newUser);
            }

            if (newUser.getId() > 0) {
                UsersDTO usersDTO = userToUserDTO(newUser);
                response.setUsersDTO(usersDTO);
                response.setStatusCode(201);
                response.setMessage("User created successfully");
            }

        } catch (OurException e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred during user creation: " + e.getMessage());
        }
        return response;
    }

    // Phương thức trả về tất cả người dùng
    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }

    // Phương thức tìm người dùng theo id
    public Users findUserById(Long id) {
        Optional<Users> user = usersRepository.findById(id);
        return user.orElse(null);
    }

    // Phương thức lưu người dùng
    public Users saveUsers(Users user) {
        return usersRepository.save(user);
    }

    // Phương thức xóa người dùng theo id
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    // Phương thức cập nhật thông tin người dùng
    public Users updateUsers(Long id, Users newUser) {
        return usersRepository.findById(id)
                .map(user -> {
                    // Cập nhật các trường cần thiết từ đối tượng mới (newUser)
                    user.setEmail(newUser.getEmail());
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword()); // Cần mã hóa nếu cập nhật mật khẩu mới
                    user.setBirthDate(newUser.getBirthDate());
                    user.setAvatar(newUser.getAvatar());
                    user.setAddress(newUser.getAddress());
                    user.setPhone(newUser.getPhone());
                    user.setGender(newUser.getGender());
                    user.setDateUpdated(LocalDateTime.now()); // Cập nhật thời gian sửa đổi

                    // Cập nhật vai trò nếu cần (Tùy chọn)
                    if (newUser.getRole() != null) {
                        Role role = roleRepository.findByRoleName(newUser.getRole().getRoleName())
                                .orElseThrow(() -> new OurException("Role not found"));
                        user.setRole(role);
                    }

                    // Trả về người dùng đã được cập nhật
                    return usersRepository.save(user);
                })
                .orElseThrow(() -> new OurException("User not found with id: " + id));
    }

    // Phương thức lấy thông tin profile của người dùng dựa trên email
    public UsersDTO getMyProfile(String email) {
        try {
            // Tìm người dùng trong cơ sở dữ liệu bằng email
            Users user = usersRepository.findByEmail(email)
                    .orElseThrow(() -> new OurException("User not found"));

            // Chuyển đổi từ đối tượng Users sang UsersDTO
            UsersDTO userDTO = userToUserDTO(user);

            return userDTO;
        } catch (OurException e) {
            throw new OurException("Error retrieving profile: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error: " + e.getMessage());
        }
    }

    // Phương thức chuyển đổi từ Users sang UsersDTO
    private UsersDTO userToUserDTO(Users user) {
        UsersDTO userDTO = new UsersDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setAddress(user.getAddress());
        userDTO.setPhone(user.getPhone());
        userDTO.setGender(user.getGender());
        userDTO.setDateUpdated(user.getDateUpdated());
        userDTO.setDateCreated(user.getDateCreated());
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(user.getRole().getId());
        roleDTO.setRoleName(user.getRole().getRoleName());
        userDTO.setRole(roleDTO);
        return userDTO;
    }
}
