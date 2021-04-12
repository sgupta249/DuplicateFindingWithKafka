package com.alive.springboot.interfaces;

import com.google.gson.JsonObject;

public interface IDuplicate {
    JsonObject findDuplicate(String messageBody);
}