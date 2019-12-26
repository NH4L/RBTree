package RBTree;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class RBTreeEditionDialog extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private JPanel contentPane = this.contentPanel;

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


    public static void main(String[] args) {
        try {
            RBTreeEditionDialog dialog = new RBTreeEditionDialog();
            dialog.setDefaultCloseOperation(2);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public RBTreeEditionDialog() {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());

        this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(this.contentPanel, "Center");

        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(Color.WHITE);
        buttonPane.setLayout(new FlowLayout(2));
        getContentPane().add(buttonPane, "South");

        JButton okButton = new JButton("确定");
        okButton.setBackground(Color.WHITE);
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);


        JButton cancelButton = new JButton("退出");
        cancelButton.setBackground(Color.WHITE);
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);


        setTitle("手动调整红黑树");


        this.contentPane.setBackground(Color.WHITE);


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
                if (RBTreeEditionDialog.this._gen._rootNode == null) {

                    JOptionPane.showMessageDialog(null, "您需要修改的红黑树为空，请按照正常流程使用该程序。", "警告", 1);
                    return;
                }
            }
        });
        this.btnNewButton.setBackground(Color.WHITE);
        panel.add(this.btnNewButton);

        this.btnNewButton_1 = new JButton("删除节点");
        this.btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (RBTreeEditionDialog.this._gen._rootNode == null) {

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
                if (RBTreeEditionDialog.this._gen._rootNode == null) {

                    JOptionPane.showMessageDialog(null, "您需要修改的红黑树为空，请按照正常流程使用该程序。", "警告", 1);
                    return;
                }
            }
        });
        this.btnNewButton_2.setBackground(Color.WHITE);
        panel.add(this.btnNewButton_2);

        this.button = new JButton("染黑");
        this.button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (RBTreeEditionDialog.this._gen._rootNode == null) {

                    JOptionPane.showMessageDialog(null, "您需要修改的红黑树为空，请按照正常流程使用该程序。", "警告", 1);
                    return;
                }
            }
        });
        this.button.setBackground(Color.WHITE);
        panel.add(this.button);

        this.button_1 = new JButton("左旋");
        this.button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (RBTreeEditionDialog.this._gen._rootNode == null) {

                    JOptionPane.showMessageDialog(null, "您需要修改的红黑树为空，请按照正常流程使用该程序。", "警告", 1);
                    return;
                }
            }
        });
        this.button_1.setBackground(Color.WHITE);
        panel.add(this.button_1);

        this.button_2 = new JButton("右旋");
        this.button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (RBTreeEditionDialog.this._gen._rootNode == null) {

                    JOptionPane.showMessageDialog(null, "您需要修改的红黑树为空，请按照正常流程使用该程序。", "警告", 1);
                    return;
                }
            }
        });
        this.button_2.setBackground(Color.WHITE);
        panel.add(this.button_2);

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
