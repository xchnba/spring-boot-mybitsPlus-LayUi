package com.example.project.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.project.dao.UserUnknowMapper;
import com.example.project.dao.VocabularyMapper;
import com.example.project.entity.UserUnknow;
import com.example.project.entity.Vocabulary;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

@Controller
public class EnglishController {
    @Autowired
    VocabularyMapper mapper;
    @Autowired
    UserUnknowMapper unknowMapper;

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
    public PageInfo getDanci(String _page,String from) {

        Integer test = null;
        Integer uses = null;
        Integer star = null;
        Integer sort = null;
        String dan = null;
        if(StringUtils.isNotEmpty(from)){
            String st = from.substring(0,1);
            String end = from.substring(1);
            if("1".equals(st)){
                test = Integer.valueOf(end);
            }
            if("2".equals(st)){
                uses = Integer.valueOf(end);
            }
            if("3".equals(st)){
                star = Integer.valueOf(end);
            }
            if("4".equals(st)){
                sort = Integer.valueOf(end);
            }
        }
        PageHelper.startPage(Integer.valueOf(_page), 1);
        List<Vocabulary> list = mapper.getByDanciByParam(test,uses,star,sort,dan);
        PageInfo pageInfo = new PageInfo(list);
        System.out.println("页码：" + pageInfo.getPageNum());
        System.out.println("页大小：" + pageInfo.getPageSize());
        System.out.println("总记录：" + pageInfo.getTotal());
        for (Vocabulary ls:list){
            System.out.println("单词：" + ls.getDanci());
            System.out.println("含义：" + ls.getHanyi());
        }
        return pageInfo;
    }

    /**
     * 获取用户的openId
     * @param code
     * @return
     */
    @RequestMapping("/getOpenId")
    @ResponseBody
    public String getOpenId(String code) {
        //微信小程序官方接口
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        //接口所需参数
        HashMap<String, Object> requestUrlParam = new HashMap<>();
        //小程序appId
        requestUrlParam.put("appid", "wxfc4777fa8cb2fa1b");
        //小程序secret
        requestUrlParam.put("secret", "17fbaa4a4296aa6baa11171c900176d3");
        //小程序端返回的code
        requestUrlParam.put("js_code", code);
        //默认参数，固定写死即可
        requestUrlParam.put("grant_type", "authorization_code");
        //发送post请求读取调用微信接口获取openid用户唯一标识
        String result = HttpUtil.get(requestUrl, requestUrlParam);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        String openid = jsonObject.get("openid", String.class);
        return openid;
    }

    /**
     * 保存用户不认识的单词
     * @param danci
     * @return
     */
    @RequestMapping("/saveUnknow")
    @ResponseBody
    public Map<String ,Object> saveUnknow(String danci,String openId,String hanyi) {
        Map<String ,Object> map = new HashMap<>();
        try {
            //每个用户最多只能保存1000个不认识的单词
            Integer count = unknowMapper.getUserUnknowCount(openId);
            if(count>999){
                map.put("suc",false);
                map.put("message","每个用户最多只能记录1000个不认识的单词，请先使用模糊题集");
                return map;
            }
            //保存单词
            UserUnknow unknow = new UserUnknow();
            unknow.setDanci(danci);
            unknow.setOpenid(openId);
            unknow.setHanyi(hanyi);
            unknow.setChangdu(danci.length());
            unknow.setDate(new Date());
            unknowMapper.insert(unknow);
            map.put("suc",true);
            map.put("message","保存成功");
        } catch (Exception e) {
            map.put("suc",false);
            map.put("message","保存成功");
            e.printStackTrace();
        }
        return map;
    }


    @RequestMapping("/getUnknowDanci")
    @ResponseBody
    public PageInfo getUnknowDanci(String _page,String _limit) {
        Integer test = 1;
        String dan = "";
        String chang = "hala";
        PageHelper.startPage(Integer.valueOf(_page), 1);
        List<UserUnknow> list = unknowMapper.getUnKnowDanciByParam(dan,chang);
        PageInfo pageInfo = new PageInfo(list);
        System.out.println("页码：" + pageInfo.getPageNum());
        System.out.println("页大小：" + pageInfo.getPageSize());
        System.out.println("总记录：" + pageInfo.getTotal());
        for (UserUnknow ls:list){
            System.out.println("单词：" + ls.getDanci());
            System.out.println("含义：" + ls.getHanyi());
        }
        return pageInfo;
    }



    /**
     * 保存用户不认识的单词
     * @param danci
     * @return
     */
    @RequestMapping("/deleteUnknow")
    @ResponseBody
    public Map<String ,Object> deleteUnknow(String danci,String openId) {
        Map<String ,Object> map = new HashMap<>();
        try {
            //删除单词
//            UserUnknow unknow = new UserUnknow();
            QueryWrapper<UserUnknow> queryWrapper = new QueryWrapper<>();
           // 添加查询条件
            queryWrapper.eq("danci", danci); // 等于条件
            queryWrapper.eq("openid", openId); // 等于条件
            unknowMapper.delete(queryWrapper);
            map.put("suc",true);
            map.put("message","删除成功");
        } catch (Exception e) {
            map.put("suc",false);
            map.put("","每个用户最多只能记录1000个不认识的单词，请先解决模糊");
            e.printStackTrace();
        }
        return map;
    }




}
