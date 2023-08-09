package com.example.project.controller;

import com.example.project.dao.TickerEntityMapper;
import com.example.project.dto.*;
import com.example.project.entity.TickerEntity;
import com.example.project.utils.TargetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TickerController {
    @Autowired
    TickerEntityMapper entityMapper;
    //止赢次数
    public static int zyc = 0;
    //止损次数
    public static int zsc = 0;
    //开多的次数
    public static int cs = 0;
    //开空的次数
    public static int kc = 0;
    public static int kk = 0;
    public static int kd = 0;
    //开车总次数
    public static int total = 0;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    //账户余额初始为1000块钱
    public static Double account = 1000d;
    //初始单子为
    public static Order order = new Order();

    @RequestMapping("/queryTicker")
    @ResponseBody
    public String queryshares() {
        //初始化订单的持仓量和持仓方向
        order.setAmount(0d);
        order.setDirection("wu");
        order.setPrice(0d);
        List<TickerEntity> tickers = entityMapper.selectByDate("2023-04-15 09:54:56", "2023-05-31 23:54:56");

        //获取K线,第一条是最新的倒叙排序，limit最大300
//                List<TickerEntity> tickers = TargetUtils.getTickers(marketDataAPIService,"ETH-USDT-SWAP","1H","300");

//        double lastPrice = entity.getSpj();
//                System.out.println("当前的收盘价"+lastPrice);
//                //获取均线
//                Double avg5 = TargetUtils.getAverage(tickers,5);
//                Double avg60 = TargetUtils.getAverage(tickers,60);
//                Double avg4 = TargetUtils.getAverage(tickers,4);
//                Double avg24 = TargetUtils.getAverage(tickers,24);
//                System.out.println("5小时均线="+avg5);
//                //获取macd
        List<MacdTarget> macdList = TargetUtils.getMacdTargets(tickers);
        MacdTarget macd = macdList.get(macdList.size() - 1);
        System.out.println("macd值====" + macd.getMacd());
        System.out.println("macd状态====" + macd.getStatus());
        //获取KDJ的值
        List<KdjTarget> kdjTarget = TargetUtils.geKdj(tickers);
        int n = kdjTarget.size() - 1;
        System.out.println("K==" + kdjTarget.get(n).getK() + "  D==" + kdjTarget.get(n).getD() + "  J==" + kdjTarget.get(n).getJ());
        //获取bll值
        List<BollTarget> bollTargets = TargetUtils.geBollTarget(tickers);
        int p = bollTargets.size() - 1;
        System.out.println("Up Band: " + bollTargets.get(p).getUp());
        System.out.println("mid Band: " + bollTargets.get(p).getMid());
        System.out.println("down Band: " + bollTargets.get(p).getDown());
        List<Seed> seedList = new ArrayList<>(bollTargets.size());
        for (int i = 1; i < bollTargets.size() + 1; i++) {
            Seed seed = new Seed();
            seed.setTicker(tickers.get(tickers.size() - i));
            seed.setTime(tickers.get(tickers.size() - i).getDate());
            seed.setKdj(kdjTarget.get(kdjTarget.size() - i));
            seed.setMacd(macdList.get(macdList.size() - i));
            seed.setBoll(bollTargets.get(bollTargets.size() - i));
            seedList.add(seed);
        }
        List<Seed> list = seedList.stream().sorted(Comparator.comparing(Seed::getTime)).collect(Collectors.toList());
        Seed seed = list.get(list.size() - 1);
        System.out.println("当前时间="+seed.getTime());
        System.out.println("当前的KDJ="+seed.getKdj().toString());
        System.out.println("当前的macd="+seed.getMacd().toString());
        System.out.println("当前的boll="+seed.getBoll().toString());
        System.out.println("上一个时间="+list.get(list.size() - 2).getTime());

        for (int i = 360; i <list.size() ; i++) {
            //获取当前的对象
            Seed daSeed = list.get(i);
            TickerEntity entity = daSeed.getTicker();
            String time = entity.getDate();
            //当前的MACD值
            MacdTarget macdTarget = daSeed.getMacd();
            //上一个Macd
            MacdTarget macdPre = list.get(i-1).getMacd();
            //获取当前的KDJ
            KdjTarget kdjNow = daSeed.getKdj();
            //上一个kdj的值
            KdjTarget kdjPre = list.get(i-1).getKdj();
            //获取当前的收盘价
            double lastPrice = daSeed.getTicker().getSpj();
            //如果在上中之间说明在上轨，如果在中下之间说明在下轨，还有两种一种是超越上轨，一种是下穿下轨
            //根据标准差来判断是口子收缩还是扩大
            //当前布林线
            BollTarget bollNow = list.get(i).getBoll();
            BollTarget bollPre = list.get(i-1).getBoll();
            BollTarget bollPrs = list.get(i-2).getBoll();
            //如果有单子判断这段时间内的波动有没有打到止盈或者止损点
            if(order.getAmount() != 0){
                //最高价
                Double zgj = entity.getZgj();
                //最低价
                Double zdj = entity.getZdj();
                //如果订单方向为多
                if(order.getDirection().equals("duo")){
                    //计算止盈止损的点位
                    Double price = order.getPrice();
                    //止盈止损点
                    Double zyd = 1.011;
                    Double zsd = 0.989;
                    //杠杆倍数
                    Double bs = 75.0;
                    //手续来回费率
                    Double fl = 0.001;
                    //止盈百分比
                    Double zyl = zyd - 1 - fl;
                    //止损百分比
                    Double zsl = 1 - zsd + fl;
                    //止盈价格
                    Double zyb = new BigDecimal(price).multiply(new BigDecimal(zyd)).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
                    //止损价格
                    Double zsb = new BigDecimal(price).multiply(new BigDecimal(zsd)).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
                    //止盈点数量
                    Double zyCount = new BigDecimal(order.getAmount()).multiply(new BigDecimal(bs)).multiply(new BigDecimal(zyl)).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
                   //止损点数量
                    Double zsCount = new BigDecimal(order.getAmount()).multiply(new BigDecimal(bs)).multiply(new BigDecimal(zsl)).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
                    //先判断是否止损，再判断止盈
                    //如果止损的价格比最低价格大，说明打到了止损
                    if(zdj<zsb){
                       account = account - zsCount;
                       //状态初始化
                       order.setAmount(0d);
                       order.setDirection("wu");
                       order.setPrice(0d);
                       zsc = zsc + 1;
                       System.out.println("我的多单止损了"+zsb+"当前账户="+account);
                    }else {
                        //如果最高价格>止盈价格，说明打到了止盈
                        if(zgj>zyb){
                            account = account + zyCount;
                            //状态初始化
                            order.setAmount(0d);
                            order.setDirection("wu");
                            order.setPrice(0d);
                            zyc = zyc + 1;
                            System.out.println("我的多单止赢了"+zsb+"当前账户="+account);
                        }
                    }
                }
                //如果订单方向为空
                if(order.getDirection().equals("kong")){
                    //计算止盈止损的点位
                    Double price = order.getPrice();
                    //止盈止损点
                    Double zyd = 0.994;
                    Double zsd = 1.011;
                    //杠杆倍数
                    Double bs = 75.0;
                    //手续来回费率
                    Double fl = 0.001;
                    //止盈百分比
                    Double zyl = 1.0 - zyd -fl;
                    //止损百分比
                    Double zsl = zsd - 1 + fl;

                    //止盈价格
                    Double zyb = new BigDecimal(price).multiply(new BigDecimal(zyd)).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
                    //止损价格
                    Double zsb = new BigDecimal(price).multiply(new BigDecimal(zsd)).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
                    //止盈点数量
                    Double zyCount = new BigDecimal(order.getAmount()).multiply(new BigDecimal(bs)).multiply(new BigDecimal(zyl)).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
                    //止损点数量
                    Double zsCount = new BigDecimal(order.getAmount()).multiply(new BigDecimal(bs)).multiply(new BigDecimal(zsl)).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
                    //先判断是否止损，再判断止
                    //如果最高价大于止损价格，说明打到了止损
                    if(zgj>zsb){
                        account = account - zsCount;
                        //状态初始化
                        order.setAmount(0d);
                        order.setDirection("wu");
                        order.setPrice(0d);
                        zsc = zsc + 1;
                        System.out.println("我的空单止损了"+zsb+"当前账户="+account);
                    }else {
                        //如果最最低价<止盈价格，说明打到了止盈
                        if(zdj<zyb){
                            account = account + zyCount;
                            //状态初始化
                            order.setAmount(0d);
                            order.setDirection("wu");
                            order.setPrice(0d);
                            zyc = zyc + 1;
                            System.out.println("我的空单止赢了"+zsb+"当前账户="+account);
                        }
                    }
                }
            }
            //标准差在递增说明口子在不断变大
            if (bollNow.getStand() > bollPre.getStand()) {
                double cha = bollNow.getStand() - bollPre.getStand();
                double shang = cha / bollPrs.getStand();
                //如果当最近两个标准差的差值比上个标准差大百分之40说明有大行情来了
//                if (shang > 0.4) {
//                    //当前的价格在上轨，追多
//                    double mid = bollNow.getMid();
//                    if (lastPrice > mid) {
//                        //为了防止重复下单
//                        if (kd == 0) {
//                            //当前并没有持仓
//                            if(order.getAmount() == 0){
//                                //下单---->开盘价 ——等于收盘价
//                                //拿出账户的六分之一进行开单
//                                Double duoD  = new BigDecimal(account).divide(new BigDecimal(6), 2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
//                                order.setAmount(duoD);
//                                order.setDirection("duo");
//                                order.setPrice(entity.getSpj());
//                                //计算止盈止损的值
//                                //一旦触发会多少赚多少亏多少
////                                Order order = getTrad(tradeAPIService, accountAPIService, marketDataAPIService,"buy");
////                                // 测试文本邮件发送（无附件）
//                                String content = "我在=" + time + "的59分59秒=开多了" + "开多价格是=" + entity.getSpj();
//                                System.out.println("大行情开始了" + content);
//                                total = total + 1;
//                                cs = cs+1;
//                                kd = 1;
//                            }
//
//                        }
//
//                    }
//                    //当前的价格在下轨，追空
//                    if (lastPrice < mid) {
//                        //为了防止重复下单
//                        if (kk == 0) {
//                            //当前并没有持仓
//                            if(order.getAmount() == 0){
//                                //下单---->开盘价 ——等于收盘价
//                                //拿出账户的六分之一进行开单
////                                Double kongD  = new BigDecimal(account).setScale(6, BigDecimal.ROUND_HALF_DOWN).doubleValue();
//                                Double kongD  = new BigDecimal(account).divide(new BigDecimal(6), 2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
//                                order.setAmount(kongD);
//                                order.setDirection("kong");
//                                order.setPrice(entity.getSpj());
////                                // 测试文本邮件发送（无附件）
//                                String content = "我在=" + time + "的59分59秒=开空了" + "开空价格是=" + entity.getSpj();
//                                System.out.println("大行情开始了" + content);
////                                Order order = getTrad(tradeAPIService, accountAPIService, marketDataAPIService,"sell");
//                                //开仓设置止盈止损成功
//                                total = total + 1;
//                                kc = kc+1;
//                                kk = 1;
//                            }
//                        }
//                    }
//                }
                //通过KDJ下单
                getOrderByKdj(time, entity, kdjNow, kdjPre,bollNow,macdTarget,macdPre);


            }
            //口子在缩小
            if (bollNow.getStand() < bollPre.getStand()) {
                kd = 0;
                kk = 0;
                //通过KDJ下单
                getOrderByKdj(time, entity,  kdjNow, kdjPre, bollNow,macdTarget,macdPre);
            }

        }

        System.out.println("在跑总价" +account+ "等空数=" + kc + "等多数=" + cs + "总次数=" + total);
        System.out.println("止盈次数" +zyc+ "止损次数=" + zsc );

        return "查询成功";
    }

    private void getOrderByKdj(String time, TickerEntity entity, KdjTarget target,KdjTarget targetPre, BollTarget bollNow,MacdTarget macdTarget,MacdTarget macdPre) {
        //如果kdj的K>D金叉，K<D死叉，金叉D要小于26，死叉D要大于65，一定要距离成叉的一格之内
        //当前kdj target
        //上一个小时的kdj targetPre
       /* if(target.getK()>target.getD()&&target.getD()<26){
            //上一个小时死叉，当前小时金叉
            if(targetPre.getD()>targetPre.getK()){
                //批量撤销止盈单子
                //当前并没有持仓
                if(order.getAmount() == 0) {
                    //下单开多---->开盘价 ——等于收盘价
                    //拿出账户的六分之一进行开单
//                    Double duoD = new BigDecimal(account).setScale(6, BigDecimal.ROUND_HALF_DOWN).doubleValue();
                    Double duoD  = new BigDecimal(account).divide(new BigDecimal(6), 2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
                    order.setAmount(duoD);
                    order.setDirection("duo");
                    order.setPrice(entity.getSpj());
                    //设置止盈止损的值
                    String content = "我a在=" + time + "的59分59秒=开多了" + "开多价格是=" + entity.getSpj();
                    System.out.println("开始了" + content);
                    total = total + 1;
                }
            }
        }*/
        //当前小时死叉，上个小时金叉
        if(target.getK()<target.getD()&&target.getD()>65){
            if(targetPre.getD()<targetPre.getK()){
                //批量撤销止盈单子
                //当前并没有持仓
                if(order.getAmount() == 0){
                    //Macd强势空的时候不开空
                    if(macdTarget.getStatus() != 1){
                        if(macdPre.getStatus() !=1 ){
                            //下单---->开盘价 ——等于收盘价
                            //拿出账户的六分之一进行开单
                            Double kongD  = new BigDecimal(account).divide(new BigDecimal(6), 2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
                            order.setAmount(kongD);
                            order.setDirection("kong");
                            order.setPrice(entity.getSpj());
                            String content = "我在b=" + time + "的59分59秒=开空了" + "开空价格是=" + entity.getSpj();
                            System.out.println("开始了" + content);
                            String ah = macdTarget.getDiff()>macdTarget.getDea() ? "金叉":"死叉";
                            String wei = "有0轴下方";
                            if(macdTarget.getDiff()>0&&macdTarget.getDea()>0){
                                wei = "都在0轴上方";
                            }
                            Double ca  = 0.0;
                            if(macdTarget.getDiff()>macdTarget.getDea()){
                                ca =  macdTarget.getDiff()-macdTarget.getDea();
                            }else {
                                ca = macdTarget.getDea() - macdTarget.getDiff();
                            }
                            System.out.println("上一个Macd的状态="+macdPre.getStatus());
                            System.out.println("Macd的间距="+ca);
                            System.out.println("Macd的情况="+wei);
                            System.out.println("Macd的情况="+ah);
                            System.out.println("Macd的情况="+macdTarget.getStatus());
                            String guidao = entity.getSpj()>bollNow.getMid() ? "上轨":"下轨";
                            System.out.println("Boll的情况="+guidao);
                            //开仓设置止盈止损成功
                            total = total + 1;
                        }

                    }
                }
            }
        }
    }

}

