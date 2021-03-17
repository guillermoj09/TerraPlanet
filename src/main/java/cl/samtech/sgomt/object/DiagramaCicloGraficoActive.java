package cl.samtech.sgomt.object;

import java.util.List;

public class DiagramaCicloGraficoActive {
	
	List<NodeDataArrayActive> nodeDataArray;
	List<LinkDataArrayActive> linkDataArray;
	List<DiagramaCicloTablaActive> diagramaCicloTablaActive;
		
	public List<DiagramaCicloTablaActive> getDiagramaCicloTablaActive() {
		return diagramaCicloTablaActive;
	}
	public void setDiagramaCicloTablaActive(List<DiagramaCicloTablaActive> diagramaCicloTablaActive) {
		this.diagramaCicloTablaActive = diagramaCicloTablaActive;
	}
	public List<NodeDataArrayActive> getNodeDataArray() {
		return nodeDataArray;
	}
	public void setNodeDataArray(List<NodeDataArrayActive> nodeDataArray) {
		this.nodeDataArray = nodeDataArray;
	}
	public List<LinkDataArrayActive> getLinkDataArray() {
		return linkDataArray;
	}
	public void setLinkDataArray(List<LinkDataArrayActive> linkDataArray) {
		this.linkDataArray = linkDataArray;
	}

}
