package BubbleTank;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class StatisticTable implements Constants {
 JDialog dialog;
 JTable table;
 
 public StatisticTable(int enemy, int move, int level){
   dialog = new JDialog();
   dialog.setTitle("Statistic");
   
   DefaultTableModel model = new DefaultTableModel(
       new Object[] {"Average movements", "Average enemies", "Sum of levels"},0);
   table = new JTable(model);
   dialog.setLayout(new BorderLayout());
   dialog.add(new JScrollPane(table),BorderLayout.CENTER);
   model.addRow(new Object[] {move, enemy, level});
   dialog.pack();
   dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
   dialog.setResizable(false);
   dialog.setVisible(true);
 } 
}
