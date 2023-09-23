package com.book.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.book.DTO.BoardDTO;
import com.book.entity.Board;
import com.book.repository.BoardRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	
	public void insertBoard(BoardDTO boardDTO) {
		Board board = new Board();
		board.setTitle(boardDTO.getTitle());
		board.setContent(boardDTO.getContent());
		this.boardRepository.save(board);
	}
	public void deleteBoard(Long idx) {
		this.boardRepository.deleteById(idx);
	}
	public void modifyBoard(Long idx, BoardDTO boardDTO) {
		Optional<Board> _board = this.boardRepository.findById(idx);
		if(_board.isEmpty()) {
			throw new NullPointerException();
		}else {
			Board board = _board.get();
			board.setTitle(boardDTO.getTitle());
			board.setContent(boardDTO.getContent());
			this.boardRepository.save(board);
		}
	}
	public List<Board> AllBoard(){
		return this.boardRepository.findAll();
	}
	public Board getBoard(Long idx) {
		Optional<Board> board = this.boardRepository.findById(idx);
		if(board.isEmpty()) {
			throw new NullPointerException();
		}else {
			return board.get();
		}
	}
}
