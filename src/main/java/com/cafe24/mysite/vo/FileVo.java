package com.cafe24.mysite.vo;

public class FileVo {
	private Long no;
	private Long boardNo;
	private String url;
	private String oriName;
	private String changeName;
	private String path;
	private Long size;
	@Override
	public String toString() {
		return "FileVo [no=" + no + ", boardNo=" + boardNo + ", url=" + url + ", oriName=" + oriName + ", changeName="
				+ changeName + ", path=" + path + ", size=" + size + "]";
	}
	public Long getNo() {
		return no;
	}
	public Long getBoardNo() {
		return boardNo;
	}
	public String getUrl() {
		return url;
	}
	public String getOriName() {
		return oriName;
	}
	public String getChangeName() {
		return changeName;
	}
	public String getPath() {
		return path;
	}
	public Long getSize() {
		return size;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public void setBoardNo(Long boardNo) {
		this.boardNo = boardNo;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setOriName(String oriName) {
		this.oriName = oriName;
	}
	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	
	
}
