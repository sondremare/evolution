package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import overskaug.evolution.Evolution;
import overskaug.evolution.geneticoperators.mutation.Mutation;
import overskaug.evolution.problems.LOLZPrefix;
import overskaug.evolution.problems.OneMax;
import overskaug.evolution.problems.Problem;
import overskaug.evolution.problems.SurprisingSequences;
import overskaug.evolution.selection.AdultSelection;
import overskaug.evolution.selection.ParentSelection;

public class GUI {
    private static Problem problem;
    private static LineChart plot;

    public static GridPane initGUI() {
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(1000, 600);

        plot = initPlotter();
        gridPane.add(plot, 0, 0);

        //ControlDashboard
        GridPane controlDashboard = new GridPane();
        controlDashboard.setPrefSize(1000, 200);
        controlDashboard.setStyle("-fx-background-color: lightgrey");
        controlDashboard.setPadding(new Insets(10));

        //Problemspecific grid
        final GridPane problemGrid = new GridPane();
        problemGrid.setPrefSize(250, 200);
        problemGrid.setVgap(5);
        problemGrid.setHgap(5);

        Label problemLabel = new Label("Problem:");

        //Problem specific grids
        final GridPane oneMaxGrid = new GridPane();
        oneMaxGrid.setVgap(5);
        oneMaxGrid.setHgap(5);
        Label bitLength = new Label("Bit length: ");
        final TextField bitLengthInput = new TextField();
        bitLengthInput.setMaxWidth(50);
        bitLengthInput.setText("40");

        oneMaxGrid.add(bitLength, 0, 0);
        oneMaxGrid.add(bitLengthInput, 1, 0);

        final GridPane lolzPrefixGrid = new GridPane();
        lolzPrefixGrid.setVgap(5);
        lolzPrefixGrid.setHgap(5);
        Label bitLength2 = new Label("Bit length: ");
        final TextField bitLengthInput2 = new TextField();
        bitLengthInput2.setMaxWidth(50);
        bitLengthInput2.setText("40");
        Label thresholdLabel = new Label("Treshold");
        final TextField thresholdInput = new TextField();
        thresholdInput.setMaxWidth(50);
        thresholdInput.setText("6");

        lolzPrefixGrid.add(bitLength2, 0, 0);
        lolzPrefixGrid.add(bitLengthInput2, 1, 0);
        lolzPrefixGrid.add(thresholdLabel, 0, 1);
        lolzPrefixGrid.add(thresholdInput, 1, 1);

        final GridPane surprisingSequenceGrid = new GridPane();
        surprisingSequenceGrid.setVgap(5);
        surprisingSequenceGrid.setHgap(5);

        ToggleGroup globalVSLocal = new ToggleGroup();
        final RadioButton global = new RadioButton("Globally surprising");
        global.setToggleGroup(globalVSLocal);
        global.setSelected(true);
        RadioButton local = new RadioButton("Locally surprising");
        local.setToggleGroup(globalVSLocal);

        Label symbolSetSize = new Label("Symbol set size (S): ");
        final TextField symbolSetSizeInput = new TextField();
        symbolSetSizeInput.setMaxWidth(50);
        symbolSetSizeInput.setText("10");
        Label sequenceLength = new Label("Sequence length (L): ");
        final TextField sequenceLengthInput = new TextField();
        sequenceLengthInput.setMaxWidth(50);
        sequenceLengthInput.setText("26");

        surprisingSequenceGrid.add(global, 0, 0);
        surprisingSequenceGrid.add(local, 0, 1);
        surprisingSequenceGrid.add(symbolSetSize, 0, 2);
        surprisingSequenceGrid.add(symbolSetSizeInput, 1, 2);
        surprisingSequenceGrid.add(sequenceLength, 0, 3);
        surprisingSequenceGrid.add(sequenceLengthInput, 1, 3);

        final ComboBox problemSelector = new ComboBox();
        problemSelector.getItems().addAll("OneMax","LOLZPrefix","SurprisingSequences");
        problemSelector.setValue("OneMax");
        problemSelector.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String previous, String chosen) {
                if (chosen.equals("OneMax")) {
                    problemGrid.getChildren().remove(2);
                    problemGrid.add(oneMaxGrid, 0, 2);
                } else if (chosen.equals("LOLZPrefix")) {
                    problemGrid.getChildren().remove(2);
                    problemGrid.add(lolzPrefixGrid, 0, 2);
                } else if (chosen.equals("SurprisingSequences")) {
                    problemGrid.getChildren().remove(2);
                    problemGrid.add(surprisingSequenceGrid, 0, 2);
                }
            }
        });

        GridPane controlButtonsGrid = new GridPane();
        Button startButton = new Button("Evolve!");

        Button clearButton = new Button("Clear Plot");
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clearPlot();
            }
        });
        controlButtonsGrid.add(startButton, 0, 0);
        //controlButtonsGrid.add(clearButton, 1, 0);

        problemGrid.add(problemLabel, 0, 0);
        problemGrid.add(problemSelector, 0, 1);
        problemGrid.add(oneMaxGrid, 0, 2);

        final GridPane adultSelectionGrid = new GridPane();
        adultSelectionGrid.setPrefSize(250, 200);
        adultSelectionGrid.setVgap(5);
        adultSelectionGrid.setHgap(5);

        Label overProductionPoolSize = new Label("Overproduction pool size: ");
        final TextField overProductionPoolSizeInput = new TextField();
        overProductionPoolSizeInput.setMaxWidth(50);
        overProductionPoolSizeInput.setText("120");

        final GridPane overProductionGrid = new GridPane();
        overProductionGrid.setHgap(5);
        overProductionGrid.setVgap(5);
        overProductionGrid.add(overProductionPoolSize, 0, 0);
        overProductionGrid.add(overProductionPoolSizeInput, 1, 0);

        final GridPane adultSelectionPlaceHolderGrid = new GridPane();

        Label adultSelectionLabel = new Label("Adult Selection");
        ToggleGroup adultSelectionGroup = new ToggleGroup();
        final RadioButton fullGenerationalReplacement = new RadioButton("Full Generational Replacement");
        fullGenerationalReplacement.setToggleGroup(adultSelectionGroup);
        fullGenerationalReplacement.setSelected(true);
        final RadioButton overProduction = new RadioButton("Overproduction");
        overProduction.setToggleGroup(adultSelectionGroup);
        final RadioButton generationalMixing = new RadioButton("Generational Mixing");
        generationalMixing.setToggleGroup(adultSelectionGroup);

        adultSelectionGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldToggle, Toggle newToggle) {
                String radioButtonText = ((RadioButton)newToggle).getText();
                if (radioButtonText.equals("Overproduction")) {
                    adultSelectionGrid.getChildren().remove(4);
                    adultSelectionGrid.add(overProductionGrid, 0, 4);
                } else {
                    adultSelectionGrid.getChildren().remove(4);
                    adultSelectionGrid.add(adultSelectionPlaceHolderGrid, 0, 4);
                }
            }
        });

        adultSelectionGrid.add(adultSelectionLabel, 0, 0);
        adultSelectionGrid.add(fullGenerationalReplacement, 0, 1);
        adultSelectionGrid.add(overProduction, 0, 2);
        adultSelectionGrid.add(generationalMixing, 0, 3);
        adultSelectionGrid.add(adultSelectionPlaceHolderGrid, 0, 4);

        final GridPane parentSelectionGrid = new GridPane();
        parentSelectionGrid.setPrefSize(250, 200);
        parentSelectionGrid.setVgap(5);
        parentSelectionGrid.setHgap(5);

        Label parentSelectionLabel = new Label("Parent Selection");
        final ToggleGroup parentSelectionGroup = new ToggleGroup();
        final RadioButton fitnessProportionate = new RadioButton("Fitness-proportionate");
        fitnessProportionate.setToggleGroup(parentSelectionGroup);
        fitnessProportionate.setSelected(true);
        final RadioButton sigmaScaling = new RadioButton("Sigma Scaling");
        sigmaScaling.setToggleGroup(parentSelectionGroup);
        final RadioButton boltzmannSelection = new RadioButton("Boltzmann Selection");
        boltzmannSelection.setToggleGroup(parentSelectionGroup);
        final RadioButton tournamentSelection = new RadioButton("Tournament Selection");
        tournamentSelection.setToggleGroup(parentSelectionGroup);

        final GridPane boltzmannGrid = new GridPane();
        boltzmannGrid.setVgap(5);
        boltzmannGrid.setHgap(5);

        Label temperature = new Label("Temperature: ");
        final TextField temperatureInput = new TextField();
        temperatureInput.setMaxWidth(50);
        temperatureInput.setText("6");
        Label decrease = new Label("Decrease: ");
        final TextField decreaseInput = new TextField();
        decreaseInput.setMaxWidth(50);
        decreaseInput.setText("0.01");

        boltzmannGrid.add(temperature, 0, 0);
        boltzmannGrid.add(temperatureInput, 1, 0);
        boltzmannGrid.add(decrease, 0, 1);
        boltzmannGrid.add(decreaseInput, 1, 1);

        final GridPane tournamentGrid = new GridPane();
        tournamentGrid.setVgap(5);
        tournamentGrid.setHgap(5);

        Label groupSize = new Label("Group size: ");
        final TextField groupSizeInput = new TextField();
        groupSizeInput.setText("10");
        groupSizeInput.setMaxWidth(50);
        Label probability = new Label("Random probability: ");
        final TextField probabilityInput = new TextField();
        probabilityInput.setText("0.05");
        probabilityInput.setMaxWidth(50);

        tournamentGrid.add(groupSize, 0, 0);
        tournamentGrid.add(groupSizeInput, 1, 0);
        tournamentGrid.add(probability, 0, 1);
        tournamentGrid.add(probabilityInput, 1, 1);

        final GridPane placeHolderGrid = new GridPane();

        parentSelectionGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldToggle, Toggle newToggle) {
                String radioButtonText = ((RadioButton)newToggle).getText();
                if (radioButtonText.equals("Boltzmann Selection")) {
                    parentSelectionGrid.getChildren().remove(5);
                    parentSelectionGrid.add(boltzmannGrid, 0, 5);
                } else if (radioButtonText.equals("Tournament Selection")) {
                    parentSelectionGrid.getChildren().remove(5);
                    parentSelectionGrid.add(tournamentGrid, 0, 5);
                } else {
                    parentSelectionGrid.getChildren().remove(5);
                    parentSelectionGrid.add(placeHolderGrid, 0, 5);
                }
            }
        });

        parentSelectionGrid.add(parentSelectionLabel, 0, 0);
        parentSelectionGrid.add(fitnessProportionate, 0, 1);
        parentSelectionGrid.add(sigmaScaling, 0, 2);
        parentSelectionGrid.add(boltzmannSelection, 0, 3);
        parentSelectionGrid.add(tournamentSelection, 0, 4);
        parentSelectionGrid.add(placeHolderGrid, 0, 5);

        GridPane generalSettingsGrid = new GridPane();
        generalSettingsGrid.setPrefSize(250, 200);
        generalSettingsGrid.setVgap(5);
        generalSettingsGrid.setHgap(5);

        Label populationSize = new Label("Population size: ");
        final TextField populationSizeInput = new TextField();
        populationSizeInput.setMaxWidth(50);
        populationSizeInput.setText("100");
        Label maxGenerations = new Label("Max generations: ");
        final TextField maxGenerationsInput = new TextField();
        maxGenerationsInput.setMaxWidth(50);
        maxGenerationsInput.setText("100");
        Label crossoverRate = new Label("Crossover rate: ");
        final TextField crossoverRateInput = new TextField();
        crossoverRateInput.setMaxWidth(50);
        crossoverRateInput.setText("1");
        Label mutationRate = new Label("Mutation rate: ");
        final TextField mutationRateInput = new TextField();
        mutationRateInput.setMaxWidth(50);
        mutationRateInput.setText("0.05");

        Label mutationType = new Label("Mutation type: ");
        ToggleGroup mutationTypeGroup = new ToggleGroup();
        final RadioButton genome = new RadioButton("Genome");
        genome.setToggleGroup(mutationTypeGroup);
        genome.setSelected(true);
        final RadioButton perGene = new RadioButton("Per gene");
        perGene.setToggleGroup(mutationTypeGroup);

        generalSettingsGrid.add(populationSize, 0, 0);
        generalSettingsGrid.add(populationSizeInput, 1, 0);
        generalSettingsGrid.add(maxGenerations, 0, 1);
        generalSettingsGrid.add(maxGenerationsInput, 1, 1);
        generalSettingsGrid.add(crossoverRate, 0, 2);
        generalSettingsGrid.add(crossoverRateInput, 1, 2);
        generalSettingsGrid.add(mutationRate, 0, 3);
        generalSettingsGrid.add(mutationRateInput, 1, 3);
        generalSettingsGrid.add(mutationType, 0, 4);
        generalSettingsGrid.add(genome, 0, 5);
        generalSettingsGrid.add(perGene, 0, 6);

        controlDashboard.add(problemGrid, 0, 0);
        controlDashboard.add(adultSelectionGrid, 1, 0);
        controlDashboard.add(parentSelectionGrid, 2, 0);
        controlDashboard.add(generalSettingsGrid, 3, 0);
        controlDashboard.add(controlButtonsGrid, 0, 1);
        gridPane.add(controlDashboard, 0, 1);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //retainData();
                clearPlot();

                //Set various constants
                Evolution.MAXIMUM_POOL_SIZE = Integer.parseInt(populationSizeInput.getText());
                Evolution.MAX_GENERATIONS = Integer.parseInt(maxGenerationsInput.getText());
                Evolution.CROSSOVER_RATE = Double.parseDouble(crossoverRateInput.getText());
                Evolution.MUTATION_RATE = Double.parseDouble(mutationRateInput.getText());

                if (problemSelector.getValue().equals("OneMax")) {
                    int bitLength = Integer.parseInt(bitLengthInput.getText());
                    problem = new OneMax(bitLength);
                } else if (problemSelector.getValue().equals("LOLZPrefix")) {
                    int bitLength = Integer.parseInt(bitLengthInput2.getText());
                    int threshold = Integer.parseInt(thresholdInput.getText());
                    problem = new LOLZPrefix(bitLength, threshold);
                } else if (problemSelector.getValue().equals("SurprisingSequences")) {
                    int symbolSetSize = Integer.parseInt(symbolSetSizeInput.getText());
                    int sequenceLength = Integer.parseInt(sequenceLengthInput.getText());
                    problem = new SurprisingSequences(symbolSetSize, sequenceLength, global.isSelected());
                }

                //Set AdultSelection
                if (fullGenerationalReplacement.isSelected()) {
                    Evolution.adultSelectionType = AdultSelection.AdultSelectionEnum.FULL_GENERATIONAL_REPLACEMENT;
                    Evolution.REPRODUCTION_POOL_SIZE = Evolution.MAXIMUM_POOL_SIZE;
                } else if (overProduction.isSelected()) {
                    Evolution.adultSelectionType = AdultSelection.AdultSelectionEnum.OVER_PRODUCTION;
                    Evolution.REPRODUCTION_POOL_SIZE = Integer.parseInt(overProductionPoolSizeInput.getText());
                } else if (generationalMixing.isSelected()) {
                    Evolution.adultSelectionType = AdultSelection.AdultSelectionEnum.GENERATIONAL_MIXING;
                    Evolution.REPRODUCTION_POOL_SIZE = Evolution.MAXIMUM_POOL_SIZE;
                }

                //Set ParentSelection
                if (fitnessProportionate.isSelected()) {
                    Evolution.parentSelectionType = ParentSelection.ParentSelectionEnum.FITNESS_PROPORTIONATE;
                } else if (sigmaScaling.isSelected()) {
                    Evolution.parentSelectionType = ParentSelection.ParentSelectionEnum.SIGMA_SCALING;
                } else if (tournamentSelection.isSelected()) {
                    Evolution.parentSelectionType = ParentSelection.ParentSelectionEnum.TOURNAMENT_SELECTION;
                    Evolution.TOURNAMENT_GROUP_SIZE = Integer.parseInt(groupSizeInput.getText());
                    Evolution.TOURNAMENT_RANDOM_PROBABILITY = Double.parseDouble(probabilityInput.getText());
                } else if (boltzmannSelection.isSelected()) {
                    Evolution.parentSelectionType = ParentSelection.ParentSelectionEnum.BOLTZMANN_SELECTION;
                    Evolution.TEMPERATURE = Double.parseDouble(temperatureInput.getText());
                    Evolution.TEMPERATURE_DECREASE = Double.parseDouble(decreaseInput.getText());
                }

                //Set mutation type
                if (genome.isSelected()) {
                    Evolution.mutationType = Mutation.MutationEnum.GENOME_MUTATION;
                } else if (perGene.isSelected()) {
                    Evolution.mutationType = Mutation.MutationEnum.PER_GENE_MUTATION;
                }

                try {
                    Evolution.run(problem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return gridPane;
    }

    private static LineChart initPlotter() {
        final NumberAxis generationAxis = new NumberAxis();
        generationAxis.setLabel("Generation");
        final NumberAxis fitnessAxis = new NumberAxis("Fitness", 0, 1, 0.1);
        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(generationAxis, fitnessAxis);
        lineChart.setAnimated(false);
        lineChart.setCreateSymbols(false);
        lineChart.setPrefSize(1000, 400);

        Evolution.bestFitnessSeries = new XYChart.Series();
        Evolution.bestFitnessSeries.setName("Best fitness");
        Evolution.averageFitnesSeries = new XYChart.Series();
        Evolution.averageFitnesSeries.setName("Average fitness");
        Evolution.standardDeviationFitnessSeries = new XYChart.Series();
        Evolution.standardDeviationFitnessSeries.setName("Standard deviation");
        lineChart.getData().add(Evolution.bestFitnessSeries);
        lineChart.getData().add(Evolution.averageFitnesSeries);
        lineChart.getData().add(Evolution.standardDeviationFitnessSeries);
        return lineChart;
    }

    private static void clearPlot() {
        if (Evolution.bestFitnessSeries.getData() != null) Evolution.bestFitnessSeries.getData().clear();
        if (Evolution.averageFitnesSeries.getData() != null) Evolution.averageFitnesSeries.getData().clear();
        if (Evolution.standardDeviationFitnessSeries.getData() != null) Evolution.standardDeviationFitnessSeries.getData().clear();
    }

    private static void retainData() {
        plot.getData().retainAll();
        plot.getData().add(Evolution.bestFitnessSeries);
        plot.getData().add(Evolution.averageFitnesSeries);
        plot.getData().add(Evolution.standardDeviationFitnessSeries);
    }
}
