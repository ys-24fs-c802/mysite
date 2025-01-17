package com.myfruit.pms.mapper;

import com.myfruit.pms.dto.ItemDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {
    // ItemDto insertItem(ItemDto itemDto); // PK가 생성, 전달된 객체와 같은 참조를 가지고 있어 정보 추가
    // int insertItem(ItemDto itemDto); // PK가 생성, int는 성공 또는 실패 반환
    void insertItem(ItemDto itemDto); // PK가 생성
    Optional<ItemDto> getItemById(int id);
    List<ItemDto> getItems();
    void updateItem(ItemDto itemDto); // 내용을 변경
    void deleteItem(int id);
}
