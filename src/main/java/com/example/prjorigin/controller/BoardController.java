package com.example.prjorigin.controller;

import com.example.prjorigin.dto.Board;
import com.example.prjorigin.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService service;

    @GetMapping("new")
    public void newBoard() {

    }

    @PostMapping("new")
    public String newBoard(Board board, RedirectAttributes rttr) {
        service.add(board);

        rttr.addFlashAttribute("message", Map.of("type", "success",
                "text", "새 게시물이 작성되었습니다."));
        return "redirect:/board/list";
    }

    @GetMapping("list")
    public void listBoard(Model model) {
        List<Board> list = service.list();
        model.addAttribute("boardList", list);

    }

    @GetMapping("view")
    public void viewBoard(Integer id, Model model) {
        Board board = service.get(id);
        model.addAttribute("board", board);
    }

    @PostMapping("delete")
    public String deleteBoard(Integer id, RedirectAttributes rttr) {
        service.remove(id);
        rttr.addFlashAttribute("message", Map.of("type", "warning",
                "text", id + "번 게시물 삭제 했습니다."));
        return "redirect:/board/list";
    }

    @GetMapping("edit")
    public void editBoard(Integer id, Model model) {
        Board board = service.get(id);
        model.addAttribute("board", board);
    }

    @PostMapping("edit")
    public String editBoard(Board board, RedirectAttributes rttr) {
        service.update(board);
        rttr.addAttribute("id", board.getId());

        rttr.addFlashAttribute("message", Map.of("type", "success",
                "text", board.getId() + "번 게시물 수정 했습니다."));
        return "redirect:/board/view";
    }
}
