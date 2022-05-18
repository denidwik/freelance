package com.project.freelance.dto.request;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class BaseFilter {
	private int pageNo;
	private int pageSize;
	private String sortOrder;
	private String sortBy;
	private Integer isActive;
	private String sort;
}
