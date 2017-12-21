package edu.swust.goods.view;
/**
 * 分页
 * @author hanpeng
 *
 */

import java.util.List;
/**
 * 分页对象
 * @author hanpeng
 *
 * @param <T> 待分页的映射类
 */
public class PaginationView<T> {
    private Integer currentPageCount;//从 1开始
    private Integer allPageCount;
	private List<T> list;
	public PaginationView() {
	}
	
	public PaginationView(Integer currentPageCount, Integer allPageCount, List<T> list) {
		this.currentPageCount = currentPageCount;
		this.allPageCount = allPageCount;
		this.list = list;
	}

	public Integer getCurrentPageCount() {
		return currentPageCount;
	}
	public void setCurrentPageCount(Integer currentPageCount) {
		this.currentPageCount = currentPageCount;
	}
	public Integer getAllPageCount() {
		return allPageCount;
	}
	public void setAllPageCount(Integer allPageCount) {
		this.allPageCount = allPageCount;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
