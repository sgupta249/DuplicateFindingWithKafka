package com.alive.springboot.impl;

import com.alive.springboot.interfaces.IDuplicate;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DuplicateImpl implements IDuplicate {

    @Override
    public JsonObject findDuplicate(String messageBody) {
        String message = messageBody.replace(" ", "");
        String[] split = message.split(",");
        Set<String> set = new HashSet<>();
        Set<String> unique = Arrays.stream(split).filter(x -> !set.add(x)).collect(Collectors.toSet());
        List<String> collect = Arrays.stream(split).distinct().collect(Collectors.toList());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("duplicate values", unique.stream().collect(Collectors.joining(",")));
        jsonObject.addProperty("String after removing duplicate", collect.stream().collect(Collectors.joining(",")));
        return jsonObject;
    }
}
