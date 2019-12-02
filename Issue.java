package issues;


public abstract class Issue{
	private String code;
	private String name;

	//constructor
	public Issue(){
		this.code = "defaultCode";
		this.name = "defaultName";
	}
	public Issue(String code, String name){
		if(code==null || name==null){
			throw new IllegalArgumentException("\n���͓��e�ɃG���[������܂��B\n�m�F���Ă�������");
		}
		this.code = code;
		this.name = name;
	}
	
	//getter
	public String getCode(){
		return code;
	}
	public String getName(){
		return name;
	}
}