package com.varomir.qa.commons.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;

public interface WithFaker {

    default Name getFakePersonName() {

        return Faker.instance().name();
    }
}
