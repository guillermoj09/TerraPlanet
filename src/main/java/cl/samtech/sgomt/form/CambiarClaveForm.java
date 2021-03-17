package cl.samtech.sgomt.form;

import javax.validation.constraints.Size;

public class CambiarClaveForm {
			
		private String username;
		
		@Size(min = 1, message = "Campo Clave Actual, No puedes dejar este campo vacio")
		private String passwordviejo;
			
		@Size(min=5, max=12,  message = "Campo Nueva Clave, Debe tener entre 5 y 12 caractere") 	 
		private String passwordnuevo;
		
		@Size(min=5, max=12, message = "Campo Repetir Nueva Clave, Debe tener entre 5 y 12 caractere")
		private String passwordnuevo2;
			
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		
		public String getPasswordviejo() {
			return passwordviejo;
		}
		
		public void setPasswordviejo(String passwordviejo) {
			this.passwordviejo = passwordviejo;
		}

		public String getPasswordnuevo() {
			return passwordnuevo;
		}

		
		public void setPasswordnuevo(String passwordnuevo) {
			this.passwordnuevo = passwordnuevo;
		}

		public String getPasswordnuevo2() {
			return passwordnuevo2;
		}

		public void setPasswordnuevo2(String passwordnuevo2) {
			this.passwordnuevo2 = passwordnuevo2;
		}

		
		
}

/*
 Validaciones ejemplo
 
  
    @NotNull
    protected String firstName;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
        +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
        +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
             message="{invalid.email}")
    protected String email;
    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
             message="{invalid.phonenumber}")
    protected String mobilePhone;
   
 
 
 */
