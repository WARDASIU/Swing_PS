package Projekt_PS.WorkerForm;

import javax.swing.*;

public class WorkerFormSearch {
    WorkerFormSearch() {

    }

    public void showEqualItems(String text, int rowHeight, JTable table) {
        boolean found = false;
        for (int i = 0; i < rowHeight; i++) {
            for (int j = 0; j < 9; j++) {
                if (text.equals(table.getValueAt(i, j))) {
                    table.changeSelection(i,j,false,false);
                    found = true;
                }
            }
        }
        if (!found) JOptionPane.showMessageDialog(null,"Couldn't find this value!");
    }

}
