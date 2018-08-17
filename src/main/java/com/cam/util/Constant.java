package com.cam.util;

/**
 * 
 * 作者：ChenJianrong
 *
 * 创建时间：2018年8月5日 下午12:45:07
 *
 * 功能描述： 常量描述类
 * 
 * 版本： V1.0 
 */
public class Constant {
	public static final int DONATE_STATE_UNCREDIT=0; //申请待审核状态
	public static final int DONATE_STATE_CREDITED=1; //申请审核通过
	public static final int DONATE_STATE_REJECTED=2; //申请拒绝
	
	public static final int DONATE_TYPE_CLOTHES_SCORE=10;  //定义一件衣物获取积分数
	public static final int DONATE_TYPE_BOOK_SCORE=8;	   //定义一本书获取积分数
	public static final int DONATE_TYPE_OTHER_SCORE=5;     //定义一件其他物品获取积分数

	public static final String DONATE_TYPE_CLOTHES="衣物类";
	public static final String DONATE_TYPE_BOOK="书籍类";
	public static final String DONATE_TYPE_OTHER="其他";
	
}
