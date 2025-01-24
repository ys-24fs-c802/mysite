package com.myfruit.pms.service;

import com.myfruit.pms.dto.ItemDto;
import com.myfruit.pms.dto.PageDto;
import com.myfruit.pms.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemMapper itemMapper;

    public void createItem(ItemDto itemDto) {
        itemMapper.insertItem(itemDto);
    }

    public ItemDto getItem(int id) {
        // NullException 처리
//        ItemDto itemDto = itemMapper.getItemById(id).orElseThrow(
//                () -> new IllegalStateException("파일을 찾을 수 없습니다.")
//        );
        return itemMapper.getItemById(id).orElseThrow(
                () -> new IllegalStateException("파일을 찾을 수 없습니다.")
        );
    }

    public PageDto<ItemDto> getItems(int page, int limit) {
        int offset = (page - 1) * limit;
        // 갯수가 size인 item목록
        List<ItemDto> items = itemMapper.getItems(limit, offset);
        // 총갯수
        int totalElements = itemMapper.countTotal();
        // 총페이지
        // 13/5 2.xxx 2, 3 Math.ceil(2.xxx) => 3.0  => 3
        //int totalPages = (int) Math.ceil((double) totalElements / limit);

        PageDto<ItemDto> pageDto = new PageDto<ItemDto>(page, limit, totalElements, items);
        return pageDto;
    }

    public void modifyItem(ItemDto itemDto) {
        itemMapper.updateItem(itemDto);
    }

    public void removeItem(int id) {
        itemMapper.deleteItem(id);
    }

    // 메서드 구문
    // 접근제어자 리턴타입 메서드이름() {}
}
