package data.dao;
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
