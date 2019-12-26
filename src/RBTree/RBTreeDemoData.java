package RBTree;

public class RBTreeDemoData {
    public RBTreeGen _gen = new RBTreeGen();


    public RBTreeDemoData() {
        TreeUnit _rootNode = createTreeUnit(null, 9.0F, false);
        TreeUnit lv1_A = createTreeUnit(_rootNode, 1.0F, true);
        TreeUnit lv1_B = createTreeUnit(_rootNode, 12.0F, true);

        _rootNode._leftChild = lv1_A;
        _rootNode._rightChild = lv1_B;

        TreeUnit lv1_a_1 = createTreeUnit(lv1_A, 0.0F, false);
        TreeUnit lv1_a_2 = createTreeUnit(lv1_A, 4.0F, false);
        lv1_A._leftChild = lv1_a_1;
        lv1_A._rightChild = lv1_a_2;

        TreeUnit lv1_b_1 = createTreeUnit(lv1_B, 11.0F, false);
        TreeUnit lv1_b_2 = createTreeUnit(lv1_B, 19.0F, false);
        lv1_B._leftChild = lv1_b_1;
        lv1_B._rightChild = lv1_b_2;

        TreeUnit lv3_t1 = createTreeUnit(lv1_a_2, 2.0F, true);
        TreeUnit lv3_t2 = createTreeUnit(lv1_a_2, 7.0F, true);
        lv1_a_2._leftChild = lv3_t1;
        lv1_a_2._rightChild = lv3_t2;

        TreeUnit lv3_t3 = createTreeUnit(lv1_b_2, 15.0F, true);
        lv1_b_2._leftChild = lv3_t3;
        this._gen._rootNode = _rootNode;
    }

    private TreeUnit createTreeUnit(TreeUnit _parent, float indexNO, boolean isRed) {
        TreeUnit t = new TreeUnit();
        t.indexNO = indexNO;
        if (isRed) {
            t.color = "red";
            t.isRed = true;
        } else {

            t.color = "black";
            t.isRed = false;
        }
        t._parent = _parent;
        return t;
    }
}
