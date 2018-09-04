package com.example.ci.cidemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DemoModel {
    private long id;
    private String payload;
}
