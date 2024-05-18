package com.groades.nttdata.request;

import com.groades.nttdata.resources.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    private String name;
    private String email;
    private String password;
    private List<Phone> phones;
}
