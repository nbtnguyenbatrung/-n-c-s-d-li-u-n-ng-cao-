package it1.doan.webapp.admin.service;

import it1.doan.webapp.admin.service.impl.AdminPage;
import it1.doan.webapp.model.Pagination;
import org.springframework.stereotype.Component;

@Component
public class AdminPagepml implements AdminPage {
    public AdminPagepml() {
    }

    @Override
    public Pagination GetInfoPage(int totalData , int limit , int currentPage) {
        Pagination pagination = new Pagination();

        pagination.setLimit(limit);

        pagination.setTotalPage(SetInfoTotalPage(totalData,limit));

        pagination.setCurrentPage(CheckCurentPage(currentPage,pagination.getTotalPage()));
        int start = FindStart(pagination.getCurrentPage(),limit) ;
        pagination.setStart(start);
        int end = FindEnd(pagination.getStart(),limit,totalData) ;
        pagination.setEnd(end);
        return pagination;
    }

    private int FindEnd(int start, int limit, int totalData) {
        return start + limit >totalData ? totalData : start + limit -1;
    }

    private int FindStart(int currentPage, int limit) {
        if(currentPage == 0){
            currentPage = 1;
            return ((currentPage-1) * limit ) + 1;
        }else {
            return ((currentPage - 1) * limit) + 1;
        }
    }

    private int SetInfoTotalPage(int totalData, int limit) {
        int totalPage = 0;
        totalPage = totalData/limit;
        totalPage = totalPage*limit <totalData ?totalPage+1 :totalPage;
        return totalPage;
    }

    private int CheckCurentPage(int currentPage , int totalPage){
        if (currentPage < 1 ){
            return 1;
        }
        if (currentPage > totalPage) {
            return totalPage+1;
        }
            return currentPage;

    }

}
