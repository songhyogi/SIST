package kr.spring.ch11.vo;

public class PageRank {
	private int rank;
	private String page;
	
	public PageRank(int rank, String page) {//인자가 있는 생성자
		this.rank = rank;
		this.page = page;
	}
	
	//인자가 있는 생성자를 사용할 수 있지만 set메서드를 사용할 수 있기 때문에 기본생성자를 넣어주는 것이 좋다.
	public PageRank() {}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	
	@Override
	public String toString() {
		return "PageRank [rank=" + rank + ", page=" + page + "]";
	}
	
}
