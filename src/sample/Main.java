package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import overskaug.evolution.Evolution;
import overskaug.evolution.genotypes.BitVectorGenotype;
import overskaug.evolution.phenotypes.IntegerPhenotype;
import overskaug.evolution.problems.LOLZPrefix;
import overskaug.evolution.problems.OneMax;
import overskaug.evolution.problems.SurprisingSequences;
import overskaug.evolution.util.Converter;
import overskaug.evolution.util.FixedBitSet;

import java.util.BitSet;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(GUI.initGUI(), 1000, 600);
        primaryStage.setTitle("Survival of the fittest");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        launch(args);
        int bitLength = 40;
        OneMax problem = new OneMax(bitLength);
        //LOLZPrefix problem = new LOLZPrefix(bitLength, 6);
        //SurprisingSequences problem = new SurprisingSequences(10, 35, true);

        //Evolution.run(problem);
    }
}
