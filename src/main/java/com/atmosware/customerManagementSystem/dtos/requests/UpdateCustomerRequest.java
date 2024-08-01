package com.atmosware.customerManagementSystem.dtos.requests;

import com.atmosware.customerManagementSystem.crossCuttingConcerns.ValidLong;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class UpdateCustomerRequest {
    @NotNull
    @ValidLong(message = "CitizenNumber must be 11 digits and contain only numbers")
   private long citizenNumber;
    @NotNull
    private boolean is_active;

}
