package com.groades.nttdata.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Phone {
    private int number;
    private int citycode;
    private int countrycode;
}
