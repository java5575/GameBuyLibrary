/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.domain;

import java.text.NumberFormat;
import java.util.Date;

/**
 *
 * @author Robbie wu
 */
public class Product {
    public static final NumberFormat priceFormat;
    static{
        priceFormat = NumberFormat.getInstance();
        priceFormat.setMinimumFractionDigits(0);
        priceFormat.setMaximumFractionDigits(2);
    }  

    private int id;//PKey AutoIcrement
    private String name;
    private double unitPrice;
    private int stock;
    private String url;
    private String description;
    private int bonus;
    private int status;//0:未販售 1:預售中 2:販售中 
    private double preferentialPrice;
    private PlatForm platForm;
    private GameType gameType;
    private java.util.Date issueDate;

    public Product() {
    }

    public Product(int id, String name, double unitPrice) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) throws GameBuyException {
        if (id >= 0) {
            this.id = id;
        } else {
            throw new GameBuyException("產品編號不得為負數");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws GameBuyException {
        if (name != null) {
            this.name = name;
        } else {
            throw new GameBuyException("遊戲名稱不能為空白");
        }
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) throws GameBuyException {
        if (unitPrice >= 0) {
            this.unitPrice = unitPrice;
        } else {
            throw new GameBuyException("產品價格不能為負數");
        }
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) throws GameBuyException {
        if (url != null) {
            this.url = url;
        } else {
            throw new GameBuyException("來源位置不能為空白");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws GameBuyException {
        if (description != null) {
            this.description = description;
        } else {
            throw new GameBuyException("產品說明不能為空白");
        }
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus)  {
       
            this.bonus = bonus;
        
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getPreferentialPrice() {
        return preferentialPrice;
    }

    public void setPreferentialPrice(double preferentialPrice) throws GameBuyException {
        if (preferentialPrice >= 0 && preferentialPrice <= this.getUnitPrice()) {
            this.preferentialPrice = preferentialPrice;
        } else {
            throw new GameBuyException("特價價格不可大於原價");
        }
    }

    public PlatForm getPlatForm() {
        return platForm;
    }
    
    public void setPlatForm(PlatForm platForm) throws GameBuyException {
        
        if (platForm != null) {
            this.platForm = platForm;
        } else {
            throw new GameBuyException("遊戲平台不能為空白");
        }
    }

    public GameType getGameType() {
        return gameType;
    }
    
    public void setGameType(GameType gameType) throws GameBuyException{
        if (gameType != null) {
            this.gameType = gameType;
        } else{
            throw new GameBuyException("遊戲類型不能為空白");
        }
    }
    
    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(java.util.Date issueDate) throws GameBuyException {
        if (issueDate != null) {
            this.issueDate = issueDate;
        } else {
            throw new GameBuyException("發行日期不能為空白");
        }
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "編號: " + id
                + ", 名稱: " + name + ", 原價: " + unitPrice
                + ", 庫存: " + stock + ", 資料路徑: " + url
                + ", 紅利: " + bonus + ", 商品狀態: " + status
                + ", 優惠價: " + preferentialPrice +  "發行日期: " 
                + issueDate + "遊戲平台: " + platForm + "遊戲類型:" + gameType ;
    }

    /*
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }*/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.unitPrice) != Double.doubleToLongBits(other.unitPrice)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.id;
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.unitPrice) ^ (Double.doubleToLongBits(this.unitPrice) >>> 32));
        return hash;
    }

}
