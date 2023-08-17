//package com.example.demo.jvm;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
////配置堆内存大小
//public class Test001 {
//
//	public static void main(String[] args) {
//		String a = "abc";
//		String b = "abc";
//		String c = new String("abc");
//		System.out.println(a==b);
//		System.out.println(b==c);
//		String url = "https://www.quword.com/ciku/id_0_0_0_0_0.html"; // 需要爬取的目标网站地址
//		try {
//			Document document = Jsoup.connect(url).get(); // 获取该网页的文档对象
//			String title = document.title(); // 获取页面标题
//			System.out.println("Title: " + title);
//			Elements links = document.select("a[href]"); // 获取该网页中所有的链接元素
//			Elements trs = document.getElementsByTag("p");
//			List<String> dc = new ArrayList<>(20);
//			List<String> hy = new ArrayList<>(20);
//			for (int i = 0; i <links.size(); i++) {
//				String linkText = links.get(i).text();
//				if(i>47&&i<68){
//					dc.add(linkText);
////					System.out.println("Link text: " + linkText);
//				}
//
//			}
//			for (int i = 0; i < trs.size(); i++) {
//				String hyText = trs.get(i).text(); // 获取链接文字
//				if (i<20){
//					hy.add(hyText);
////					System.out.println("意义 text: " + hyText);
//				}
//			}
//
//			for (int i = 0; i < dc.size(); i++) {
//				System.out.println("单词 text: " + dc.get(i));
//				System.out.println("长度 : " + dc.get(i).length());
//				System.out.println("意义 text: " + hy.get(i));
//			}
////			for (Element link : links) {
////				String linkHref = link.attr("href"); // 获取链接地址
////				String linkText = link.text(); // 获取链接文字
////				if("树".equals(linkText)){
////
////				}
//////				System.out.println("Link href: " + linkHref);
////				System.out.println("Link text: " + linkText);
////			}
////			for (Element link : trs) {
////				String linkText = link.text(); // 获取链接文字
////				System.out.println("意义 text: " + linkText);
////			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//
//
////        for(int i= 126;i<130;i++){
////        	Integer d = i;
////        	Integer e = i;
////			System.out.println(i + " " +(d==e));
////
////		}
//
////		float a = 1.22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222f;
////		double c =1.22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222;
////		System.out.println(a);
////		System.out.println(c);
////		byte[] b = new byte[25 * 1024 * 1024];
////		System.out.println("分配了25M空间给数组");
////        System.out.println("最大内存" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M");
////		System.out.println("可用内存" + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M");
////		System.out.println("已经使用内存" + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M");
//	}
//}
