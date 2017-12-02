package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import static java.lang.Math.*;

public class Controller {
    @FXML
    private Button draw;
    @FXML
    private TextField x0_input;
    @FXML
    private TextField y0_input;
    @FXML
    private TextField x1_input;
    @FXML
    private TextField accuracy_input;
    @FXML
    private RadioButton func1;
    @FXML
    private RadioButton func2;
    @FXML
    private RadioButton func3;
    @FXML
    private Label info;

    @FXML
    private NumberAxis xAxis = new NumberAxis();
    @FXML
    private NumberAxis yAxis = new NumberAxis();
    @FXML
    private LineChart<Number, Number> chart = new LineChart<Number, Number>(xAxis,yAxis);

    @FXML
    public void drawGraphic(){
        Function func;
        if(func1.isSelected()){
            func = (double x,double y)->2*x-y+pow(x,2);
        }else if(func2.isSelected()){
            func = (double x,double y)->x + y;
        }else{
            func = (double x,double y)->cos(x)*y;
        }
        try{
            double x0 = Double.parseDouble(x0_input.getText());
            double x1 = Double.parseDouble(x1_input.getText());
            double y0 = Double.parseDouble(y0_input.getText());
            double accuracy = Double.parseDouble(accuracy_input.getText());
            if(x1<x0){
                info.setText("x0 должен быть меньше x1");
            }else{
                Difur difur = new Difur(x0,y0,x1,accuracy,func);
                difur.RungKut();
                chart.getData().clear();
                XYChart.Series series = new XYChart.Series();
                ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();
//                Interpolate inter = new Interpolate();
//                for(double i=difur.getX()[0]; i<x1-0.1; i+=0.01){
//                    datas.add(new XYChart.Data(i,inter.polinomValue(difur.getX().length-1,difur.getX(),inter.computeCoef(difur.getX().length-1,difur.getX(),difur.getY()),i)));
//                }
                for(int i = 0; i<difur.getSteps()+1; i++){
                    datas.add(new XYChart.Data(difur.getX()[i],difur.getY()[i]));
                }
                series.setData(datas);
                chart.getData().add(series);
            }
        }catch(NumberFormatException ex){
            info.setText("Введите число");
        }
    }
}