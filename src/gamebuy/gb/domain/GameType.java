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
    SOFTWARE("遊戲軟體"), CHSOFTWARE("中文化遊戲軟體"), RESTRICTED("限制級專區");
    
    private String chName;
    //private int id;
    
    public String getChName(){
        return chName;
    }
    
    private GameType(String chName){
        this.chName = chName;
        //this.id = id;
    }
    
//    public int getNumber(){
//        return id;
//    }    
    
//    @Override
//    public String toString() {
//        return super.toString();
//    }
    
}
