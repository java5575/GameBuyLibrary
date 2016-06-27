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
public enum PlatForm {
    DOWNLOAD("數位遊戲"), PSFOUR("PS4"), XBOX("Xbox"), PSTHREE("PS3"), WII("Wii U"), PC("電腦遊戲");

    private String chName;
//    private int id;

    public String getChName(){
        return chName;
    }
    
    private PlatForm(String chName){
        this.chName = chName;
    }

//    @Override
//    public String toString() {
//        return super.toString();
//    }
}
