package com.costr.tailklicker.Logik;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.costr.tailklicker.GUI.Kachel;
import static com.costr.tailklicker.Logik.InvertedClick.invert;

public class MyActionListener implements ActionListener{


    private Kachel kachel;
    public static Kachel[][] kachelGroup;

    public MyActionListener(Kachel[][] kachelGroup, Kachel kachel) {
        this.kachelGroup = kachelGroup;
        this.kachel = kachel;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        invert(kachelGroup, kachel);
        
        
    }




}