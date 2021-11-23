/*
 * File Created: 2021/11/23 15:41:38
 * Author: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 * -----
 * Last Modified: 2021/11/23 15:42:52
 * Modified By: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 */
package anony.entity;

public class ErrorMessage {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorMessage(String message) {
        this.message = message;
    }

    
}
