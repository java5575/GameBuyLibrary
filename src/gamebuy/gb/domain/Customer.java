/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 *
 * @author Administrator
 */
public class Customer {

    //EMAIL正規表達式
    private static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    //密碼正規表達式
    private static final String PASSWORD_PATTERN = "^[a-zA-Z]\\w{3,14}$";
    //生日的格式化
    public static final DateFormat birthdayFormat = new SimpleDateFormat("yyyy/M/d");
    public static final DateFormat birthdayWebFormat = new SimpleDateFormat("yyyy-MM-dd");
    //身分證的正規表達式
    public static final String ID_PATTERN = "[A-Z][12]\\d{8}";

    private String id;
    private String email;
    private String password = "";
    private String address;
    private String phone;
    private String name;

    private Date birthday;
    private int status;
    private int bonus;

    public String getId() {
        return id;
    }

    public void setId(String id) throws GameBuyException {
        //ID長度等於10而且符合身分的正規表達式
        if (id.length() == 10 && id.matches(ID_PATTERN)) {
            //將第1個字元轉成對應的數字            
            char firstChar = id.charAt(0);
            int firstNum = 0;
            //如果第一個英文從A到H
            if (firstChar >= 'A' && firstChar <= 'H') {
                //取出來的數字等於英文-A+10
                firstNum = firstChar - 'A' + 10;
                //如果第一個英文從J到N
            } else if (firstChar >= 'J' && firstChar <= 'N') {
                //取出來的數字等於英文-J+10
                firstNum = firstChar - 'J' + 18;
                //如果第一個英文從P到V
            } else if (firstChar >= 'P' && firstChar <= 'V') {
                //取出來的數字等於英文-P+10
                firstNum = firstChar - 'P' + 23;
            } else {
                //各自設定I,O,W,X,Y,Z的數字
                switch (firstChar) {
                    case 'I':
                        firstNum = 34;
                        break;
                    case 'O':
                        firstNum = 35;
                        break;
                    case 'W':
                        firstNum = 32;
                        break;
                    case 'X':
                        firstNum = 30;
                        break;
                    case 'Y':
                        firstNum = 31;
                        break;
                    case 'Z':
                        firstNum = 33;
                        break;
                }
            }
            //2. 將步驟1轉出的數字及身分證號第2~10個數字字元依規則加總
            //第一個數字加第二的數字
            int sum = firstNum / 10 + firstNum % 10 * 9;
            //第三個數字到第九個數字相加
            for (int i = 1; i < 10; i++) {
                sum += (id.charAt(i) - '0') * (9 - i);
            }
            //前面相加的總合再加上第十個數字
            sum += id.charAt(9) - '0';

            //3. 將步驟2的總和除於10，若餘數為0則正確            
            if (sum % 10 == 0) {
                this.id = id;

            } else {
                throw new GameBuyException("請輸入正確的身分證字號");
            }
        }

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws GameBuyException {
        //如果email不為空值而且符合去掉前後空白後的正規表達式
        if (email != null && (email = email.trim()).matches(EMAIL_PATTERN)) {
            //輸出email
            this.email = email;
        } else {
            //System.out.println("email為必要欄位");
            throw new GameBuyException("請輸入正確的Email");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws GameBuyException {
        //如果密碼不為空值而且符合去掉前後空白後的正規表達式
        if (password != null && (password = password.trim()).matches(PASSWORD_PATTERN)) {
            this.password = password;
        } else {
            //System.out.println("密碼不正確");
            throw new GameBuyException("密碼不正確");
        }
    }

    public java.util.Date getBirthday() {
        return birthday;
    }
    public String getBirthdayWebString() {
        if(this.birthday!=null){
            return birthdayWebFormat.format(this.birthday);
        }else{
            return "";
        }
    }
    public void setBirthday(Date birthday) throws GameBuyException {
        if (birthday == null) {
            //如果生日是空值，直接輸入空值
            this.birthday = birthday;
        } else //after()現在時間在生日之後
         if (new Date().after(birthday)) {
                //如果現在日期大於生日，輸出生日
                this.birthday = birthday;
            } else {
                //如果生日大於現在日期，輸出錯誤訊息
                //System.out.println("出生日期必須小於今天");
                throw new GameBuyException("出生日期必須小於今天");
            }
    }

    public void setBirthday(String s) throws GameBuyException {
        if (s != null) {
            s = s.replace('-', '/');
            try {
                //輸入的生日符合日期的格式
                Date date = birthdayFormat.parse(s);
                this.setBirthday(date);
            } catch (ParseException ex) {
                //Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, "日期格式錯誤", ex);
                throw new GameBuyException("日期格式錯誤", ex);
            }
        } else {
            this.birthday = null;
        }
    }
//    public void setBirthday(int y, int m, int d) throws GameBuyException {
//           Date date = new GregorianCalendar(y,m-1,d).getTime();
//           this.setBirthday(date);
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws GameBuyException {
        //如果地址不是空值而且去掉空白後的長度大於等於8
        if (address != null && (address = address.trim()).length() >= 8) {
            //輸出地址
            this.address = address;
        } else {
            // System.out.println("請輸入正確的地址");
            throw new GameBuyException("請輸入正確的地址");
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws GameBuyException {
        //如果輸入的電話不為空值而且去掉空白後的長度大於等於8
        if (phone != null && (phone = phone.trim()).length() >= 6) {
            //輸出電話
            this.phone = phone;
        } else {
            //System.out.println("請輸入正確的電話號碼");
            throw new GameBuyException("請輸入正確的電話號碼");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws GameBuyException {
        //如果輸入的姓名不為空值而且去掉空白後的長度大於0
        if (name != null && (name = name.trim()).length() > 0) {
            //輸出姓名
            this.name = name;
        } else {
            //System.out.println("請入正確的姓名");
            throw new GameBuyException("請輸入正確的姓名");
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getAge() {
        int returnValue = 0;
        //1.取得今年
        Calendar calendar = Calendar.getInstance();//取得萬年曆
        int thisYear = calendar.get(Calendar.YEAR);//查詢萬年曆的中的今年
        //2.取得客戶的出生年
        if (getBirthday() != null) {
            //設定客戶的生日到calendar
            calendar.setTime(getBirthday());
            //查詢萬年曆中的客戶出生年
            int birthYear = calendar.get(Calendar.YEAR);
            //3.取得客戶年紀
            returnValue = thisYear - birthYear;//今年-客戶出生的那一年
        }
        return returnValue;
    }

    //hashCode跟equals只要是在取主鍵值
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    //toString主要是讓開發者看的資訊
    @Override
    public String toString() {
        return "客戶資料為：" + "id=" + id + ", email=" + email + ", password=" + password.length()
                + ", address=" + address + ", phone=" + phone + ", name=" + name
                + ", birthday=" + birthday + ", status=" + status + ", bonus=" + bonus;
    }

    


}
