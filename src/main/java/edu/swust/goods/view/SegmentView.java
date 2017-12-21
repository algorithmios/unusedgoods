package edu.swust.goods.view;

import java.util.ArrayList;
import java.util.List;
/**
 * 局部分级类
 * @author hanpeng
 *
 * @param <T> 待分级的映射类
 */
public class SegmentView<T> {
    private String description;
    List<T> views;
    public SegmentView() {
    	views = new ArrayList<T>();
	}
    
    public SegmentView(String description, List<T> views) {
		this.description = description;
		this.views = views;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<T> getViews() {
		return views;
	}
	public void setViews(List<T> views) {
		this.views = views;
	}
    
}
