package com.atmosware.customerManagementSystem.dtos.requests;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByCitizenNumberCustomerRequest {
    @NotNull
    @Pattern(regexp = "^[0-9]{11}$",message = "CitizenNumber must be 11 digits and contain only numbers")
    private String citizenNumber;

}
