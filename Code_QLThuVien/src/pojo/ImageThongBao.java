/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import javax.swing.ImageIcon;

/**
 *
 * @author 84396
 */
public class ImageThongBao {
    
    private ImageIcon imgCompleteGreen,imgCompleteYellow,imgError,imgQuestion,imgLock,imgWarning;

    public ImageIcon getImgCompleteGreen() {
        return imgCompleteGreen;
    }

    public void setImgCompleteGreen(ImageIcon imgCompleteGreen) {
        this.imgCompleteGreen = imgCompleteGreen;
    }

    public ImageIcon getImgCompleteYellow() {
        return imgCompleteYellow;
    }

    public void setImgCompleteYellow(ImageIcon imgCompleteYellow) {
        this.imgCompleteYellow = imgCompleteYellow;
    }

    public ImageIcon getImgError() {
        return imgError;
    }

    public void setImgError(ImageIcon imgError) {
        this.imgError = imgError;
    }

    public ImageIcon getImgQuestion() {
        return imgQuestion;
    }

    public void setImgQuestion(ImageIcon imgQuestion) {
        this.imgQuestion = imgQuestion;
    }

    public ImageIcon getImgLock() {
        return imgLock;
    }

    public void setImgLock(ImageIcon imgLock) {
        this.imgLock = imgLock;
    }

    public ImageIcon getImgWarning() {
        return imgWarning;
    }

    public void setImgWarning(ImageIcon imgWarning) {
        this.imgWarning = imgWarning;
    }

    public ImageThongBao() {
        this.imgCompleteGreen = new ImageIcon("src/Icon/complete.png");
        this.imgCompleteYellow = new ImageIcon("src/Icon/complete2.png");
        this.imgError = new ImageIcon("src/Icon/error.png");
        this.imgQuestion = new ImageIcon("src/Icon/question.png");
        this.imgLock = new ImageIcon("src/Icon/lock.png");
        this.imgWarning = new ImageIcon("src/Icon/warning.png");
    }
   
    
    
}
