package functions;
public class TabulatedFunction {
    private FunctionPoint[] points;
    final double EPSILON = 2.220446049250326E-16;
    private int pointsCount;
    public TabulatedFunction(double leftX, double rightX, int pointsCount){
        if (pointsCount < 2) {
            throw new IllegalArgumentException("Количество точек должно быть не менее 2");
        }
        if (leftX >= rightX) {
            throw new IllegalArgumentException("Левая граница должна быть меньше правой");
        }
        points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i<pointsCount;i++){
            double x = leftX + i*step;
            points[i] = new FunctionPoint(x, 0);
        }
        this.pointsCount = pointsCount;
    }
    public TabulatedFunction(double leftX, double rightX, double[] values){
        if (values.length < 2) {
            throw new IllegalArgumentException("Количество точек должно быть не менее 2");
        }
        if (leftX >= rightX) {
            throw new IllegalArgumentException("Левая граница должна быть меньше правой");
        }
        pointsCount = values.length;
        points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i< pointsCount;i++){
            double x = leftX + i*step;
            points[i] = new FunctionPoint(x, values[i]);
        }

    }
    public double getLeftDomainBorder(){
        return points[0].getX();
    }
    public double getRightDomainBorder(){
        return points[pointsCount-1].getX();
    }
    public int getPointsCount() {
        return pointsCount;
    }

    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }


        for (int i = 0; i < pointsCount; i++) {
            if (Math.abs(points[i].getX() - x) <= EPSILON) {
                return points[i].getY();
            }
        }


        for (int i = 0; i < pointsCount - 1; i++) {
            if (x >= points[i].getX() && x <= points[i + 1].getX()) {
                double leftX = points[i].getX();
                double rightX = points[i + 1].getX();
                double leftY = points[i].getY();
                double rightY = points[i + 1].getY();


                return leftY + (rightY - leftY) * (x - leftX) / (rightX - leftX);
            }
        }


        return Double.NaN;
    }
    public void showPoints(){
        for (int i = 1; i<pointsCount;i++){
            System.out.print("Значение точки "+ (i+1) + ": ");
            points[i].showPoint();
            System.out.println();
        }
    }
    public FunctionPoint getPoint(int index){
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы массива точек");
        }
        return new FunctionPoint(points[index]);
    }
    public void setPoint(int index, FunctionPoint point){
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы массива точек");
        }
        if (point.getX()<getLeftDomainBorder() || point.getX()>getRightDomainBorder()){
            throw new IndexOutOfBoundsException("Координата x задаваемой точки лежит вне интервала");
        }
        points[index] = point;
    }
    public double getPointX(int index){
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы массива точек");
        }
        return points[index].getX();
    }
    public void setPointX(int index, double x){

        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы массива точек");
        }
        if (x<getLeftDomainBorder() || x>getRightDomainBorder()){
            throw new IndexOutOfBoundsException("Координата x задаваемой точки лежит вне интервала");
        }
        if(x>=points[index+1].getX()||x<=points[index-1].getX()){
            throw new IllegalArgumentException("Новое значение x нарушает упорядоченность");
        }
        points[index].setX(x);
    }
    public double getPointY(int index){
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы массива точек");
        }
        return points[index].getY();
    }
    public void setPointY(int index, double y){
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы массива точек");
        }
        points[index].setY(y);
    }
    public void deletePoint(int index){
        if (index < 0 || index >=pointsCount) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы массива точек");
        }
        if (pointsCount <= 2) {
            throw new IllegalStateException("Нельзя удалить точку: функция должна содержать минимум 2 точки");
        }
        System.arraycopy(points,index +1,points,index,pointsCount-1-index);
        pointsCount--;
        points[pointsCount] = null;


    }
    public void addPoint(FunctionPoint point){
        int insertIndex = 0;
        while (insertIndex < pointsCount && point.getX() > points[insertIndex].getX()) {
            insertIndex++;
        }
        if (insertIndex < pointsCount && (Math.abs(point.getX() - points[insertIndex].getX())<=EPSILON)) {
            throw new IllegalArgumentException("Точка с x=" + point.getX() + " уже существует");
        }
        if (pointsCount == points.length) {
            int newCapacity = points.length + points.length / 2 + 1;
            FunctionPoint[] newPoints = new FunctionPoint[newCapacity];
            System.arraycopy(points, 0, newPoints, 0, pointsCount);
            points = newPoints;
        }

        if (insertIndex < pointsCount) {
            System.arraycopy(points, insertIndex, points, insertIndex + 1, pointsCount - insertIndex);
        }
        points[insertIndex] = new FunctionPoint(point);
        pointsCount++;
    }
}
