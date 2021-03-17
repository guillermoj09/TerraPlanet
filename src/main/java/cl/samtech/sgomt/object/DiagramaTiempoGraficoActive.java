package cl.samtech.sgomt.object;

import java.util.List;

public class DiagramaTiempoGraficoActive {
	
	private String categoria;
	private List<DiagramaTiempoGraficoDetalleActive> diagramaTiempoGraficoDetalleActives;
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public List<DiagramaTiempoGraficoDetalleActive> getDiagramaTiempoGraficoDetalleActives() {
		return diagramaTiempoGraficoDetalleActives;
	}
	public void setDiagramaTiempoGraficoDetalleActives(
			List<DiagramaTiempoGraficoDetalleActive> diagramaTiempoGraficoDetalleActives) {
		this.diagramaTiempoGraficoDetalleActives = diagramaTiempoGraficoDetalleActives;
	}
		
	
}
