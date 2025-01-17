package com.myfruit.pms.controller;

import com.myfruit.pms.dto.ItemDto;
import com.myfruit.pms.mapper.ItemMapper;
import com.myfruit.pms.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/{id}")
    @ResponseBody
    public ItemDto getItem(@PathVariable("id") int id) {
        try {
            return itemService.getItem(id);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }
}