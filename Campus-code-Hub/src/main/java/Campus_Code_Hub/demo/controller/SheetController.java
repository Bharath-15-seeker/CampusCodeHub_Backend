package Campus_Code_Hub.demo.controller;

import Campus_Code_Hub.demo.dto.SheetResponse;
import Campus_Code_Hub.demo.model.SheetType;
import Campus_Code_Hub.demo.service.SheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/sheets")
@RequiredArgsConstructor
public class SheetController {

    private final SheetService sheetService;

    @GetMapping("/coding")
    public SheetResponse getCodingSheet(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            // This will return a 401 Unauthorized instead of a 500 NullPointer
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please log in");
        }
        String email = authentication.getName();
        return sheetService.getSheet(SheetType.CODING, email);
    }

    @GetMapping("/aptitude")
    public SheetResponse getAptitudeSheet(Authentication authentication) {
        String email=authentication.getName();
        return sheetService.getSheet(SheetType.APTITUDE,email);
    }
}
