package cl.samtech.sgomt.object;

import java.sql.Timestamp;

public class ConsultarComandoActive {
	
	//  0         1        2           3                          4             5         6              7          8                9                   10     11
	//204521	SC73	SMS_ITD	2018-02-01 08:36:15.380	2018-02-01 08:35:22.763	1	>SCC01R;ID=SC73<	>RCC	RCC010000100000	2018-02-01 08:36:15.087	NULL	NULL
	
	private String idGPS; // 1
	private String idUser;  //2
	private Timestamp ansDate; //3 
	private Timestamp txDate; //4 
	private int retrieve; // 5
	private String msg;  // 6
	private String nom; // 7
	private String ans; // 8
		
	public String getIdGPS() {
		return idGPS;
	}
	public void setIdGPS(String idGPS) {
		this.idGPS = idGPS;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public Timestamp getAnsDate() {
		return ansDate;
	}
	public void setAnsDate(Timestamp ansDate) {
		this.ansDate = ansDate;
	}
	public Timestamp getTxDate() {
		return txDate;
	}
	public void setTxDate(Timestamp txDate) {
		this.txDate = txDate;
	}
	public int getRetrieve() {
		return retrieve;
	}
	public void setRetrieve(int retrieve) {
		this.retrieve = retrieve;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
	
	
	

}
