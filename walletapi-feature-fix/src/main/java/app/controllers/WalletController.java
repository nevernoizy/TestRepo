package app.controllers;

import app.models.RequestModel;
import app.services.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping(value = "/wallets/{id}")
    public ResponseEntity<Integer> read(@PathVariable("id") UUID id) {
        try{
            final Integer result = walletService.getBalance(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/wallet")
    public ResponseEntity<?> read(@Valid @RequestBody RequestModel requestModel){
        try {
            walletService.changeBalance(requestModel.getValletId(), requestModel.getOperationType(), requestModel.getAmount());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (FileNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
