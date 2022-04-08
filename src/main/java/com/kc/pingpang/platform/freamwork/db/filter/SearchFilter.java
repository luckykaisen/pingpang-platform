package com.kc.pingpang.platform.freamwork.db.filter;

import com.kc.pingpang.platform.freamwork.http.api.api.PagingRequest;
import org.springframework.util.Assert;

public class SearchFilter {
    // 用于控制最多的返回记录数，避免造成数据性能问题
    private static final int MAX_RECORD = 10000;
    
    private Integer limit;
    
    private boolean paged;
    private PagingData pagingData;
    
    private boolean ordered;

    public SearchFilter() {
    	
    }
    
    public SearchFilter(int pageNumber, int pageSize) {
    	pagingData = new PagingData(pageNumber, pageSize);
    	paged = true;
    }

    public boolean isPaged() {
        return paged;
    }
    
    public void setPaged(boolean paged) {
        this.paged = paged;
    }
    
    public PagingData getPagingData() {
        return pagingData;
    }
    
    public void setPagingData(PagingData pagingData) {
        this.pagingData = pagingData;
    }
    
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public int getLimit() {
        if (limit  == null) {
            return MAX_RECORD;
        }
        return limit;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

	public void initPagingAndOrdering(PagingRequest request) {
		if (request.isPaged()) {
			Assert.isTrue(request.getPageSize() <= 200, "Max page size is 200.");
			
			setPaged(true);
			setPagingData(new PagingData(request.getPageNumber(), request.getPageSize()));
		}
	}
}
