package Campus_Code_Hub.demo.controller;

import Campus_Code_Hub.demo.dto.SheetResponse;
import Campus_Code_Hub.demo.model.SheetType;
import Campus_Code_Hub.demo.service.SheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sheets")
@RequiredArgsConstructor
public class SheetController {

    private final SheetService sheetService;

    @GetMapping("/coding")
    public SheetResponse getCodingSheet() {
        return sheetService.getSheet(SheetType.CODING);
    }

    @GetMapping("/aptitude")
    public SheetResponse getAptitudeSheet() {
        return sheetService.getSheet(SheetType.APTITUDE);
    }
}
