package com.myfruit.pms.controller;

import com.myfruit.pms.dto.ItemDto;
import com.myfruit.pms.mapper.ItemMapper;
import com.myfruit.pms.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemService itemService;

    @GetMapping("/create")
    public String create() {
        return "shop/create-item";
    }

    @PostMapping
    @ResponseBody
    public void createItem(@RequestBody ItemDto itemDto) {
        System.out.println(itemDto.getItem());
        itemMapper.insertItem(itemDto);
    }

    @GetMapping("/{id}")
    public String getItem(@PathVariable("id") int id, Model model) {
        ItemDto itemDto = itemService.getItem(id);
        model.addAttribute("item", itemDto);

        return "shop/detail";
    }
}