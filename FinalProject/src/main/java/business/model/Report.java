/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.model;

import data.dao.ReportDAO;
import transferobjects.MonthlySummaryDTO;
import transferobjects.ActivityCreditDTO;
import java.util.List;
/**
 *
 * @author biyababu
 */

/*
* This class handles all report related business logic. 
* it fetch monthly activity credit report, Retrieve activity credit reports
*/
public class Report {
   private ReportDAO reportDAO;

    public Report(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    /**
     * Retrieves monthly summary (credits, debits, amount due) for a user.
     * 
     * @param userId of the user
     * @return MonthlySummaryDTO containing financial summary
     * @throws Exception if database access fails
     */
    public MonthlySummaryDTO getMonthlySummary(int userId) throws Exception {

        MonthlySummaryDTO summary = reportDAO.getMonthlySummary(userId);

        if (summary == null) {
             return new MonthlySummaryDTO(userId, 0, 0);      
        }

        return summary;
    }

    /**
     * Retrieves activity credit report for all users.
     * 
     * @return List of ActivityCreditDTO objects
     * @throws Exception if database access fails
     */
    public List<ActivityCreditDTO> getActivityCredits() throws Exception {
        return reportDAO.getActivityCredits();
    }  
}