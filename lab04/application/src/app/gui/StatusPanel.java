package app.gui;

import processor.Processor;
import processor.Status;
import processor.StatusListener;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel implements StatusListener {

    JLabel label;
    JLabel finalLabel;
    Processor processor;

    public StatusPanel(){
        this.setLayout(new GridLayout(1, 2));
        label = new JLabel("status");
        this.add(label);
        finalLabel = new JLabel("result");
        this.add(finalLabel);
    }

    @Override
    public void statusChanged(Status s) {
        setLabel(String.valueOf(s.getProgress()));
        if(s.getProgress() == 100){
            finalLabel.setText(processor.getResult());
        }
    }

    public void setLabel(String text){
        label.setText(text);
    }

    public void setProcessor(Processor processor){
        this.processor = processor;
    }
}
