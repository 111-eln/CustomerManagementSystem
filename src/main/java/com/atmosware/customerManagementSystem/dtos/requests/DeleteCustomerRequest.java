package com.atmosware.customerManagementSystem.dtos.requests;

import com.atmosware.customerManagementSystem.crossCuttingConcerns.ValidLong;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeleteCustomerRequest {
    @NotNull
    @ValidLong(message = "CitizenNumber must be 11 digits and contain only numbers")
    private long citizenNumber;
}
