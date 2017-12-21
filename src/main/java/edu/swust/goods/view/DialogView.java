package edu.swust.goods.view;

import java.util.Date;
import java.util.List;
/**
 * 一次回话
 * @author hanpeng
 *
 */
public class DialogView implements Comparable<DialogView> {
    private Long id;    ///<回话id
    private Long time;  ///<回话最新更新时间
    private List<DialogContentView> contents;
                        ///<回话内容
	public DialogView() {
	}
	
    public DialogView(Long id, List<DialogContentView> contents) {
    	time = new Date().getTime();
		this.id = id;
		this.contents = contents;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public List<DialogContentView> getContents() {
		return contents;
	}

	public void setContents(List<DialogContentView> contents) {
		this.contents = contents;
	}
	
	public void setTime(Long time) {
		this.time = time;
	}

	public int compareTo(DialogView o) {
		
		return o.time.compareTo(time);
	}
	
}
