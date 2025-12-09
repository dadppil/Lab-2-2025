import functions.*;
public class Main {
    public static void main(String[] args){
        TabulatedFunction func = new TabulatedFunction(0, 4, 3);
        System.out.println("Исходная функция: ");
        func.showPoints();


        System.out.println("Левая граница: " + func.getLeftDomainBorder());
        System.out.println("Правая граница: " + func.getRightDomainBorder());
        System.out.println("f(2.0) = " + func.getFunctionValue(2.0));
        System.out.println("f(5.0) = " + func.getFunctionValue(5.0)); // Должен вернуть NaN


        System.out.println("Количество точек: " + func.getPointsCount());
        System.out.println("Точка 1: ");
        func.getPoint(1).showPoint();
        System.out.println("X точки 1: " + func.getPointX(1));
        System.out.println("Y точки 1: " + func.getPointY(1));


        func.setPointY(1, 5.0);
        System.out.println("После изменения Y точки 1: ");
        func.showPoints();

        func.addPoint(new FunctionPoint(1.5, 2.5));
        System.out.println("После добавления точки (1.5; 2.5): ");
        func.showPoints();

        func.deletePoint(1);
        System.out.println("После удаления точки 1: ");
        func.showPoints();

        System.out.println("f(0.5) = " + func.getFunctionValue(0.5));
        System.out.println("f(1.0) = " + func.getFunctionValue(1.0));
        System.out.println("f(1.7) = " + func.getFunctionValue(1.7));
    }
}