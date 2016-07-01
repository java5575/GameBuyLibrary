package gamebuy.gb.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Order {

    private int id;
    private Customer customer;
    private Date createdTime;
    private int bonus;
    
    private PaymentType paymentType;
    private double paymentFee;
    private String paymentNote;
    
    private ShippingType shippingType;
    private double shippingFee;
    private String shippingNote;
    private String shippingAddress;
    
    private String receiverName;
    private String receiverEmail;    
    private String receiverPhone;
    
    private double totalAmount;
    private int status;//{0:新訂單..}

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public double getPaymentFee() {
        return paymentFee;
    }

    public void setPaymentFee(double paymentFee) {
        this.paymentFee = paymentFee;
    }

    public String getPaymentNote() {
        return paymentNote;
    }

    public void setPaymentNote(String paymentNote) {
        this.paymentNote = paymentNote;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public ShippingType getShippingType() {
        return shippingType;
    }

    public void setShippingType(ShippingType shippingType) {
        this.shippingType = shippingType;
    }

    public String getShippingNote() {
        return shippingNote;
    }

    public void setShippingNote(String shippingNote) {
        this.shippingNote = shippingNote;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public double getTotalAmount() {
        if(orderItemLst == null || orderItemLst.isEmpty()){
        return totalAmount;
        }else{
            double sum = 0;
            for(OrderItem item:orderItemLst){
                sum += item.getPrice() * item.getQuantity();
            }
            sum = sum - bonus;
            return sum;
        }
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItem> getOrderItemLst() {
//        return new ArrayList(orderItemLst);//拿到新覆本
        return Collections.unmodifiableList(orderItemLst);
    }
    private List<OrderItem> orderItemLst;

    public void add(OrderItem item) {
        if (orderItemLst == null) {
            orderItemLst = new ArrayList<>();
        }
        orderItemLst.add(item);
    }

    public void add(ShoppingCart cart) throws GameBuyException {
        if (customer == null) {
            throw new GameBuyException("請先指派客戶資料");
        }

        for (Product p : cart.keySet()) {
            OrderItem item = new OrderItem();
            item.setProduct(p);
            item.setQuantity(cart.getQuantity(p));
            if (bonus !=0 ) {
                item.setPrice(p.getUnitPrice()-p.getBonus());
            }else{
                item.setPrice(p.getUnitPrice());
            }
            this.add(item);
        }
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id 
                + ",\n customer=" + customer 
                + ",\n createdTime=" + createdTime + ", bonus=" + bonus 
                + ",\n paymentType=" + paymentType + ", paymentFee=" + paymentFee + ", paymentNote=" + paymentNote 
                + ",\n shippingType=" + shippingType + ", shippingFee=" + shippingFee + ", shippingNote=" + shippingNote + ", shippingAddress=" + shippingAddress
                + ",\n receiverName=" + receiverName + ", receiverEmail="+ receiverEmail  + ", receiverPhone=" + receiverPhone 
                + ", status=" + status + ",TotalAmount= " + this.getTotalAmount() + '}';
    }
    
    
    

}
