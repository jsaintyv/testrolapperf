package rolapperf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name="restitution_month",indexes = {		
	@Index(columnList = "vueId"),
	@Index(columnList = "monthIndex"),
	@Index(columnList = "companyId"),
	@Index(columnList = "deviseType"),
	@Index(columnList = "contractId"),
	@Index(columnList = "scenarioId"),
	@Index(columnList = "custom1"),
	@Index(columnList = "custom2"),
	@Index(columnList = "custom3"),
	@Index(columnList = "custom4")
	
	
})
public class RestitutionMonth  {
	
	// Asset
	public final static int ACCOUNT_ACTIF = 0;
	public final static int ACCOUNT_AMORT = 1;
	
	// Equity
	public final static int ACCOUNT_RESERVE = 2;		
	public final static int ACCOUNT_RESULTAT = 3;
	
	// Debt
	public final static int ACCOUNT_DETTE_LT = 4;
	public final static int ACCOUNT_DETTE_CT = 5;
	public final static int ACCOUNT_CHARGE_CONSTATE_AVANCE = 6;
	public final static int ACCOUNT_CHARGE_A_PAYER = 7;
	
	// PrimaryKey
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    

	// Vue
    private int vueId;
    
    // Axes 
    private int companyId;
    private int scenarioId;
    private int contractId;
    
    
    private int deviseType;
    private int year;
    private int monthIndex;
    private int accountId;
    private int custom1;
    private int custom2;
    private int custom3;
    private int custom4;
        
    // Measures
    private Double fta;
    private Double fc;
    private Double incNc1;
    private Double incNc2;
    private Double incNc23;
    private Double decCash;
    private Double inPerimeter;
    private Double fusion;
    private Double reclass;
    private Double interrupt;
    private Double impairment;
    private Double reclassCustom1;
    private Double reclassCustom2;
    private Double reclassCustom3;
    private Double reclassCustom4;
    private Double clo;

	public RestitutionMonth() {}
    	
    public int getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	

	public int getMonthIndex() {
		return monthIndex;
	}

	public void setMonthIndex(int monthIndex) {
		this.monthIndex = monthIndex;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getCustom1() {
		return custom1;
	}

	public void setCustom1(int custom1) {
		this.custom1 = custom1;
	}

	public int getCustom2() {
		return custom2;
	}

	public void setCustom2(int custom2) {
		this.custom2 = custom2;
	}

	public int getCustom3() {
		return custom3;
	}

	public void setCustom3(int custom3) {
		this.custom3 = custom3;
	}

	public int getCustom4() {
		return custom4;
	}

	public void setCustom4(int custom4) {
		this.custom4 = custom4;
	}

	public int getVueId() {
		return vueId;
	}

	public void setVueId(int vueId) {
		this.vueId = vueId;
	}

	public int getScenarioId() {
		return scenarioId;
	}

	public void setScenarioId(int scenarioId) {
		this.scenarioId = scenarioId;
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public double getFta() {
		return fta;
	}

	public void setFta(double fta) {
		this.fta = fta;
	}

	public double getFc() {
		return fc;
	}

	public void setFc(double fc) {
		this.fc = fc;
	}

	public double getIncNc1() {
		return incNc1;
	}

	public void setIncNc1(double incNc1) {
		this.incNc1 = incNc1;
	}

	public double getIncNc2() {
		return incNc2;
	}

	public void setIncNc2(double incNc2) {
		this.incNc2 = incNc2;
	}

	public double getIncNc23() {
		return incNc23;
	}

	public void setIncNc23(double incNc23) {
		this.incNc23 = incNc23;
	}

	public double getDecCash() {
		return decCash;
	}

	public void setDecCash(double decCash) {
		this.decCash = decCash;
	}

	public double getInPerimeter() {
		return inPerimeter;
	}

	public void setInPerimeter(double inPerimeter) {
		this.inPerimeter = inPerimeter;
	}

	public double getFusion() {
		return fusion;
	}

	public void setFusion(double fusion) {
		this.fusion = fusion;
	}

	public double getReclass() {
		return reclass;
	}

	public void setReclass(double reclass) {
		this.reclass = reclass;
	}

	public double getInterrupt() {
		return interrupt;
	}

	public void setInterrupt(double interrupt) {
		this.interrupt = interrupt;
	}

	public double getImpairment() {
		return impairment;
	}

	public void setImpairment(double impairment) {
		this.impairment = impairment;
	}

	public double getReclassCustom1() {
		return reclassCustom1;
	}

	public void setReclassCustom1(double reclassCustom1) {
		this.reclassCustom1 = reclassCustom1;
	}

	public double getReclassCustom2() {
		return reclassCustom2;
	}

	public void setReclassCustom2(double reclassCustom2) {
		this.reclassCustom2 = reclassCustom2;
	}

	public double getReclassCustom3() {
		return reclassCustom3;
	}

	public void setReclassCustom3(double reclassCustom3) {
		this.reclassCustom3 = reclassCustom3;
	}

	public double getReclassCustom4() {
		return reclassCustom4;
	}

	public void setReclassCustom4(double reclassCustom4) {
		this.reclassCustom4 = reclassCustom4;
	}

	public double getClo() {
		return clo;
	}

	public void setClo(double clo) {
		this.clo = clo;
	}
	
	public int getDeviseType() {
		return deviseType;
	}

	public void setDeviseType(int deviseType) {
		this.deviseType = deviseType;
	}

}