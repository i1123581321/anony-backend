/*
 * File Created: 2021/11/23 14:34:35
 * Author: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 * -----
 * Last Modified: 2021/11/23 16:49:39
 * Modified By: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 */
package anony.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import anony.entity.UserInfo;
import anony.repository.UserRepository;

@RestController
@RequestMapping(name = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/user")
    EntityModel<UserInfo> get(@RequestParam Optional<String> username) {
        var name = username.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "you need to provide username"));
        var users = repository.findByUsername(name);
        if (users.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user %s does not exist.", name));
        }
        return EntityModel.of(users.get(0));
    }
}
