package com.hnumi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Res {
    private Integer code;
    private String message;
    private Map<String, Object> map;

    public Res(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Res(Integer code, Map map) {
        this.code = code;
        this.map = map;
    }

    public Res(Integer code) {
        this.code = code;
    }
}
