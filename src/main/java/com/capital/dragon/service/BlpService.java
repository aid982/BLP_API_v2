package com.capital.dragon.service;

import java.util.List;

import com.capital.dragon.model.Security;

/**
 * Created by bluser on 11/23/2016.
 */
public interface BlpService {
    List<Security> getSecurityListHistoricalInfo(String data1,String data2,List<String> securityList) throws Exception;
    List<Security> getSecurityListInfo(List<String> securityList) throws Exception;
}
