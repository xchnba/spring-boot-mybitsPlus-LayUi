package com.example.project.utils;

import com.example.project.dto.BollTarget;
import com.example.project.dto.KdjTarget;
import com.example.project.dto.MacdTarget;
import com.example.project.entity.TickerEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * 指标工具类，根据K线数据获取相应的指标
 */
public class TargetUtils {


    /**
     * 获取均线
     * @param tickers
     * @param number 多久的均线
     * @return
     */
    public static Double getAverage(List<TickerEntity> tickers , int number){
        //获取5小时均线
        List<TickerEntity> temp = tickers.subList(0, number);
        OptionalDouble average5 = temp.stream().mapToDouble(TickerEntity::getSpj).average();
        double avg51 = average5.getAsDouble();
        double avg5 = new BigDecimal(avg51).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return avg5;
    }

    /**
     * 根据K线图获取macd
     * @param tickers
     * @return
     */
    public static List<MacdTarget> getMacdTargets(List<TickerEntity> tickers) {
        List<TickerEntity> list =  tickers.stream().sorted(Comparator.comparing(TickerEntity::getDate)).collect(Collectors.toList());
        double EMA12 = 0;
        double EMA26 = 0;
        double DIFF = 0;
        double DEA = 0;
        double MACD = 0;
        double OLDMACD = 0;
        List<MacdTarget> macdList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                EMA12 = list.get(i).getSpj();
                EMA26 = list.get(i).getSpj();
            } else {
                EMA12 = EMA12 * 11 / 13 + list.get(i).getSpj() * 2 / 13;
                EMA26 = EMA26 * 25 / 27 + list.get(i).getSpj() * 2 / 27;
            }

            DIFF = EMA12 - EMA26;
            DEA = DEA * 8 / 10 + DIFF * 2 / 10;
            MacdTarget macdTarget = new MacdTarget();
            macdTarget.setOpen(list.get(i).getKpj());
            macdTarget.setClose(list.get(i).getSpj());
            macdTarget.setHigh(list.get(i).getZgj());
            macdTarget.setLow(list.get(i).getZdj());
            macdTarget.setCloseTime(list.get(i).getDate());
            macdTarget.setId(i);
            macdTarget.setDiff(DIFF);
            macdTarget.setDea(DEA);
            MACD = 2 * (DIFF - DEA);
            if (MACD > 0) {
                if (OLDMACD < MACD) {
                    //macd 大于0 且由小变大——空心阳柱
                    macdTarget.setStatus(1);
                } else {
                    //macd 大于0 且由大变小——实心阳柱
                    macdTarget.setStatus(2);
                }

            } else {
                if (OLDMACD < MACD) {
                    //macd 小于0 且由小变大——空心阴柱
                    macdTarget.setStatus(3);
                } else {
                    //macd 小于0 且由大变小——实心阴柱
                    macdTarget.setStatus(4);
                }
            }
            OLDMACD = MACD;
            macdTarget.setMacd(MACD);
            macdList.add(macdTarget);
        }
        //    MacdTarget macd = macdList.get(macdList.size()-1);  当前macd
//                //当前状态
//                int cunt = macd.getStatus();
//                //上个状态
//                int pre = macdList.get(macdList.size()-2).getStatus();
//                //上上个状态
//                int sh = macdList.get(macdList.size()-3).getStatus();
        return macdList;
    }


    public static List<KdjTarget> geKdj(List<TickerEntity> tickers){
        List<Double> closePriceList = new ArrayList<>();
        List<Double> highList = new ArrayList<>();
        List<Double> lowList = new ArrayList<>();
        List<TickerEntity> list =  tickers.stream().sorted(Comparator.comparing(TickerEntity::getDate)).collect(Collectors.toList());
        list.stream().forEach(e->{
            double cl =  e.getSpj().doubleValue();
            closePriceList.add(cl);
        });
        list.stream().forEach(e->{
            double cl =  e.getZgj().doubleValue();
            highList.add(cl);
        });
        list.stream().forEach(e->{
            double cl =  e.getZdj().doubleValue();
            lowList.add(cl);
        });
        //最高
        double high = 0;
        //最低
        double low = 0;
        //收盘价
        double cl = 0;
        //rsv
        double rsv = 0;
        //k
        double k = 0;
        //d
        double d = 0;
        //j
        double j = 0;
        //k
//        double oldK = 50.0;
        //d
//        double oldD = 50.0;

        List<KdjTarget> kdjTarget = new ArrayList<>();
        KdjTarget kdjTarget1 = new KdjTarget();
        kdjTarget1.setK(50.0);
        kdjTarget1.setD(50.0);
        kdjTarget1.setJ(50.0);
        kdjTarget.add(kdjTarget1);
        // 10
        int length = highList.size()-8;
        for(int st=0;st<length;st++){
            int ed = st+9;
            double oldK = kdjTarget.get(st).getK();
            double oldD = kdjTarget.get(st).getD();
            high = getHigh(st,ed,highList);
            low = getLow(st,ed,lowList);
            cl = closePriceList.get(ed-1);
            //RSV=（Cn－Ln）÷（Hn－Ln）×100
            rsv = (cl-low)/(high-low)*100;
            //当日K值=2/3×前一日K值＋1/3×当日RSV
            k = 2*oldK/3 + rsv/3;
            // 当日D值=2/3×前一日D值＋1/3×当日K值
            d = 2*oldD/3 + k/3;
            j = 3*k -2*d;
            KdjTarget kdjTarget2 = new KdjTarget();
            kdjTarget2.setId(st);
            kdjTarget2.setK(k);
            kdjTarget2.setD(d);
            kdjTarget2.setJ(j);
            kdjTarget.add(kdjTarget2);
        }
        return kdjTarget;
    }
    //获取Boll指标
    public static List<BollTarget> geBollTarget(List<TickerEntity> tickers){
        List<BollTarget> bollTargetList = new ArrayList<>();
        List<Double> closePriceList = new ArrayList<>();
        List<TickerEntity> list =  tickers.stream().sorted(Comparator.comparing(TickerEntity::getDate)).collect(Collectors.toList());
        list.stream().forEach(e->{
            double cl =  e.getSpj().doubleValue();
            closePriceList.add(cl);
        });
        // 计算中轨线（20日简单移动平均线）
        List<Double> middleBand = calculateSMA(closePriceList, 20);
        // 计算标准差
        List<Double> standardDeviation = calculateStandardDeviation(closePriceList, 20);
        // 计算上轨线和下轨线
        for (int i = 0; i < middleBand.size(); i++) {
            double upper = middleBand.get(i) + 2 * standardDeviation.get(i);
            double lower = middleBand.get(i) - 2 * standardDeviation.get(i);
            BollTarget bollTarget = new BollTarget();
            bollTarget.setMid(middleBand.get(i));
            bollTarget.setUp(upper);
            bollTarget.setDown(lower);
            bollTarget.setStand(standardDeviation.get(i));
            bollTargetList.add(bollTarget);
        }
        return bollTargetList;
    }

    public static  List<Double> getRsi(List<TickerEntity> tickers,int period){
        List<Double> rsiValues = new ArrayList<>();
        List<Double> prices = new ArrayList<>();
        List<TickerEntity> list =  tickers.stream().sorted(Comparator.comparing(TickerEntity::getDate)).collect(Collectors.toList());
        list.stream().forEach(e->{
            double cl =  e.getSpj().doubleValue();
            prices.add(cl);
        });
        List<Double> gains = new ArrayList<>();
        List<Double> losses = new ArrayList<>();

        for (int i = 1; i < prices.size(); i++) {
            double priceDiff = prices.get(i) - prices.get(i - 1);
            if (priceDiff >= 0) {
                gains.add(priceDiff);
                losses.add(0.0);
            } else {
                gains.add(0.0);
                losses.add(Math.abs(priceDiff));
            }
        }

        double avgGain = calculateAverage(gains, period);
        double avgLoss = calculateAverage(losses, period);

        for (int i = period; i < prices.size(); i++) {
            double rs = avgGain / avgLoss;
            double rsi = 100 - (100 / (1 + rs));
            rsiValues.add(rsi);
            double currentGain = gains.get(i - 1);
            double currentLoss = losses.get(i - 1);
            avgGain = ((avgGain * (period - 1)) + currentGain) / period;
            avgLoss = ((avgLoss * (period - 1)) + currentLoss) / period;
        }
        //最新的一次RSI还要再计算一下
        double rs = avgGain / avgLoss;
        double rsi = 100 - (100 / (1 + rs));
        rsiValues.add(rsi);
        return rsiValues;
    }
    // 计算平均值
    private static double calculateAverage(List<Double> values, int period) {
        double sum = 0;
        for (int i = 0; i < period; i++) {
            sum += values.get(i);
        }
        return sum / period;
    }

    // 计算简单移动平均线
    private static List<Double> calculateSMA(List<Double> prices, int period) {
        List<Double> sma = new ArrayList<>();
        for (int i = period - 1; i < prices.size(); i++) {
            double sum = 0;
            for (int j = i - period + 1; j <= i; j++) {
                sum += prices.get(j);
            }
            double average = sum / period;
            sma.add(average);
        }
        return sma;
    }

    // 计算标准差
    private static List<Double> calculateStandardDeviation(List<Double> prices, int period) {
        List<Double> standardDeviation = new ArrayList<>();
        for (int i = period - 1; i < prices.size(); i++) {
            double sum = 0;
            for (int j = i - period + 1; j <= i; j++) {
                sum += Math.pow(prices.get(j) - calculateSMA(prices, period).get(i - period + 1), 2);
            }
            double variance = sum / period;
            double sd = Math.sqrt(variance);
            standardDeviation.add(sd);
        }
        return standardDeviation;
    }
    private static double getHigh(int st, int ed, List<Double> highList) {
        double s = 0;
        List<Double> newList1 = highList.subList(st, ed);
        for(int i=0;i<newList1.size();i++){
            if(s<newList1.get(i)){
                s = newList1.get(i);
            }
        }
        return s;
    }

    private static double getLow(int st, int ed, List<Double> lowList) {
        double x = 999999999;
        List<Double> newList2 = lowList.subList(st, ed);
        for(int i=0;i<newList2.size();i++){
            if(x>newList2.get(i)){
                x = newList2.get(i);
            }
        }
        return x;
    }


}
