/*
 * File Created: 2021/11/23 15:12:47
 * Author: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 * -----
 * Last Modified: 2021/12/02 19:25:19
 * Modified By: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 */
package anony.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import anony.entity.User;
import anony.payload.UserInfo;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}
