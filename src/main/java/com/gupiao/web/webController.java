package com.gupiao.web;

import com.gupiao.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xn066751 on 2017/9/18.
 */
@Controller
@RequestMapping("/web")
public class webController {

    @Autowired
    private AssetService assetService;

    @RequestMapping("/getAssetId")
    public void getAssetId(){
        System.out.print(assetService.getAssetId());
    }
}
