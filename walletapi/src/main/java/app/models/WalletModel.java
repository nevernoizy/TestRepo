package app.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "wallets")
public class WalletModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer balance;

    public WalletModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "WalletModel{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}
