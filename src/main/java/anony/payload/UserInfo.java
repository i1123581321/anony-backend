/*
 * File Created: 2021/11/23 15:23:12
 * Author: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 * -----
 * Last Modified: 2021/12/02 19:20:58
 * Modified By: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 */
package anony.payload;

import java.time.LocalDateTime;

public interface UserInfo {
    public String getUsername();

    public LocalDateTime getCreateOn();
}
