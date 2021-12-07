/*
 * File Created: 2021/11/30 15:41:17
 * Author: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 * -----
 * Last Modified: 2021/12/04 13:44:17
 * Modified By: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 */
package anony.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import anony.payload.request.UserRequest;
import anony.payload.response.TokenResponse;
import anony.security.jwt.JwtUtils;

@RestController
@RequestMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
public class TokenController {

    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    @Autowired
    public TokenController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EntityModel<TokenResponse>> put(@Valid @RequestBody UserRequest login) {
        var auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        var jwt = jwtUtils.generateJwtToken(auth);
        var tokenResponse = new TokenResponse(jwt);
        var tokenEntity = EntityModel.of(tokenResponse);
        return ResponseEntity.ok().body(tokenEntity);
    }
}
