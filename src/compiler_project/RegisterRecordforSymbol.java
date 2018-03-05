
package compiler_project;

public class RegisterRecordforSymbol {

	public String identName;
	public String identType;
	public String identBase;
	public String identOffeset;
	public boolean arrVar;
	public boolean arrRegister;
	
	public RegisterRecordforSymbol(String identName, String identType){
		this.identName =identName;
		this.identType = identType;
		identBase = null;
		identOffeset = null;
		arrVar=false;
		arrRegister=false;
	}
	
	public void setIdentBase(String baseAddress){
		this.identBase = baseAddress;
		this.arrRegister=true;
	}
	
	public String getIdentBase(){
		return this.identBase;
	}
	
	public void setIdentOffset(String offset){
		this.identOffeset=offset;
	}
	
	public String getIdentOffset(){
		return this.identOffeset;
	}
	
}

