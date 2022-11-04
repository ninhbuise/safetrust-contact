package com.safetrust.contacts.viewmodel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public record ContactPostVm(
        @NotEmpty String name,
        @NotEmpty @Email String email,
        @NotEmpty String phone,
        String postalAddress
) {
}
