package RBTree;

/**
 * 红黑树Demo原始数据
 * @author lcy
 * @date 2019-12-27
 */
public class RBTreeShowData {
    public RBTreeGen _gen = new RBTreeGen();

    public RBTreeShowData() {
        TreeUnit _rootNode = createTreeUnit(null, 'I', false);
        TreeUnit lv1_A = createTreeUnit(_rootNode, 'G', true);
        TreeUnit lv1_B = createTreeUnit(_rootNode, 'O', true);

        _rootNode._leftChild = lv1_A;
        _rootNode._rightChild = lv1_B;

        TreeUnit lv1_a_1 = createTreeUnit(lv1_A, 'A', false);
        TreeUnit lv1_a_2 = createTreeUnit(lv1_A, 'H', false);
        lv1_A._leftChild = lv1_a_1;
        lv1_A._rightChild = lv1_a_2;

        TreeUnit lv1_b_1 = createTreeUnit(lv1_B, 'L', false);
        TreeUnit lv1_b_2 = createTreeUnit(lv1_B, 'R', false);
        lv1_B._leftChild = lv1_b_1;
        lv1_B._rightChild = lv1_b_2;

        TreeUnit lv3_t1 = createTreeUnit(lv1_a_2, 'M', true);
        lv1_b_1._rightChild = lv3_t1;

        this._gen._rootNode = _rootNode;
    }

    private TreeUnit createTreeUnit(TreeUnit _parent, char indexNO, boolean isRed) {
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
