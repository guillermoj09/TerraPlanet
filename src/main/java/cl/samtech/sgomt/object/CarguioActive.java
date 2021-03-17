package cl.samtech.sgomt.object;

import java.util.List;

public class CarguioActive {
	
		//Para reporte sinoptico time
		private String nrocarg;
		private String nombre;
		private List<SinopticoActive> sinopticoactive;
		
		
		
		public String getNrocarg() {
			return nrocarg;
		}
		public void setNrocarg(String nrocarg) {
			this.nrocarg = nrocarg;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public List<SinopticoActive> getSinopticoactive() {
			return sinopticoactive;
		}
		public void setSinopticoactive(List<SinopticoActive> sinopticoactive) {
			this.sinopticoactive = sinopticoactive;
		}
		
		
		

}
