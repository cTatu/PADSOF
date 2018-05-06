package gui.vista.oferta;

import javax.swing.table.DefaultTableModel;

public interface ConsultanteOferta {
	public void showInfoOferta(String atributoUnico, Object... detallesOferta);
	public void addElementosTabla(Object... elementos);
	public void limpiarTabla();
}
