package com.nsearch.hr.controller;

import com.nsearch.hr.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor

@Builder
@Data
public class RequestResponse {
    private String message;
    private List<User> results;
}
