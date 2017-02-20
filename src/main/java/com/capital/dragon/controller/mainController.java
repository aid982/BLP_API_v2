package com.capital.dragon.controller;

import com.capital.dragon.model.Security;
import com.capital.dragon.service.BlpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class mainController {
    private BlpService blpService;
    @Autowired
    public mainController(BlpService blpService) {
        this.blpService = blpService;
    }
    @RequestMapping(value = "/history",method = RequestMethod.POST)
    public List<Security> getSecurityHistoricalInfo(@RequestParam("date1") String data1,@RequestParam("date2") String data2,@RequestBody List<String> sec) throws Exception {
        //sec=sec.stream().map(ex->ex+" Equity").collect(Collectors.toList());
        return blpService.getSecurityListHistoricalInfo(data1,data2,sec);
    }

    @RequestMapping(value = "/reference",method = RequestMethod.POST)
    public List<Security> getSecurityCurrentInfo(@RequestBody List<String> sec) throws Exception {

        return blpService.getSecurityListInfo(sec);
    }
}
