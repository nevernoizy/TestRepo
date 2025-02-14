package app.models;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class RequestModel {
    @NotNull
    private UUID valletId;

    @NotNull
    private OperationType operationType;

    @NotNull
    private Integer amount;

    public RequestModel() {
    }

    public UUID getValletId() {
        return valletId;
    }

    public void setValletId(UUID valletId) {
        this.valletId = valletId;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "RequestModel{" +
                "valletId=" + valletId +
                ", operationType='" + operationType + '\'' +
                ", amount=" + amount +
                '}';
    }
}
