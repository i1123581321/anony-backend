/*
 * File Created: 2021/11/23 14:41:28
 * Author: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 * -----
 * Last Modified: 2021/11/23 15:17:47
 * Modified By: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 */
package anony.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @Column(name = "create_on")
    private LocalDateTime createOn;

    protected User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreateOn() {
        return createOn;
    }

    public void setCreateOn(LocalDateTime createOn) {
        this.createOn = createOn;
    }

    public User(String username, String password, LocalDateTime createOn) {
        this.username = username;
        this.password = password;
        this.createOn = createOn;
    }


}
