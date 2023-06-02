package com.example.endofterm.serivce.Impl;

import com.example.endofterm.mapper.BorrowRecordMapper;
import com.example.endofterm.pojo.BorrowRecord;
import com.example.endofterm.serivce.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {
    private final BorrowRecordMapper borrowRecordMapper;

    @Autowired
    public BorrowRecordServiceImpl(BorrowRecordMapper borrowRecordMapper) {
        this.borrowRecordMapper = borrowRecordMapper;
    }


    @Override
    public int addBorrowRecord(BorrowRecord borrowRecord) {
        System.out.println(borrowRecord);
        return borrowRecordMapper.addBorrowRecord(borrowRecord);
    }
}
