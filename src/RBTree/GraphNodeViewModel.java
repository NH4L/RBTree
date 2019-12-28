package RBTree;

public class GraphNodeViewModel {
    public char indexNO = 'A';
    public boolean isRed = true;
    public int xIndex = 0;
    public int yIndex = 0;

    public int locX = 0;
    public int locY = 0;
    public int HSep = 0;
    public int VSep = 0;

    public Object _cellObject = null;
    public GraphNodeViewModel _parent = null;
    public GraphNodeViewModel leftChild = null;
    public GraphNodeViewModel rightChild = null;

    public float leftNO = 0.0F;
    public float rightNO = 0.0F;

    public int leftChildLocX = 0;
    public int rightChildLocX = 0;
}