package org.example.newsWebsite.controller;

import org.example.newsWebsite.model.User;
import org.example.newsWebsite.model.convertor.PrimitiveConvertor;
import org.example.newsWebsite.model.dto.FavoritesDto;
import org.example.newsWebsite.model.dto.UserDto;
import org.example.newsWebsite.service.api.NewsService;
import org.example.newsWebsite.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Component
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("newsServiceImpl")
    private NewsService newsService;

    @Autowired
    @Qualifier("UserConvertor")
    private PrimitiveConvertor<User, UserDto> userConvertor;

    @Autowired
    @Qualifier("favoritesConvertor")
    private PrimitiveConvertor<Byte, FavoritesDto> favoritesConvertor;


    @PostMapping("signup")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        userDto.setId(null);
        if(userDto.getFavorites() == null){
            userDto.setFavorites(new FavoritesDto(false,false,false,false,false));
        }

        if(userService.isUserExist(userDto.getUsername())){
            return ResponseEntity
                           .status(HttpStatus.CONFLICT)
                           .body(null);
        }
        else {
            UserDto userCreate = userConvertor.modelToDto(
                                    userService.addUser(
                                            userConvertor.dtoToModel(userDto)
                                    )
                        );
            return ResponseEntity
                           .status(HttpStatus.CREATED)
                           .body(userCreate);
        }
    }

    @GetMapping("all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = new ArrayList<>();
        for(User user : userService.getAllUsers()){
            userDtos.add(userConvertor.modelToDto(user));
        }
        return ResponseEntity
                       .status(HttpStatus.FOUND)
                       .body(userDtos);
    }

    @GetMapping("byId")
    public ResponseEntity<UserDto> getUserById(@RequestParam(name = "id") Long id) {
        User user = userService.getUser(id);
        if(user == null){
            return ResponseEntity
                           .status(HttpStatus.NOT_FOUND)
                           .body(null);
        }
        else {
            UserDto userDto = userConvertor.modelToDto(user);
            return ResponseEntity
                           .status(HttpStatus.FOUND)
                           .body(userDto);
        }
    }

    @GetMapping("byUsername")
    public ResponseEntity<UserDto> getUserByUsername(@RequestParam(name = "username") String username) {
        User user = userService.getUser(username);
        if(user == null){
            return ResponseEntity
                           .status(HttpStatus.NOT_FOUND)
                           .body(null);
        }
        else {
            UserDto userDto = userConvertor.modelToDto(user);
            return ResponseEntity
                           .status(HttpStatus.FOUND)
                           .body(userDto);
        }
    }

    @PutMapping("edit")
    public ResponseEntity<UserDto> editUser(@RequestBody UserDto userDto){
        User user = userService.editUser(userConvertor.dtoToModel(userDto));

        if(userService.isUserExist(userDto.getUsername()) && !userService.getUser(userDto.getId()).getUsername().equals(userDto.getUsername())){
            return ResponseEntity
                           .status(HttpStatus.CONFLICT)
                           .body(null);
        }
        if(user == null){
            return ResponseEntity
                           .status(HttpStatus.CONFLICT)
                           .body(null);
        }
        else {
            userDto = userConvertor.modelToDto(user);
            return ResponseEntity
                           .status(HttpStatus.OK)
                           .body(userDto);
        }
    }

    @DeleteMapping("deleteById")
    public ResponseEntity deleteUser(@RequestParam(name = "id") Long id) {
        if (userService.deleteUser(id)){
            return ResponseEntity
                           .status(HttpStatus.NO_CONTENT)
                           .build();
        }
        else {
            return ResponseEntity
                           .status(HttpStatus.NOT_FOUND)
                           .build();
        }
    }

    @DeleteMapping("deleteByUsername")
    public ResponseEntity deleteUser(@RequestParam(name = "username") String username) {
        if (userService.deleteUser(username)){
            return ResponseEntity
                           .status(HttpStatus.NO_CONTENT)
                           .build();
        }
        else {
            return ResponseEntity
                           .status(HttpStatus.NOT_FOUND)
                           .build();
        }
    }

    /*@PostMapping("picture")
    public ResponseEntity uploadProfilePicture(*//*@PathVariable Long id,*//*@RequestParam("file") MultipartFile file) {
        System.out.println("----------------------------");
        boolean transactionStatus = userService.uploadeImage(file, String.valueOf(1));
        return ResponseEntity
                       .status(transactionStatus ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                       .build();
    }*/

    @GetMapping("userExistence")
    public ResponseEntity isUserExist(@RequestParam(name = "u") String username) {
        Boolean exist = userService.isUserExist(username);

        return ResponseEntity
                       .status(exist ? HttpStatus.FOUND : HttpStatus.NOT_FOUND)
                       .build();
    }

    @GetMapping("emailExistence")
    public ResponseEntity isEmailExist(@RequestParam(name = "e") String email) {
        Boolean exist = userService.isEmailExist(email);

        return ResponseEntity
                       .status(exist ? HttpStatus.FOUND : HttpStatus.NOT_FOUND)
                       .build();
    }

    /*@GetMapping("phoneExistence")
    public ResponseEntity isPhoneExist(@RequestParam(name = "p") String phone) {
        Boolean exist = userService.isPhoneExist(phone);

        return ResponseEntity
                       .status(exist ? HttpStatus.FOUND : HttpStatus.NOT_FOUND)
                       .build();
    }*/

    @GetMapping("allVisitsI")
    public ResponseEntity<Integer> getAllVisits(@RequestParam(name = "i") Long userId) {
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(newsService.getAllVisits(userId));
    }

    @GetMapping("allVisitsU")
    public ResponseEntity<Integer> getAllVisits(@RequestParam(name = "u") String username) {
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(newsService.getAllVisits(username));
    }

    @GetMapping("allNewsI")
    public ResponseEntity<Integer> getAllNews(@RequestParam(name = "i") Long userId) {
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(newsService.getNumberOfAllNews(userId));
    }

    @GetMapping("allNewsU")
    public ResponseEntity<Integer> getAllNews(@RequestParam(name = "u") String username) {
        return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(newsService.getNumberOfAllNews(username));
    }

    @PutMapping("changeFavorites")
    public ResponseEntity<Boolean> changeFavorites(@RequestParam(name = "id") Long id,@RequestBody FavoritesDto favoritesDto) {
        boolean status = userService.changeFavorites(id, favoritesConvertor.dtoToModel(favoritesDto));
        return ResponseEntity
                       .status(status ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                       .body(status);
    }

    @GetMapping("favorites")
    public ResponseEntity<FavoritesDto> getFavorites(@RequestParam(name = "id") Long id) {
        Byte f = userService.getFavorites(id);
        if(f == null){
            return ResponseEntity
                           .status(HttpStatus.NOT_FOUND)
                           .build();
        }
        else {
            FavoritesDto favoritesDto = favoritesConvertor.modelToDto(f);
            return ResponseEntity
                           .status(HttpStatus.FOUND)
                           .body(favoritesDto);
        }
    }
}
