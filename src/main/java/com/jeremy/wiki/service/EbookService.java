package com.jeremy.wiki.service;

import com.jeremy.wiki.domain.Ebook;
import com.jeremy.wiki.domain.EbookExample;
import com.jeremy.wiki.mapper.EbookMapper;
import com.jeremy.wiki.req.EbookReq;
import com.jeremy.wiki.resp.EbookResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req){
        EbookExample ebookExample = new EbookExample();
        // 内部类
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike( "%" + req.getName() + "%");
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
        List<EbookResp> respList = new ArrayList<>();
        for (Ebook ebook : ebookList) {
            EbookResp ebookResp = new EbookResp();
//          ebookResp.setId(ebook.getId());
            //实现对象复制
            BeanUtils.copyProperties(ebook,ebookResp);
            respList.add(ebookResp);
        }

        return respList;
    }

}
