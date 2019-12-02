package issues;


import java.math.BigDecimal;

public class Bond extends Issue {
	private int maturity;
	private BigDecimal coupon;
	private BigDecimal interestRate;
	//private BigDecimal bookValue;

	//10-2 Define enum
	public enum BondType{
		COUPON_BOND,
		ZERO_COUPON_BOND;
	}
	
	//Define constructor
	//1011,JPN,0.05,20230501,2
	public Bond(){
		super();
		this.maturity = 0;
		this.coupon = new BigDecimal("0");
	}
	public Bond(String code, String name, int maturity, String cp){
		super(code,name);
		BigDecimal zero = new BigDecimal("0");

		coupon = new BigDecimal(cp);
		if(code==null || name==null || maturity<20000101|| maturity>29991231||zero.compareTo(coupon)!=-1){
			throw new IllegalArgumentException("\n入力値がerrorです");
		}
		this.maturity = maturity;
		this.coupon = new BigDecimal(cp);
	}
	public Bond(String code, String name, int maturity, double cp){
		super(code,name);
		BigDecimal zero = new BigDecimal("0");

		coupon = new BigDecimal(cp);
		if(code==null || name==null || maturity<20000101|| maturity>29991231||zero.compareTo(coupon)!=-1){
			throw new IllegalArgumentException("\n入力値がerrorです");
		}
		this.maturity = maturity;
		this.coupon = new BigDecimal(String.valueOf(cp));
	}
	/*
	public Bond(String code, String name, String interestRate, int maturity, double coupon){
		super(code,name);
		if(code==null || name==null || maturity<20000101|| maturity>29991231||coupon<0){
			throw new IllegalArgumentException("\n入力値がerrorです");
		}
		this.interestRate = new BigDecimal(interestRate);
		this.maturity = maturity;
		this.coupon = coupon;
	}
	*/
	public Bond(String code, String name, String rate, int maturity, String cp){
		super(code,name);
		BigDecimal zero = new BigDecimal("0");
		coupon = new BigDecimal(cp);
		if(code==null || name==null || maturity<20000101|| maturity>29991231||zero.compareTo(coupon)!=-1){
			throw new IllegalArgumentException("\n入力値がerrorです");
		}
		this.interestRate = new BigDecimal(rate);
		this.maturity = maturity;
		this.coupon = coupon;
	}

/*	public Bond(String code, String name, int maturity, double coupon,
				BigDecimal interestRate,BigDecimal bookValue){
		super(code,name);
		if(code==null || name==null || maturity<20000101|| maturity>29991231||coupon<0||bookValue<0){
			throw new IllegalArgumentException("\n入力値がerrorです");
		}
		this.maturity = maturity;
		this.coupon = coupon;
		this.interestRate = interestRate;
		this.bookValue = bookValue;
	}
*/
	
	//BondType getter
	public BondType getBondType(){
		BigDecimal zero = new BigDecimal("0");
		if(zero.compareTo(coupon)==0){
			return BondType.ZERO_COUPON_BOND;
		}else return BondType.COUPON_BOND;
	}
	
	//getter
	public BigDecimal getInterestRate(){
		return interestRate;
	}
	public int getMaturity(){
		return maturity;
	}
	public BigDecimal getCoupon(){
		return coupon;
	}

	
	
	//10-4 Override
	@Override
	public String toString(){
		String bondStr = "code:"+ getCode() + ", name:"+ getName()
						+", maturity:"+maturity+", coupon:"+coupon;
		return bondStr;
	}
	
	@Override
	public boolean equals(Object bondObj){
		if(bondObj instanceof Bond){
			Bond bondOther = (Bond)bondObj;
			//��r�Ώۂ�Bond��Bond�̃C���X�^���X�̂Ƃ�
			//code maturity coupon�����������true
			return bondOther.getCode().equals(this.getCode()) 
					&& bondOther.getMaturity()==this.maturity
					&& bondOther.getCoupon()==this.coupon;
		}else return false;
		
	}
	@Override
	public int hashCode(){
		final int prime = 31;
		//int intCoupon = this.getCoupon().intValue();
//		int intCoupon = this.coupon.intValue();

		int bondHash =prime * this.getCode().hashCode();
		bondHash += prime * this.maturity;
		bondHash += prime * this.coupon.intValue();
		return bondHash;
	}

	
}