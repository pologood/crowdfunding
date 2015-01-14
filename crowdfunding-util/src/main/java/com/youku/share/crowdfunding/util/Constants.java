package com.youku.share.crowdfunding.util;

public class Constants {
	
	public static enum Product{
		AD("", 1),
		INTERACT("", 2);
		
		private int flagBitIndex;
		private String productName;
		
		Product(String product_name, int flag_bit_index){
			productName = product_name;
			flagBitIndex = flag_bit_index;
		}

		public int getFlagBitIndex() {
			return flagBitIndex;
		}

		public String getProductName() {
			return productName;
		}	
		
	}
	
	public static enum Operation{
		USER_ADD,
		USER_DELETE;
	}
	
	
}
