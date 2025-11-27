package com.company.datareport.util.sm4;




/**
* @Title: SM4_Context.java
* @Package com.zefu.core.sm4
* @author wangrsh
* @date 2018年8月1日
* @version V1.0
 */
public class SM4_Context
{
	public int mode;
	
	public long[] sk;
	
	public boolean isPadding;

	public SM4_Context() 
	{
		this.mode = 1;
		this.isPadding = true;
		this.sk = new long[32];
	}
}
