package com.example.quizapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.quizapp.dto.BookmarkRequest;
import com.example.quizapp.dto.MessageResponse;
import com.example.quizapp.dto.PageResponse;
import com.example.quizapp.dto.UnifiedResponse;
import com.example.quizapp.entity.Bookmark;
import com.example.quizapp.enums.Severity;
import com.example.quizapp.service.BookmarkService;
import com.example.quizapp.service.UserService;
import com.example.quizapp.util.CommonHelper;
import com.example.quizapp.util.ResponseBuilder;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {

	@Autowired
	BookmarkService bookmarkService;

	@Autowired
	UserService userService;

	@Autowired
	CommonHelper commonHelper;

	@PostMapping()
	public ResponseEntity<UnifiedResponse<MessageResponse>> bookmark(@Valid @RequestBody BookmarkRequest request) {
		return ResponseBuilder.buildResponse(HttpStatus.CREATED, bookmarkService.bookmarkQuiz(request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UnifiedResponse<Void>> deleteBookmark(@PathVariable("id") Long id) {
		return ResponseBuilder.buildOKResponse(bookmarkService.deleteBookmarkById(id));
	}

	@GetMapping("/filters")
	public ResponseEntity<UnifiedResponse<PageResponse<Bookmark>>> filterBookmarks(
			@RequestParam(required = false) String query, @RequestParam(required = false) Severity severity,
			@RequestParam(required = false) Long timeLimit, @RequestParam(required = false) Boolean randomizeQuestions,
			@RequestParam(required = false) Long categoryId, @RequestParam(required = false) String sort,
			@RequestParam(required = false) String start, @RequestParam(required = false) String end,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = Integer.MAX_VALUE + "") int size) {
		return ResponseBuilder.buildOKResponse(bookmarkService.findBookmarksByFilters(start, end, query, severity,
				categoryId, timeLimit, randomizeQuestions, sort, commonHelper.makePageReq(page, size)));
	}
}
