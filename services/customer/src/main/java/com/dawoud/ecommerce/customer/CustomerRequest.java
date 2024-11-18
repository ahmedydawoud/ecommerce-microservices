package com.dawoud.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
         String id,
         @NotNull(message = "First name is required")
         String firstname,
         @NotNull(message = "Last name is required")
         String lastname,
         @NotNull(message = "Email name is required")
         @Email(message = "email is not valid")
         String email,
         Address address
) {
}
