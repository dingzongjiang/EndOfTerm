package com.example.endofterm.mapper;

import com.example.endofterm.pojo.BorrowRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BorrowRecordMapper {
    int addBorrowRecord(BorrowRecord borrowRecord);
}
