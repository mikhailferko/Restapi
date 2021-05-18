package ferko.restapi.controller;

import ferko.restapi.exception.NotFoundException;
import ferko.restapi.service.user.UserService;
import ferko.restapi.dto.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public List<UserFilterOutDto> filter(@RequestBody @Valid UserFilterInDto userFilterInDTO){
        return userService.filter(userFilterInDTO);
    }

    @GetMapping("{id}")
    public UserGetDto getUser(@PathVariable int id){
        if(userService.findById(id) != null){
            return userService.findById(id);
        }
        else throw new NotFoundException();
    }

    @PostMapping("save")
    public void saveUser(@RequestBody @Valid UserSaveDto userSaveDTO){
        userService.save(userSaveDTO);
    }

    @PostMapping("update")
    public void updateUser(@RequestBody @Valid UserUpdateDto userUpdateDTO){
        userService.update(userUpdateDTO);
    }
}
