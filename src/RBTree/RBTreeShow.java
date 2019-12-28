package RBTree;

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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * 红黑树演示样例
 * @author lcy
 * @date 2019-12-27
 */
public class RBTreeShow extends JFrame {
    private JPanel contentPane;
    RBTreeCanvas treeCanvas = new RBTreeCanvas();
    private JTextField txt_indexNO;
    JCheckBox cbox_showDetails;
    private RBTreeGen _gen = new RBTreeGen();
    private RBTreeShowData _demoData = new RBTreeShowData();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
                    RBTreeShow frame = new RBTreeShow();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public RBTreeShow() {
        setBackground(Color.WHITE);
        setTitle("红黑树");
        setDefaultCloseOperation(3);
        setBounds(100, 100, 450, 300);
        this.contentPane = new JPanel();
        this.contentPane.setBackground(Color.WHITE);
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(this.contentPane);
        this.contentPane.setLayout((LayoutManager) new FormLayout(new ColumnSpec[]{
                ColumnSpec.decode("default:grow")
        }, new RowSpec[]{
                RowSpec.decode("max(50dlu;default)"),
                RowSpec.decode("fill:max(50dlu;default):grow")
        }));
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "参数设置", 4, 2, null, null));
        panel_1.setBackground(Color.WHITE);
        this.contentPane.add(panel_1, "1, 1, fill, fill");
        panel_1.setLayout(new FlowLayout(0, 5, 5));

        JLabel label = new JLabel("插入字符:");
        panel_1.add(label);

        this.txt_indexNO = new JTextField();
        this.txt_indexNO.setHorizontalAlignment(2);
        panel_1.add(this.txt_indexNO);

        this.txt_indexNO.setPreferredSize(new Dimension(75, 30));

        JButton button = new JButton("插入");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                char indexNO1 = 'A';
                try {
                    String input = RBTreeShow.this.txt_indexNO.getText();
                    if (input == null) {
                        JOptionPane.showMessageDialog(null, "必须输入字符！");
                        return;
                    }
                    if (input.length()>1) {
                        JOptionPane.showMessageDialog(null, "必须输入单个字符！");
                        return;
                    }
                    indexNO1 = input.charAt(0);

                } catch (Exception ef) {
                    ef.printStackTrace();
                    JOptionPane.showMessageDialog(null, "无法将关键字转换成为小数！请检查您的输入！");
                    return;
                }
                if (RBTreeShow.this._gen.containsKey(indexNO1)) {
                    JOptionPane.showMessageDialog(null, "红黑树里面已经存在【" + indexNO1 + "】，请输入其他关键字或者删除此关键字再尝试。", "无法插入关键字", 2);
                    RBTreeShow.this.treeCanvas.setSelected(indexNO1);
                    return;
                }
                RBTreeShow.this._gen.insert(indexNO1);
                RBTreeShow.this.treeCanvas.paintRBTree(RBTreeShow.this._gen._rootNode);
            }
        });

        button.setBackground(Color.WHITE);
        panel_1.add(button);

        JButton button_1 = new JButton("删除选中节点");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object selectedObj = RBTreeShow.this.treeCanvas.getSelectIndex();
                if (selectedObj == null) {
                    JOptionPane.showMessageDialog(null, "您尚未选中任何记录。", "警告", 1);

                    return;
                }
                System.out.println("当前选中节点：" + selectedObj.toString());
                char f1 = 'A';
                try {
                    f1 = selectedObj.toString().charAt(0);
                } catch (Exception ed) {
                    ed.printStackTrace();
                    JOptionPane.showMessageDialog(null, "您尚未选中任何节点。", "警告", 1);

                    return;
                }

                RBTreeShow.this._gen.delete(f1);
                RBTreeShow.this.treeCanvas.paintRBTree(RBTreeShow.this._gen._rootNode);
            }
        });

        button_1.setBackground(Color.WHITE);
        panel_1.add(button_1);

        JButton button_2 = new JButton("采用默认数据");
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RBTreeShow.this._gen = new RBTreeGen(RBTreeShow.this._demoData._gen._rootNode);
                RBTreeShow.this.treeCanvas.paintRBTree(RBTreeShow.this._gen._rootNode);
            }
        });

        button_2.setBackground(Color.WHITE);
        panel_1.add(button_2);

        JButton button_3 = new JButton("手动修改");
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
//                        RBTreeManual bSemiauto = new RBTreeManual((RBTreeShow.null.access$0(RBTreeShow.null.this))._gen._rootNode);
                            RBTreeManual bSemiauto = new RBTreeManual(RBTreeShow.this._gen._rootNode);
                            bSemiauto.setVisible(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


        button_3.setBackground(Color.WHITE);
        panel_1.add(button_3);

        JLabel label_2 = new JLabel("     ");
        panel_1.add(label_2);

//        this.cbox_showDetails = new JCheckBox("显示红黑树具体调整步骤");
//        this.cbox_showDetails.setSelected(true);
//        this.cbox_showDetails.setBackground(Color.WHITE);
//        panel_1.add(this.cbox_showDetails);


        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "红黑树演示", 4, 2, null, null));
        panel.setBackground(Color.WHITE);
        this.contentPane.add(panel, "1, 2, fill, fill");
        panel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, "Center");

        scrollPane.setViewportView(this.treeCanvas);
        setSize(800, 600);
    }
}