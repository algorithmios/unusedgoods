package edu.swust.goods.view;

import edu.swust.goods.domain.Suggestion;
import edu.swust.goods.utils.TimeToStringUtil;
/**
 * 建议映射类
 * @author hanpeng
 *
 */
public class SuggestionView {
	private Long id;
    private String fromName;
    private String content;
    private String date;
    
    public SuggestionView() {
	}
    
    public SuggestionView(Suggestion suggestion) {
		id = suggestion.getId();
		fromName = suggestion.getFromName();
		content = suggestion.getContent();
		date = TimeToStringUtil.timeToString(suggestion.getDate());
	}
    public SuggestionView(int length, int pageSize) {
    	id = Long.valueOf(length);
    	fromName = String.valueOf(pageSize);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
