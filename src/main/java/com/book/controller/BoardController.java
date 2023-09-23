package com.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.DTO.BoardDTO;
import com.book.entity.Board;
import com.book.service.BoardService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class BoardController {
	
	private final BoardService boardService;

	@GetMapping("/")
	public String boardList(Model model) {
		List<Board> board = this.boardService.AllBoard();
		model.addAttribute("board", board);
		return "main";
	}
	
	@GetMapping("/create")
	public String createBoard(BoardDTO boardDTO) {
		return "create";
	}
	@PostMapping("/create")
	public String postCreateBoard(@Validated BoardDTO boardDTO, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "create";
		}
		this.boardService.insertBoard(boardDTO);
		return "redirect:/";
	}
	@GetMapping(value = "/{id}")
	public String getBoard(@PathVariable("id") Long idx, Model model) {
		try {
			Board board = this.boardService.getBoard(idx);
			model.addAttribute("board",board);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "detail";
	}
	
	@GetMapping(value="delete/{id}")
	public String deleteBoard(@PathVariable("id") Long idx) {
		this.boardService.deleteBoard(idx);
		return "redirect:/";
	}
	@GetMapping(value = "/modifiy/{id}")
	public String modify(BoardDTO boardDTO, Model model, @PathVariable("id") Long idx) {
		Board board = this.boardService.getBoard(idx);
		model.addAttribute("baord", board);
		
		return "modify";
	}
	@PostMapping(value = "/modifiy/{id}")
	public String modify(@PathVariable("id") Long idx, BoardDTO boardDTO) {
		this.boardService.modifyBoard(idx, boardDTO);
		return "redirect:/";
	}
}
