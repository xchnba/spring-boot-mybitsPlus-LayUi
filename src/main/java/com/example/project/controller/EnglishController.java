package com.example.project.controller;

import com.example.project.dao.VocabularyMapper;
import com.example.project.entity.Vocabulary;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EnglishController {
    @Autowired
    VocabularyMapper mapper;

    @RequestMapping("/charuDanChi")
    @ResponseBody
    public String charuDanChi() {
        String url = "https://www.quword.com/ciku/id_0_0_0_0_";
        
//        String url = "https://www.quword.com/ciku/id_0_0_0_0_0.html"; // 需要爬取的目标网站地址
        try {
            for (int x = 2235; x < 2236; x++) {
                String page = String.valueOf(x);
                url = url+page+".html";
                Document document = Jsoup.connect(url).get(); // 获取该网页的文档对象
                String title = document.title(); // 获取页面标题
                System.out.println("Title: " + title);
                Elements links = document.select("a[href]"); // 获取该网页中所有的链接元素
                Elements trs = document.getElementsByTag("p");
                List<String> dc = new ArrayList<>(20);
                List<String> hy = new ArrayList<>(20);
                for (int i = 0; i <links.size(); i++) {
                    String linkText = links.get(i).text();
                    if(i>47&&i<68){
                        dc.add(linkText);
                    }
                }
                for (int i = 0; i < trs.size(); i++) {
                    String hyText = trs.get(i).text(); // 获取链接文字
                    if (i<20){
                        hy.add(hyText);
                    }
                }
                for (int i = 0; i < dc.size(); i++) {
                    System.out.println("单词 text: " + dc.get(i));
                    System.out.println("长度 : " + dc.get(i).length());
                    System.out.println("意义 text: " + hy.get(i));
                    Vocabulary vocabulary = new Vocabulary();
                    vocabulary.setDanci(dc.get(i));
                    vocabulary.setChangdu(dc.get(i).length());
                    vocabulary.setHanyi(hy.get(i));
                    mapper.insert(vocabulary);
                }
                url = "https://www.quword.com/ciku/id_0_0_0_0_";
            }
            System.out.println("全部成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "成功";
    }


    @RequestMapping("/chuLiDanChi2")
    @ResponseBody
    public String chuLiDanChi2() {
        String url = "https://www.quword.com/ciku/id_1_0_0_0_";

//        String url = "https://www.quword.com/ciku/id_0_0_0_0_0.html"; // 需要爬取的目标网站地址
        try {
            for (int x = 0; x < 292; x++) {
                String page = String.valueOf(x);
                url = url+page+".html";
                Document document = Jsoup.connect(url).get(); // 获取该网页的文档对象
                String title = document.title(); // 获取页面标题
                System.out.println("Title: " + title);
                Elements links = document.select("a[href]"); // 获取该网页中所有的链接元素
                Elements trs = document.getElementsByTag("p");
                List<String> dc = new ArrayList<>(20);

                for (int i = 0; i <links.size(); i++) {
                    String linkText = links.get(i).text();
                    if(i>47&&i<68){
                        if("首页".equals(linkText)){
                            break;
                        }
                        dc.add(linkText);
                    }
                }

                for (int i = 0; i < dc.size(); i++) {
                    System.out.println("单词 text: " + dc.get(i));
                    Vocabulary vocabulary = mapper.getByDanci(dc.get(i));
                    if(vocabulary !=null){
                        vocabulary.setTest(1);
                        mapper.updateById(vocabulary);
                    }
                }
                url = "https://www.quword.com/ciku/id_1_0_0_0_";
            }
            System.out.println("全部成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "成功";
    }

    @RequestMapping("/getDanci")
    @ResponseBody
    public String getDanci() {
        Integer test = null;
        Integer uses = null;
        Integer star = null;
        Integer sort = null;
        String dan = null;
        PageHelper.startPage(1, 100);
        List<Vocabulary> list = mapper.getByDanciByParam(test,uses,star,sort,dan);
        PageInfo pageInfo = new PageInfo(list);
        System.out.println("页码：" + pageInfo.getPageNum());
        System.out.println("页大小：" + pageInfo.getPageSize());
        System.out.println("总记录：" + pageInfo.getTotal());
        for (Vocabulary ls:list){
            System.out.println("单词：" + ls.getDanci());
            System.out.println("含义：" + ls.getHanyi());
        }
        return list.get(0).getDanci();
    }

}
