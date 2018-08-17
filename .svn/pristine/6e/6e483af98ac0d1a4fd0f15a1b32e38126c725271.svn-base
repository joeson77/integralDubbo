package com.cam.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * 作者：ChenJianrong
 *
 * 创建时间：2018年7月29日 下午2:54:04
 *
 * 功能描述： 分页对象
 * 
 * 版本： V1.0
 */
public class PageResult implements Serializable{
    private static final long serialVersionUID=2L;
    private Integer totalCount;
    private Integer currentPage=1;
    private Integer pageSize=8;
    private List result;

    public PageResult(){}

    public PageResult(Integer totalCount, Integer currentPage, Integer pageSize, List result) {
        super();
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.result = result;
    }

    public Integer getTotalPage(){
        return totalCount%pageSize==0 ? totalCount/pageSize : totalCount/pageSize+1;
    }

    public Integer getPrev(){
        return Math.max(currentPage-1,1);
    }

    public Integer getNext(){
        return Math.min(currentPage+1,getTotalPage());
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public static PageResult empty(Integer pageSize) {
        return new PageResult(0,1,pageSize,new ArrayList());
    }

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }
}
