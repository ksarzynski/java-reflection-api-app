package app.gui;

import app.classloading.MyClassLoader;
import processor.Processor;

import javax.swing.*;
import java.awt.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

public class Gui {

    public Gui(){

        MyClassLoader loaderChildish = new MyClassLoader();
        MyClassLoader loaderEmotes = new MyClassLoader();
        MyClassLoader loaderSize = new MyClassLoader();

        final File[] file = new File[3];

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,400);

        JPanel mainPanel = new JPanel(new GridLayout(9, 1));
        frame.add(mainPanel);

        JPanel loadPanel = new JPanel(new GridLayout(1, 2));
        JButton loadButton = new JButton("load class");

        loadButton.addActionListener(e -> {

            file[0] = new File("C:\\Users\\kacpe\\IdeaProjects\\escienceGit\\lab04\\application\\processors\\childish\\childishProcessor.class");
            file[1] = new File("C:\\Users\\kacpe\\IdeaProjects\\escienceGit\\lab04\\application\\processors\\emotes\\emotesProcessor.class");
            file[2] = new File("C:\\Users\\kacpe\\IdeaProjects\\escienceGit\\lab04\\application\\processors\\size\\MyProcessor.class");

        });

        loadPanel.add(loadButton);
        JButton unloadButton = new JButton("unload");


        loadPanel.add(unloadButton);
        mainPanel.add(loadPanel);

        JPanel topPanel = new JPanel(new GridLayout(1, 1));
        JLabel infoChildish = new JLabel("info (childish)");
        JLabel infoEmotes = new JLabel("info (emotes)");
        JLabel infoSize = new JLabel("info (size)");
        JTextArea text = new JTextArea("Nie miałem pomysłów na więcej błędów.", 5, 10);
        topPanel.add(text);

        JLabel processedTextChildish = new JLabel("processed text (childish)");

        JLabel processedTextEmotes = new JLabel("processed text (emotes)");

        JLabel processedTextSize = new JLabel("processed text (size)");

        StatusPanel statusPanelChildish = new StatusPanel();

        StatusPanel statusPanelEmotes = new StatusPanel();

        StatusPanel statusPanelSize = new StatusPanel();

        JPanel middlePanel = new JPanel(new GridLayout(1, 1));
        JButton processButton = new JButton("process");
        processButton.addActionListener(e -> {
            try {
                //
                Constructor constructorChildish = loaderChildish.loadClassData("childish.ChildishProcessor", file[0].getParentFile() + "\\").getDeclaredConstructor();
                Processor processorChildish = (Processor) constructorChildish.newInstance();
                infoChildish.setText(processorChildish.getInfo());
                statusPanelChildish.setProcessor(processorChildish);
                String t1 = text.getText();
                processorChildish.submitTask(t1, statusPanelChildish);
                TimeUnit.SECONDS.sleep(1);
                processedTextChildish.setText(processorChildish.getResult());
                //

                //
                Constructor constructorEmotes = loaderEmotes.loadClassData("emotes.EmotesProcessor", file[1].getParentFile() + "\\").getDeclaredConstructor();
                Processor processorEmotes = (Processor) constructorEmotes.newInstance();
                infoEmotes.setText(processorEmotes.getInfo());
                statusPanelEmotes.setProcessor(processorEmotes);
                processorEmotes.submitTask(t1, statusPanelEmotes);
                TimeUnit.SECONDS.sleep(1);
                processedTextEmotes.setText(processorEmotes.getResult());
                //

                //
                Constructor constructorSize = loaderSize.loadClassData("size.SizeProcessor", file[2].getParentFile() + "\\").getDeclaredConstructor();
                Processor processorSize = (Processor) constructorSize.newInstance();
                infoSize.setText(processorSize.getInfo());
                statusPanelSize.setProcessor(processorSize);
                processorSize.submitTask(t1, statusPanelSize);
                TimeUnit.SECONDS.sleep(1);
                processedTextSize.setText(processorSize.getResult());
                //

            } catch (NoSuchMethodException | IOException | IllegalAccessException | InstantiationException | InvocationTargetException | InterruptedException noSuchMethodException) {
                noSuchMethodException.printStackTrace();
            }
        });

        unloadButton.addActionListener(e -> {
            statusPanelChildish.setLabel("dziala");
        });

        middlePanel.add(processButton);
        mainPanel.add(topPanel);
        mainPanel.add(middlePanel);
        mainPanel.add(statusPanelChildish);
        mainPanel.add(infoChildish);
        mainPanel.add(statusPanelEmotes);
        mainPanel.add(infoEmotes);
        mainPanel.add(statusPanelSize);
        mainPanel.add(infoSize);

        frame.setVisible(true);
    }
}
