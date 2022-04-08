package com.kc.pingpang.platform.freamwork.http.api.api;

public class PagingRequest {
	
	private boolean paged;
	private int pageNumber;
	private int pageSize;	

	public PagingRequest() {
		this.paged = true;
		this.pageNumber = 1;
		this.pageSize = 10;
	}
	
	public boolean isPaged() {
		return paged;
	}

	public void setPaged(boolean paged) {
		this.paged = paged;
	}

	public int getPageNumber() {
        return pageNumber;
    }
    
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
