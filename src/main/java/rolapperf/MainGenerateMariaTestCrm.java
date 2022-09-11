package rolapperf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;

import rolapperf.MetricReport.MetricEntry;
import rolapperf.model.Company;
import rolapperf.model.Contract;
import rolapperf.model.RestitutionMonth;

public class MainGenerateMariaTestCrm {
	private static final MetricReport report = new MetricReport();
	/**
	 * Main maria db test args[0] host args[1] port args[2] schema args[3] login
	 * args[4] password
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		RestitutionRolapService service = RestitutionRolapService.buildService(args);
		System.out.println("Clear");
		service.clear();
		
		Set<Integer> leaves = new HashSet<>();

		System.out.println("Build companies");
		try (Session session = service.openSession()) {
			service.addUser(session, "admin");
			service.addUser(session, "test");

			buildCompany(session, service, null, null, 0, 0, leaves);
		}

		System.out.println("Build contracts");
		for(Integer companyId : leaves) {
			buildContracts(service, companyId);
		}
	}

	public static void buildCompany(Session session, RestitutionRolapService service, String parentName,
			Integer parentId, int id, int level, Set<Integer> leaves) {
		String name = "C" + (parentName != null ? parentName + "_" : "") + id;

		Company company = service.addCompany(session, parentId, level, name);

		if (level == 4) {
			leaves.add(company.getId());			
		} else {
			for (int x = 0; x < 4; x++) {
				buildCompany(session, service, name, company.getId(), x, level + 1, leaves);
			}
		}

	}

	private static void buildContracts(RestitutionRolapService service, int companyId) {
		try (Session session = service.openSession()) {
			Company company = service.getCompany(session, companyId);
			
		// TODO Auto-generated method stub
		for(int x=0;x < 100;x++) {
			final int id = x;
			report.get("contract").measure(() -> {
				Contract contract = service.buildContract(session, company.getId(), company.getName() + "_ct" + id);						
				writeRestitutionByContract(session, service, company, contract);
				
				
			});
			
		}
		System.out.println(report.get("contract"));
		}
	}

	private static void writeRestitutionByContract(Session session, RestitutionRolapService service, Company company,
			Contract contract) {
		boolean fta = true;
		int currentYear = 2018;
		int currentMonth = 0;
		int endYear = 2024;
		int endMonth = 11;

		int custom1 = contract.getId() % 3;
		int custom2 = contract.getId() % 5;
		int custom3 = contract.getId() % 7;
		int custom4 = contract.getId() % 11;

		while (currentYear < endYear && currentMonth <= endMonth) {
			for (int vueId = 0; vueId < 2; vueId++) {
				for (int deviseId = 0; deviseId < 3; deviseId++) {
					for (int accountId = 0; accountId < 8; accountId++) {

						RestitutionMonth restit = new RestitutionMonth();

						// Vue
						restit.setVueId(vueId);

						// Axes
						restit.setCompanyId(company.getId());
						restit.setScenarioId(0);
						restit.setContractId(contract.getId());

						restit.setDeviseType(deviseId);
						restit.setMonthIndex(currentYear*12+ currentMonth);
						restit.setAccountId(accountId);
						restit.setCustom1(custom1);
						restit.setCustom2(custom2);
						restit.setCustom3(custom3);
						restit.setCustom4(custom4);

						// Measures
						restit.setFta(1.0);
						restit.setFc(1.0);
						restit.setIncNc1(1.0);
						restit.setIncNc2(1.0);
						restit.setIncNc23(1.0);
						restit.setDecCash(1.0);
						restit.setInPerimeter(1.0);
						restit.setFusion(1.0);
						restit.setReclass(1.0);
						restit.setInterrupt(1.0);
						restit.setImpairment(1.0);
						restit.setReclassCustom1(1.0);
						restit.setReclassCustom2(1.0);
						restit.setReclassCustom3(1.0);
						restit.setReclassCustom4(1.0);
						restit.setClo(1.0);


						session.save(restit);
					}
				}
			}
			currentMonth++;
			if (currentMonth == 12) {
				currentMonth = 0;
				currentYear++;
			}

		}

	}

	
}
