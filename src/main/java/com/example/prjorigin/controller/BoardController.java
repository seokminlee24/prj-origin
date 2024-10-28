package com.example.prjorigin.controller;

import com.example.prjorigin.dto.Board;
import com.example.prjorigin.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService service;

    @GetMapping("new")
    public void newBoard() {

    }

    @PostMapping("new")
    public String newBoard(Board board) {
        service.add(board);

        return "redirect:/board/new";
    }

    @GetMapping("list")
    public void listBoard(Model model) {
        List<Board> list = service.list();
        model.addAttribute("boardList", list);

    }
}
