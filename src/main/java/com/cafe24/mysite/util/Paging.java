package com.cafe24.mysite.util;

import java.util.ArrayList;
import java.util.List;

public class Paging {
	private static List<Long> pageList;

	public static List<Long> getPageList() {
		return pageList;
	}

	public Paging(Long nowPage, Long pageSize) {
		if (pageSize == 0 ) {
			pageSize = 1L;
		}
		
		if(nowPage<=2 && pageSize>=5) {
			nowPage = 3L;
		}
		List<Long> li = new ArrayList();
		for(int i=-2; i<=2; i++) {
			Long result = nowPage + i;
			if(result >= 1 && result <= pageSize) {
				li.add(result);				
			}
		}
		if(li.size()<5 && li.get(0) >2) {
			Long differnce = pageSize - nowPage;
			if(differnce == 1) {
				li.add(0,li.get(0)-1);
			} else if(differnce == 0 ) {
				li.add(0,li.get(0)-1);
				li.add(0,li.get(0)-1);
			}
		}
		pageList = li;
	}


}
