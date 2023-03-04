package kz.bitlab.logistic.api;

import kz.bitlab.logistic.model.User;
import kz.bitlab.logistic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
}
