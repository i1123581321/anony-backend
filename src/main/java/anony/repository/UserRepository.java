/*
 * File Created: 2021/11/23 15:12:47
 * Author: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 * -----
 * Last Modified: 2021/12/12 12:22:02
 * Modified By: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 */
package anony.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import anony.entity.User;
import anony.payload.response.UserProjection;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query("select u from User u where u.username = ?1")
    Optional<UserProjection> findResponseByUsername(String username);
}
