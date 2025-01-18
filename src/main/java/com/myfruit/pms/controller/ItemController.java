package com.myfruit.pms.controller;

import com.myfruit.pms.dto.ItemDto;
import com.myfruit.pms.mapper.ItemMapper;
import com.myfruit.pms.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemMapper itemMapper;

    @GetMapping
    public String getItems(Model model) {
        List<ItemDto> items = itemService.getItems();
        model.addAttribute("items", items);

        return "shop/list";
    }

    @GetMapping("/create")
    public String create() {
        return "shop/create-item";
    }

    @PostMapping
    public ResponseEntity<Void> createItem(@RequestBody ItemDto itemDto) {
        // JAON 요청데이터 처리를 위해 @RequestBody을 사용
        // ItemDto가 자동으로 바인딩
        System.out.println(itemDto.getItem());
        itemMapper.insertItem(itemDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public String getItem(@PathVariable("id") int id, Model model) {
        ItemDto itemDto = itemService.getItem(id);
        model.addAttribute("item", itemDto);

        return "shop/detail";
    }
}