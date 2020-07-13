package com.smartosc.training.searchHelp;

import org.modelmapper.internal.util.ToStringBuilder;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 09/07/2020 - 3:04 PM
 */
public class SearchDTO {
    private int pageIndex;

    private String searchTerm;

    public SearchDTO() {

    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

}
