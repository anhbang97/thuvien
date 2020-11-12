/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuvien;

import javafx.concurrent.Task;

/**
 *
 * @author Ánh
 */
class doWork extends Task<Void>{

    private int tu;
    private int mau;

    public doWork(int tu, int mau) {
        this.tu = tu;
        this.mau = mau;
    }

    @Override
    protected Void call() throws Exception {
        for (int i=0; i<tu; i++){
            if (isCancelled()){
                updateMessage("Cancelled");
                break;
            }
            updateProgress(i+1,mau);
            updateMessage(String.valueOf(i+1) + "%");
            Thread.sleep(100);
        }
        
        return null;
    }
}
