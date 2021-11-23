/*
 * File Created: 2021/11/23 15:45:46
 * Author: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 * -----
 * Last Modified: 2021/11/23 16:28:03
 * Modified By: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 */
package anony.controller;

import java.util.Collections;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {
    @GetMapping(name = "/")
    CollectionModel<Object> get() {
        var collection = CollectionModel.of(Collections.emptyList());
        collection.add(Link.of("/user", "current_user"));
        return collection;
    }
}
