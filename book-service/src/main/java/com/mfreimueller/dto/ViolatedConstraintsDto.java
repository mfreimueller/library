package com.mfreimueller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ViolatedConstraintsDto extends ExceptionDto {
    private List<ViolatedFieldDto> violations;

    public ViolatedConstraintsDto(String message, List<ViolatedFieldDto> violations) {
        super(message);
        this.violations = violations;
    }
}
