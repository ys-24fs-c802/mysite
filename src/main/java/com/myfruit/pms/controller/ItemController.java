package com.myfruit.pms.controller;

import com.myfruit.pms.dto.ItemDto;
import com.myfruit.pms.dto.PageDto;
import com.myfruit.pms.mapper.ItemMapper;
import com.myfruit.pms.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> createItem(@Valid @RequestBody ItemDto itemDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, List<String>> errorMap = new HashMap<>();

            // BindingResult에서 에러들을 순회
            bindingResult.getFieldErrors().forEach(error -> {
                // 에러가 발생한 필드명 추출
                String field = error.getField();
                // 해당 필드의 에러 메시지 추출
                String message = error.getDefaultMessage();

                // errorMap에 필드별 에러메시지 리스트 추가
                // computeIfAbsent: 해당 key가 없으면 새 ArrayList 생성
                // 있으면 기존 리스트에 메시지 추가
                // 참고: https://tinyurl.com/mrxbfpz8
                errorMap.computeIfAbsent(field, k -> new ArrayList<>()).add(message);
            });

            return ResponseEntity.badRequest().body(errorMap);
        }
        itemService.createItem(itemDto);
        return ResponseEntity.ok().build();
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