package ferko.restapi.controller;

import ferko.restapi.service.user.UserService;
import ferko.restapi.dto.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("list")
    public List<UserFilterOutDto> filter(@RequestBody UserFilterInDto userFilterInDTO){
        return userService.filter(userFilterInDTO);
    }

    @GetMapping("{id}")
    public UserGetDto getUser(@PathVariable int id){
        return userService.findById(id);
    }

    @PostMapping("save")
    public String saveUser(@RequestBody UserSaveDto userSaveDTO){
        userService.save(userSaveDTO);
        return "success";
    }

    @PostMapping("update")
    public String updateUser(@RequestBody UserUpdateDto userUpdateDTO){
        userService.update(userUpdateDTO);
        return "success";
    }
}
