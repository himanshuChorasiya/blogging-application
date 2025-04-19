package com.hblog.api.payloads;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponsePage {

	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private int totalPages;
	private boolean lastPage;
}
