package app.services;

import app.models.OperationType;
import app.models.WalletModel;
import app.repositories.WalletRepository;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    // СОЗДАНИЕ КОШЕЛЬКА и сохранение в бд
    public void addWallet(Integer balance) {
        WalletModel walletModel = new WalletModel();
        walletModel.setBalance(balance);
        walletRepository.save(
                walletModel
        );
    }

    public Integer getBalance(UUID id) {
        return walletRepository.findById(id).orElseThrow(() ->
                        new IllegalArgumentException("Wallet not found by id: " + id))
                .getBalance();
    }

    public void changeBalance(UUID id, OperationType operation, Integer amount) throws FileNotFoundException {
        WalletModel walletModel = walletRepository.findById(id).orElseThrow(() ->
                new FileNotFoundException("Wallet not found by id: " + id));
        switch (operation) {
            case DEPOSIT -> walletModel.setBalance(walletModel.getBalance() + amount);
            case WITHDRAW -> {
                if (walletModel.getBalance() < amount) {
                    throw new IllegalArgumentException();
                } else {
                    walletModel.setBalance(walletModel.getBalance() - amount);
                }
            }
        }
        walletRepository.save(walletModel);
    }
}
