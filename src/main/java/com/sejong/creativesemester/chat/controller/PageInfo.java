package com.sejong.creativesemester.chat.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PageInfo {
    private int page;
    private int size;
    private int totalElements;
    private int totalPages;

}
