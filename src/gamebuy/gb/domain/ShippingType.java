/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.domain;

/**
 *
 * @author Administrator
 */
public enum ShippingType {
    STORE("便利商店取貨", 60),
    HOME("送貨到府", 70);
    
    private final String desciption;
    private final int fee;
    

    public String getDesciption() {
        return desciption;
    }

    public int getFee() {
        return fee;
    }
    
    public static ShippingType convertFromDesciption(String desciption){
        for(ShippingType stype:values()){
            if(stype.desciption.equals(desciption)){ return stype;}
                
        }
        return null;
    }

    private ShippingType(String desciption) {
        this(desciption, 0);
    }

    private ShippingType(String desciption, int fee) {
        this.desciption = desciption;
        this.fee = fee;
    }

    @Override
    public String toString() {
        return desciption + ", NT$" + fee ;
    }
    
    
    
    
}

