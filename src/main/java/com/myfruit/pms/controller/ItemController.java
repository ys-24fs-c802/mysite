package com.myfruit.pms.controller;

import com.myfruit.pms.dto.ItemDto;
import com.myfruit.pms.dto.PageDto;
import com.myfruit.pms.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/create")
    public String create() {
        return "shop/item/create-item";
    }

    @PostMapping
    @ResponseBody
    public void createItem(@RequestBody ItemDto itemDto) {
        System.out.println(itemDto.getName());
        itemService.createItem(itemDto);
    }

    @GetMapping("/{id}")
    public String getItem(@PathVariable("id") int id, Model model) {
        try {
            ItemDto itemDto = itemService.getItem(id);
            model.addAttribute("item", itemDto);
        } catch (IllegalStateException e) {
            model.addAttribute("message", e.getMessage());
            return "common/error/404";
        }
        return "shop/item/detail";
    }

    // 요청URL 형식: /items?page=1&limit=10
    @GetMapping
    public String getItems(@RequestParam(name="page", defaultValue = "1") int page,
                           @RequestParam(name="limit", defaultValue = "3") int limit,
                           Model model) {
        PageDto<ItemDto> pageDto = itemService.getItems(page, limit);
        model.addAttribute("pageDto", pageDto);

        return "shop/item/list";
    }

    // modify나 edit을 사용한다.
    @GetMapping("/{id}/modify")
    public String getItem2(@PathVariable("id") int id, Model model) {
        try {
            ItemDto itemDto = itemService.getItem(id);
            model.addAttribute("item", itemDto);
        } catch (IllegalStateException e) {
            model.addAttribute("message", e.getMessage());
            return "common/error/404";
        }
        return "shop/item/modify";
    }


    @PostMapping("/{id}/modify")
    @ResponseBody
    public void modifyItem(@RequestBody ItemDto itemDto) {

        System.out.println(itemDto.getName());
        itemService.modifyItem(itemDto);
    }

    @GetMapping("/{id}/remove")
    public String removeItem(@PathVariable("id") int id) {
        itemService.removeItem(id);
        return "redirect:/items";
    }


    // 생성 페이지 GET /items/create
    // 생성 POST /items
    // 상세보기 GET /items/{id}
    // 수정페이지 GET /items/{id}/modify
    // 수정 POST /items/{id}
    // 삭제 GET /items/{id}/remove
    // 목록 GET /items

    // 생성 페이지 GET /items/create
    // 생성 POST /items
    // 상세보기 GET /items/{id}
    // 수정 PUT /items/{id}
    // 삭제 DELETE /items/{id}
    // 목록 GET /items

}