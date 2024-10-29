package com.example.prjorigin.service;

import com.example.prjorigin.dto.Board;
import com.example.prjorigin.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper mapper;

    public void add(Board board) {
        mapper.insert(board);
    }

    public Map<String, Object> list(Integer page) {
        Integer offset = (page - 1) * 10;

        List<Board> list = mapper.selectAllPaging(offset);

        Map<String, Object> map = new HashMap<>();

        Integer countAll = mapper.countAll();
        Integer lastPageNumber = (countAll - 1) / 10 + 1;


        map.put("lastPageNumber", lastPageNumber);
        map.put("boardList", list);
        return map;
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
