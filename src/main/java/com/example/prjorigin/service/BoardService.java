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

    public List<Board> list(Integer page) {
        Integer offset = (page - 1) * 10; // page가 1일 때 offset은 0
        return mapper.selectAllPaging(offset);
    }

    public Board get(Integer id) {
        return mapper.selectById(id);
    }

    public void remove(Integer id) {
        mapper.deleteById(id);
    }

    public void update(Board board) {
        mapper.update(board);
    }
}
