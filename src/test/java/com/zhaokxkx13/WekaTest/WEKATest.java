package com.zhaokxkx13.WekaTest;

import com.zhaokxkx13.StatApplication;
import com.zhaokxkx13.utils.WEKADbutils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.experiment.InstanceQuery;

/**
 * Created by zhaokxkx13 on 2017/5/3.
 */
@SpringBootTest(classes = StatApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class WEKATest {

    @Test
    public void testGetInstance() {
        try {
            InstanceQuery query = WEKADbutils.getQuery();
            System.out.print(query);
            Instances instances = WEKADbutils.getData("select * from j48_sheet");
            Instances labled = new Instances(instances);
            J48 m_classifier = new J48();
            instances.setClassIndex(1);
            labled.setClassIndex(1);
            m_classifier.buildClassifier(instances);
            for (int i = 0; i < instances.numInstances(); i++) {
                double pred = m_classifier.classifyInstance(instances.instance(i));
                System.out.print("ID: " + instances.instance(i).value(0));
                System.out.print(", actual: " + instances.classAttribute().value((int) instances.instance(i).classValue()));
                System.out.println(", predicted: " + instances.classAttribute().value((int) pred));
                labled.instance(i).setClassValue(pred);
            }
            System.out.print(m_classifier.distributionForInstance(labled.instance(1)));
            System.out.print(m_classifier.graph());
            System.out.println(m_classifier.collapseTreeTipText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
