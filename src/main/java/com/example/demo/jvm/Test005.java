package com.example.demo.jvm;

//栈溢出
public class Test005 {

	private static int count;

	// 最大深度:10636 244972 10805
	public static void count() {
		try {
			count++;
			// count();
		} catch (Throwable e) {
			System.out.println("最大深度:" + count);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {


		double initialPrice = 100.0;
		double buyAmount = 100.0;
		int maxBuyTimes = 10;
		double discountRate = 0.2;
		double totalPrice = 0.0;
		double totalShares = 0.0;

		double currentPrice = initialPrice;
		double current = 0.0;
		int buyTimes = 0;

		while (buyTimes < maxBuyTimes) {
			double currentBuyAmount = buyAmount / currentPrice;
			totalShares += currentBuyAmount;
			totalPrice += buyAmount;
			current = currentPrice;
			System.out.println("当前买入价=="+current);
			currentPrice = currentPrice * (1 - discountRate);
			System.out.println("当前总份额=="+totalShares);
			System.out.println("当前总投入=="+totalPrice);
			System.out.println("下次买入价格=="+currentPrice);
			double averagePrice1 = totalPrice / totalShares;
			System.out.println("当前均价=="+averagePrice1);
			double zy = averagePrice1*1.2;
			System.out.println("当前止盈价格=="+zy);
			System.out.println("当前需要上涨=="+zy/current);
			System.out.println("----------------------------");
			buyTimes++;
		}

		double averagePrice = totalPrice / totalShares;
		System.out.println("全部买入之后的均价为: " + averagePrice);
		System.out.println("买入的份额为: " + totalShares);


//		for (int i = 0; i < 20805; i++) {
//			count();
//		}
	}

}
