
package compiler_project;

public class MIPSRegister {

	public static int mipsResgister =-1;
	public static HashTableforSymbol Hsym;
	public MIPSRegister(){
		Hsym  = HashTableforSymbol.getInsatnce();
	}
	
	public static String getMIPSRegister(){
		if(Hsym==null){
			Hsym = HashTableforSymbol.getInsatnce();
		}
		
		mipsResgister++;
		String mipsNewRegister = "r"+mipsResgister;
		Hsym.addToHashTableforSymbol(mipsNewRegister, "register");
		
		return mipsNewRegister;
	}
	
	/*public static String getVarRegister(String identName){
		String varNewRegister = "r_"+identName;
		Hsym.addToHashTableforSymbol(varNewRegister, "register");
		return varNewRegister;
	}*/
	
}
