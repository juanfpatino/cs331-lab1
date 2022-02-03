import java.util.Comparator;

//For the PriorityQueue in lab1. A smaller f value is better.
public class PointComparator implements Comparator<Point> {
    @Override
    public int compare(Point o1, Point o2) {

        return Double.compare(o1.getF(), o2.getF());

    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
