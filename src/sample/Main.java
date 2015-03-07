package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import overskaug.evolution.Evolution;
import overskaug.evolution.genotypes.BitVectorGenotype;
import overskaug.evolution.phenotypes.IntegerPhenotype;
import overskaug.evolution.problems.OneMax;
import overskaug.evolution.util.Converter;
import overskaug.evolution.util.FixedBitSet;

import java.util.BitSet;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //launch(args);
        OneMax oneMax = new OneMax(Evolution.BIT_LENGTH);
        Evolution.run(oneMax);
    }
}
