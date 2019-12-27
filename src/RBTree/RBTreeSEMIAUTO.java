package RBTree;

import Easis.Common.StringUtil;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * 手动调整红黑树 JFrame
 * @author lcy
 * @date 2019-12-27
 */
public class RBTreeSEMIAUTO extends JFrame {
    private JPanel contentPane;
    private JTextField txt_indexNO;
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JButton btnNewButton_2;
    private JButton button;
    private JButton button_1;
    private JButton button_2;
    private JPanel panel_1;
    private JScrollPane scrollPane;
    private RBTreeCanvas treeCanvas = new RBTreeCanvas();

    private RBTreeGen _gen = new RBTreeGen();
    private RBTreeGen _genCopy = new RBTreeGen();
    private JButton btnNewButton_3;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RBTreeSEMIAUTO frame = new RBTreeSEMIAUTO();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 构造函数
     * @param _rootNode 根节点
     */
    public RBTreeSEMIAUTO(TreeUnit _rootNode) {
        this();
        this._gen = new RBTreeGen(_rootNode);
        this.treeCanvas.paintRBTree(this._gen._rootNode);
        this._genCopy = new RBTreeGen(_rootNode);
    }

    /**
     * 构造函数
     */
    public RBTreeSEMIAUTO() {
        setTitle("手动调整红黑树");

        setBounds(100, 100, 450, 300);
        this.contentPane = new JPanel();
        this.contentPane.setBackground(Color.WHITE);
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(this.contentPane);
        this.contentPane.setLayout((LayoutManager) new FormLayout(new ColumnSpec[]{
                ColumnSpec.decode("default:grow")
        }, new RowSpec[]{
                RowSpec.decode("fill:max(50dlu;default)"),
                RowSpec.decode("fill:default:grow")
        }));
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "调整操作", 4, 2, null, null));
        panel.setBackground(Color.WHITE);
        this.contentPane.add(panel, "1, 1, fill, fill");
        panel.setLayout(new FlowLayout(0, 5, 5));

        JLabel label = new JLabel("关键字：");
        panel.add(label);

        this.txt_indexNO = new JTextField();
        panel.add(this.txt_indexNO);
        this.txt_indexNO.setColumns(10);
        this.txt_indexNO.setPreferredSize(new Dimension(50, 25));

        this.btnNewButton = new JButton("插入节点");
        this.btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (RBTreeSEMIAUTO.this._gen._rootNode == null) {

                    JOptionPane.showMessageDialog(null, "您需要修改的红黑树为空，请按照正常流程使用该程序。", "警告", 1);
                    return;
                }
                float indexNO1 = 0.0F;
                try {
                    indexNO1 = Float.parseFloat(RBTreeSEMIAUTO.this.txt_indexNO.getText());
                } catch (Exception ef) {
                    ef.printStackTrace();
                    JOptionPane.showMessageDialog(null, "无法将关键字转换成为小数！请检查您的输入！");

                    return;
                }

                if (RBTreeSEMIAUTO.this._gen.containsKey(indexNO1)) {
                    JOptionPane.showMessageDialog(null, "红黑树里面已经存在【" + indexNO1 + "】，请输入其他关键字或者删除此关键字再尝试。", "无法插入关键字", 2);
                    RBTreeSEMIAUTO.this.treeCanvas.setSelected(indexNO1);
                    return;
                }
                RBTreeSEMIAUTO.this._gen.___insert(indexNO1);
                RBTreeSEMIAUTO.this.treeCanvas.paintRBTree(RBTreeSEMIAUTO.this._gen._rootNode);
            }
        });
        this.btnNewButton.setBackground(Color.WHITE);
        panel.add(this.btnNewButton);

        this.btnNewButton_1 = new JButton("删除节点");
        this.btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (RBTreeSEMIAUTO.this._gen._rootNode == null) {

                    JOptionPane.showMessageDialog(null, "您需要修改的红黑树为空，请按照正常流程使用该程序。", "警告", 1);
                    return;
                }
            }
        });
        this.btnNewButton_1.setBackground(Color.WHITE);
        panel.add(this.btnNewButton_1);

        this.btnNewButton_2 = new JButton("染红");
        this.btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (RBTreeSEMIAUTO.this._gen._rootNode == null) {

                    JOptionPane.showMessageDialog(null, "您需要修改的红黑树为空，请按照正常流程使用该程序。", "警告", 1);
                    return;
                }
                Object selectedObj = RBTreeSEMIAUTO.this.treeCanvas.getSelectIndex();
                if (selectedObj == null) {
                    JOptionPane.showMessageDialog(null, "您尚未选中任何记录。", "警告", 1);

                    return;
                }

                RBTreeSEMIAUTO.this._gen.___fillRed(StringUtil.toFloat(selectedObj.toString()));
                RBTreeSEMIAUTO.this.treeCanvas.paintRBTree(RBTreeSEMIAUTO.this._gen._rootNode);
                RBTreeSEMIAUTO.this.treeCanvas.setSelected(StringUtil.toFloat(selectedObj.toString()));
            }
        });

        this.btnNewButton_2.setBackground(Color.WHITE);
        panel.add(this.btnNewButton_2);

        this.button = new JButton("染黑");
        this.button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (RBTreeSEMIAUTO.this._gen._rootNode == null) {

                    JOptionPane.showMessageDialog(null, "您需要修改的红黑树为空，请按照正常流程使用该程序。", "警告", 1);
                    return;
                }
                Object selectedObj = RBTreeSEMIAUTO.this.treeCanvas.getSelectIndex();
                if (selectedObj == null) {
                    JOptionPane.showMessageDialog(null, "您尚未选中任何记录。", "警告", 1);

                    return;
                }

                RBTreeSEMIAUTO.this._gen.___fillBlack(StringUtil.toFloat(selectedObj.toString()));
                RBTreeSEMIAUTO.this.treeCanvas.paintRBTree(RBTreeSEMIAUTO.this._gen._rootNode);
                RBTreeSEMIAUTO.this.treeCanvas.setSelected(StringUtil.toFloat(selectedObj.toString()));
            }
        });
        this.button.setBackground(Color.WHITE);
        panel.add(this.button);

        this.button_1 = new JButton("左旋");
        this.button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (RBTreeSEMIAUTO.this._gen._rootNode == null) {

                    JOptionPane.showMessageDialog(null, "您需要修改的红黑树为空，请按照正常流程使用该程序。", "警告", 1);
                    return;
                }
                Object selectedObj = RBTreeSEMIAUTO.this.treeCanvas.getSelectIndex();
                if (selectedObj == null) {
                    JOptionPane.showMessageDialog(null, "您尚未选中任何记录。", "警告", 1);
                    return;
                }
                float f1 = 0.0F;

                try {
                    f1 = Float.parseFloat(selectedObj.toString());


                } catch (Exception ed) {
                    ed.printStackTrace();
                    JOptionPane.showMessageDialog(null, "您选中的节点并非可用数据节点！", "错误", 0);

                    return;
                }

                RBTreeSEMIAUTO.this._gen.___turnLeft(f1);
                RBTreeSEMIAUTO.this.treeCanvas.paintRBTree(RBTreeSEMIAUTO.this._gen._rootNode);
            }
        });
        this.button_1.setBackground(Color.WHITE);
        panel.add(this.button_1);

        this.button_2 = new JButton("右旋");
        this.button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (RBTreeSEMIAUTO.this._gen._rootNode == null) {

                    JOptionPane.showMessageDialog(null, "您需要修改的红黑树为空，请按照正常流程使用该程序。", "警告", 1);
                    return;
                }
                Object selectedObj = RBTreeSEMIAUTO.this.treeCanvas.getSelectIndex();
                if (selectedObj == null) {
                    JOptionPane.showMessageDialog(null, "您尚未选中任何记录。", "警告", 1);
                    return;
                }
                float f1 = 0.0F;

                try {
                    f1 = Float.parseFloat(selectedObj.toString());


                } catch (Exception ed) {
                    ed.printStackTrace();
                    JOptionPane.showMessageDialog(null, "您选中的节点并非可用数据节点！", "错误", 0);

                    return;
                }

                RBTreeSEMIAUTO.this._gen.___turnRight(f1);
                RBTreeSEMIAUTO.this.treeCanvas.paintRBTree(RBTreeSEMIAUTO.this._gen._rootNode);
            }
        });
        this.button_2.setBackground(Color.WHITE);
        panel.add(this.button_2);

        this.btnNewButton_3 = new JButton("重做");
        this.btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (RBTreeSEMIAUTO.this._gen._rootNode == null) {

                    JOptionPane.showMessageDialog(null, "您需要修改的红黑树为空，请按照正常流程使用该程序。", "警告", 1);

                    return;
                }
                RBTreeSEMIAUTO.this._gen = new RBTreeGen(RBTreeSEMIAUTO.this._genCopy._rootNode);
                RBTreeSEMIAUTO.this.treeCanvas.paintRBTree(RBTreeSEMIAUTO.this._gen._rootNode);
            }
        });

        this.btnNewButton_3.setBackground(Color.WHITE);
        panel.add(this.btnNewButton_3);

        this.panel_1 = new JPanel();
        this.panel_1.setBorder(new TitledBorder(null, "红黑树示意图", 4, 2, null, null));
        this.panel_1.setBackground(Color.WHITE);
        this.contentPane.add(this.panel_1, "1, 2, fill, fill");
        this.panel_1.setLayout(new BorderLayout(0, 0));

        this.scrollPane = new JScrollPane();
        this.panel_1.add(this.scrollPane, "Center");


        this.scrollPane.setViewportView(this.treeCanvas);
        setSize(new Dimension(800, 700));
    }
}
