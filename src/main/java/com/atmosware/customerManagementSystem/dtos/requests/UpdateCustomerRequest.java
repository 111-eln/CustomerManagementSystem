package com.atmosware.customerManagementSystem.dtos.requests;

import com.atmosware.customerManagementSystem.crossCuttingConcerns.ValidLong;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class UpdateCustomerRequest {
    @NotNull
    @Pattern(regexp = "^[0-9]{11}$",message = "CitizenNumber must be 11 digits and contain only numbers")
    private String citizenNumber;
    @NotNull
    private boolean is_active;

}
