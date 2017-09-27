package com.gupiao.service.impl;

import com.gupiao.dao.AssetDao;
import com.gupiao.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xn066751 on 2017/9/26.
 */
@Service
public class AssetServiceImpl implements AssetService {
    @Autowired
    private AssetDao assetDao;
    @Override
    public int getAssetId(){
        int t= assetDao.getByAssetId();
        return t;
    }
}
