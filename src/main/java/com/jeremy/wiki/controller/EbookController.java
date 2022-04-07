package com.jeremy.wiki.controller;

import com.jeremy.wiki.req.EbookReq;
import com.jeremy.wiki.resp.CommonResp;
import com.jeremy.wiki.resp.EbookResp;
import com.jeremy.wiki.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController // 接口
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(EbookReq req){
        CommonResp<List<EbookResp>> resp = new CommonResp<>();
        List<EbookResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }
//    public List<Ebook> list(){
//        return ebookService.list();
//    }

}
