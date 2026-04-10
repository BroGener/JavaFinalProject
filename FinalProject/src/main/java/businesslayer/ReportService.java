/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;
import datalayer.ReportDAO;
import transferobjects.MonthlySummaryDTO;
import transferobjects.ActivityCreditDTO;
import java.util.List;
/**
 *
 * @author biyababu
 */
public class ReportService {
   private ReportDAO reportDAO;

    public ReportService(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    
    public MonthlySummaryDTO getMonthlySummary(int userId) throws Exception {

        MonthlySummaryDTO summary = reportDAO.getMonthlySummary(userId);

        if (summary == null) {
             return new MonthlySummaryDTO(userId, 0, 0);      
        }

        return summary;
    }

   
    public List<ActivityCreditDTO> getActivityCredits() throws Exception {
        return reportDAO.getActivityCredits();
    }  
}
