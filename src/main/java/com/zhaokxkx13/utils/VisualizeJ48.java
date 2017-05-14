package com.zhaokxkx13.utils;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.experiment.InstanceQuery;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Displays a trained J48 as tree.
 * Expects an ARFF filename as first argument.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class VisualizeJ48 {

    public static String drawBalanceTree() throws Exception {
//        System.setProperty("java.awt.headless", "true");

        InstanceQuery query = WEKADbutils.getQuery();
        Instances instances = WEKADbutils.getData("select * from j48_sheet");
        Instances labled = new Instances(instances);
        J48 cls = new J48();
        instances.setClassIndex(1);
        cls.buildClassifier(instances);
        return cls.graph();
    }

    public static String getCashFlowTree() throws Exception {
        InstanceQuery query = WEKADbutils.getQuery();
        Instances instances = WEKADbutils.getData("select * from j48_sheet");
        Instances labled = new Instances(instances);
        J48 cls = new J48();
        instances.setClassIndex(1);
        cls.buildClassifier(instances);
        return cls.graph();
    }

    public static void main(String args[]) throws Exception {
        InstanceQuery query = WEKADbutils.getQuery();
        Instances instances = WEKADbutils.getData("select * from j48_sheet");
        Instances labled = new Instances(instances);
        J48 cls = new J48();
        instances.setClassIndex(1);
        cls.buildClassifier(instances);
        final javax.swing.JFrame jf =
                new javax.swing.JFrame("Weka Classifier Tree Visualizer: J48");
        jf.setSize(1000, 800);
        jf.getContentPane().setLayout(new BorderLayout());
        TreeVisualizer tv = new TreeVisualizer(null,
                cls.graph(),
                new PlaceNode2());
        jf.getContentPane().add(tv, BorderLayout.CENTER);
        jf.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                jf.dispose();
            }
        });

        jf.setVisible(true);
        tv.fitToScreen();
        BufferedImage bi = new BufferedImage(jf.getWidth(), jf.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        jf.print(g2d);
        ImageIO.write(bi, "PNG", new File("src/main/resources/static/img/tree.png"));
        jf.dispose();
    }
}

