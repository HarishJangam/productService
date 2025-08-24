package com.capstone.springprojecta.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
public class GetAllProductDto {
    private String status;
    private String message;
    private List<FakeStroreProductServiceDto> products;
}
