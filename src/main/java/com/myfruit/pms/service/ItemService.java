package com.myfruit.pms.service;

import com.myfruit.pms.dto.ItemDto;
import com.myfruit.pms.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemMapper itemMapper;

    public List<ItemDto> getItems() {
        return itemMapper.getItems();
    }

    public ItemDto getItem(int id) {
        return itemMapper.getItemById(id);
    }

    // 메서드 구문
    // 접근제어자 리턴타입 메서드이름() {}
}
