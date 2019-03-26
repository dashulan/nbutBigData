package com.bigdata.storm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entity {
    private String code;
    private Long requestTime;
    private String returnType;

}
