package RBTree;

import Easis.Common.StringUtil;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 * 红黑树画布
 * @author lcy
 * @date 2019-12-27
 */
public class RBTreeCanvas extends JPanel {
    private mxGraph _graph = new mxGraph();

    private int _panelWidth = 0;
    private int _panelHeight = 0;
    private int _treeNodeWidth = 50;
    private int _treeNodeHeight = 50;
    private int _treeNodeHSep = 50;
    private int _treeNodeVSep = 50;
    private int _maxNode = 0;

    private ArrayList<ArrayList<GraphNodeViewModel>> _modelContainer = new ArrayList<>();
    mxGraphComponent _component;
    HashMap<String, Object> _nodeContainer = new HashMap<>();

    public RBTreeCanvas() {
        setBackground(Color.WHITE);
        mxGraph graph = this._graph;
        Object parent = graph.getDefaultParent();
        this._component = new mxGraphComponent(this._graph);
        add((Component) this._component);
    }

    public Object getSelectIndex() {
        Object cell = this._graph.getSelectionCell();
        if (cell == null) {
            return null;
        }
        Object res = this._graph.getModel().getValue(cell);
        return res;
    }

    public void setSelected(char indexNO) {
        for (Map.Entry<String, Object> entry : this._nodeContainer.entrySet()) {
            String keyName = entry.getKey();
            Object value = entry.getValue();
            if (keyName.contains("key")) {
                String str1 = keyName.replace("key", "");
                if (str1.charAt(0) == indexNO) {
                    this._graph.setSelectionCell(value);
                    break;
                }
            }
        }
    }

    public void paintRBTree(TreeUnit _rootNode) {
        initModelContainer(_rootNode);
        this._graph.getModel().beginUpdate();

        try {
            for (Map.Entry<String, Object> entry : this._nodeContainer.entrySet()) {
                Object cellObj = entry.getValue();
                this._graph.getModel().remove(cellObj);
            }
            this._nodeContainer.clear();
            drawRBTree(_rootNode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this._graph.getModel().endUpdate();
            validate();
        }
    }

    private void initModelContainer(TreeUnit _currentUnit) {
        this._modelContainer.clear();
        recursion_setModel(_currentUnit, 0);
    }

    private void recursion_setModel(TreeUnit _currentUnit, int level) {
        if (_currentUnit == null) {
            return;
        }
        ArrayList<GraphNodeViewModel> _arrList = new ArrayList<>();
        if (this._modelContainer.size() > level && this._modelContainer.get(level) != null) {
            _arrList = this._modelContainer.get(level);
        } else {

            this._modelContainer.add(_arrList);
        }
        GraphNodeViewModel _gNodeModel = new GraphNodeViewModel();
        GraphNodeViewModel _parentModel = null;
        if (level - 1 >= 0 && level < this._modelContainer.size() && _currentUnit._parent != null) {

            ArrayList<GraphNodeViewModel> _parentList = this._modelContainer.get(level - 1);
            for (GraphNodeViewModel gModel : _parentList) {
                if (gModel.indexNO == _currentUnit._parent.indexNO) {
                    _parentModel = gModel;
                    break;
                }
            }
        }
        _gNodeModel._parent = _parentModel;

        _gNodeModel.yIndex = level;
        _gNodeModel.indexNO = _currentUnit.indexNO;
        _gNodeModel.isRed = _currentUnit.isRed;
        _arrList.add(_gNodeModel);

        if (_currentUnit._leftChild != null) {
            _gNodeModel.leftNO = _currentUnit._leftChild.indexNO;
            recursion_setModel(_currentUnit._leftChild, level + 1);
        }
        if (_currentUnit._rightChild != null) {
            _gNodeModel.rightNO = _currentUnit._rightChild.indexNO;
            recursion_setModel(_currentUnit._rightChild, level + 1);
        }
    }

    private void drawRBTree(TreeUnit _rootNode) {
        this._maxNode = (int) Math.pow(2.0D, (this._modelContainer.size() - 1));
        this._panelWidth = this._maxNode * (this._treeNodeWidth + this._treeNodeHSep);
        this._panelHeight = (this._treeNodeHeight + this._treeNodeVSep) * this._modelContainer.size();
        recursion_drawNode(_rootNode, 1, 0, 0);
    }

    private void recursion_drawNode(TreeUnit _currentNode, int levelTotal, int index, int heightLevel) {
        if (_currentNode == null) {
            return;
        }

        String style = "shape=ellipse;fillColor=red;fontColor=white;Align=Center;fontSize=14;";
        if (!_currentNode.isRed) {
            style = "shape=ellipse;fillColor=black;fontColor=white;Align=Left;fontSize=14;";
        }

        int currentLocX = 0;
        int currentLocY = (this._treeNodeHeight + this._treeNodeVSep) * heightLevel;
        int nodeAreaWidth = this._panelWidth / levelTotal;
        currentLocX = nodeAreaWidth * index + nodeAreaWidth / 2 - this._treeNodeWidth;

        Object cellObject = this._graph.insertVertex(this._graph.getDefaultParent(), null, Character.valueOf(_currentNode.indexNO), currentLocX, currentLocY, this._treeNodeWidth, this._treeNodeHeight, style);
        this._nodeContainer.put("key" + _currentNode.indexNO, cellObject);
        if (_currentNode._parent != null) {
            Object parentCell = this._nodeContainer.get("key" + _currentNode._parent.indexNO);
            Object edgeObject = this._graph.insertEdge(this._graph.getDefaultParent(), null, "", parentCell, cellObject);
            this._nodeContainer.put("edge_parent_" + _currentNode._parent.indexNO + "_self_" + _currentNode.indexNO, edgeObject);
        }

        if (_currentNode._leftChild != null) {
            recursion_drawNode(_currentNode._leftChild, levelTotal * 2, index * 2, heightLevel + 1);
        }
        if (_currentNode._rightChild != null) {
            recursion_drawNode(_currentNode._rightChild, levelTotal * 2, index * 2 + 1, heightLevel + 1);
        }
    }

    private void drawRBTree() {
        this._maxNode = (int) Math.pow(2.0D, (this._modelContainer.size() - 1));
        this._panelWidth = this._maxNode * (this._treeNodeWidth + this._treeNodeHSep);
        this._panelHeight = (this._treeNodeHeight + this._treeNodeVSep) * this._modelContainer.size();


        int cindex = 0;
        for (ArrayList<GraphNodeViewModel> _litem1 : this._modelContainer) {

            int _levelTotalNodes = (int) Math.pow(2.0D, cindex);


            int xIndex = 0;
            int locY = (this._treeNodeHeight + this._treeNodeVSep) * cindex;
            for (GraphNodeViewModel _litem2 : _litem1) {
                int locX = 0;
                int childLeftLocX = 0;
                int childRightLocX = 0;
                if (cindex == 0) {
                    locX = (this._panelWidth - this._treeNodeWidth) / 2;
                }


                _litem2.leftChildLocX = (locX + (this._treeNodeWidth + this._treeNodeHSep) / 2) / 2 - (this._treeNodeHSep + this._treeNodeWidth) / 2;
                _litem2.rightChildLocX = (locX + (this._treeNodeWidth + this._treeNodeHSep) / 2) * 3 / 2 - (this._treeNodeHSep + this._treeNodeWidth) / 2;

                if (_litem2._parent != null) {


                    if (_litem2.indexNO == _litem2._parent.leftNO) {
                        locX = _litem2._parent.leftChildLocX;

                    } else if (_litem2.indexNO == _litem2._parent.rightNO) {
                        locX = _litem2._parent.rightChildLocX;
                    }

                    _litem2.leftChildLocX = (locX + (this._treeNodeWidth + this._treeNodeHSep) / 2) / 2 - (this._treeNodeHSep + this._treeNodeWidth) / 2;
                    _litem2.rightChildLocX = (locX + (this._treeNodeWidth + this._treeNodeHSep) / 2) * 3 / 2 - (this._treeNodeHSep + this._treeNodeWidth) / 2;
                }

                String style = "shape=ellipse;fillColor=red;fontColor=white;Align=Center;fontSize=14;";
                if (!_litem2.isRed) {
                    style = "shape=ellipse;fillColor=black;fontColor=white;Align=Left;fontSize=14;";
                }
                Object cellObject = this._graph.insertVertex(this._graph.getDefaultParent(), null, Character.valueOf(_litem2.indexNO), locX, locY, this._treeNodeWidth, this._treeNodeHeight, style);


                _litem2._cellObject = cellObject;
                _litem2.locX = locX;
                _litem2.locY = locY;
                _litem2.xIndex = xIndex;
                _litem2.yIndex = cindex;
                this._nodeContainer.put("key" + _litem2.indexNO, cellObject);


                if (_litem2._parent != null) {
                    Object edgeObject = this._graph.insertEdge(this._graph.getDefaultParent(), null, "", _litem2._parent._cellObject, cellObject, "stockColor=yellow");
                    this._nodeContainer.put("edge_parent_" + _litem2._parent.indexNO + "_self_" + _litem2.indexNO, edgeObject);
                }
                xIndex++;
            }
            cindex++;
        }
    }
}
