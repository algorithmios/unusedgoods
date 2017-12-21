package edu.swust.goods.tempbean;
/**
 * 查询信息映射类
 * @author hanpeng
 *
 */
public class QueryBean {
    private String classification = null;
    private String keyWords = null;
    private Double min = null;
    private Double max = null;
    private Integer start = 0;
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public Double getMin() {
		return min;
	}
	public void setMin(Double min) {
		this.min = min;
	}
	public Double getMax() {
		return max;
	}
	public void setMax(Double max) {
		this.max = max;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	@Override
	public String toString() {
		return "QueryBean [classification=" + classification + ", keyWords=" + keyWords + ", min=" + min + ", max="
				+ max + ", start=" + start + "]";
	}
	
}
