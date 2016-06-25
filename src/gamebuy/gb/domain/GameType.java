/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.domain;

/**
 *
 * @author Robbie wu
 */
public enum GameType {
    SOFTWARE("遊戲軟體"), TRADITIONAL_CHINESE_SOFTWARE("中文化遊戲軟體"), 
    HARDWARE("硬體周邊"), RESTRICTED("限制級專區");
    
    private String chName;
    
    public String getName(){
        return chName;
    }
    private GameType(String chName){
        this.chName = chName;
    }
    
    public String getChName(){
        return chName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
}
