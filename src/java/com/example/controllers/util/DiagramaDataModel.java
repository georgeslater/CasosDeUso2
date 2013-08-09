/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers.util;

/**
 *
 * @author George
 */
import com.example.entities.Diagrama;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

public class DiagramaDataModel extends ListDataModel<Diagrama> implements SelectableDataModel<Diagrama> {

    public DiagramaDataModel() {
    }

    public DiagramaDataModel(List<Diagrama> data) {
        super(data);
    }

    @Override
    public Diagrama getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  

        List<Diagrama> diag = (List<Diagrama>) getWrappedData();

        for (Diagrama d : diag) {
            if (d.getId().toString().equals(rowKey)) {
                return d;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Diagrama d) {
        return d.getId();
    }
}
