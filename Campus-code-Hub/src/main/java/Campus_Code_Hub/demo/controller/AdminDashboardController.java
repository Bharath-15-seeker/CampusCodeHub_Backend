package Campus_Code_Hub.demo.controller;

import Campus_Code_Hub.demo.dto.DashboardStatsDTO;
import Campus_Code_Hub.demo.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminDashboardController {

    private final DashboardService dashboardService;

    public AdminDashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/api/admin/dashboard/stats")
    public DashboardStatsDTO getStats() {
        return dashboardService.getStats();
    }
}