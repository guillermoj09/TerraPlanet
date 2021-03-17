package cl.samtech.sgomt.util;
import java.security.SecureRandom;


public class RandomString {


	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();
	
	public String randomString( int len ){
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}

	
	public static String codigoSeguridad() {
		
		int num1 = 97;
		int num2 = 122;
		String codigo="";
		char c = 0;
			for (int i=1; i<=6; i++){
			int numAleatorio = (int)Math.floor(Math.random()*(num2 -num1)+num1);
			
			codigo=codigo+(char)numAleatorio;
			
			
			c++;
			
		}
			
			return codigo;
	}
	
}
