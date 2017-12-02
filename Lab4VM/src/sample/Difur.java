package sample;

public class Difur{
    private Function func;
    private double x0;
    private double y0;
    private double x1;
    private double acc;
    private double[] x;
    private double[] y;
    private int steps;
    public Difur(double x0, double y0, double x1, double acc, Function func){
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.acc = acc;
        this.func = func;
    }
    public void RungKut(){
        double k1, k2, k3, k4;
        steps = (int)((x1-x0)/acc)+1;
        x = new double[steps+1];
        y = new double[steps+1];
        y[0] = y0;
        x[0] = x0;
        double y_prev = y[0];
        double x_prev = x[0];
        for(int i=1;i<=steps;i++){
            k1 = func.calculate(x_prev,y_prev);
            k2 = func.calculate(x_prev+acc/2,y_prev+(k1*acc/2));
            k3 = func.calculate(x_prev+acc/2,y_prev+(k2*acc/2));
            k4 = func.calculate(x_prev+acc,y_prev+k3*acc);
            y[i] = y_prev+(acc/6)*(k1+k2*2+k3*2+k4);
            x[i] = x_prev+acc;
            y_prev = y[i];
            x_prev = x[i];
        }

    }
    public double[] getX(){
        return x;
    }
    public double[] getY() {
        return y;
    }
    public int getSteps(){
        return steps;
    }
}
