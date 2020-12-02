package com.stephen.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author stephen
 * @date 2020/12/2 3:51 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    private Integer userId;
    private String userName;
}
