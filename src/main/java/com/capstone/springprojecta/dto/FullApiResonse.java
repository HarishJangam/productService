package com.capstone.springprojecta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FullApiResonse {
    private String status;
    private String message;
    private FakeStroreProductServiceDto product;
}
