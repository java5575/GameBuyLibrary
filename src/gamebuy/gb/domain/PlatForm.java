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
    DOWNLOAD("數位遊戲"), PSFOUR("PlayStation4"), XBOX("Xbox"), PSTHREE("PlayStation3"), WII("Wii U"), PC("個人電腦");

    private String chName;
//    private int id;

    public String getChName(){
        return chName;
    }
    
    private PlatForm(String chName){
        this.chName = chName;
    }

    public static PlatForm convertFromChname(String chName){
        for(PlatForm form:values()){
            if(form.chName.equals(chName)){
                return form;
            }
        }
        return null;
    }
}
