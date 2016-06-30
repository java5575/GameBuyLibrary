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
public enum PaymentType {

    ATM("ATM轉帳", new ShippingType[]{ShippingType.STORE, ShippingType.HOME}),
    STORE("便利商店付款", new ShippingType[]{ShippingType.STORE, ShippingType.HOME}),
    HOME("貨到付款", 150, ShippingType.HOME),
    CREDITCARD("信用卡付款", new ShippingType[]{ShippingType.STORE, ShippingType.HOME});

    private final String desciption;
    private final int fee;
    private final ShippingType[] shippingArray;

    private PaymentType(String desciption, ShippingType... shippingArray) {
        this(desciption, 0, shippingArray);
    }

    private PaymentType(String desciption, int fee, ShippingType... shippingArray) {
        this.desciption = desciption;
        this.fee = fee;
        this.shippingArray = shippingArray;
    }

    public ShippingType[] getShippingArray() {
        return shippingArray.clone();
    }

    public String getDesciption() {
        return desciption;
    }

    public int getFee() {
        return fee;
    }

    @Override
    public String toString() {
        return desciption + (fee > 0 ? (", NT$" + fee + "元") : "");
    }

}
