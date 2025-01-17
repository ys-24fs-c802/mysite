package com.myfruit.pms.controller;

import com.myfruit.pms.dto.ItemDto;
import com.myfruit.pms.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemMapper itemMapper;

    @GetMapping("/create")
    public String create() {
        return "shop/create-item";
    }

    @PostMapping
    public void createItem(@ModelAttribute ItemDto itemDto) {
        // @ModelAttribute를 사용하여 ItemDto가 자동으로 바인딩
        System.out.println(itemDto.getItem());
        itemMapper.insertItem(itemDto);
    }

}