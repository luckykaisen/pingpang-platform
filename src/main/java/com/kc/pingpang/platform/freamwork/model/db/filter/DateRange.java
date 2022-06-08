package com.kc.pingpang.platform.freamwork.model.db.filter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateRange {
	private String from;
    private String to;

    public DateRange() {
    	
    }
    
    public DateRange(String from, String to) {
    	this.from = from;
    	this.to = to;
    }
    
    public DateRange(Date fromDate, Date toDate) {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	
    	this.from = dateFormat.format(fromDate);
    	this.to = dateFormat.format(toDate);
    }
    
    public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
}
