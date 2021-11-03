package it1.doan.webapp.admin.service;

import org.springframework.stereotype.Component;

@Component
public class function {
    public String Laystt(String ma){
        String mastring = ma.substring(2);
        int stt = Integer.parseInt(mastring);
        stt = stt + 1 ;
        if (stt < 10)
            return "000" + stt;

        else if (stt >= 10 && stt < 100)
            return "00" + stt;

        else if (stt >= 100 && stt < 1000)
            return "0" + stt;

        else if (stt >= 1000 && stt < 10000)
            return "" + stt;

        else
            return "";
    }


}
