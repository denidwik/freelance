package com.project.freelance.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Paginated<T> {
	public List<T> results;
	public int totalPage;
	public long totalRecord;
}
