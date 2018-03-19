package com.sample.sfms.api;

import com.sample.sfms.entity.User;
import com.sample.sfms.service.interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserAPI {

    @Autowired
    private UserService userService;

    @GetMapping
    private Iterable<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    private User getUser(@PathVariable("id") int id){
        return userService.findOne(id);
    }

    @PostMapping
    private void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @DeleteMapping
    private void deleteUser(@RequestParam("id") int id){
        userService.delete(id);
    }

}
