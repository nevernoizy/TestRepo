package app.repositories;

import app.models.WalletModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<WalletModel, UUID> {
}
