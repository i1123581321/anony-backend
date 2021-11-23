/*
 * File Created: 2021/11/23 15:12:47
 * Author: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 * -----
 * Last Modified: 2021/11/23 15:24:11
 * Modified By: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 */
package anony.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import anony.entity.User;
import anony.entity.UserInfo;

public interface UserRepository extends CrudRepository<User, Long> {
    List<UserInfo> findByUsername(String username);
}
