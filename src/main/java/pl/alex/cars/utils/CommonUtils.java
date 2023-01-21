package pl.alex.cars.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class CommonUtils {

	public static Pageable createPageable(CarPageable pageable) {
		return createPageable(pageable, Sort.by("name"));
	}

	public static Pageable createPageable(CarPageable pageable, Sort sort) {
		if(pageable.getPageNumber() == null) {
			pageable.setPageNumber(0);
		}
		if(pageable.getPageSize() == null || pageable.getPageSize() > 20) {
			pageable.setPageSize(20);
		}
		// TODO: exceptions if page number < 0 || page size <= 0 
		return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
	}

}
