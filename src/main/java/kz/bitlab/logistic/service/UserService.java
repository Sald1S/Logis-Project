package kz.bitlab.logistic.service;


import kz.bitlab.logistic.dto.UserDTO;
import kz.bitlab.logistic.mapper.RequestMapper;
import kz.bitlab.logistic.mapper.UserMapper;
import kz.bitlab.logistic.model.Permission;
import kz.bitlab.logistic.model.User;
import kz.bitlab.logistic.repository.PermissionRepository;
import kz.bitlab.logistic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;


public class UserService implements UserDetailsService {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  PermissionRepository permissionRepository;
    @Autowired
    private  RequestMapper requestMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User user = (User) authentication.getPrincipal();
            return user;
        }
        return null;
    }

    public UserDTO getUserDto(){
        return userMapper.toDto(getUser());
    }

    public Boolean registerUser(String email, String password,String rePassword,String fullname,String country, String phone,String company, String address){
        Boolean result = null;
        if(password.equals(rePassword)){
            result = Boolean.FALSE;
            User user = userRepository.findByEmail(email);
            if(user == null){
                User newUser = new User();
                newUser.setEmail(email);
                newUser.setFull_name(fullname);
                newUser.setCountry(country);
                newUser.setCompany(company);
                newUser.setAddress(address);
                newUser.setPhoneNumber(phone);
                newUser.setPassword(passwordEncoder.encode(password));

                List<Permission> permissions = new ArrayList<>();
                Permission permission = permissionRepository.findByName("ROLE_USER");
                if(permission!=null){
                    permissions.add(permission);
                    newUser.setPermissions(permissions);
                    userRepository.save(newUser);
                    result = Boolean.TRUE;
                }
            }
        }
        return result;
    }

    public User updateUser(User user){
        User currentUser = getUser();
        if (currentUser!=null) {
            currentUser.setFull_name(user.getFull_name());
            currentUser.setCompany(user.getCompany());
            currentUser.setPhoneNumber(user.getPhoneNumber());
            currentUser.setCountry(user.getCountry());
        }
        return userRepository.save(currentUser);
    }

    public User updatePic(User user){
        return userRepository.save(user);
    }

    public Boolean changePassword(String oldPass, String newPass, String reNewPass){
        Boolean result = null;
        User user = getUser();
        if (user != null) {
            if(passwordEncoder.matches(oldPass, user.getPassword())) {
                result = Boolean.FALSE;
                if(newPass.equals(reNewPass)){
                    user.setPassword(passwordEncoder.encode(newPass));
                    userRepository.save(user);
                    result = Boolean.TRUE;
                }
            }
        }
        return result;
    }

    public int count(){
        List<User> userList = userRepository.findAll();
        return userList.size();
    }
}
