package ru.gis.app;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import ru.gis.app.entities.ConceptEntity;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TEST {
    public static void main(String[] args) {
        try {
            /*MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            String str = "qwerty123";
            byte[] output = str.getBytes(StandardCharsets.UTF_8);

            *//*Integer iterations = (int) ((Math.random() * 6) + 5);
            for (int i = 0; i < iterations; i++) {
                output = messageDigest.digest(output);
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (byte elem : output) {
                String correctSym = Integer.toHexString(0xff & elem);
                correctSym = (correctSym.length() == 1) ? "0" + correctSym : correctSym;
                stringBuilder.append(correctSym);
            }
            stringBuilder.append(iterations);*//*

            output = str.getBytes(StandardCharsets.UTF_8);
            messageDigest.update(output);
            output = messageDigest.digest();
            outHash(output);
            messageDigest.reset();
            messageDigest.update(output);
            output = messageDigest.digest();
            outHash(output);*/
            LinearObjectiveFunction f = new LinearObjectiveFunction(new double[]{-2, 1}, -5);
            Collection constraints = new ArrayList();
            constraints.add(new LinearConstraint(new double[]{1, 2}, Relationship.LEQ, 6));
            constraints.add(new LinearConstraint(new double[]{3, 2}, Relationship.LEQ, 12));
            constraints.add(new LinearConstraint(new double[]{0, 1}, Relationship.GEQ, 0));
            SimplexSolver ss = new SimplexSolver();
            PointValuePair solution = new SimplexSolver().optimize(f, new LinearConstraintSet(constraints), GoalType.MINIMIZE, new NonNegativeConstraint(false));
            System.out.println(solution);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void doSomethingWithList(List<ConceptEntity> conceptEntities) {
        conceptEntities.get(0).setTitle("anotherTitle");
    }

    /*private static void outHash(byte[] message) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte elem : message) {
            String correctSym = Integer.toHexString(0xff & elem);
            correctSym = (correctSym.length() == 1) ? "0" + correctSym : correctSym;
            stringBuilder.append(correctSym);
        }
        System.out.println(stringBuilder);
    }*/
}
