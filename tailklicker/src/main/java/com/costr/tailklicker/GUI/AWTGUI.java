package com.costr.tailklicker.GUI;

import java.awt.Frame;
import java.awt.Graphics;


public class AWTGUI extends Frame{
    

    public AWTGUI() {
        super("Tailklicker");
        setSize(800, 600);
        setVisible(true);
        setResizable(false);
        setLayout(null);
    }

    @Override
    public void paint(Graphics g) {
        
    }
}