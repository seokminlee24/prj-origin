package com.example.prjorigin.service;

import com.example.prjorigin.dto.Board;
import com.example.prjorigin.dto.Member;
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

    public void add(Board board, Member member) {
        mapper.insert(board, member);
    }

    public Map<String, Object> list(Integer page) {
        Integer offset = (page - 1) * 10;

        List<Board> list = mapper.selectAllPaging(offset);

        Map<String, Object> map = new HashMap<>();

        Integer countAll = mapper.countAll();
        Integer lastPageNumber = (countAll - 1) / 10 + 1;
        Integer rightPageNumber = ((page - 1) / 10 + 1) * 10;
        Integer leftPageNumber = rightPageNumber - 9;
        Integer nextPageNumber = rightPageNumber + 1;
        Integer prevPageNumber = leftPageNumber - 1;
        boolean hasNextPage = nextPageNumber < lastPageNumber;
        boolean hasPrevPage = prevPageNumber > 0;

        rightPageNumber = Math.min(rightPageNumber, lastPageNumber);

        Map<String, Object> pageInfo = new HashMap<>();

        pageInfo.put("nextPageNumber", nextPageNumber);
        pageInfo.put("prevPageNumber", prevPageNumber);
        pageInfo.put("hasNextPage", hasNextPage);
        pageInfo.put("hasPrevPage", hasPrevPage);
        pageInfo.put("leftPageNumber", leftPageNumber);
        pageInfo.put("rightPageNumber", rightPageNumber);
        pageInfo.put("lastPageNumber", lastPageNumber);
        pageInfo.put("currentPageNumber", page);


        map.put("pageInfo", pageInfo);
        map.put("boardList", list);
        return map;
    }

    public Board get(Integer id) {
        return mapper.selectById(id);
    }

    public void remove(Integer id, Member member) {
        Board board = mapper.selectById(id);
        if (board.getWriter().equals(member.getId())) {
            mapper.deleteById(id);
        } else {
            throw new RuntimeException("삭제 권한이 없습니다.");
        }
        mapper.selectById(id);
    }

    public void update(Board board, Member member) {
        Board board1 = mapper.selectById(board.getId());
        if (board1.getWriter().equals(member.getId())) {
            mapper.update(board);
        } else {
            throw new RuntimeException("수정 권한이 없습니다.");
        }
    }
}
