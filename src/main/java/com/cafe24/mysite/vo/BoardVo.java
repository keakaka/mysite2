package com.cafe24.mysite.vo;

public class BoardVo {
	private Long no;
	private Long writerNo;
	private String writer;
	private String title;
	private String content;
	private String writeDate;
	private int hit;
	private int groupNo;
	private int orderNo;
	private int depth;
	private String status;
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", writerNo=" + writerNo + ", writer=" + writer + ", title=" + title + ", content="
				+ content + ", writeDate=" + writeDate + ", hit=" + hit + ", groupNo=" + groupNo + ", orderNo="
				+ orderNo + ", depth=" + depth + ", status=" + status + "]";
	}
	public Long getNo() {
		return no;
	}
	public Long getWriterNo() {
		return writerNo;
	}
	public String getWriter() {
		return writer;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public int getHit() {
		return hit;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public int getDepth() {
		return depth;
	}
	public String getStatus() {
		return status;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public void setWriterNo(Long writerNo) {
		this.writerNo = writerNo;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
