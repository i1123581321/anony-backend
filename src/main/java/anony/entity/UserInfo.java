/*
 * File Created: 2021/11/23 15:23:12
 * Author: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 * -----
 * Last Modified: 2021/11/23 15:23:47
 * Modified By: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 */
package anony.entity;

import java.time.LocalDateTime;

public interface UserInfo {
    public String getUsername();

    public LocalDateTime getCreateOn();
}
