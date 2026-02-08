package Campus_Code_Hub.demo.controller;

import Campus_Code_Hub.demo.dto.SheetProgressDTO;
import Campus_Code_Hub.demo.model.SheetType;
import Campus_Code_Hub.demo.service.SheetProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    @Autowired
    private SheetProgressService progressService;

    @GetMapping("/aptitude")
    public SheetProgressDTO aptitudeProgress(@RequestParam Long userId) {
        return progressService.getProgress(userId, SheetType.APTITUDE);
    }

    @GetMapping("/coding")
    public SheetProgressDTO codingProgress(@RequestParam Long userId) {
        return progressService.getProgress(userId, SheetType.CODING);
    }

}

