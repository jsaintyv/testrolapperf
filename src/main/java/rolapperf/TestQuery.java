package rolapperf;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestQuery {
	public static void main(String[] args) throws SQLException {
		RestitutionRolapService service = RestitutionRolapService.buildService(args);
		try(Connection connection = service.getConnection()) {
			try(Statement statement = connection.createStatement()) {
				long start = System.currentTimeMillis();
				ResultSet query =statement.executeQuery("SELECT companyId, SUM(fta) as  tfta, SUM(clo) as tclo FROM `restitution_month` WHERE vueId = 1 and monthIndex = (2020*12+11) and deviseType = 2 group by companyId");
				
				while(query.next()) {
					System.out.println(query.getInt("companyId") + ";" + query.getInt("tfta") + ";" + query.getInt("tclo"));
				}
				long diff = System.currentTimeMillis() - start;
				System.out.println(diff + "ms");
			}
		}
	}
}
