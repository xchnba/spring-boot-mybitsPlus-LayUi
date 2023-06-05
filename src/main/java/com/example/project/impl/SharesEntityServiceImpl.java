package com.example.project.impl;

import com.example.project.dao.SharesEntityMapper;
import com.example.project.entity.SharesEntity;
import com.example.project.service.SharesEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class SharesEntityServiceImpl implements SharesEntityService {
    @Autowired
    SharesEntityMapper sharesMapper;


    @Override
    public List<SharesEntity> getSharesBydate() {
        SharesEntity sharesEntity = new SharesEntity();
        sharesEntity.setGpdate(new Date());
        sharesEntity.setName("cesi1");
        sharesMapper.insert(sharesEntity);
        return sharesMapper.getSharesByDateSearch();
    }
}

