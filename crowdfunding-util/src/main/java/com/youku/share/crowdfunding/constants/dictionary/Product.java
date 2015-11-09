package com.youku.share.crowdfunding.constants.dictionary;

public enum Product{
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