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
public enum Status {
    NEW("新訂單"),PAID("已轉帳"),CONFIRMED("已確認"),
    SHIPPED("已出貨"),ARRIVED("已到貨"),CHECKED("已簽收"),
    COMPLETED("已完成"),CANCELED("已取消");
    
    private final String description;
    
    private Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
