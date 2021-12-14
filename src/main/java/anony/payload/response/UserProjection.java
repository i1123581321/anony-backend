/*
 * File Created: 2021/11/23 15:23:12
 * Author: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 * -----
 * Last Modified: 2021/12/03 08:52:30
 * Modified By: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 */
package anony.payload.response;

import java.time.Instant;

public interface UserProjection {
    String getUsername();

    Instant getCreateOn();

    Instant getUpdateOn();
}
