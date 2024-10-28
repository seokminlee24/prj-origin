package com.example.prjorigin.service;

import com.example.prjorigin.dto.Board;
import com.example.prjorigin.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper mapper;

    public void add(Board board) {
        mapper.insert(board);
    }

    public List<Board> list() {
        List<Board> list = mapper.selectAll();
        return list;
    }
}
