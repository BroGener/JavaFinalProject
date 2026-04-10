/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datalayer;
import java.util.List;
import transferobjects.MonthlySummaryDTO;
import transferobjects.ActivityCreditDTO;
/**
 *
 * @author biyababu
 */
public interface ReportDAO {
   MonthlySummaryDTO getMonthlySummary(int userId) throws Exception;

    List<ActivityCreditDTO> getActivityCredits() throws Exception; 
}
