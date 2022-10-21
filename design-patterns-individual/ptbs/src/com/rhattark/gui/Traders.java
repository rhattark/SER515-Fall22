package com.rhattark.gui;

import com.rhattark.businessLogic.Facade;
import com.rhattark.businessLogic.ListIterator;
import com.rhattark.businessLogic.OfferingList;
import com.rhattark.businessLogic.Product;
import com.rhattark.util.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Traders extends JPanel {
    Facade facade = Facade.getInstance();
    OfferingList offeringList;

    public Traders() throws IOException {
        setLayout(null);
        setBounds(new GUIOuterRectangle());
        setBackground(Color.decode("#e0e0e0"));
        facade.createOfferingList();
        offeringList = facade.getOfferingList();
        showHeading();
        showTraders();
    }

    private void showHeading() {
        int userType = facade.getUserType();
        String product = facade.getTheSelectedProduct().getProduct();
        String label = userType == 0 ? "Choose sellers selling product: " +
                product : "Choose buyers wanting product: " + product;
        JLabel heading = new JLabel(label);
        heading.setBounds(50, 10, 300, 30);
        add(heading);
    }

    private void showTraders() {
        ListIterator listIterator = offeringList.getIterator();
        int y = 50;
        int userType = facade.getUserType();
        String product = facade.getTheSelectedProduct().getProduct();
        FileManager fileManager = FileManager.getInstance();

        while (listIterator.hasNext()) {
            JRadioButton trader = new JRadioButton((String)(listIterator.next()));
            trader.setBounds(50, y, 300, 30);
            String traderName = facade.getThePerson().getName();

            ActionListener actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (userType == 0) {
                        JOptionPane.showMessageDialog(facade.getGui(), "Buying: " + product
                                + " by: " + traderName);
                    } else {
                        JOptionPane.showMessageDialog(facade.getGui(), "Selling: " + product
                                + " by: " + traderName);
                    }

                    try {
                        String toAppend = traderName+":"+product;
                        fileManager.appendStringToFile(toAppend, "UserProduct.txt");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            };
            trader.addActionListener(actionListener);
            add(trader);
            y += 40;
        }
    }
}
