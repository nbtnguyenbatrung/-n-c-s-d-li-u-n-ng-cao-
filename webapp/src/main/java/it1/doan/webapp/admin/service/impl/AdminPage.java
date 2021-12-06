package it1.doan.webapp.admin.service.impl;

import it1.doan.webapp.model.Pagination;
import org.springframework.stereotype.Service;

@Service
public interface AdminPage {
    Pagination  GetInfoPage(int totalData, int limit, int currentPage);
}
